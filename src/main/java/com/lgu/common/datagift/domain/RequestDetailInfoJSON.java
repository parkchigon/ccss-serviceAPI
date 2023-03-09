package com.lgu.common.datagift.domain;

public class RequestDetailInfoJSON {
	private String svcCategory;
	private String svcItem;
	
	public String getSvcCategory() {
		return svcCategory;
	}
	public void setSvcCategory(String svcCategory) {
		this.svcCategory = svcCategory;
	}
	public String getSvcItem() {
		return svcItem;
	}
	public void setSvcItem(String svcItem) {
		this.svcItem = svcItem;
	}
	@Override
	public String toString() {
		return "RequestDetailInfoJSON [svcCategory=" + svcCategory + ", svcItem=" + svcItem + "]";
	}
	
	
}
