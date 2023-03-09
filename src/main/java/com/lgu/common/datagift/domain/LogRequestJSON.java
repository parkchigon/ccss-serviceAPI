package com.lgu.common.datagift.domain;

import java.util.List;


public class LogRequestJSON {
	private List<LogDataJSON> log;

	public List<LogDataJSON> getLog() {
		return log;
	}

	public void setLog(List<LogDataJSON> log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "LogRequestJSON [log=" + log + "]";
	}
	
	
}
