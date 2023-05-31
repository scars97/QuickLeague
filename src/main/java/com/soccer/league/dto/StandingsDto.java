package com.soccer.league.dto;

import org.json.JSONObject;

import lombok.Getter;
import lombok.ToString;

@Getter
public class StandingsDto {

	private int rank;
	private String teamName;
	private int points;
	private int played;
	private int win;
	private int draw;
	private int lose;
	
	public StandingsDto(JSONObject intJson, JSONObject teamJson, JSONObject allJson) {
		this.rank = intJson.getInt("rank");
		this.teamName = teamJson.getString("name");
		this.points = intJson.getInt("points");
		this.played = allJson.getInt("played");
		this.win = allJson.getInt("win");
		this.draw = allJson.getInt("draw");
		this.lose = allJson.getInt("lose");
	}
}
