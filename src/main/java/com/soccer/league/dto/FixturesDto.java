package com.soccer.league.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

//import org.json.simple.JSONObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FixturesDto {

	//경기 날짜, 시간
	private String date;
	private String time;
	
	//홈팀 정보
	private String homeTeam;
//	private String homeLogo;
	
	//원정팀 정보
	private	String awayTeam;
//	private String awayLogo;
	
	//경기 점수,결과
	private String homeResult;
	private String awayResult;
	
	//지난 경기
	public FixturesDto( 
			JSONObject fixtureJson, 
			JSONObject homeJson,
			JSONObject awayJson,
			JSONObject goalsJson) throws ParseException {		
		this.date = fixtureJson.getString("date");
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");
		String result = goalsJson.get("home").toString();
		this.homeResult = result;
		String result2 = goalsJson.get("away").toString();
		this.awayResult = result2;		
		
	}
	
	//다음 경기
	public FixturesDto(JSONObject fixtureJson, 
			JSONObject homeJson,
			JSONObject awayJson) throws ParseException {
		this.date = fixtureJson.getString("date");
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");
	}

}
