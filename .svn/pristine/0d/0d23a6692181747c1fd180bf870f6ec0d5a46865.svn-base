package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import java.util.ArrayList;
import java.util.List;

import com.ceqi.footballBettingRecommendation.server.features.EPLFeatures;
import com.ceqi.footballBettingRecommendation.server.features.EPLFeaturesParser;
import com.ceqi.footballBettingRecommendation.server.features.EPLTeams;

public class ExampleParser {
	private EPLFeaturesParser featuresParser;

	// lists of features' values
	private List<String> isHomeList = new ArrayList<String>();
	private List<Double> avgPointsList = new ArrayList<Double>();
	private List<Double> avgGoalsList = new ArrayList<Double>();
	private List<Double> cornersList = new ArrayList<Double>();
	private List<Double> foulsList = new ArrayList<Double>();
	private List<Double> cardsList = new ArrayList<Double>();
	private List<Double> shotsList = new ArrayList<Double>();
	private List<Double> shotsOnTargetList = new ArrayList<Double>();
	private List<Double> opAvgPointsList = new ArrayList<Double>();
	private List<Double> opAvgGoalsList = new ArrayList<Double>();
	private List<Double> opCornersList = new ArrayList<Double>();
	private List<Double> opFoulsList = new ArrayList<Double>();
	private List<Double> opCardsList = new ArrayList<Double>();
	private List<Double> opShotsList = new ArrayList<Double>();
	private List<Double> opShotsOnTargetList = new ArrayList<Double>();
	private List<Integer> ftResultsList = new ArrayList<Integer>();

	private int numOfExamples = 0;

	public ExampleParser(EPLTeams teamName, List<String> localDatasets,
			int history) {
		featuresParser = new EPLFeaturesParser(teamName, localDatasets, history);

	}

	/**
	 * now I have lists of features values construct a map whose key is
	 * featureName, value is feature value, do it iteratively return a list of
	 * such map
	 * 
	 * 
	 * @param featureNamesList
	 *            a list of EPLFeatures
	 * @return a list of such map
	 */
	public List<Example> extractExamples(List<EPLFeatures> featureNamesList) {

		getFeatureValuesList(featureNamesList);
		getNumOfExamples();
		return constructExamples(featureNamesList);
	}

	/**
	 * @param featureNamesList
	 */
	private void getFeatureValuesList(List<EPLFeatures> featureNamesList) {
		for (EPLFeatures feature : featureNamesList) {
			switch (feature) {
			case ISHOME:
				isHomeList = featuresParser.isHome();
				break;
			case AVGPOINTS:
				avgPointsList = featuresParser.avgPoints();
				break;
			case AVGGOALS:
				avgGoalsList = featuresParser.avgGoals();
				break;
			case CORNERS:
				cornersList = featuresParser.corners();
				break;
			case FOULS:
				foulsList = featuresParser.fouls();
				break;
			case CARDS:
				cardsList = featuresParser.cards();
				break;
			case SHOTS:
				shotsList = featuresParser.shots();
				break;
			case SHOTS_ON_TARGET:
				shotsOnTargetList = featuresParser.shotsOnTarget();
				break;
			case OP_AVGPOINTS:
				opAvgPointsList = featuresParser.opAvgPoints();
				break;
			case OP_AVGGOALS:
				opAvgGoalsList = featuresParser.opAvgGoals();
				break;
			case OP_CORNERS:
				opCornersList = featuresParser.opCorners();
				break;
			case OP_FOULS:
				opFoulsList = featuresParser.opFouls();
				break;
			case OP_CARDS:
				opCardsList = featuresParser.opCards();
				break;
			case OP_SHOTS:
				opShotsList = featuresParser.opShots();
				break;
			case OP_SHOTS_ON_TARGET:
				opShotsOnTargetList = featuresParser.opShotsOnTarget();
				break;
			case FTRESULTS:
				ftResultsList = featuresParser.ftResults();
				break;
			default:
				throw new IllegalArgumentException("no such feature.");
			}
		}// end of for loop
	}

	private void getNumOfExamples() {
		if (isHomeList != null)
			numOfExamples = isHomeList.size();
		else if (avgPointsList != null)
			numOfExamples = avgPointsList.size();
		else if (avgGoalsList != null)
			numOfExamples = avgGoalsList.size();
		else if (cornersList != null)
			numOfExamples = cornersList.size();
		else if (foulsList != null)
			numOfExamples = foulsList.size();
		else if (cardsList != null)
			numOfExamples = cardsList.size();
		else if (shotsList != null)
			numOfExamples = shotsList.size();
		else if (shotsOnTargetList != null)
			numOfExamples = shotsOnTargetList.size();
		else if (opAvgPointsList != null)
			numOfExamples = opAvgPointsList.size();
		else if (opAvgGoalsList != null)
			numOfExamples = opAvgGoalsList.size();
		else if (opCornersList != null)
			numOfExamples = opCornersList.size();
		else if (opFoulsList != null)
			numOfExamples = opFoulsList.size();
		else if (opCardsList != null)
			numOfExamples = opCardsList.size();
		else if (opShotsList != null)
			numOfExamples = opShotsList.size();
		else if (opShotsOnTargetList != null)
			numOfExamples = opShotsOnTargetList.size();
		else if (ftResultsList != null)
			numOfExamples = ftResultsList.size();

	}

	/**
	 * 
	 * @param featuresList
	 * @return
	 */
	private List<Example> constructExamples(List<EPLFeatures> featuresList) {

		List<Example> examplesList = new ArrayList<Example>();
		// construct training examples
		for (int i = 0; i < numOfExamples; i++) {

			Example example = new Example();
			for (EPLFeatures feature : featuresList) {
				switch (feature) {
				case ISHOME:
					example.put(feature, isHomeList.get(i));
					break;
				case AVGPOINTS:
					example.put(feature, String.valueOf(avgPointsList.get(i)));
					break;
				case AVGGOALS:
					example.put(feature, String.valueOf(avgGoalsList.get(i)));
					break;
				case CORNERS:
					example.put(feature, String.valueOf(cornersList.get(i)));
					break;
				case FOULS:
					example.put(feature, String.valueOf(foulsList.get(i)));
					break;
				case CARDS:
					example.put(feature, String.valueOf(cardsList.get(i)));
					break;
				case SHOTS:
					example.put(feature, String.valueOf(shotsList.get(i)));
					break;
				case SHOTS_ON_TARGET:
					example.put(feature,
							String.valueOf(shotsOnTargetList.get(i)));
					break;
				case OP_AVGPOINTS:
					example.put(feature, String.valueOf(opAvgPointsList.get(i)));
					break;
				case OP_AVGGOALS:
					example.put(feature, String.valueOf(opAvgGoalsList.get(i)));
					break;
				case OP_CORNERS:
					example.put(feature, String.valueOf(opCornersList.get(i)));
					break;
				case OP_FOULS:
					example.put(feature, String.valueOf(opFoulsList.get(i)));
					break;
				case OP_CARDS:
					example.put(feature, String.valueOf(opCardsList.get(i)));
					break;
				case OP_SHOTS:
					example.put(feature, String.valueOf(opShotsList.get(i)));
					break;
				case OP_SHOTS_ON_TARGET:
					example.put(feature,
							String.valueOf(opShotsOnTargetList.get(i)));
					break;
				case FTRESULTS:
					example.put(feature, String.valueOf(ftResultsList.get(i)));
					break;
				default:
					throw new IllegalArgumentException("no such feature.");
				}
			}// end of for loop
			examplesList.add(example);
		}

		return examplesList;
	}

}
