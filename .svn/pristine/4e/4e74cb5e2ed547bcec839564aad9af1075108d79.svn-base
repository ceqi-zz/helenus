package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.io.Resources;

/**
 * 
 * A very simple assumption that home team will always win.
 * 
 * @author ce
 *
 */
public class HomeWins {

	public static void main(String[] args) throws IOException {
		processData();
		evaluate();
	}

	// ******************************* data related fields **********

	// raw data
	private static List<String> raw = null;

	// all teams results
	// key is a home team name, values are a home team results
	private static HashMap<String, ArrayList<String>> teamResultsAll = new HashMap<String, ArrayList<String>>();

	/**
	 * 
	 * @throws IOException
	 */
	public static void processData() throws IOException {

		Splitter onComma = Splitter.on(",");

		// read the data
		raw = Resources.readLines(Resources.getResource("1415E0.csv"),
				Charsets.UTF_8);
		raw.addAll(Resources.readLines(Resources.getResource("1314E0.csv"),
				Charsets.UTF_8));
		raw.addAll(Resources.readLines(Resources.getResource("1213E0.csv"),
				Charsets.UTF_8));
		raw.addAll(Resources.readLines(Resources.getResource("1112E0.csv"),
				Charsets.UTF_8));

		for (String line : raw.subList(1, raw.size())) {

			Iterable<String> values = onComma.split(line);

			// if the hash map does not have the key, then add the key values
			// pair, otherwise, add new team result to the homeTeamResults array
			// list, then update the hash map

			String homeTeam = Iterables.get(values, 2);
			String teamResult = Iterables.get(values, 6);
			if (!teamResultsAll.containsKey(homeTeam)) {
				ArrayList<String> homeTeamResults = new ArrayList<String>();
				// the 6th column is the full time result
				homeTeamResults.add(teamResult);
				teamResultsAll.put(homeTeam, homeTeamResults);
			} else {
				ArrayList<String> homeTeamResults = teamResultsAll
						.get(homeTeam);
				homeTeamResults.add(teamResult);
				teamResultsAll.put(homeTeam, homeTeamResults);
			}

		}

		teamResultsAll.remove("HomeTeam");
	}

	/**
	 * accuracy = home team wins / total games
	 */

	public static void evaluate() {
		// iterate each team and its home game results, then show the prediction
		// accuracy
		int i = 1;

		for (String team : teamResultsAll.keySet()) {
			double win = 0, total = 0, accuracy = 0.0;
			ArrayList<String> results = teamResultsAll.get(team);
			total = results.size();
			for (String result : results) {
				if (result.equals("H"))
					win++;
			}
			accuracy = win / total;
			System.out.println(String.format(
					"%d. accuracy of %s: %.2f%%, %.0f out of %.0f games", i++,
					team, accuracy * 100, win, total));
		}
	}

}
