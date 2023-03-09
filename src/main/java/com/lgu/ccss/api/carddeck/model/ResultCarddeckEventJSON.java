package com.lgu.ccss.api.carddeck.model;

import java.util.ArrayList;
import java.util.List;

public class ResultCarddeckEventJSON {
	private List<CarddeckEventData> carddeckEvent = new ArrayList<CarddeckEventData>();

	public List<CarddeckEventData> getCarddeckEvent() {
		return carddeckEvent;
	}

	public void setCarddeckEvent(List<CarddeckEventData> carddeckEvent) {
		this.carddeckEvent = carddeckEvent;
	}

	@Override
	public String toString() {
		return "ResultCarddeckEventJSON [carddeckEvent=" + carddeckEvent + "]";
	}
}
