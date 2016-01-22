package com.ceqi.footballBettingRecommendation.server.machineLearningModule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.ceqi.footballBettingRecommendation.server.json.ListEvents;
import com.ceqi.footballBettingRecommendation.server.json.MatchOfTheDayJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HTTPRequest {
	private static HTTPRequest instance = null;
	private String sessionToken = null;
	private BufferedReader reader;
	private Properties properties;

	public static HTTPRequest getHTTPRequestInstance() {
		if (instance == null)
			return instance = new HTTPRequest();
		else
			return instance;
	}

	private HTTPRequest() {
	}

	public String login() throws MalformedURLException, IOException {
		/*
		 * preparing
		 */
		String url = "https://identitysso.betfair.com/api/login";
		String charset = StandardCharsets.UTF_8.name();
		try {
			reader = new BufferedReader(new FileReader("config.properties"));
		} catch (FileNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}

		properties = new Properties();
		try {
			properties.load(reader);
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}

		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		String query = String.format("username=%s&password=%s",
				URLEncoder.encode(username, charset),
				URLEncoder.encode(password, charset));

		/*
		 * HTTP POST - login
		 */
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("X-Application",
				properties.getProperty("X-Application"));
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type",
				" application/x-www-form-urlencoded");

		try (OutputStream output = connection.getOutputStream()) {
			output.write(query.getBytes(charset));
		}

		InputStream response = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				response));
		String line = null;
		String[] strAry = null;
		if ((line = reader.readLine()) != null) {
			System.out.println(line);
			strAry = line.split("[,:\"]");
		}

		this.sessionToken = strAry[4];
		response.close();

		return this.sessionToken;
	}

	public MatchOfTheDayJson post() throws ClientProtocolException, IOException {
		ListEvents listCompetitions = new ListEvents();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonReq = gson.toJson(listCompetitions);
		System.out.println(jsonReq);
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"https://api.betfair.com/exchange/betting/json-rpc/v1/");

		StringEntity req = new StringEntity(jsonReq);
		post.addHeader("Accept", "application/json");
		post.addHeader("content-type", "application/json");
		post.addHeader("X-Application", properties.getProperty("X-Application"));
		post.addHeader("X-Authentication", this.sessionToken);
		post.setEntity(req);
		HttpResponse response1 = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response1
				.getEntity().getContent()));
		StringBuffer jsonRes = new StringBuffer();
		String line1 = "";
		while ((line1 = rd.readLine()) != null) {
			jsonRes.append(line1);
		}
		System.out.println(jsonRes.toString());

		MatchOfTheDayJson modJson = gson.fromJson(jsonRes.toString(),
				MatchOfTheDayJson.class);
		System.out.println(modJson.toString());

		return modJson;
	}

}