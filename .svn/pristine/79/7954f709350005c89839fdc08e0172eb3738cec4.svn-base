package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.mahout.classifier.sgd.CrossFoldLearner;

import com.ceqi.footballBettingRecommendation.server.features.EPLFeatures;
import com.ceqi.footballBettingRecommendation.server.features.EPLTeams;
import com.ceqi.footballBettingRecommendation.server.json.Event;
import com.ceqi.footballBettingRecommendation.server.json.MatchOfTheDayJson;
import com.ceqi.footballBettingRecommendation.server.json.ResultElmts;
import com.google.gwt.thirdparty.guava.common.base.Splitter;

/**
 * 
 *
 */

public final class Prediction {
	private static String Y = "Y";
	private static String N = "N";

	private static ArrayList<String> scores = new ArrayList<String>();
	private static ArrayList<Integer> rows = new ArrayList<Integer>();
	private static ArrayList<Integer> cols = new ArrayList<Integer>();
	private static ArrayList<String> games = new ArrayList<String>();

	/**
	 * Record the row position of the team's prediction score in the table. <br />
	 * Why no column position of the team score? homeTeam column is always at 1,
	 * awayTeam at 2 <br />
	 * This map is set up in isHomeMap method. <br />
	 */
	private static HashMap<EPLTeams, Integer> teamPosRowMap = new HashMap<EPLTeams, Integer>();
	private static LinkedHashMap<String, EPLTeams> teamsMap = new LinkedHashMap<String, EPLTeams>();
	// isHomeMap keeps 20 instinct teams isHome info
	private static HashMap<EPLTeams, String> isHomeMap = new HashMap<EPLTeams, String>();
	// a map record positions of "not available" games
	private static LinkedHashMap<EPLTeams, Integer> notAvailableTeamPosRowMap = new LinkedHashMap<EPLTeams, Integer>();
	private static LinkedHashMap<EPLTeams, Integer> notAvailableTeamPosColMap = new LinkedHashMap<EPLTeams, Integer>();
	/**
	 * fields used by the ML algorithm
	 * 
	 */
	private static List<String> localDatasets = new ArrayList<String>();
	private static List<EPLFeatures> features = new ArrayList<EPLFeatures>();

	private static String score = "Not available";

	static {
		initEPLTeamsMap();
	}

	private Prediction() {

	}

	// betfair string ( which is not consistent with footballdata.co.uk string)
	// -> EPLTeams enum teamStrToEnum
	private static void initEPLTeamsMap() {
		teamsMap.put("Chelsea", EPLTeams.CHELSEA);
		teamsMap.put("Sunderland", EPLTeams.SUNDERLAND);
		teamsMap.put("Everton", EPLTeams.EVERTON);
		teamsMap.put("Leicester", EPLTeams.LEICESTER);
		teamsMap.put("Man Utd", EPLTeams.MANUNITED);
		teamsMap.put("Norwich", EPLTeams.NORWICH);
		teamsMap.put("Southampton", EPLTeams.SOUTHAMPTON);
		teamsMap.put("Tottenham", EPLTeams.TOTTENHAM);
		teamsMap.put("Stoke", EPLTeams.STOKE);
		teamsMap.put("C Palace", EPLTeams.CRYSTALPALACE);
		teamsMap.put("West Brom", EPLTeams.WESTBROM);
		teamsMap.put("Bournemouth", EPLTeams.BOURNEMOUTH);
		teamsMap.put("Newcastle", EPLTeams.NEWCASTLE);
		teamsMap.put("Aston Villa", EPLTeams.ASTONVILLA);
		teamsMap.put("Watford", EPLTeams.WATFORD);
		teamsMap.put("Liverpool", EPLTeams.LIVERPOOL);
		teamsMap.put("Swansea", EPLTeams.SWANSEA);
		teamsMap.put("West Ham", EPLTeams.WESTHAM);
		teamsMap.put("Arsenal", EPLTeams.ARSENAL);
		teamsMap.put("Man City", EPLTeams.MANCITY);

	}

	/**
	 * Clear the 4 lists before initialisation(maps setup). <br />
	 * because this method will be called by a scheduled task.<br />
	 * Initialise the map fields
	 */
	public static void init() {
		clearLists();
		fetchGames();
		initPredictions();
		initMaps();

	}

	/**
	 * a scheduled task to generatePredictions.
	 */
	public static void generatePredictions() {
		algoSetup();

		// all 15-16 season's teams are ordered
		LinkedHashMap<String, EPLTeams> teamMap1516 = teamsMap;
		int index = 0;
		for (EPLTeams teamEnum : teamMap1516.values()) {

			String team = teamEnum.toString();
			Helenus helenus = new Helenus(localDatasets, teamEnum, features, 3);
			CrossFoldLearner classifier;
			score = "Dropped";
			if (helenus.getExamples().size() < 40) {
				fillInScore(index, teamEnum, score);
				index++;
				continue;
			}
			helenus.train();
			if (helenus.getLearnerAuc() < 0.65) {
				fillInScore(index, teamEnum, score);
				index++;
				continue;
			}
			helenus.dissect();
			double tmpScore = 0.50000000;
			while (tmpScore == 0.50000000) {
				try {
					helenus.createModel(team);
					classifier = helenus.getModel();
				} catch (IOException e) {
					throw new RuntimeException("Error reading model..");
				}

				Instance instance = new Instance(teamEnum, localDatasets, 3,
						features);
				// team win score
				tmpScore = classifier.classifyScalar(instance.asVector());
			}
			score = roundScore(tmpScore);
			fillInScore(index, teamEnum, score);
			index++;
		}

	}

	private static void fillInScore(int index, EPLTeams teamEnum, String score) {
		rows.add(getRow(teamEnum));
		cols.add(getCol(teamEnum));
		scores.set(index, score);
	}

	private static String roundScore(double number) {
		DecimalFormat halfUp = new DecimalFormat("##%");
		halfUp.setRoundingMode(RoundingMode.CEILING);
		DecimalFormat halfDown = new DecimalFormat("##%");
		halfDown.setRoundingMode(RoundingMode.FLOOR);
		if (number < 0.50000000) {
			return halfDown.format(number);
		} else {
			return halfUp.format(number);
		}
	}

	public static void fillInNotAvailableTeamScores() {
		for (EPLTeams eplTeam : notAvailableTeamPosRowMap.keySet()) {
			rows.add(notAvailableTeamPosRowMap.get(eplTeam));
			cols.add(notAvailableTeamPosColMap.get(eplTeam));
		}
	}

	private static void clearLists() {
		games.clear();
		rows.clear();
		cols.clear();
		scores.clear();
	}

	/**
	 * Get game fixture from betfair.
	 * 
	 * @return a list of games(20 maximum)
	 */
	private static ArrayList<String> fetchGames() {

		ArrayList<Event> eventsList = getEvents();

		sortEventsByDate(eventsList);
		for (int i = 0; i < eventsList.size(); i++) {
			Event evt = eventsList.get(i);
			if (isGame(evt) && games.size() < 20) {
				games.add(evt.getName());
			}
		}

		// for (String game : games) {
		// System.out.print(game + " ");
		// }
		// System.out.println();

		return games;
	}

	// init the prediction scores
	private static void initPredictions() {
		for (int i = 0; i < games.size() * 2; i++) {
			scores.add(score);
		}

	}

	/**
	 * Set up isHomeMap: it records info that if teams' next game is going to
	 * play at home or not. used by isHomeIns() to set up instance ISHOME
	 * feature <br />
	 * 
	 * Also, this method will set up the teamPosRowMap
	 * 
	 * Why do it in this way? ML models can only predict next game the teams are
	 * going to play.There are 20 teams in EPL (English Premier League)
	 * 
	 * @return
	 */
	private static void initMaps() {
		ArrayList<String> gameList = getGames();
		Splitter onV = Splitter.on(" v ");
		for (int i = 0; i < gameList.size(); i++) {
			String gameName = gameList.get(i);
			List<String> twoTeamList = onV.splitToList(gameName);
			String homeTeam = twoTeamList.get(0);
			String awayTeam = twoTeamList.get(1);
			EPLTeams homeTeamEnum = teamsMap.get(homeTeam);
			EPLTeams awayTeamEnum = teamsMap.get(awayTeam);
			if ((isHomeMap.size() < 20)
					&& (!isHomeMap.containsKey(homeTeamEnum))) {
				// distinct homeTeamEnum
				isHomeMap.put(teamStrToEnum(homeTeam), Y);
				teamPosRowMap.put(teamStrToEnum(homeTeam), i + 1);

			} else {
				notAvailableTeamPosRowMap.put(teamStrToEnum(homeTeam), i + 1);
				notAvailableTeamPosColMap.put(teamStrToEnum(homeTeam), 1);

			}
			if ((isHomeMap.size() < 20)
					&& (!isHomeMap.containsKey(awayTeamEnum))) {
				// distinct awayTeamEnum
				isHomeMap.put(teamStrToEnum(awayTeam), N);
				teamPosRowMap.put(teamStrToEnum(awayTeam), i + 1);

			} else {
				notAvailableTeamPosRowMap.put(teamStrToEnum(awayTeam), i + 1);
				notAvailableTeamPosColMap.put(teamStrToEnum(awayTeam), 2);
			}

		}

	}

	private static EPLTeams teamStrToEnum(String homeTeam) {
		return teamsMap.get(homeTeam);
	}

	private static int getRow(EPLTeams teamName) {

		return teamPosRowMap.get(teamName);

	}

	/**
	 * homeTeam column is always at 1, awayTeam at 2
	 * 
	 * @param teamName
	 * @return
	 */
	private static int getCol(EPLTeams teamName) {

		if (isHomeMap.get(teamName).equals(Y)) {
			return 1;
		}
		return 2;
	}

	/*
	 * ML algo simple setup
	 */

	private static void algoSetup() {
		setLocalDatasets();
		setFeatures();
	}

	private static void setLocalDatasets() {

		localDatasets.add("1112E0.csv");
		localDatasets.add("1213E0.csv");
		localDatasets.add("1314E0.csv");
		localDatasets.add("1415E0.csv");

	}

	private static void setFeatures() {
		features.add(EPLFeatures.FTRESULTS);
		features.add(EPLFeatures.ISHOME);
		features.add(EPLFeatures.AVGGOALS);
		features.add(EPLFeatures.AVGPOINTS);
		features.add(EPLFeatures.CARDS);
		features.add(EPLFeatures.CORNERS);
		features.add(EPLFeatures.FOULS);
		features.add(EPLFeatures.SHOTS);
		features.add(EPLFeatures.SHOTS_ON_TARGET);
		features.add(EPLFeatures.OP_AVGGOALS);
		features.add(EPLFeatures.OP_AVGPOINTS);
		features.add(EPLFeatures.OP_CARDS);
		features.add(EPLFeatures.OP_CORNERS);
		features.add(EPLFeatures.OP_FOULS);
		features.add(EPLFeatures.OP_SHOTS);
		features.add(EPLFeatures.OP_SHOTS_ON_TARGET);
	}

	/*
	 * methods used by fetchGames
	 */

	public static ArrayList<Event> getEvents() {
		ArrayList<Event> eventList = new ArrayList<Event>();
		try {
			HTTPRequest httpRequest = HTTPRequest.getHTTPRequestInstance();
			httpRequest.login();
			MatchOfTheDayJson json;
			json = httpRequest.post();
			ResultElmts[] results = json.getResultElmts();
			for (int i = 0; i < results.length; i++) {
				eventList.add(results[i].getEvent());
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return eventList;

	}

	public static void sortEventsByDate(ArrayList<Event> eventList) {
		Collections.sort(eventList);
		Collections.reverse(eventList);
	}

	public static boolean isGame(Event evt) {
		String regex = "^[a-zA-Z ]{5,11} v [a-zA-Z ]{5,11}$";
		if (evt.getName().matches(regex)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Getters
	 */

	public static ArrayList<String> getGames() {
		return games;
	}

	public static ArrayList<String> getScores() {
		return scores;
	}

	public static ArrayList<Integer> getRows() {
		return rows;
	}

	public static ArrayList<Integer> getCols() {
		return cols;
	}

	public static HashMap<EPLTeams, String> getIsHomeMap() {
		return isHomeMap;
	}

}
