package com.lgu.ccss.api.voiceguide.model;

public class VoiceGuideListData {
	private String domain;
	private String command;
	private String priority;
	private String level;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "VoiceGuideListData [domain=" + domain + ", command=" + command + ", priority=" + priority + ", level="
				+ level + "]";
	}
}
