package com.lgu.ccss.common.model.pushgw;


import java.util.HashMap;


public class PushGwMessageIosContentVO {

	private HashMap<String,Object> aps;
	
	//private String cm;
	
	public PushGwMessageIosContentVO(){
		aps = new HashMap<String,Object>();
	}

	public HashMap<String, Object> getAps() {
		return aps;
	}

	public void setAps(HashMap<String, Object> aps) {
		this.aps = aps;
	}

	/*public String getCm() {
		return cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}*/

	
	
	
}
