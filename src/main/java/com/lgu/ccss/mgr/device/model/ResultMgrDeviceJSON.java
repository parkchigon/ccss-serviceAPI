package com.lgu.ccss.mgr.device.model;

import java.util.List;

public class ResultMgrDeviceJSON {
	private List<MgrDeviceInfo> device;

	public List<MgrDeviceInfo> getDevice() {
		return device;
	}

	public void setDevice(List<MgrDeviceInfo> device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "ResultMgrDeviceJSON [device=" + device + "]";
	}
}
