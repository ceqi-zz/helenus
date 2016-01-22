package com.ceqi.footballBettingRecommendation.server.json;

/**
 * 
 * Response Json
 * 
 * @author ce
 *
 */
public class MatchOfTheDayJson {
	private String jsonrpc;
	private ResultElmts[] result;
	private int id;

	public MatchOfTheDayJson() {
	}

	public ResultElmts[] getResultElmts() {
		return result;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < result.length; i++) {
			str += result[i].toString() + ",";
		}
		return String.format("{jsonrpc: %s, result:%s, id: %d}", jsonrpc, str,
				id);
	}
}
