package com.lgu.ccss.api.notice.model;

public class NoticeListData {
	private String index;
	private String importance;
	private String title;
	private String category;
	private String content;
	private String regDate;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "NoticeListData [index=" + index + ", importance=" + importance + ", title=" + title + ", category="
				+ category + ", content=" + content + ", regDate=" + regDate + "]";
	}
}
