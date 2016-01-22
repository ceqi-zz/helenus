package com.ceqi.footballBettingRecommendation.shared;

import java.io.Serializable;

/**
 * It contains information for the scores of teams and the positions of the
 * scores in the table.
 * 
 * @author ce
 *
 */
public class ScoreWithPos implements Serializable {
	private String score;
	private int row;
	private int col;

	public ScoreWithPos() {

	}

	public ScoreWithPos(String score, int row, int col) {
		this.score = score;
		this.row = row;
		this.col = col;
	}

	public String getScore() {
		return score;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}
