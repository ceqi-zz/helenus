package com.ceqi.footballBettingRecommendation.server.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Comparable<Event> {
	private String id;
	private String name;
	private String countryCode;
	private String timezone;
	private String openDate;

	public String getName() {
		return this.name;
	}

	public String getOpneDate() {
		return this.openDate;
	}

	public String toString() {
		return String
				.format("{id: %s, name: %s, countryCode : %s, timezone: %s, openDate: %s}",
						id, name, countryCode, timezone, openDate);
	}

	public Event() {
	}

	@Override
	public int compareTo(Event o) {
		Date date1 = convertStrToDate(openDate);
		Date date2 = convertStrToDate(o.getOpneDate());
		return date2.compareTo(date1);

	}

	private Date convertStrToDate(String dateStr) {

		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;

	}

	private String trimOpenDateStr(String openDateStr) {
		return openDateStr.substring(0, 10);
	}
}