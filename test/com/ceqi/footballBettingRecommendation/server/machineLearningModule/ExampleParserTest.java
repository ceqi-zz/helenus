package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ceqi.footballBettingRecommendation.server.features.EPLFeatures;
import com.ceqi.footballBettingRecommendation.server.features.EPLTeams;

public class ExampleParserTest {

	List<EPLFeatures> fl = new ArrayList<EPLFeatures>();
	private List<String> datasets = new ArrayList<String>();
	ExampleParser eParser;
	List<Example> el = new ArrayList<Example>();

	@Before
	public void setup() {

		fl.add(EPLFeatures.ISHOME);
		fl.add(EPLFeatures.AVGGOALS);
		fl.add(EPLFeatures.AVGPOINTS);
		fl.add(EPLFeatures.CORNERS);
		fl.add(EPLFeatures.FOULS);
		fl.add(EPLFeatures.CARDS);
		fl.add(EPLFeatures.SHOTS);
		fl.add(EPLFeatures.SHOTS_ON_TARGET);
		fl.add(EPLFeatures.OP_AVGPOINTS);
		fl.add(EPLFeatures.OP_AVGGOALS);
		fl.add(EPLFeatures.OP_CORNERS);
		fl.add(EPLFeatures.OP_FOULS);
		fl.add(EPLFeatures.OP_CARDS);
		fl.add(EPLFeatures.OP_SHOTS);
		fl.add(EPLFeatures.OP_SHOTS_ON_TARGET);
		fl.add(EPLFeatures.FTRESULTS);

		datasets.add("1314E0.csv");
		datasets.add("1415E0.csv");
		datasets.add("sampleOf1516E0.csv");
		eParser = new ExampleParser(EPLTeams.ARSENAL, datasets, 2);

		// Arseanl's first example
		Example e = new Example();
		e.put(EPLFeatures.ISHOME, "Y");
		e.put(EPLFeatures.AVGGOALS, "0.0222");
		e.put(EPLFeatures.AVGPOINTS, "0.0166");
		e.put(EPLFeatures.CORNERS, "0.0667");
		e.put(EPLFeatures.FOULS, "0.01277");
		e.put(EPLFeatures.CARDS, "0.0388");
		e.put(EPLFeatures.SHOTS, "0.01888");
		e.put(EPLFeatures.SHOTS_ON_TARGET, "0.0611");
		e.put(EPLFeatures.OP_AVGPOINTS, "0.0166");
		e.put(EPLFeatures.OP_AVGGOALS, "0.0222");
		e.put(EPLFeatures.OP_CORNERS, "0.0222");
		e.put(EPLFeatures.OP_FOULS, "0.01555");
		e.put(EPLFeatures.OP_CARDS, "0.0166");
		e.put(EPLFeatures.OP_SHOTS, "0.02111");
		e.put(EPLFeatures.OP_SHOTS_ON_TARGET, "0.05");
		e.put(EPLFeatures.FTRESULTS, "1");
		el.add(e);
	}

	@Test
	public void testExtractExamples_isHome() {

		String actual = el.get(0).get(EPLFeatures.ISHOME);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.ISHOME);
		assertEquals(expected, actual);
	}

	@Test
	public void testExtractExamples_avgGoals() {

		String actual = el.get(0).get(EPLFeatures.AVGGOALS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.AVGGOALS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExamples_avgPoints() {

		String actual = el.get(0).get(EPLFeatures.AVGPOINTS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.AVGPOINTS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExamples_corners() {

		String actual = el.get(0).get(EPLFeatures.CORNERS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.CORNERS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExamples_fouls() {
		String actual = el.get(0).get(EPLFeatures.FOULS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.FOULS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExamples_cards() {
		String actual = el.get(0).get(EPLFeatures.CARDS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.CARDS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_shots() {
		String actual = el.get(0).get(EPLFeatures.SHOTS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.SHOTS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_shotsOnTarget() {
		String actual = el.get(0).get(EPLFeatures.SHOTS_ON_TARGET);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.SHOTS_ON_TARGET);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_opAvgPoints() {
		String actual = el.get(0).get(EPLFeatures.OP_AVGPOINTS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.OP_AVGPOINTS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_opAvgGoals() {
		String actual = el.get(0).get(EPLFeatures.OP_AVGGOALS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.OP_AVGGOALS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_opCorners() {
		String actual = el.get(0).get(EPLFeatures.OP_CORNERS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.OP_CORNERS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_opFouls() {
		String actual = el.get(0).get(EPLFeatures.OP_FOULS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.OP_FOULS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_opCards() {
		String actual = el.get(0).get(EPLFeatures.OP_CARDS);
		String expected = eParser.extractExamples(fl).get(2)
				.get(EPLFeatures.OP_CARDS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_opShots() {
		String actual = el.get(0).get(EPLFeatures.OP_SHOTS);
		String expected = eParser.extractExamples(fl).get(23)
				.get(EPLFeatures.OP_SHOTS);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.0001);
	}

	@Test
	public void testExtractExample_opShotOnTarget() {
		String actual = el.get(0).get(EPLFeatures.OP_SHOTS_ON_TARGET);
		String expected = eParser.extractExamples(fl).get(5)
				.get(EPLFeatures.OP_SHOTS_ON_TARGET);
		assertEquals(Double.valueOf(expected), Double.valueOf(actual), 0.01);
	}

	@Test
	public void testExtractExamples_ftResults() {

		String actual = el.get(0).get(EPLFeatures.FTRESULTS);
		String expected = eParser.extractExamples(fl).get(0)
				.get(EPLFeatures.FTRESULTS);
		assertEquals(expected, actual);
	}

}
