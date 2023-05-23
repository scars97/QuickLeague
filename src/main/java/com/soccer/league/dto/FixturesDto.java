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
@Component
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
//		this.time = dateFormat(fixtureJson).get(1);
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");
		String result = goalsJson.get("home").toString();
//		int intResult = Integer.parseInt(result);
		this.homeResult = result;
		String result2 = goalsJson.get("away").toString();
//		int intResult2 = Integer.parseInt(result2);
		this.awayResult = result2;		
		
	}
	
	//다음 경기
	public FixturesDto(JSONObject fixtureJson, 
			JSONObject homeJson,
			JSONObject awayJson) throws ParseException {
		this.date = fixtureJson.getString("date");
//		this.time = dateFormat(fixtureJson).get(1);
		this.homeTeam = homeJson.getString("name");
		this.awayTeam = awayJson.getString("name");
	}
	
	//날짜 포맷
	public List<String> dateFormat(String date) throws ParseException {
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat newDtFormat = new SimpleDateFormat("MM.dd. (E)");
		SimpleDateFormat newTmFormat = new SimpleDateFormat("HH:mm");
		// String 타입을 Date 타입으로 변환
		Date formatDate = dtFormat.parse(date);
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환
		String returnDate = newDtFormat.format(formatDate);
		String returnTime = newTmFormat.format(formatDate);
		
		List<String> returnList = new ArrayList<>();
		returnList.add(returnDate);
		returnList.add(returnTime);
		
		return returnList;
	}

}
