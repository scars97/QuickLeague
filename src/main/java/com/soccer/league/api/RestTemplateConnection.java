package com.soccer.league.api;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestTemplateConnection {

	private final ApiKey apiKey;

	public String standingsConnect(int leagueId) {

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

		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
		
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}

	// 경기 일정
	public String lastFixturesConnect(int leagueId) throws IOException {

		String url = "https://api-football-v1.p.rapidapi.com/";
		int season = 2022;

		LocalDateTime time = LocalDateTime.now();

		String nowTime = time.plusWeeks(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastTime = time.minusWeeks(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		URI uri = UriComponentsBuilder
				.fromHttpUrl(url)
				.path("v3/fixtures")
				.queryParam("league", leagueId)
				.queryParam("season", season)
				.queryParam("from", "2023-05-14")
				.queryParam("to", "2023-05-21")
				.queryParam("timezone", "Asia/seoul")
				.encode()
				.build()
				.toUri();

		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
		
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}

	public String nextFixturesConnect(int leagueId) throws IOException {

		String url = "https://api-football-v1.p.rapidapi.com/";

		URI uri = UriComponentsBuilder
				.fromHttpUrl(url)
				.path("v3/fixtures")
				.queryParam("league", leagueId)
				.queryParam("next", 10)
				.queryParam("timezone", "Asia/seoul")
				.encode()
				.build()
				.toUri();

		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
		
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}

	public String topScorers(int leagueId) {

		String url = "https://api-football-v1.p.rapidapi.com/";
		String season = "2022";
		
		URI uri = UriComponentsBuilder
				.fromHttpUrl(url)
				.path("v3/players/topscorers")
				.queryParam("league", leagueId)
				.queryParam("season", season)
				.encode()
				.build()
				.toUri();

		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
		
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}
}
