package com.soccer.league.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.soccer.league.config.FixturesComparator;
import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.dto.TopScorersDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonParser {
	
	private final FixturesComparator fixturesComparator;

	/*
	 * 구단 순위 
	 */
	public List<StandingsDto> standingsParser(String result){
		
		JSONObject json = new JSONObject(result);
		
		JSONArray responseJson = json.getJSONArray("response");
		JSONObject intJson1 = (JSONObject) responseJson.get(0);
		JSONObject leagueJson = (JSONObject) intJson1.get("league");// league 선택
		JSONArray standingsJson = (JSONArray) leagueJson.get("standings");// standings 배열
		JSONArray intJson2 = (JSONArray) standingsJson.get(0);
	
		List<StandingsDto> standings = new ArrayList<>();
	
		// PL 구단 순위
		for (int i = 0; i < intJson2.length(); i++) {
			JSONObject intJson3 = (JSONObject) intJson2.get(i); // 순위,팀점수
			JSONObject teamJson = intJson3.getJSONObject("team"); // 팀명,팀로고
			JSONObject allJson = intJson3.getJSONObject("all"); // 경기수,성적
	
			StandingsDto standingsDto = new StandingsDto(intJson3, teamJson, allJson);
			standings.add(standingsDto);
		}
			
		return standings;
	}
	
	/*
	 * 지난 경기 
	 */
	public List<FixturesDto> lastFixturesParser(String result) throws ParseException{
		
		JSONObject obj = new JSONObject(result);

		JSONArray responseJson = obj.getJSONArray("response");

		List<FixturesDto> lastFixtures = new ArrayList<>();

		// 결과도출될거
		for (int i = 0; i < responseJson.length(); i++) {

			JSONObject intJson1 = (JSONObject) responseJson.get(i);
			// 경기 날짜
			JSONObject fixtureJson = (JSONObject) intJson1.get("fixture");
			// 경기 구단
			JSONObject teamsJson = (JSONObject) intJson1.get("teams");
			JSONObject homeJson = (JSONObject) teamsJson.get("home");
			JSONObject awayJson = (JSONObject) teamsJson.get("away");
			// 경기 결과,점수
			JSONObject goalsJson = (JSONObject) intJson1.get("goals");

			FixturesDto fixturesDto = new FixturesDto(fixtureJson, homeJson, awayJson, goalsJson);
			lastFixtures.add(fixturesDto);
		}
		
		//날짜 오름차순 정렬
		Collections.sort(lastFixtures, fixturesComparator);
		
		for (FixturesDto dateSort : lastFixtures) {
			List<String> dateResult = dateFormat(dateSort.getDate());
			dateSort.setDate(dateResult.get(0));
			dateSort.setTime(dateResult.get(1));
		}
		
		return lastFixtures;
	}
		
	/*
	 * 다음 경기
	 */
	public List<FixturesDto> nextFixturesParser(String result) throws ParseException{

		JSONObject obj = new JSONObject(result);

		JSONArray responseJson = obj.getJSONArray("response");

		List<FixturesDto> nextFixtures = new ArrayList<>();

		// 결과도출될거
		for (int i = 0; i < responseJson.length(); i++) {

			JSONObject intJson1 = (JSONObject) responseJson.get(i);
			// 경기 날짜
			JSONObject fixtureJson = (JSONObject) intJson1.get("fixture");
			// 경기 구단
			JSONObject teamsJson = (JSONObject) intJson1.get("teams");
			JSONObject homeJson = (JSONObject) teamsJson.get("home");
			JSONObject awayJson = (JSONObject) teamsJson.get("away");

			FixturesDto fixturesDto = new FixturesDto(fixtureJson, homeJson, awayJson);
			nextFixtures.add(fixturesDto);
		}
		return nextFixtures;
	}
	
	/*
	 * 선수 순위 
	 */
	public List<TopScorersDto> topScorersParser(String result){
		
		JSONObject obj = new JSONObject(result);

		JSONArray responseJson = obj.getJSONArray("response");
		
		List<TopScorersDto> topScorers = new ArrayList<>();
		
		for (int i = 0; i < responseJson.length(); i++) {
			JSONObject intJson = (JSONObject) responseJson.get(i);
			JSONObject playerJson = (JSONObject) intJson.get("player");
			
			JSONArray statisticsJson = intJson.getJSONArray("statistics");
			JSONObject intJson2 = (JSONObject) statisticsJson.get(0);
			JSONObject teamJson = (JSONObject) intJson2.get("team");
			JSONObject gamesJson = (JSONObject) intJson2.get("games");
			JSONObject goalsJson = (JSONObject) intJson2.get("goals");
		
			TopScorersDto topScorersDto = new TopScorersDto(playerJson,teamJson,gamesJson,goalsJson);
			topScorers.add(topScorersDto);
		}
		
		return topScorers;
	}
	
	
	/*
	 * 데이터 포맷 
	 */
	private List<String> dateFormat(String date) throws ParseException {

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
