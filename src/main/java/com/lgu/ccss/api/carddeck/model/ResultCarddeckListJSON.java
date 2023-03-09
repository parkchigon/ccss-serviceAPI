package com.lgu.ccss.api.carddeck.model;

import java.util.ArrayList;
import java.util.List;

public class ResultCarddeckListJSON {
	private List<CarddeckListData> carddeckList = new ArrayList<CarddeckListData>();

	public List<CarddeckListData> getCarddeckList() {
		return carddeckList;
	}

	public void setCarddeckList(List<CarddeckListData> carddeckList) {
		this.carddeckList = carddeckList;
	}

	@Override
	public String toString() {
		return "ResultCarddeckListJSON [carddeckList=" + carddeckList + "]";
	}
}
