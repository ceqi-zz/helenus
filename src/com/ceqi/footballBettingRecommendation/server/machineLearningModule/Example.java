package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.vectorizer.encoders.ConstantValueEncoder;
import org.apache.mahout.vectorizer.encoders.FeatureVectorEncoder;
import org.apache.mahout.vectorizer.encoders.StaticWordValueEncoder;

import com.ceqi.footballBettingRecommendation.server.features.EPLFeatures;

/**
 * 
 * training example with target variable <br/>
 * encode features into vector; get target value
 * 
 * @author ce
 *
 */

public class Example extends AbstractExample {

	private LinkedHashMap<EPLFeatures, String> exampleMap;
	private static final ConstantValueEncoder interceptEncoder = new ConstantValueEncoder(
			"intercept");
	private static final FeatureVectorEncoder featureWordEncoder = new StaticWordValueEncoder(
			"featureWord");
	private static final Map<String, Set<Integer>> traceDictionary = new TreeMap<>();
	private RandomAccessSparseVector vector;

	public Example() {
		exampleMap = new LinkedHashMap<EPLFeatures, String>();
		setTraceDict();
	}

	public void put(EPLFeatures feature, String value) {
		exampleMap.put(feature, value);
	}

	public String get(EPLFeatures key) {
		return exampleMap.get(key);
	}

	public void setTraceDict() {

		featureWordEncoder.setTraceDictionary(traceDictionary);
	}

	public Map<String, Set<Integer>> getTraceDict() {
		return traceDictionary;
	}

	public Vector asVector() {
		vector = new RandomAccessSparseVector(FEATURES);

		interceptEncoder.addToVector("1", vector);

		for (EPLFeatures feature : exampleMap.keySet()) {
			switch (feature) {
			case ISHOME: {
				featureWordEncoder.addToVector(feature.toString() + ":"
						+ exampleMap.get(feature), 1, vector);
				break;
			}
			case AVGPOINTS:
			case AVGGOALS:
			case CORNERS:
			case FOULS:
			case CARDS:
			case SHOTS:
			case SHOTS_ON_TARGET:
			case OP_AVGPOINTS:
			case OP_AVGGOALS:
			case OP_CORNERS:
			case OP_FOULS:
			case OP_CARDS:
			case OP_SHOTS:
			case OP_SHOTS_ON_TARGET: {
				double v = Double.valueOf(exampleMap.get(feature));
				featureWordEncoder.addToVector(feature.toString(), v, vector);
				break;
			}

			case FTRESULTS:
				break;
			default:
				throw new IllegalArgumentException(String.format(
						"bad feature name: %s", feature));

			}
		}

		return vector;
	}

	public int getTarget() {
		return Integer.valueOf(exampleMap.get(EPLFeatures.FTRESULTS));

	}
}
