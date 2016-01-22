package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ceqi.footballBettingRecommendation.server.features.EPLFeaturesParser;
import com.ceqi.footballBettingRecommendation.server.features.EPLTeams;

/**
 * 
 * 
 * TODO: test getInsMap()
 * 
 * @author ce
 *
 */
public class InstanceTest {
	private List<String> datasets = new ArrayList<String>();
	private EPLFeaturesParser featuresParser = null;

	@Before
	public void setup() {
		datasets.add("1314E0.csv");
		datasets.add("1415E0.csv");
		datasets.add("sampleOf1516E0.csv");
		featuresParser = new EPLFeaturesParser(EPLTeams.ARSENAL, datasets, 2);
	}

	/*
	 * 
	 * These are FeatureParser's methods, used by Instance, so do the test in
	 * Instance class
	 */
	@Test
	public void testAvgPointsIns() {

		double expected = 0.0333;
		double actual = featuresParser.avgPointsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpAvgPointsIns() {
		double expected = 0.0000;
		double actual = featuresParser.opAvgPointsIns();
		assertEquals(expected, actual, 0.0001);

	}

	@Test
	public void testAvgGoalsIns() {
		double expected = 0.0166;
		double actual = featuresParser.avgGoalsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpAvgGoalsIns() {
		double expected = 0.0000;
		double actual = featuresParser.opAvgGoalsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testCornersIns() {
		double expected = 0.0888;
		double actual = featuresParser.cornersIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpCornersIns() {
		double expected = 0.0388;
		double actual = featuresParser.opCornersIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testFoulsIns() {
		double expected = 0.0094;
		double actual = featuresParser.foulsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void testOpFoulsIns() {
		double expected = 0.0094;
		double actual = featuresParser.opFoulsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void cardsIns() {
		double expected = 0.0222;
		double actual = featuresParser.cardsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void opCardsIns() {
		double expected = 0.0111;
		double actual = featuresParser.opCardsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void shotsIns() {
		double expected = 0.0161;
		double actual = featuresParser.shotsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void opShotsIns() {
		double expected = 0.0138;
		double actual = featuresParser.opShotsIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void shotsOnTargetIns() {
		double expected = 0.0500;
		double actual = featuresParser.shotsOnTargetIns();
		assertEquals(expected, actual, 0.0001);
	}

	@Test
	public void opShotsOnTargetIns() {
		double expected = 0.0500;
		double actual = featuresParser.opShotsOnTargetIns();
		assertEquals(expected, actual, 0.0001);
	}
}
