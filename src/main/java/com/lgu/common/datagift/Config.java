package com.lgu.common.datagift;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Config {

		public static final int LGD_ERR_NO_HOME_DIR = 10001;
		public static final int LGD_ERR_NO_MALL_CONFIG = 10002;
		public static final int LGD_ERR_NO_LGDACOM_CONFIG = 10003;
		private static int m_count = 0;
		private static HashMap<String, String> m_hm = new HashMap<String, String>();
		private static boolean m_testMode = false;

		public static String getTestMode() {
			return m_testMode ? "test" : "service";
		}

		public static void setTestMode(boolean mode) {
			m_testMode = mode;
		}

		public static int count() {
			return ++m_count;
		}

		public static void parseConfig(String line) {
			if (line.length() > 0 && line.charAt(0) != ';') {
				int pos = line.indexOf(61);
				if (pos > 0) {
					String name = line.substring(0, pos).trim();
					String val = line.substring(pos + 1, line.length()).trim();
					m_hm.put(name, val);
				}
			}
		}

		public static int load(String home_dir) {
			FileInputStream fs;
			BufferedReader in;
			String line;
			try {
				fs = new FileInputStream(home_dir + "/conf/lgdacom.conf");
				in = new BufferedReader(new InputStreamReader(fs));
				line = in.readLine();

				while (true) {
					if (line == null) {
						in.close();
						break;
					}

					parseConfig(line);
					line = in.readLine();
				}
			} catch (Exception var5) {
				var5.printStackTrace();
				return 10003;
			}

			try {
				fs = new FileInputStream(home_dir + "/conf/mall.conf");
				in = new BufferedReader(new InputStreamReader(fs));

				for (line = in.readLine(); line != null; line = in.readLine()) {
					parseConfig(line);
				}

				in.close();
				return 0;
			} catch (Exception var4) {
				var4.printStackTrace();
				return 10002;
			}
		}

		public static int loadfile(String file) {
			try {
				FileInputStream fs = new FileInputStream(file);
				BufferedReader in = new BufferedReader(new InputStreamReader(fs));

				for (String line = in.readLine(); line != null; line = in
						.readLine()) {
					parseConfig(line);
				}

				in.close();
				return 0;
			} catch (Exception var4) {
				var4.printStackTrace();
				return 10003;
			}
		}

		public static String value(String name) {
			String val = (String) m_hm.get(name);
			return val == null ? "" : val;
		}

		public static int valueInt(String name) {
			String val = value(name);
			int num = 0;

			try {
				num = Integer.parseInt(val);
			} catch (Exception var4) {
				;
			}

			if (name.equals("timeout")) {
				return num > 0 ? num : 20;
			} else if (name.equals("log_level")) {
				return num >= 0 && num <= 4 ? num : 1;
			} else if (name.equals("verify_cert")) {
				return num >= 0 && num <= 1 ? num : 1;
			} else if (name.equals("verify_host")) {
				return num >= 0 && num <= 1 ? num : 1;
			} else if (name.equals("report_error")) {
				return num >= 0 && num <= 1 ? num : 1;
			} else if (name.equals("auto_rollback")) {
				return num >= 0 && num <= 1 ? num : 1;
			} else if (name.equals("output_UTF8")) {
				return num >= 0 && num <= 1 ? num : 0;
			} else {
				return num;
			}
		}

		public static void put(String name, String val) {
			m_hm.put(name, val);
		}

		public static void main(String[] args) {
			load("c:/lgdacom");
			put("navercheckout", "1282081084201hd01920102e910e901");
			System.out.println(m_hm);
			Collection<String> col = m_hm.values();
			Iterator<String> iter = col.iterator();

			while (iter.hasNext()) {
				String val = (String) iter.next();
				System.out.println(val);
			}

		}
}
