package com.soccer.league.api;

import java.io.IOException;

import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class Connection {

	private final ApiKey apiKey;
	
	public Connection(ApiKey apiKey) {
		this.apiKey = apiKey;
	}
	
	public String standings(int leagueId) throws IOException{
			
		OkHttpClient client = new OkHttpClient();
	
		Request request = new Request.Builder()
				.url("https://api-football-v1.p.rapidapi.com/v3/standings?league="+ leagueId + "&season=2022")
				.get()
				.addHeader("X-RapidAPI-Key", apiKey.getApiKey())
				.addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
	
		Response response = client.newCall(request).execute();
	
		String result = response.body().string();
		
		return result;
	}
	
}
