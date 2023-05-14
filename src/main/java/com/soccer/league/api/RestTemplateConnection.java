package com.soccer.league.api;

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
	
	public String restConnection(int leagueId) {
		
		String url = "https://api-football-v1.p.rapidapi.com/";
		int season = 2022;
		
		URI uri = UriComponentsBuilder
				.fromHttpUrl(url)
				.path("v3/standings")
				.queryParam("league", leagueId)
				.queryParam("season", season)
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
		
		ResponseEntity<String> result = restTemplate.exchange(req, String.class);
		
		return result.getBody();
	}
}
