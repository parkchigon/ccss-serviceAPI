package com.lgu.common.pushgw.model;

public class ResPushJSON {
	
	private String RETURN_CODE;
	
	private String PUSH_ID;
	
	private String RESPONSE_TIME;
	
	private PushErrorJSON ERROR;

	public String getRETURN_CODE() {
		return RETURN_CODE;
	}

	public String getPUSH_ID() {
		return PUSH_ID;
	}

	public void setPUSH_ID(String pUSH_ID) {
		PUSH_ID = pUSH_ID;
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

	public void setRETURN_CODE(String rETURN_CODE) {
		RETURN_CODE = rETURN_CODE;
	}

	@Override
	public String toString() {
		return "ResPushJSON [RETURN_CODE=" + RETURN_CODE + ", PUSH_ID="
				+ PUSH_ID + ", RESPONSE_TIME=" + RESPONSE_TIME + ", ERROR="
				+ ERROR + "]";
	}
	
}
