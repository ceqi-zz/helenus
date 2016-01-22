package com.ceqi.footballBettingRecommendation.server.rawStats;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.Iterables;

/**
 * 
 * Game data is one line from the CSV file.
 * 
 * @author ce
 *
 */
public class Game {

	private Map<String, String> rawStats = new LinkedHashMap<String, String>();

	public Map<String, String> getRawStats() {
		return rawStats;
	}

	public Game(Iterable<String> fieldNames, Iterable<String> fieldValues,
			int limitSize) {

		Iterable<String> gameValues = Iterables.limit(fieldValues, limitSize);
		Iterator<String> gameValue = gameValues.iterator();

		Iterable<String> gameNames = Iterables.limit(fieldNames, limitSize);

		for (String name : gameNames) {
			String value = gameValue.next();
			rawStats.put(name, value);

		}
	}

}
