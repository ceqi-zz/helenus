package com.ceqi.footballBettingRecommendation.server.features;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ceqi.footballBettingRecommendation.server.rawStats.Game;

public class EPLFeaturesParserTest {

	private List<String> datasets = new ArrayList<String>();
	private EPLFeaturesParser featuresParser = null;

	private List<Game> games;

	@Before
	public void setup() {
		datasets.add("1314E0.csv");
		datasets.add("1415E0.csv");
		datasets.add("sampleOf1516E0.csv");
		featuresParser = new EPLFeaturesParser(EPLTeams.ARSENAL, datasets, 2);
		games = featuresParser.getGames();
	}

	// helper method
	public void print(List<Double> toPrint) {
		int count = 0;
		for (double i : toPrint) {
			System.out.print(" " + i);
			count++;
			if (count % 5 == 0)
				System.out.println();
		}
	}

	@Test
	public void testFetchTeamDataOneTeam() throws IOException {

		for (Game game : games) {

			Assert.assertEquals(true,
					(game.getRawStats().get("HomeTeam") + game.getRawStats()
							.get("AwayTeam")).contains(EPLTeams.ARSENAL
							.toString()));
		}

	}

	@Test
	public void testFetchTeamDataNoDraw() throws IOException {

		for (Game game : games) {

			Assert.assertFalse("draw match occurs!",
					game.getRawStats().get("FTR").equals("D"));
		}

		Assert.assertEquals(games.size(), 87);

	}

	// this test also make sure that there is only one team "Arsenal' data.
	@Test
	public void testIsHome() throws IOException {

		// HISTORY = 2, remove first 2 games which is (1,0)
		String[] isHomeAry = { "Y", "N", "Y", "N", "Y", "N", "Y", "N", "Y",
				"N", "Y", "N", "N", "N", "Y", "N", "Y", "Y", "N", "Y", "N",
				"N", "N", "N", "Y", "N", "Y", "Y", "N", "Y", "N", "N", "N",
				"Y", "N", "Y", "N", "Y", "N", "Y", "Y", "N", "N", "Y", "N",
				"Y", "N", "Y", "N", "Y", "N", "Y", "N", "Y", "N", "N", "Y",
				"Y", "Y", "N", "N", "Y", "N", "N", "Y", "N", "Y", "N" };

		for (int i = 0; i < isHomeAry.length; i++) {

			Assert.assertEquals(isHomeAry[i], featuresParser.isHome().get(i));
		}

	}

	@Test
	public void testAvgPoints() {
		Double expected = 0.0166;
		Double actual = featuresParser.avgPoints().get(0);
		assertEquals(expected, actual, 0.0001);

	}

	@Test
	public void testOpAvgPoints() {
		Double actual = 0.0166;
		Double expected = featuresParser.opAvgPoints().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testAvgGoals() {
		Double expected = 0.0222;
		Double actual = featuresParser.avgGoals().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpAvgGoals() {
		Double[] actual = { 0.0222, 0.0055 };
		List<Double> opAvgGoals = featuresParser.opAvgGoals();
		for (int i = 0; i < actual.length; i++) {
			Assert.assertEquals(opAvgGoals.get(i), actual[i], 0.0001);
		}
	}

	@Test
	public void testCorners() {
		double expected = 0.0667;
		double actual = featuresParser.corners().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpCorners() {
		double expected = 0.0222;
		double actual = featuresParser.opCorners().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testFouls() {
		double expected = 0.01277;
		double actual = featuresParser.fouls().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpFouls() {
		double expected = 0.01555;
		double actual = featuresParser.opFouls().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testCards() {
		double expected = 0.0388;
		double actual = featuresParser.cards().get(0);
		assertEquals(expected, actual, 0.0001);

	}

	@Test
	public void testOpCards() {
		double[] expected = { 0.0388, 0.0222, 0.0166 };
		double actual0 = featuresParser.opCards().get(0);
		double actual1 = featuresParser.opCards().get(1);
		double actual2 = featuresParser.opCards().get(2);
		assertEquals(expected[0], actual0, 0.0001);
		assertEquals(expected[1], actual1, 0.0001);
		assertEquals(expected[2], actual2, 0.0001);

	}

	@Test
	public void testShots() {
		double expected = 0.01888;
		double actual = featuresParser.shots().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpShots() {
		double expected = 0.02111;
		double actual = featuresParser.opShots().get(23);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testShotsOnTarget() {
		double expected = 0.0611;
		double actual = featuresParser.shotsOnTarget().get(0);
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpShotsOnTarget() {
		double expected = 0.05;
		double actual = featuresParser.opShotsOnTarget().get(5);
		assertEquals(expected, actual, 0.01);
	}

	@Test
	public void testFtResults() {
		Integer actual = 1;
		Integer expected = featuresParser.ftResults().get(1);
		assertEquals(expected, actual);
	}
}
