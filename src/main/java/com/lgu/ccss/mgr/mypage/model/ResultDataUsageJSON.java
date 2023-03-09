package com.lgu.ccss.mgr.mypage.model;

public class ResultDataUsageJSON {
	
	private String alloValue = null;
	private String useValue = null;
	
	public String getAlloValue() {
		return alloValue;
	}
	public void setAlloValue(String alloValue) {
		this.alloValue = alloValue;
	}
	public String getUseValue() {
		return useValue;
	}
	public void setUseValue(String useValue) {
		this.useValue = useValue;
	}
	
	@Override
	public String toString() {
		return "ResultDataUsageJSON [alloValue=" + alloValue
				+ ", useValue=" + useValue + "]";
	}

}
