package com.lgu.common.clova.auth.model;

public class ClovaAuthResponseDiscoveryJSON {
	private ClovaAuthResponseMetaJSON meta;
	private ClovaAuthResponseDiscoveryResultJSON result;
	
	public ClovaAuthResponseMetaJSON getMeta() {
		return meta;
	}
	public void setMeta(ClovaAuthResponseMetaJSON meta) {
		this.meta = meta;
	}
	public ClovaAuthResponseDiscoveryResultJSON getResult() {
		return result;
	}
	public void setResult(ClovaAuthResponseDiscoveryResultJSON result) {
		this.result = result;
	}
	
	
}
