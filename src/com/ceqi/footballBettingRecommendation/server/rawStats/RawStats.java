/**
 * 
 */
package com.ceqi.footballBettingRecommendation.server.rawStats;

/**
 * @author ce
 * 
 * 
 * 
 *         Div = League Division Date = Match Date (dd/mm/yy) HomeTeam = Home
 *         Team AwayTeam = Away Team FTHG = Full Time Home Team Goals FTAG =
 *         Full Time Away Team Goals FTR = Full Time Result (H=Home Win, D=Draw,
 *         A=Away Win) HTHG = Half Time Home Team Goals HTAG = Half Time Away
 *         Team Goals HTR = Half Time Result (H=Home Win, D=Draw, A=Away Win)
 * 
 *         Match Statistics (where available) Attendance = Crowd Attendance
 *         Referee = Match Referee HS = Home Team Shots AS = Away Team Shots HST
 *         = Home Team Shots on Target AST = Away Team Shots on Target HHW =
 *         Home Team Hit Woodwork AHW = Away Team Hit Woodwork HC = Home Team
 *         Corners AC = Away Team Corners HF = Home Team Fouls Committed AF =
 *         Away Team Fouls Committed HO = Home Team Offsides AO = Away Team
 *         Offsides HY = Home Team Yellow Cards AY = Away Team Yellow Cards HR =
 *         Home Team Red Cards AR = Away Team Red Cards
 *
 */
public enum RawStats {
	HOMETEAM("HomeTeam"), AWAYTEAM("AwayTeam"), FULLTIME_HOMEGOALS("FTHG"), FULLTIME_AWAYGOALS(
			"FTAG"), FULLTIME_RESULT("FTR"), HOME_SHOTS("HS"), AWAY_SHOTS("AS"), HOME_SHOTS_ON_TARGET(
			"HST"), AWAY_SHOTS_ON_TARGET("AST"), HOME_FOULS("HF"), AWAY_FOULS(
			"AF"), HOME_CORNERS("HC"), AWAY_CORNERS("AC"), HOME_YELLOW_CARDS(
			"HY"), AWAY_YELLOW_CARDS("AY"), HOME_RED_CARDS("HR"), AWAY_RED_CARDS(
			"AR");

	private String name;

	private RawStats(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
