package com.soccer.league.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.soccer.league.api.ApiKey;
import com.soccer.league.api.HttpConnectionPolicy;
import com.soccer.league.api.OkHttpConnection;
import com.soccer.league.api.RestTemplateConnection;

import okhttp3.OkHttpClient;

@Configuration
public class HttpConnectionConfig {

	@Bean
	public HttpConnectionPolicy httpConnectionPolicy() {
		System.out.println("HttpConnectionConfig.RestTemplate을 사용중이야");
		return new RestTemplateConnection(new ApiKey(), restTemplate());
//		return new OkHttpConnection(new ApiKey(), new OkHttpClient());
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
