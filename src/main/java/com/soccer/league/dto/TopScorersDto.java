package com.soccer.league.dto;

import org.json.JSONObject;

import lombok.Getter;

@Getter
public class TopScorersDto {

	private String playerName;
	private String teamName;
	private int games;
	private int goals;
	
	public TopScorersDto(JSONObject playerJson, 
			JSONObject teamJson, 
			JSONObject gamesJson, 
			JSONObject goalsJson) {
		this.playerName = playerJson.getString("name");
		this.teamName = teamJson.getString("name");
		this.games = gamesJson.getInt("appearences");
		this.goals = goalsJson.getInt("total");
		
	}
}
