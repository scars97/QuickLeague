package com.soccer.league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.soccer.league.config.HttpConnectionConfig;

@Import(HttpConnectionConfig.class)
@SpringBootApplication
public class SoccerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerApplication.class, args);
	}

}
