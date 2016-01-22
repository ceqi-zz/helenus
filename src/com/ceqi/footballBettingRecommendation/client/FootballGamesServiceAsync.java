package com.ceqi.footballBettingRecommendation.client;

import java.util.ArrayList;

import com.ceqi.footballBettingRecommendation.shared.ScoreWithPos;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FootballGamesServiceAsync {
	void getGames(AsyncCallback<ArrayList<String>> callback);

	void getPredictions(AsyncCallback<ArrayList<ScoreWithPos>> callback);
}
