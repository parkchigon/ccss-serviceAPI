package com.lgu.common.ai.coif.model;

public class COIFRequestBodyJSON {
	private String skill;
	private String intent;
	private WeatherRequestBodySlotJSON slot;
	
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public WeatherRequestBodySlotJSON getSlot() {
		return slot;
	}
	public void setSlot(WeatherRequestBodySlotJSON slot) {
		this.slot = slot;
	}
	
	@Override
	public String toString() {
		return "COIFRequestBodyJSON [skill=" + skill + ", intent=" + intent + ", slot=" + slot + "]";
	}
}
