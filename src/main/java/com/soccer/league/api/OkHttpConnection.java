package com.soccer.league.api;

import java.io.IOException;

import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class OkHttpConnection {

	private final ApiKey apiKey;
	
	public OkHttpConnection(ApiKey apiKey) {
		this.apiKey = apiKey;
	}
	
	//구단 순위
	public String standingsConnect(int leagueId) throws IOException{
			
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
	
	
	//경기 일정
	public String fixturesConnect() throws IOException {
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
			.url("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=39&season=2022&from=2023-05-06&to=2023-05-14&timezone=Asia%2Fseoul")
			.get()
			.addHeader("X-RapidAPI-Key", "00dff05ec1msh5a3fcefada34491p12c058jsn7a0d02c8ef99")
			.addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
			.build();

		Response response = client.newCall(request).execute();
		
		String result = response.body().string();
		
		return result;
	}
	
}
