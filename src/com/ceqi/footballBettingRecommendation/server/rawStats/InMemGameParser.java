package com.ceqi.footballBettingRecommendation.server.rawStats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;

/**
 * Simple CSV FILE parsing game parser parses data in memory from the
 * footballdata website.
 * 
 * @author ce
 *
 */
public class InMemGameParser extends AbstractGameParser implements
		Iterable<Game> {

	@Override
	public Iterator<Game> iterator() {
		try {
			return new AbstractIterator<Game>() {
				URL url = new URL(
						"http://www.football-data.co.uk/mmz4281/1516/E0.csv");
				InputStreamReader inStream = new InputStreamReader(
						url.openStream());
				BufferedReader input = new BufferedReader(inStream);

				// read field names ONCE only
				Iterable<String> fieldNames = onComma.split(input.readLine());

				@Override
				protected Game computeNext() {
					try {
						String line = input.readLine();
						if (line == null)
							return endOfData();
						// ignore parsing headLine
						if (line.contains("HomeTeam"))
							return null;
						// ignore parsing odds
						Iterables.limit(onComma.split(line), limitSize);

						return new Game(fieldNames, onComma.split(line),
								limitSize);

					} catch (IOException e) {
						throw new RuntimeException("Error reading data", e);
					}

				}
			};
		} catch (IOException e) {
			throw new RuntimeException("Error reading data", e);
		}
	}
}
