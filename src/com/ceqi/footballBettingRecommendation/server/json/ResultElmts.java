package com.ceqi.footballBettingRecommendation.server.json;


public class ResultElmts {
	private Event event = new Event();
	private int marketCount;

	public Event getEvent() {
		return event;
	}

	public String toString() {
		return event.toString() + String.format("marketCount: %d", marketCount);
	}

	public ResultElmts() {
	}
}
