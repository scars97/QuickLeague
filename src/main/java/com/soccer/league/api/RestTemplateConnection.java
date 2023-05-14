package com.soccer.league.api;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateConnection {

	private final ApiKey apiKey;

	public RestTemplateConnection(ApiKey apiKey) {
		this.apiKey = apiKey;
	}

	public String standingsConnect(int leagueId) {

		String url = "https://api-football-v1.p.rapidapi.com/";
		int season = 2022;

		URI uri = UriComponentsBuilder.fromHttpUrl(url)
				.path("v3/standings")
				.queryParam("league", leagueId)
				.queryParam("season", season)
				.encode()
				.build()
				.toUri();

		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<Void> req = RequestEntity.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}

	//경기 일정
	public String fixturesConnect(int leagueId) throws IOException {

		String url = "https://api-football-v1.p.rapidapi.com/";
		int season = 2022;
		
		URI uri = UriComponentsBuilder.fromHttpUrl(url)
				.path("v3/fixtures")
				.queryParam("league", leagueId)
				.queryParam("season", season)
				.queryParam("from", "2023-05-06")
				.queryParam("to", "2023-05-21")
				.queryParam("timezone", "Asia/seoul")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<Void> req = RequestEntity.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();	
	}
}
