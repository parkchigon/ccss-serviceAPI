package com.lgu.ccss.api.service.model;

public class PushTopicData {
	private PushTopicSub sub;
	private PushTopicPub pub;

	public PushTopicData(String subMessage, String subNotice, String pubStatus, String pubReport) {
		sub = new PushTopicSub();
		pub = new PushTopicPub();

		sub.setMessage(subMessage);
		sub.setNotice(subNotice);
		pub.setStatus(pubStatus);
		pub.setReport(pubReport);
	}

	public PushTopicSub getSub() {
		return sub;
	}

	public void setSub(PushTopicSub sub) {
		this.sub = sub;
	}

	public PushTopicPub getPub() {
		return pub;
	}

	public void setPub(PushTopicPub pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "PushTopicData [sub=" + sub + ", pub=" + pub + "]";
	}
}

class PushTopicSub {
	private String message;
	private String notice;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	@Override
	public String toString() {
		return "PushTopicSub [message=" + message + ", notice=" + notice + "]";
	}
}

class PushTopicPub {
	private String status;
	private String report;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	@Override
	public String toString() {
		return "PushTopicPub [status=" + status + ", report=" + report + "]";
	}
}
