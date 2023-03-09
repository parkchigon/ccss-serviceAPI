package com.lgu.common.esb.model;

public class DsGetEntrSvcOver {
	String svcTypeCd;
	String svcUnitCd;
	String alloValue;
	String useValue;
	String lastUseDttm;
	String entrId;
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
	public String getLastUseDttm() {
		return lastUseDttm;
	}
	public void setLastUseDttm(String lastUseDttm) {
		this.lastUseDttm = lastUseDttm;
	}
	public String getEntrId() {
		return entrId;
	}
	public void setEntrId(String entrId) {
		this.entrId = entrId;
	}
	@Override
	public String toString() {
		return "DsGetEntrSvcOver [svcTypeCd=" + svcTypeCd + ", svcUnitCd=" + svcUnitCd + ", alloValue=" + alloValue
				+ ", useValue=" + useValue + ", lastUseDttm=" + lastUseDttm + ", entrId=" + entrId + "]";
	}
	
	
}
