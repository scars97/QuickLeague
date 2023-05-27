package com.soccer.league.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.soccer.league.api.ApiKey;
import com.soccer.league.api.HttpConnectionPolicy;
import com.soccer.league.api.OkHttpConnection;
import com.soccer.league.api.RestTemplateConnection;
import com.soccer.league.service.LeagueService;
import com.soccer.league.service.LeagueServiceImpl;

import okhttp3.OkHttpClient;

@Configuration
public class AppConfig {
	
	@Bean
	public LeagueService leagueService() {
		return new LeagueServiceImpl(httpConnectionPolicy(), fixturesComparator(), dateFormatConfig());
	}
	
	@Bean //날짜 순서 정렬
	public FixturesComparator fixturesComparator() {
		return new FixturesComparator();
	}
	
	@Bean // 날짜 폼 정렬
	public DateFormatConfig dateFormatConfig() {
		return new DateFormatConfig();
	}

	@Bean // Connection 정책
	public HttpConnectionPolicy httpConnectionPolicy() {
		return new RestTemplateConnection(apiKey(), restTemplate());
//		return new OkHttpConnection(apiKey(), okHttpClient());
	}
	
	@Bean
	public ApiKey apiKey() {
		return new ApiKey();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
	}
}
