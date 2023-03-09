package com.lgu.common.esb.vo;

public class AmountDataVO {

	/** 제공량 */
	String alloValue;
	/** 사용량 */
	String useValue;

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
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(String.format("[%32s] %s\n", "alloValue", alloValue));
		stringBuffer.append(String.format("[%32s] %s\n", "useValue", useValue));

		return stringBuffer.toString();
	}
}
