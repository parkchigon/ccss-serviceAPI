package com.lgu.common.esb.exception;

@SuppressWarnings("serial")
public class ESBException extends Exception {

	String errorCode;

	public ESBException(String errorCode, String message) {
		super(message);

		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
