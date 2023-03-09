package com.lgu.common.esb.vo;

public class PlanResultVO {

	/** 리턴값 */
	String results;

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "results", results));

		return stringBuffer.toString();
	}
}
