package com.lgu.ccss.api.ev.model;

public class PeriodChargingData {
	
	private String evPeriodCharging;
	private String evPeriodDischarging;
	
	public String getEvPeriodCharging() {
		return evPeriodCharging;
	}
	public void setEvPeriodCharging(String evPeriodCharging) {
		this.evPeriodCharging = evPeriodCharging;
	}
	public String getEvPeriodDischarging() {
		return evPeriodDischarging;
	}
	public void setEvPeriodDischarging(String evPeriodDischarging) {
		this.evPeriodDischarging = evPeriodDischarging;
	}
	
	@Override
	public String toString() {
		return "PeriodChargingData [evPeriodCharging=" + evPeriodCharging + ", evPeriodDischarging="
				+ evPeriodDischarging + "]";
	}
}
