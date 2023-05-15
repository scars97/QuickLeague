package com.soccer.league.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

//import org.json.simple.JSONObject;

import lombok.Getter;

@Getter
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
	private Long homeResult;
	private Long awayResult;
	
	//지난 경기
	public FixturesDto( 
			JSONObject fixtureJson, 
			JSONObject homeJson,
			JSONObject awayJson,
			JSONObject goalsJson) throws ParseException {		
		this.date = dateFormat(fixtureJson).get(0);
		this.time = dateFormat(fixtureJson).get(1);
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");	
		this.homeResult = goalsJson.getLong("home");
		this.awayResult = goalsJson.getLong("away");			
		
	}
	
	//다음 경기
	public FixturesDto(JSONObject fixtureJson, 
			JSONObject homeJson,
			JSONObject awayJson) throws ParseException {
		this.date = dateFormat(fixtureJson).get(0);
		this.time = dateFormat(fixtureJson).get(1);
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");
	}
	
	private List<String> dateFormat(JSONObject fixtureJson) throws ParseException {
		String strDate = fixtureJson.getString("date");
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat newDtFormat = new SimpleDateFormat("MM.dd. (E)");
		SimpleDateFormat newDtFormat2 = new SimpleDateFormat("HH:mm");
		// String 타입을 Date 타입으로 변환
		Date formatDate = dtFormat.parse(strDate);
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환
		String returnDate = newDtFormat.format(formatDate);
		String returnDate2 = newDtFormat2.format(formatDate);
		
		List<String> returnList = new ArrayList<>();
		returnList.add(returnDate);
		returnList.add(returnDate2);
		
		return returnList;
	}

}
