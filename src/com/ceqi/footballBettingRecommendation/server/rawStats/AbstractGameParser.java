package com.ceqi.footballBettingRecommendation.server.rawStats;

import com.google.common.base.Splitter;

public abstract class AbstractGameParser {
	// split csv cells via comma
	protected final Splitter onComma = Splitter.on(",");
	// betting odds are not parsed
	protected final int limitSize = 23;

}
