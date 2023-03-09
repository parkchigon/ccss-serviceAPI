package com.lgu.ccss.api.carddeck.model;

public class ResultCarddeckNoticeJSON {
	private CarddeckNoticeData carddeckNotice;

	public CarddeckNoticeData getCarddeckNotice() {
		return carddeckNotice;
	}

	public void setCarddeckNotice(CarddeckNoticeData carddeckNotice) {
		this.carddeckNotice = carddeckNotice;
	}

	@Override
	public String toString() {
		return "ResultCarddeckNoticeJSON [carddeckNotice=" + carddeckNotice + "]";
	}
}
