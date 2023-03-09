package com.lgu.common.ai.coif.model;

public class COIFResponseBodyJSON {
	private String skillName;
	private String intentName;
	private Object slotInfo;
	private String directiveId;
	private Object deviceControl;
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public Object getSlotInfo() {
		return slotInfo;
	}
	public void setSlotInfo(Object slotInfo) {
		this.slotInfo = slotInfo;
	}
	public String getDirectiveId() {
		return directiveId;
	}
	public void setDirectiveId(String directiveId) {
		this.directiveId = directiveId;
	}
	public Object getDeviceControl() {
		return deviceControl;
	}
	public void setDeviceControl(Object deviceControl) {
		this.deviceControl = deviceControl;
	}
	
	@Override
	public String toString() {
		return "COIFResponseBodyJSON [skillName=" + skillName + ", intentName=" + intentName + ", slotInfo=" + slotInfo
				+ ", directiveId=" + directiveId + ", deviceControl=" + deviceControl + "]";
	}
}
