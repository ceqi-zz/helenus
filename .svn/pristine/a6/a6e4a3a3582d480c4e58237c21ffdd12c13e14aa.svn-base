package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.vectorizer.encoders.ConstantValueEncoder;
import org.apache.mahout.vectorizer.encoders.FeatureVectorEncoder;
import org.apache.mahout.vectorizer.encoders.StaticWordValueEncoder;

import com.ceqi.footballBettingRecommendation.server.features.EPLFeatures;
import com.ceqi.footballBettingRecommendation.server.features.EPLFeaturesParser;
import com.ceqi.footballBettingRecommendation.server.features.EPLTeams;

/**
 * 
 * ML model uses instance object to predict game outcome.
 * 
 * @author ce
 *
 */
public class Instance extends AbstractExample {

	private EPLFeaturesParser featuresParser = null;
	// the map to fill the vector
	private Map<EPLFeatures, String> insMap = new HashMap<EPLFeatures, String>();

	// vector related fields
	private static final ConstantValueEncoder interceptEncoder = new ConstantValueEncoder(
			"intercept");
	private static final FeatureVectorEncoder featureWordEncoder = new StaticWordValueEncoder(
			"featureWord");
	private RandomAccessSparseVector vector;

	/*
	 * specify the teamName to get corresponding team instance for prediction.
	 */
	public Instance(EPLTeams teamName, List<String> localDatasets, int history,
			List<EPLFeatures> featureNamesList) {
		featuresParser = new EPLFeaturesParser(teamName, localDatasets, history);
		setupInsMap(featureNamesList);
	}

	/**
	 * Establish instance map. the map insMap is initialised by the constructor.
	 * 
	 * @param featureNamesList
	 * @return
	 */
	private Map<EPLFeatures, String> setupInsMap(
			List<EPLFeatures> featureNamesList) {
		for (EPLFeatures featureName : featureNamesList) {
			switch (featureName) {
			case AVGGOALS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.avgGoalsIns()));
				break;
			case AVGPOINTS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.avgPointsIns()));
				break;
			case CARDS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.cardsIns()));
				break;
			case CORNERS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.cornersIns()));
				break;
			case FOULS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.foulsIns()));
				break;
			case ISHOME:
				break;
			case OP_AVGGOALS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.opAvgGoalsIns()));
				break;
			case OP_AVGPOINTS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.opAvgPointsIns()));
				break;
			case OP_CARDS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.cardsIns()));
				break;
			case OP_CORNERS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.opCornersIns()));
				break;
			case OP_FOULS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.opFoulsIns()));
				break;
			case OP_SHOTS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.opShotsIns()));
				break;
			case OP_SHOTS_ON_TARGET:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.opShotsOnTargetIns()));
				break;
			case SHOTS:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.shotsIns()));
				break;
			case SHOTS_ON_TARGET:
				this.insMap.put(featureName,
						String.valueOf(featuresParser.shotsOnTargetIns()));
				break;
			case FTRESULTS:
				// no this feature for instance.
				break;
			default:
				throw new IllegalArgumentException("no such feature.");

			}
		}
		return null;
	}

	public Vector asVector() {
		vector = new RandomAccessSparseVector(FEATURES);
		interceptEncoder.addToVector("1", vector);
		for (EPLFeatures featureName : this.insMap.keySet()) {
			switch (featureName) {
			case ISHOME:
				featureWordEncoder.addToVector(featureName.toString() + ":"
						+ insMap.get(featureName), 1, vector);
				break;

			// same operation
			case AVGGOALS:
			case AVGPOINTS:
			case CARDS:
			case CORNERS:
			case FOULS:
			case OP_AVGGOALS:
			case OP_AVGPOINTS:
			case OP_CARDS:
			case OP_CORNERS:
			case OP_FOULS:
			case OP_SHOTS:
			case OP_SHOTS_ON_TARGET:
			case SHOTS:
			case SHOTS_ON_TARGET:
				double v = Double.valueOf(insMap.get(featureName));
				featureWordEncoder.addToVector(featureName.toString(), v,
						vector);
				break;
			// nothing to do with it
			case FTRESULTS:
				break;
			default:
				break;

			}
		}
		return vector;
	}

	public Map<EPLFeatures, String> getInsMap() {
		return this.insMap;
	}

}
