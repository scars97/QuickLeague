package com.soccer.league;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.soccer.league.api.ApiKey;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SpringBootTest
public class ConnecttionTest {
	
	@Autowired
	ApiKey apiKey;
	
	@Test
	void connection() throws IOException {
		
		String url = "https://api-football-v1.p.rapidapi.com/";
		int season = 2022;
		
		URI uri = UriComponentsBuilder.fromHttpUrl(url)
				.path("v3/fixtures")
				.queryParam("league", 39)
				.queryParam("season", season)
				.queryParam("from", "2023-05-06")
				.queryParam("to", "2023-05-14")
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
		

		assertThat(result).isNotNull();
		
		
	}
}
