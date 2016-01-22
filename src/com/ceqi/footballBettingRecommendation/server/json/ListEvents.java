package com.ceqi.footballBettingRecommendation.server.json;

/**
 * request Json
 * 
 * @author ce
 *
 */
public class ListEvents {
	private Params params = new Params();
	private String jsonrpc = "2.0";
	private String method = "SportsAPING/v1.0/listEvents";
	private int id = 1;

	public ListEvents() {
	}
}

class Params {
	private Filter filter = new Filter();

	public Params() {
	}
}

class Filter {
	private int[] competitionIds = { 31 };
	private boolean turnInPlayEnabled = true;

	public Filter() {
	}
}
