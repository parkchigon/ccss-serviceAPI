package com.lgu.ccss.api.mgrapp.model;

import java.util.List;

public class ResultMgrUserListJSON {
	private List<ResultMgrUserJSON> userList;

	public List<ResultMgrUserJSON> getUserList() {
		return userList;
	}

	public void setUserList(List<ResultMgrUserJSON> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "ResultMgrUserListJSON [userList=" + userList + "]";
	}
}
