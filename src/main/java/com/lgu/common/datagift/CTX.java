package com.lgu.common.datagift;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketTimeoutException;
import java.rmi.dgc.VMID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CTX {
		private static final Logger logger = LoggerFactory.getLogger(CTX.class);
		
		public static final String LGD_USER_AGENT = "XPayClient (1.1.5.9/Java)";
		public static final int LGD_ERR_NO_HOME_DIR = 10001;
		public static final int LGD_ERR_NO_MALL_CONFIG = 10002;
		public static final int LGD_ERR_NO_LGDACOM_CONFIG = 10003;
		public static final int LGD_ERR_NO_MID = 10004;
		public static final int LGD_ERR_OUT_OF_MEMORY = 10005;
		public static final int LGD_ERR_NO_KEYSTORE_PATH = 10006;
		public static final int LGD_ERR_HTTP_URL = 20001;
		public static final int LGD_ERR_RESOLVE_HOST = 20002;
		public static final int LGD_ERR_RESOLVE_PROXY = 20003;
		public static final int LGD_ERR_CONNECT = 20004;
		public static final int LGD_ERR_WRITE = 20005;
		public static final int LGD_ERR_READ = 20006;
		public static final int LGD_ERR_SEND = 20007;
		public static final int LGD_ERR_RECV = 20008;
		public static final int LGD_ERR_TIMEDOUT = 20009;
		public static final int LGD_ERR_IO = 20010;
		public static final int LGD_ERR_INTERNAL = 20011;
		public static final int LGD_ERR_SSL = 20101;
		public static final int LGD_ERR_HTTP = 20201;
		public static final int LGD_ERR_JSON_DECODE = 40001;
		private ArrayList<NameValuePair> m_list = new ArrayList<NameValuePair>();
		public String m_TX_ID;
		public String m_MID;
		public String m_Auth_Code;
		public int m_nResEntryCount = 0;
		public int m_nResArrayCount = 0;
		public String[] m_szResNames;
		public String[][] m_szResVals;
		public boolean m_bTimeout = false;
		public boolean m_bJason = false;
		public String m_szHttpResponse;
		public String m_szContentType;
		public String m_szStatusCode;
		public int m_nStatusCode;
		public String m_szResCode = "";
		public String m_szResMsg = "";

		public void Set(String name, String value) {
			NameValuePair pair = new NameValuePair();
			if (value == null) {
				value = "";
			}

			pair.setName(name);
			pair.setValue(value);
			this.m_list.add(pair);
		}

		public String GetParamValue(String name) {
			
			String value = "";
			if (name == null) {
				return "";
			} else {
				int listCnt = this.m_list.size();
				if (listCnt <= 0) {
					listCnt = 0;
				}

				String tempbuf = "";

				for (int num = 0; num < listCnt; ++num) {
					new NameValuePair();
					NameValuePair pair = (NameValuePair) this.m_list.get(num);
					tempbuf = pair.getName();
					if (tempbuf != null && tempbuf.equalsIgnoreCase(name)) {
						value = pair.getValue();
						break;
					}
				}

				logger.debug(this.m_TX_ID + " GetParamValue => Name = " + name + ",Value=" + value, 4);
				return value;
			}
		}

		public static synchronized String GetUnique() {
			VMID Vmid = new VMID();
			String str = Vmid.toString();
			return str;
		}

		public static String HexEncode(byte[] in, boolean upperCase) {
			if (in == null) {
				return null;
			} else {
				StringBuffer sb = new StringBuffer();
				int size = in.length;

				for (int i = 0; i < size; ++i) {
					String hexString = Integer.toHexString((new Byte(in[i])).intValue());
					int strLen = hexString.length();
					if (strLen > 2) {
						hexString = hexString.substring(strLen - 2, strLen);
					}

					if (strLen < 2) {
						hexString = "0" + hexString;
					}

					sb.append(hexString);
				}

				if (upperCase) {
					return sb.toString().toUpperCase();
				} else {
					return sb.toString().toLowerCase();
				}
			}
		}

		public String GenTX_ID(String MID) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String header = MID + "-" + Config.value("server_id") + formatter.format(new Date());
			String TX_ID = header;

			try {
				MessageDigest md = MessageDigest.getInstance("SHA-1");
				String plain = header + GetUnique();
				md.update(plain.getBytes());
				byte[] raw = md.digest();
				TX_ID = TX_ID + HexEncode(raw, false);
			} catch (NoSuchAlgorithmException var8) {
				var8.printStackTrace();
			}

			return TX_ID;
		}

		public String GenAuth_Code(String TX_ID, String MID) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-1");
				md.update(TX_ID.getBytes());
				md.update(Config.value(MID).getBytes());
				byte[] raw = md.digest();
				String auth_code = HexEncode(raw, false);
				return auth_code;
			} catch (NoSuchAlgorithmException var6) {
				var6.printStackTrace();
				return "";
			}
		}

		public boolean Init(String MID) {
			if (Config.value(MID).equals("")) {
				logger.debug(""+ "Key for MID [" + MID + "] does not exist in mall.conf"+ 0);
				return false;
			} else {
				this.m_MID = MID;
				this.m_TX_ID = this.GenTX_ID(this.m_MID);
				this.m_Auth_Code = this.GenAuth_Code(this.m_TX_ID, this.m_MID);
				this.Set("LGD_TXID", this.m_TX_ID);
				this.Set("LGD_AUTHCODE", this.m_Auth_Code);
				this.Set("LGD_MID", this.m_MID);
				return true;
			}
		}

		public static String getPrintStacTraceString(Exception e) {
			
			String returnValue = "";
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream printStream = new PrintStream(out);
			e.printStackTrace(printStream);
			returnValue = out.toString();
			return returnValue;
		}

		@SuppressWarnings("unused")
		public boolean RequestHTTP(String url) {
			boolean bRet = false;
			boolean verifyCertificate = true;
			boolean verifyHostname = true;
			if (Config.valueInt("verify_cert") == 0) {
				verifyCertificate = false;
			}

			if (Config.valueInt("verify_host") == 0) {
				verifyHostname = false;
			}

			HttpClient client = new HttpClient();
			int timeout_ms = Config.valueInt("timeout") * 1000;
			client.getHttpConnectionManager().getParams().setSoTimeout(timeout_ms);
			client.getHttpConnectionManager().getParams() .setConnectionTimeout(timeout_ms);
			if (!Config.value("proxy_host").equals("")) {
				logger.debug(this.m_TX_ID + "Proxy Server : " + Config.value("proxy_host") + ":" + Config.valueInt("proxy_port")+ 4);
				client.getHostConfiguration().setProxy(Config.value("proxy_host"), Config.valueInt("proxy_port"));
			}

			PostMethod post = new PostMethod(url);
			logger.debug("post.getURI : "+ post.getParameter(url));

			for (int i = 0; i < this.m_list.size(); ++i) {
				NameValuePair nv = (NameValuePair) this.m_list.get(i);
				post.addParameter(nv);
				
				if (Config.value("log_except").equals("")) {
					logger.debug(this.m_TX_ID+ nv.getName() + " = " + nv.getValue(), 4);
				} else if (Config.value("log_except").indexOf(nv.getName()) >= 0) {
					logger.debug(this.m_TX_ID + nv.getName() + " = **********", 4);
				} else {
					logger.debug(this.m_TX_ID + nv.getName() + " = " + nv.getValue(), 4);
				}
			}

			post.setRequestHeader("User-Agent", "XPayClient (1.1.5.9/Java)");
			post.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=EUC-KR");

			try {
				this.m_nStatusCode = client.executeMethod(post);
				this.m_szStatusCode = "" + this.m_nStatusCode;
				logger.debug(this.m_TX_ID + "HTTP Status Code = " + this.m_szStatusCode, 4);
				Header header = post.getResponseHeader("Content-type");
				String ct = header.getValue();
				int pos = ct.indexOf(59);
				if (pos > 0) {
					this.m_szContentType = ct.substring(0, pos);
				} else {
					this.m_szContentType = ct;
				}

				logger.debug(this.m_TX_ID + " : HTTP Content-Type = " + this.m_szContentType, 4);
				byte[] responseBody = post.getResponseBody();
				this.m_szHttpResponse = new String(responseBody, "utf8");
				if (Config.value("log_except").equals("")) {
					logger.debug(this.m_TX_ID + " : HTTP Response = " + this.m_szHttpResponse, 4);
				}

				post.releaseConnection();
				bRet = true;
			} catch (ConnectTimeoutException var13) {
				this.m_szResCode = "20004";
				this.m_szResMsg = "Could not connect error;" + var13.toString();
				logger.debug(this.m_TX_ID +" : "+ getPrintStacTraceString(var13), 4);
				var13.printStackTrace();
			} catch (SocketTimeoutException var14) {
				this.m_bTimeout = true;
				this.m_szResCode = "20009";
				this.m_szResMsg = "Timeout error; " + var14.toString();
				logger.debug(this.m_TX_ID +" : "+ getPrintStacTraceString(var14), 4);
				var14.printStackTrace();
			} catch (URIException var15) {
				this.m_szResCode = "20001";
				this.m_szResMsg = "URL error; " + var15.toString();
				logger.debug(this.m_TX_ID +" : "+ getPrintStacTraceString(var15), 4);
				var15.printStackTrace();
			} catch (HttpException var16) {
				this.m_szResCode = "20201";
				this.m_szResMsg = "HTTP error; " + var16.toString();
				logger.debug(this.m_TX_ID +" : "+ getPrintStacTraceString(var16), 4);
				var16.printStackTrace();
			} catch (IOException var17) {
				logger.error("Proxy Master Server die.");
				client.getHostConfiguration().setProxy(Config.value("proxy_sub_host"), Config.valueInt("proxy_port"));
				try {
					this.m_nStatusCode = client.executeMethod(post);
					this.m_szStatusCode = "" + this.m_nStatusCode;
					
					logger.debug(this.m_TX_ID + "HTTP Status Code = " + this.m_szStatusCode, 4);
					Header header = post.getResponseHeader("Content-type");
					String ct = header.getValue();
					int pos = ct.indexOf(59);
					if (pos > 0) {
						this.m_szContentType = ct.substring(0, pos);
					} else {
						this.m_szContentType = ct;
					}

					logger.debug(this.m_TX_ID + " : HTTP Content-Type = " + this.m_szContentType, 4);
					byte[] responseBody = post.getResponseBody();
					this.m_szHttpResponse = new String(responseBody, "utf8");
					if (Config.value("log_except").equals("")) {
						logger.debug(this.m_TX_ID + " : HTTP Response = " + this.m_szHttpResponse, 4);
					}

					post.releaseConnection();
					bRet = true;
				} catch (HttpException var20) {
					this.m_szResCode = "20221";
					this.m_szResMsg = "HTTP error; " + var20.toString();
					logger.debug(this.m_TX_ID +" : "+ getPrintStacTraceString(var20), 4);
					var20.printStackTrace();
				} catch (IOException var19) {
					this.m_szResCode = "20321";
					this.m_szResMsg = "IO error; " + var17.toString();
					logger.debug(this.m_TX_ID +" : "+ getPrintStacTraceString(var17), 4);
					var19.printStackTrace();
				}
				var17.printStackTrace();
			} catch (Exception var18) {
				this.m_szResCode = "20011";
				this.m_szResMsg = "Internal error; " + var18.toString();
				logger.debug(this.m_TX_ID +" : "+ getPrintStacTraceString(var18), 4);
				var18.printStackTrace();
			}

			return bRet;
		}

		@SuppressWarnings("unchecked")
		public void JsonArray(JSONArray json_array) {
			
			this.m_nResArrayCount = json_array.size();
			JSONObject obj = (JSONObject) json_array.get(0);
			this.m_nResEntryCount = obj.size();
			@SuppressWarnings("rawtypes")
			Set keys = obj.keySet();
			this.m_szResNames = new String[this.m_nResEntryCount];
			keys.toArray(this.m_szResNames);
			this.m_szResVals = new String[this.m_nResArrayCount][this.m_nResEntryCount];

			for (int i = 0; i < this.m_nResArrayCount; ++i) {
				obj = (JSONObject) json_array.get(i);
				logger.debug(i + " : CTX JsonArray : "+ obj);
				for (int j = 0; j < this.m_nResEntryCount; ++j) {
					this.m_szResVals[i][j] = (String) obj.get(this.m_szResNames[j]);
				}
			}

		}

		public boolean TX(String url) {
			
			boolean bHttp = this.RequestHTTP(url);
			if (!bHttp) {
				logger.debug(this.m_TX_ID +" : "+ "TX failed: res code = " + this.m_szResCode + "; msg = " + this.m_szResMsg, 0);
				return false;
			} else if (!this.m_szContentType.equals("application/json")) {
				this.m_bJason = false;
				return bHttp;
			} else {
				JSONObject json_obj = (JSONObject) JSONValue.parse(this.m_szHttpResponse);
				if (json_obj == null) {
					this.m_bJason = false;
					this.m_szResCode = "40001";
					this.m_szResMsg = "JSON Decode Failed";
				} else {
					this.m_bJason = true;
					this.m_szResCode = (String) json_obj.get("LGD_RESPCODE");
					this.m_szResMsg = (String) json_obj.get("LGD_RESPMSG");
					JSONArray json_array = (JSONArray) json_obj.get("LGD_RESPONSE");
					if (json_array != null && json_array.size() > 0) {
						this.JsonArray(json_array);
					}

					for (int i = 0; i < this.m_nResEntryCount; ++i) {
						logger.debug(this.m_TX_ID +" : "+ this.ResponseName(i) + " = ", 4);
						int j;
						if (Config.value("log_except").equals("")) {
							for (j = 0; j < this.m_nResArrayCount; ++j) {
								logger.debug(this.m_TX_ID +" : "+ "\t" + this.Response(this.ResponseName(i), j), 4);
							}
						} else {
							for (j = 0; j < this.m_nResArrayCount; ++j) {
								if (Config.value("log_except").indexOf(this.ResponseName(i)) >= 0) {
									logger.debug(this.m_TX_ID +" : "+ "\t**********", 4);
								} else {
									logger.debug(this.m_TX_ID +" : "+ "\t" + this.Response(this.ResponseName(i), j), 4);
								}
							}
						}
					}
				}
				return bHttp;
			}
		}

		public int GetNameIndex(String name) {
			try {
				for (int i = 0; i < this.m_nResEntryCount; ++i) {
					if (name.equals(this.m_szResNames[i])) {
						return i;
					}
				}
			} catch (Exception var3) {
				;
			}

			return -1;
		}

		public String ResponseName(int index) {
			return this.m_szResNames[index];
		}

		public String Response(String name, int index) {
			int n = this.GetNameIndex(name);
			return n >= 0 ? this.m_szResVals[index][n] : "";
		}

		public static void main(String[] args) {
			String testMode = "service";
			Config.load("C:/lgdacom");
			CTX tx = new CTX();
			if (testMode.equalsIgnoreCase("service")) {
				tx.Init("dacomst7");
				System.out.println("mid=dacomst7");
			} else {
				tx.Init("tdacomst7");
				System.out.println("mid=tdacomst7");
			}

			tx.Set("LGD_TXNAME", "Ping");
			tx.Set("LGD_ERRORTYPE", (String) null);
			tx.Set("LGD_TEST", "TEST1");
			tx.Set("LGD_TEST", "TEST2");
			System.out.println("Status code = " + tx.m_nStatusCode);
			System.out.println("Content Type = " + tx.m_szContentType);
			System.out.println("Response = " + tx.m_szHttpResponse);
			System.out.println("resCode = " + tx.m_szResCode);
			System.out.println("resMsg = " + tx.m_szResMsg);

			int i;
			int j;
			for (i = 0; i < tx.m_nResEntryCount; ++i) {
				System.out.println(tx.ResponseName(i) + " = ");

				for (j = 0; j < tx.m_nResArrayCount; ++j) {
					System.out.println("\t" + tx.Response(tx.ResponseName(i), j));
				}
			}

			if (testMode.equalsIgnoreCase("service")) {
				tx.TX(Config.value("url"));
				System.out.println("url=" + Config.value("url"));
			} else {
				tx.TX(Config.value("test_url"));
				System.out.println("test url=" + Config.value("test_url"));
			}

			System.out.println("Status code = " + tx.m_nStatusCode);
			System.out.println("Content Type = " + tx.m_szContentType);
			System.out.println("Response = " + tx.m_szHttpResponse);
			System.out.println("resCode = " + tx.m_szResCode);
			System.out.println("resMsg = " + tx.m_szResMsg);

			for (i = 0; i < tx.m_nResEntryCount; ++i) {
				System.out.println(tx.ResponseName(i) + " = ");

				for (j = 0; j < tx.m_nResArrayCount; ++j) {
					System.out.println("\t" + tx.Response(tx.ResponseName(i), j));
				}
			}
		}
	}