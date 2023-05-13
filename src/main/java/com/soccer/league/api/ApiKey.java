package com.soccer.league.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKey {

	@Value("${apiFootball-key}")
	private String apiKey;
	
	public String getApiKey() {
		return apiKey;
	}
}
