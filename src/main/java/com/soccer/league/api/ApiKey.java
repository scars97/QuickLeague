package com.soccer.league.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
public class ApiKey {

	@Value("${apiFootball-key}")
	private String apiKey;
	
	@Value("${apiFootball-uri}")
	private String apiUri;
	
	@Value("${apiFootball-host}")
	private String apiHost;
}
