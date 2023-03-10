package com.lgu.common.clova.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ClovaAuthResponseErrorBodyJSON {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String error;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String error_description;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError_description() {
		return error_description;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	
	
	
	
}
