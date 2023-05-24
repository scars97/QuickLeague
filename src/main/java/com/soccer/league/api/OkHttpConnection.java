package com.soccer.league.api;

import java.io.IOException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
@RequiredArgsConstructor
//@Primary
public class OkHttpConnection implements HttpConnectionPolicy{

	private final ApiKey apiKey;
	private final OkHttpClient client;

	//구단 순위
	public String standingsConnect(int leagueId) throws IOException {
	
		Request request = new Request.Builder()
				.url("https://api-football-v1.p.rapidapi.com/v3/standings?league=" + 
						leagueId + "&season=2022")
				.get()
				.addHeader("X-RapidAPI-Key", apiKey.getApiKey())
				.addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
		
		try(Response response = client.newCall(request).execute()){
			String result = response.body().string();
			response.close();
			
			return result;
		}
	}
	
	//경기 일정
	public String lastFixturesConnect(int leagueId) throws IOException {

		Request request = new Request.Builder()
			.url("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=" +
					leagueId + "&season=2022&from=2023-05-06&to=2023-05-14&timezone=Asia%2Fseoul")
			.get()
			.addHeader("X-RapidAPI-Key", apiKey.getApiKey())
			.addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
			.build();

		try(Response response = client.newCall(request).execute()){
			String result = response.body().string();
			response.close();
			return result;
		}	
	}
	
	public String nextFixturesConnect(int leagueId) throws IOException {

		Request request = new Request.Builder()
			.url("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=" +
					leagueId + "next=10&timezone=Asia%2Fseoul")
			.get()
			.addHeader("X-RapidAPI-Key", apiKey.getApiKey())
			.addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
			.build();

		try(Response response = client.newCall(request).execute()){
			String result = response.body().string();
			response.close();
			return result;
		}	
	}
	
	public String topScorersConnect(int leagueId) throws IOException {

		Request request = new Request.Builder()
			.url("https://api-football-v1.p.rapidapi.com/v3/fixtures?league=" +
					leagueId + "&season=2022")
			.get()
			.addHeader("X-RapidAPI-Key", apiKey.getApiKey())
			.addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
			.build();

		try(Response response = client.newCall(request).execute()){
			String result = response.body().string();
			response.close();
			return result;
		}	
	}
}
