package com.soccer.league.api;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Primary;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Primary
public class RestTemplateConnection implements HttpConnectionPolicy{

	private final ApiKey apiKey;
	private final RestTemplate restTemplate;

	//구단 순위
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

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}

	// 경기 일정
	public String lastFixturesConnect(int leagueId) throws IOException {

		String url = "https://api-football-v1.p.rapidapi.com/";
		int season = 2022;

		LocalDateTime time = LocalDateTime.now();

		String nowTime = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String lastTime = time.minusWeeks(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		URI uri = UriComponentsBuilder
				.fromHttpUrl(url)
				.path("v3/fixtures")
				.queryParam("league", leagueId)
				.queryParam("season", season)
				.queryParam("from", lastTime)//"2023-00-00"
				.queryParam("to", nowTime)//"2023-00-00"
				.queryParam("timezone", "Asia/seoul")
				.encode()
				.build()
				.toUri();

		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-RapidAPI-Key", apiKey.getApiKey())
				.header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
				.build();
		
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

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}

	public String topScorersConnect(int leagueId) {

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

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		return result.getBody();
	}
}
