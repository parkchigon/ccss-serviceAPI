package com.lgu.common.esb.vo;

public class PauseResultVO {

	/** 성공여부 */
	String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "result", result));

		return stringBuffer.toString();
	}
}
