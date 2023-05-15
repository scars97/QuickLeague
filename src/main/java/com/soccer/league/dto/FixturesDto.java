package com.soccer.league.dto;

import org.json.JSONObject;

//import org.json.simple.JSONObject;

import lombok.Getter;

@Getter
public class FixturesDto {

	//경기 날짜
	private String date;
	
	//홈팀 정보
	private String homeTeam;
//	private String homeLogo;
	
	//원정팀 정보
	private	String awayTeam;
//	private String awayLogo;
	
	//경기 점수,결과
	private Long homeResult;
	private Long awayResult;
	
	//지난 경기
	public FixturesDto( 
			JSONObject fixtureJson, 
			JSONObject homeJson,
			JSONObject awayJson,
			JSONObject goalsJson) {		
		this.date = fixtureJson.getString("date");
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");	
		this.homeResult = goalsJson.getLong("home");
		this.awayResult = goalsJson.getLong("away");			
		
	}
	
	//다음 경기
	public FixturesDto(JSONObject fixtureJson, 
			JSONObject homeJson,
			JSONObject awayJson) {
		this.date = fixtureJson.getString("date");
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");
	}
	

}
