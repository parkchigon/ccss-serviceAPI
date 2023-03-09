package com.lgu.ccss.common.model;

import com.lgu.common.ncas.SubsInfo;

public class MembData {
	private SubsInfo subsInfo;
	private MembVO memb;
	
	public SubsInfo getSubsInfo() {
		return subsInfo;
	}
	public void setSubsInfo(SubsInfo subsInfo) {
		this.subsInfo = subsInfo;
	}
	public MembVO getMemb() {
		return memb;
	}
	public void setMemb(MembVO memb) {
		this.memb = memb;
	}
	
	@Override
	public String toString() {
		return "MembData [subsInfo=" + subsInfo + ", memb=" + memb + "]";
	}
}
