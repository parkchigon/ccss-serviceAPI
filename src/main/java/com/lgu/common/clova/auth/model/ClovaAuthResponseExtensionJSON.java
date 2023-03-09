package com.lgu.common.clova.auth.model;


public class ClovaAuthResponseExtensionJSON {
	private ClovaAuthResponseMetaJSON meta;
	private ClovaAuthResponseResultJSON result;
	public ClovaAuthResponseMetaJSON getMeta() {
		return meta;
	}
	public void setMeta(ClovaAuthResponseMetaJSON meta) {
		this.meta = meta;
	}
	public ClovaAuthResponseResultJSON getResult() {
		return result;
	}
	public void setResult(ClovaAuthResponseResultJSON result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ClovaAuthResponseExtensionJSON [meta=" + meta + ", result=" + result + "]";
	}
	
	
}
