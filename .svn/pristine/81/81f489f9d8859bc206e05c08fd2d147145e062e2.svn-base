package com.ceqi.footballBettingRecommendation.client;

import java.util.ArrayList;

import com.ceqi.footballBettingRecommendation.shared.ScoreWithPos;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FootballBettingRecommendation implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final String betfairUrl = "https://www.betfair.com/exchange/football/competition?id=31";
	/**
	 * Client- server stuff
	 * 
	 */
	private FootballGamesServiceAsync footballGamesSvc = GWT
			.create(FootballGamesService.class);

	/**
	 * GUI stuff
	 * 
	 */
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable gamesFlexTable = new FlexTable();
	private Button placeBetsButton = new Button("place bets!");

	@Override
	public void onModuleLoad() {
		/*
		 * create table for games
		 */
		gamesFlexTable.setText(0, 0, "Game");
		gamesFlexTable.setText(0, 1, "HomeScore");
		gamesFlexTable.setText(0, 2, "AwayScore");

		// add styles to elements in the table
		gamesFlexTable.getRowFormatter().addStyleName(0,
				"footballBettingRecommendationHeader");
		gamesFlexTable.addStyleName("scoreList");
		gamesFlexTable.getCellFormatter().addStyleName(0, 1,
				"scoreListNumericColumn");
		gamesFlexTable.getCellFormatter().addStyleName(0, 2,
				"scoreListNumericColumn");

		/*
		 * assemble main panel
		 */
		mainPanel.add(gamesFlexTable);
		mainPanel.add(placeBetsButton);

		/*
		 * associate the main panel with the HTML host page
		 */
		RootPanel.get("gameList").add(mainPanel);

		// Listen for mouse event on the placeBetsButton
		placeBetsButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				jumpToBettingSite();
			}

		});

		refreshGameList();
		getPredictions();
	}

	/**
	 * jump to bet fair website. Executed when the user clicks the
	 * placeBetsButton.
	 * 
	 */
	private void jumpToBettingSite() {
		Window.open(betfairUrl, "FootballBettingRecommendation", "");

	}

	private void refreshGameList() {
		// init the service proxy
		if (footballGamesSvc == null) {
			footballGamesSvc = GWT.create(FootballGamesService.class);
		}

		// set up the callback object
		AsyncCallback<ArrayList<String>> callback = new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onFailure(Throwable caught) {

				Window.alert("Failed to get response from server "
						+ caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<String> results) {
				updateTable(results);

			}

		};

		// once callback obj is set up, make the call to the
		// footballGamesService
		footballGamesSvc.getGames(callback);

	}

	private void updateTable(ArrayList<String> results) {
		for (int i = 0; i < results.size(); i++) {
			gamesFlexTable.setText(i + 1, 0, results.get(i));
		}
	}

	private void getPredictions() {
		// init the service proxy
		if (footballGamesSvc == null) {
			footballGamesSvc = GWT.create(FootballGamesService.class);
		}

		// set up the callback object
		AsyncCallback<ArrayList<ScoreWithPos>> callback = new AsyncCallback<ArrayList<ScoreWithPos>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to get response from server "
						+ caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<ScoreWithPos> result) {

				for (int i = 0; i < result.size(); i++) {
					ScoreWithPos swp = result.get(i);
					gamesFlexTable.setText(swp.getRow(), swp.getCol(),
							String.valueOf(swp.getScore()));
				}

			}

		};

		// once callback obj is set up, make the call to the
		// footballGamesService
		footballGamesSvc.getPredictions(callback);

	}

}
