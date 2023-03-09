package com.lgu.common.esb.model;

public class DsGetEntrMonthAlowsmry {
	String svcTypeCd;
	String svcUnitCd;
	String allovalue;
	String useValue;
	String lastUseDttm;
	public String getSvcTypeCd() {
		return svcTypeCd;
	}
	public void setSvcTypeCd(String svcTypeCd) {
		this.svcTypeCd = svcTypeCd;
	}
	public String getSvcUnitCd() {
		return svcUnitCd;
	}
	public void setSvcUnitCd(String svcUnitCd) {
		this.svcUnitCd = svcUnitCd;
	}
	public String getAllovalue() {
		return allovalue;
	}
	public void setAllovalue(String allovalue) {
		this.allovalue = allovalue;
	}
	public String getUseValue() {
		return useValue;
	}
	public void setUseValue(String useValue) {
		this.useValue = useValue;
	}
	public String getLastUseDttm() {
		return lastUseDttm;
	}
	public void setLastUseDttm(String lastUseDttm) {
		this.lastUseDttm = lastUseDttm;
	}
	@Override
	public String toString() {
		return "DsGetEntrMonthAlowsmry [svcTypeCd=" + svcTypeCd + ", svcUnitCd=" + svcUnitCd + ", allovalue="
				+ allovalue + ", useValue=" + useValue + ", lastUseDttm=" + lastUseDttm + "]";
	}
	
	
}
