package com.lgu.common.datagift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;

@SuppressWarnings("unused")
public class XPayClient {
	private static final Logger logger = LoggerFactory.getLogger(XPayClient.class);
	
	public static final int LGD_ERR_NO_HOME_DIR = 10001;
	public static final int LGD_ERR_NO_MALL_CONFIG = 10002;
	public static final int LGD_ERR_NO_LGDACOM_CONFIG = 10003;
	public static final int LGD_ERR_NO_MID = 10004;
	public static final int LGD_ERR_OUT_OF_MEMORY = 10005;
	public static final int LGD_ERR_NO_KEYSTORE_PATH = 10006;
	public static final int LGD_ERR_NO_SECURE_PROTOCOLS = 10007;
	public static final int LGD_ERR_HTTP_URL = 20001;
	public static final int LGD_ERR_RESOLVE_HOST = 20002;
	public static final int LGD_ERR_RESOLVE_PROXY = 20003;
	public static final int LGD_ERR_CONNECT = 20004;
	public static final int LGD_ERR_WRITE = 20005;
	public static final int LGD_ERR_READ = 20006;
	public static final int LGD_ERR_SEND = 20007;
	public static final int LGD_ERR_RECV = 20008;
	public static final int LGD_ERR_TIMEDOUT = 20009;
	public static final int LGD_ERR_SSL = 20101;
	public static final int LGD_ERR_HTTP = 20201;
	public static final int LGD_ERR_JSON_DECODE = 40001;
	private String m_home_dir;
	private String m_keystore_cacerts_dir;
	private String m_default_secure_protocols;
	private String m_MID;
	private CTX m_tx;
	private boolean m_bUsePropertyFile;
	protected boolean m_bTestMode;
	public String m_szResCode;
	public String m_szResMsg;

	public boolean Init(String mode) {
		this.m_bUsePropertyFile = false;
		if (mode.equals("test")) {
			this.m_bTestMode = true;
		} else {
			this.m_bTestMode = false;
		}
		return true;
	}

	public void SetConfig(String name, String value) {
		Config.put(name, value);
		if (name.equals("log_dir")) {
			logger.debug(Config.value("log_dir"));
		}

		if (name.equals("keystore_cacerts_dir")) {
			this.m_keystore_cacerts_dir = Config.value("keystore_cacerts_dir");
		}

		if (name.equals("default_secure_protocols")) {
			this.m_default_secure_protocols = Config.value("default_secure_protocols");
		}
	}

	public boolean Init(String home_dir, String mode) {
		this.m_bUsePropertyFile = true;
		this.m_home_dir = home_dir;
		int res = Config.load(this.m_home_dir);
		if (res != 0) {
			this.m_szResCode = "" + res;
			switch (res) {
				case 10001 :
					this.m_szResMsg = "home_dir [" + this.m_home_dir + " ] does not exist";
					break;
				case 10002 :
					this.m_szResMsg = "config file [" + this.m_home_dir + "/conf/mall.conf] does not exist";
					break;
				case 10003 :
					this.m_szResMsg = "config file [" + this.m_home_dir + "/conf/lgdacom.conf] does not exist";
				case 10004 :
				case 10005 :
				default :
					break;
				case 10006 :
					this.m_szResMsg = "keystore_cacerts_dir [" + this.m_keystore_cacerts_dir + " ] does not exist";
					break;
				case 10007 :
					this.m_szResMsg = "default_secure_protocols [" + this.m_default_secure_protocols + " ] does not exist";
			}
		}

		if (mode.equals("test")) {
			this.m_bTestMode = true;
		} else {
			this.m_bTestMode = false;
		}

		Config.setTestMode(this.m_bTestMode);
		return res == 0;
	}

	public boolean InitFile(String file, String mode) {
		int res = Config.loadfile(file);
		if (res != 0) {
			this.m_szResCode = "" + res;
			switch (res) {
				case 10003 :
					this.m_szResMsg = "config file [" + file + "] does not exist";
			}
		}

		if (mode.equals("test")) {
			this.m_bTestMode = true;
		} else {
			this.m_bTestMode = false;
		}

		return res == 0;
	}

	public boolean Init_TX(String MID) {
		this.m_tx = new CTX();
		this.m_MID = MID;
		if (this.m_tx.Init(this.m_MID)) {
			return true;
		} else {
			this.m_szResCode = "10004";
			this.m_szResMsg = "Key for MID [" + MID + "] does not exist in mall.conf";
			return false;
		}
	}

	public void Set(String name, String value) {
		this.m_tx.Set(name, value);
	}

	public String URL() {
		return this.m_bTestMode ? Config.value("test_url") : Config.value("url");
	}

	public boolean TX() {
		
		boolean bRet = false;
		boolean bTX = true;
		boolean bRollback = false;
		boolean bReporting = false;
		String strRollbackReason = "";
		String strReportStatus = "";
		String strReportMsg = "";
		boolean bRollbackOnError = Config.valueInt("auto_rollback") == 1;
		boolean bReportOnError = Config.valueInt("report_error") == 1;
		boolean bResult = this.m_tx.TX(this.URL());
		this.m_szResCode = this.m_tx.m_szResCode;
		this.m_szResMsg = this.m_tx.m_szResMsg;
		
		if (!bResult) {
			if (bRollbackOnError && this.m_tx.m_bTimeout) {
				bTX = false;
				logger.debug(this.m_tx.m_TX_ID +" : "+ "TX failed: res code = " + this.m_tx.m_szResCode + "; msg = " + this.m_tx.m_szResMsg, 0);
				bRollback = true;
				strRollbackReason = "Timeout";
			}
		} else if (this.m_tx.m_nStatusCode >= 200 && this.m_tx.m_nStatusCode < 300) {
			if (this.m_tx.m_bJason) {
				bRet = true;
				this.m_szResCode = this.m_tx.m_szResCode;
				this.m_szResMsg = this.m_tx.m_szResMsg;
				logger.debug(this.m_tx.m_TX_ID +" : "+ "TX Success Complete.", 4);
			} else if (bRollbackOnError) {
				bRollback = true;
				strRollbackReason = "JSON decode fail";
				logger.debug(this.m_tx.m_TX_ID +" : "+ "HTTP Response = [" + this.m_tx.m_szHttpResponse + "]", 4);
			}
		} else {
			this.m_szResCode = "" + (30000 + this.m_tx.m_nStatusCode);
			this.m_szResMsg = "HTTP response code = " + this.m_tx.m_szStatusCode;
			bReporting = true;
			strReportStatus = "HTTP response %d" + this.m_tx.m_nStatusCode;
			strReportMsg = this.m_tx.m_szHttpResponse;
			if (bRollbackOnError && this.m_tx.m_nStatusCode >= 500) {
				bRollback = true;
				strRollbackReason = "HTTP " + this.m_tx.m_nStatusCode;
			}
		}

		if (bRollbackOnError && bRollback) {
			String tx_id = this.m_tx.m_TX_ID;
			String value = this.m_tx.GetParamValue("LGD_HASHTYPE");
			CTX tx = new CTX();
			tx.Init(this.m_MID);
			tx.Set("LGD_TXNAME", "Rollback");
			tx.Set("LGD_RB_TXID", tx_id);
			tx.Set("LGD_RB_REASON", strRollbackReason);
			if (value != null && value.length() > 0) {
				tx.Set("LGD_HASHTYPE", value);
			}
			tx.TX(this.URL());
		}

		if (bReportOnError && bReporting) {
			logger.debug(this.m_tx.m_TX_ID +" : "+ "try Reporting", 4);
			CTX tx = new CTX();
			tx.Init(this.m_MID);
			tx.Set("LGD_TXNAME", "Report");
			tx.Set("LGD_STATUS", strReportStatus);
			tx.Set("LGD_MSG", strReportMsg);
			tx.TX(Config.value("aux_url"));
		}

		return bRet;
	}

	public int ResponseNameCount() {
		return this.m_tx.m_nResEntryCount;
	}

	public int ResponseCount() {
		return this.m_tx.m_nResArrayCount;
	}

	public String ResponseName(int index) {
		return index >= 0 && index < this.m_tx.m_nResEntryCount ? this.m_tx.m_szResNames[index] : "";
	}

	public String Response(String name, int index) {
		return this.m_tx.Response(name, index);
	}

	public boolean Rollback(String reason) {
		try {
			String tx_id = this.m_tx.m_TX_ID;
			String value = this.m_tx.GetParamValue("LGD_HASHTYPE");
			this.m_tx = new CTX();
			this.m_tx.Init(this.m_MID);
			this.m_tx.Set("LGD_TXNAME", "Rollback");
			this.m_tx.Set("LGD_RB_TXID", tx_id);
			this.m_tx.Set("LGD_RB_REASON", reason);
			if (value != null && value.length() > 0) {
				this.m_tx.Set("LGD_HASHTYPE", value);
			}

			if (!this.m_tx.TX(this.URL())) {
				logger.debug(""+" : "+ "Rollback failed", 1);
				return false;
			} else {
				return true;
			}
		} catch (Exception var4) {
			logger.debug(""+" : "+ "Rollback failed : " + var4.toString(), 1);
			return false;
		}
	}

	public boolean Patch(String filename) {
		try {
			CTX tx = new CTX();
			tx.Init(this.m_MID);
			tx.Set("LGD_TXNAME", "Patch");
			tx.Set("LGD_FILE", filename);
			boolean bResult = tx.TX(Config.value("aux_url"));
			if (bResult && tx.m_szContentType.equals("text/plain")) {
				String name = this.m_home_dir + "\\conf\\" + filename;
				FileOutputStream patchFile = new FileOutputStream(name, false);
				patchFile.write(tx.m_szHttpResponse.getBytes());
				patchFile.close();
			}

			return true;
		} catch (Exception var6) {
			logger.debug("" +" : "+ "Patch failed : " + var6.toString(), 1);
			return false;
		}
	}

	public void Log(String logMsg) {
		logger.debug(this.m_tx.m_TX_ID +" : "+ logMsg, 4);
	}

	public void Log(String logMsg, int logLevel) {
		logger.debug(this.m_tx.m_TX_ID +" : "+ logMsg, logLevel);
	}

	public static void main(String[] args) {
		XPayClient XPayTest = new XPayClient();
		String CST_PLATFORM = "test";
		boolean isInitOK = XPayTest.Init(CST_PLATFORM);
		if (!isInitOK) {
			System.out.println("결제요청을 초기화 하는데 실패하였습니다.");
			System.out.println("LG유플러스에서 제공한 환경파일이 정상적으로 설치 되었는지 확인하시기 바랍니다.");
			System.out.println("mall.conf에는 Mert ID = Mert Key 가 반드시 등록되어 있어야 합니다.");
			System.out.println("문의전화 LG유플러스 1544-7772");
		} else {
			XPayTest.SetConfig("server_id", "01");
			XPayTest.SetConfig("timeout", "60");
			XPayTest.SetConfig("log_level", "4");
			XPayTest.SetConfig("verify_cert", "0");
			XPayTest.SetConfig("verify_host", "0");
			XPayTest.SetConfig("report_error", "1");
			XPayTest.SetConfig("output_UTF8", "0");
			XPayTest.SetConfig("auto_rollback", "1");
			XPayTest.SetConfig("log_dir", "c:\\lgdacom\\log");
			XPayTest.SetConfig("keystore_cacerts_dir", "C:/Program Files/Java/jdk1.7.0_79/jre/lib/security/cacerts");
			XPayTest.SetConfig("tlgdacomxpay", "95160cce09854ef44d2edb2bfb05f9f3");
			XPayTest.SetConfig("lgdacomxpay", "95160cce09854ef44d2edb2bfb05f9f3");
			XPayTest.SetConfig("default_secure_protocols", "2560");
			XPayTest.SetConfig("url", "https://xpayclient.lgdacom.net/xpay/Gateway.do");
			XPayTest.SetConfig("test_url", "https://xpayclient.lgdacom.net:7443/xpay/Gateway.do");
			XPayTest.SetConfig("aux_url", "http://xpayclient.lgdacom.net:7080/xpay/Gateway.do");

			try {
				if (CST_PLATFORM.equals("test")) {
					isInitOK = XPayTest.Init_TX("tlgdacomxpay");
				} else {
					isInitOK = XPayTest.Init_TX("lgdacomxpay");
				}

				if (!isInitOK) {
					System.out.println("결제요청의 Init_TX 초기화 하는데 실패하였습니다.");
					System.out.println("문의전화 LG유플러스 1544-7772");
					return;
				}

				XPayTest.Set("LGD_TXNAME", "Ping");
				XPayTest.Set("LGD_DUMMY", "=+A가나다");
				XPayTest.Set("LGD_RESULTCNT", "1");
			} catch (Exception var6) {
				System.out.println("LG유플러스 제공 API를 사용할 수 없습니다.");
				System.out.println(var6.getMessage());
				return;
			}

			if (XPayTest.TX()) {
				System.out.println("결제요청이 완료되었습니다.");
				System.out.println("TX 결제요청 Response_code = " + XPayTest.m_szResCode);
				System.out.println("TX 결제요청 Response_msg = " + XPayTest.m_szResMsg);
				System.out.println("거래번호 : " + XPayTest.Response("LGD_TID", 0));
				System.out.println("상점아이디 : " + XPayTest.Response("LGD_MID", 0));
				System.out.println("상점주문번호 : " + XPayTest.Response("LGD_OID", 0));
				System.out.println("결제금액 : " + XPayTest.Response("LGD_AMOUNT", 0));
				System.out.println("결과코드 : " + XPayTest.Response("LGD_RESPCODE", 0));
				System.out.println("결과메세지 : " + XPayTest.Response("LGD_RESPMSG", 0));

				for (int i = 0; i < XPayTest.ResponseNameCount(); ++i) {
					System.out.println(XPayTest.ResponseName(i) + " = ");

					for (int j = 0; j < XPayTest.ResponseCount(); ++j) {
						System.out.println("\t"+ XPayTest.Response(XPayTest.ResponseName(i), j));
					}
				}

				if ("0000".equals(XPayTest.m_szResCode)) {
					System.out.println("최종결제요청 결과 성공 DB처리하시기 바랍니다.");
					boolean isDBOK = true;
					if (!isDBOK) {
						XPayTest.Rollback("상점 DB처리 실패로 인하여 Rollback 처리 [TID:" + XPayTest.Response("LGD_TID", 0) + ",MID:"
								+ XPayTest.Response("LGD_MID", 0) + ",OID:" + XPayTest.Response("LGD_OID", 0) + "]");
						System.out.println("TX Rollback Response_code = " + XPayTest.Response("LGD_RESPCODE", 0));
						System.out.println("TX Rollback Response_msg = " + XPayTest.Response("LGD_RESPMSG", 0));
						if ("0000".equals(XPayTest.m_szResCode)) {
							System.out.println("자동취소가 정상적으로 완료 되었습니다.");
						} else {
							System.out.println("자동취소가 정상적으로 처리되지 않았습니다.");
						}
					}
				} else {
					System.out.println("ResCode 는 0000이지만 최종결제요청 결과 실패 DB처리하시기 바랍니다.");
				}
			} else {
				System.out.println("결제요청이 실패하였습니다.");
				System.out.println("TX 결제요청 Response_code = " + XPayTest.m_szResCode);
				System.out.println("TX 결제요청 Response_msg = " + XPayTest.m_szResMsg);
				System.out.println("TX 최종결제요청 결과 실패 DB처리하시기 바랍니다.");
			}
		}
	}
}