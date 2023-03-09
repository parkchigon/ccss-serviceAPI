package com.lgu.common.pushgw.model;

import java.util.ArrayList;

public class ResPushSubInfoJSON {
	
	private String RETURN_CODE;
	
	private ArrayList<PushStatusJSON> SERVICEKEY_INFO;
	
	private String RESPONSE_TIME;
	
	private PushErrorJSON ERROR;

	public String getRETURN_CODE() {
		return RETURN_CODE;
	}

	public void setRETURN_CODE(String rETURN_CODE) {
		RETURN_CODE = rETURN_CODE;
	}

	public ArrayList<PushStatusJSON> getSERVICEKEY_INFO() {
		return SERVICEKEY_INFO;
	}

	public void setSERVICEKEY_INFO(ArrayList<PushStatusJSON> sERVICEKEY_INFO) {
		SERVICEKEY_INFO = sERVICEKEY_INFO;
	}

	public String getRESPONSE_TIME() {
		return RESPONSE_TIME;
	}

	public void setRESPONSE_TIME(String rESPONSE_TIME) {
		RESPONSE_TIME = rESPONSE_TIME;
	}

	public PushErrorJSON getERROR() {
		return ERROR;
	}

	public void setERROR(PushErrorJSON eRROR) {
		ERROR = eRROR;
	}
	
	
}
