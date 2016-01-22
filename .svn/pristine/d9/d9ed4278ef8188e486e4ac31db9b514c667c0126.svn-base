package com.ceqi.footballBettingRecommendation.server.features;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.io.Resources;

/**
 * 
 * a helper class provides helper methods for EPL.
 * 
 * @author ce
 *
 */
public class EPLHelper {

	/**
	 * get last 5 seasons' EPL distinct team names, so I can check team names
	 * and add team names to EPLTeams enum.
	 * 
	 * @throws IOException
	 */
	public static void getTeamNames() throws IOException {
		Splitter onComma = Splitter.on(",");
		List<String> raw;
		// read the data
		raw = Resources.readLines(Resources.getResource("1516E0.csv"),
				Charsets.UTF_8);
		raw.addAll(Resources.readLines(Resources.getResource("1415E0.csv"),
				Charsets.UTF_8));
		raw.addAll(Resources.readLines(Resources.getResource("1314E0.csv"),
				Charsets.UTF_8));
		raw.addAll(Resources.readLines(Resources.getResource("1213E0.csv"),
				Charsets.UTF_8));
		raw.addAll(Resources.readLines(Resources.getResource("1112E0.csv"),
				Charsets.UTF_8));

		TreeSet<String> teamSet = new TreeSet<String>();
		for (String line : raw.subList(1, raw.size())) {
			// not read titles row
			if (!line.contains("HomeTeam")) {

				Iterable<String> values = onComma.split(line);

				String team = Iterables.get(values, 2);
				teamSet.add(team);
			}
		}

		int i = 1;
		for (String team : teamSet) {
			System.out.println(String.format("%d. %s", i++, team));
		}
	}
}
