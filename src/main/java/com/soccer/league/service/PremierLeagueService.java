package com.soccer.league.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.soccer.league.api.RestTemplateConnection;
import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;

@Service
public class PremierLeagueService {
	
	private final RestTemplateConnection restConnection;
	
	//생성자 주입
	public PremierLeagueService(RestTemplateConnection restConnection) {
		this.restConnection = restConnection;
	}

	public List<StandingsDto> getStandings(int leagueId) throws IOException{

		// API Connection
		String result = restConnection.standingsConnect(leagueId);
		
		// Json 변환
		JSONObject json = new JSONObject(result);
			
		JSONArray responseJson = json.getJSONArray("response");
		JSONObject intJson1 = (JSONObject) responseJson.get(0);
		JSONObject leagueJson = (JSONObject) intJson1.get("league");//league 선택
		JSONArray standingsJson = (JSONArray) leagueJson.get("standings");//standings 배열
		JSONArray intJson2 = (JSONArray) standingsJson.get(0);
			
		List<StandingsDto> standings = new ArrayList<>();
		
		//PL 구단 순위
		for (int i = 0; i < intJson2.length(); i++) {
			JSONObject intJson3 = (JSONObject) intJson2.get(i); // 순위,팀점수
			JSONObject teamJson = intJson3.getJSONObject("team"); // 팀명,팀로고
			JSONObject allJson = intJson3.getJSONObject("all"); // 경기수,성적
				
			StandingsDto standingsDto = new StandingsDto(intJson3, teamJson, allJson);
			standings.add(standingsDto);
		}
			
		return standings; 
	}
	
	public List<FixturesDto> getLastFixtures(int leagueId) throws IOException{
		
		String result = restConnection.lastFixturesConnect(leagueId);
		
		JSONObject obj = new JSONObject(result);
		
		JSONArray responseJson = obj.getJSONArray("response");
		
		List<FixturesDto> lastFixtures = new ArrayList<>();
		
		//결과도출될거
		for (int i = 0; i < responseJson.length(); i++) {
			
			JSONObject intJson1 = (JSONObject) responseJson.get(i);
			//경기 날짜
			JSONObject fixtureJson = (JSONObject) intJson1.get("fixture");
			//경기 구단
			JSONObject teamsJson = (JSONObject) intJson1.get("teams");
			JSONObject homeJson = (JSONObject) teamsJson.get("home");
			JSONObject awayJson = (JSONObject) teamsJson.get("away");
			//경기 결과,점수
			JSONObject goalsJson = (JSONObject) intJson1.get("goals");
				
			FixturesDto fixturesDto = new FixturesDto(fixtureJson, homeJson, awayJson, goalsJson);
			lastFixtures.add(fixturesDto);				
		}		
		return lastFixtures;	
	}
	
public List<FixturesDto> getNextFixtures(int leagueId) throws IOException{
		
		String result = restConnection.nextFixturesConnect(leagueId);
		
		JSONObject obj = new JSONObject(result);
		
		JSONArray responseJson = obj.getJSONArray("response");
		
		List<FixturesDto> nextFixtures = new ArrayList<>();
		
		//결과도출될거
		for (int i = 0; i < responseJson.length(); i++) {
			
			JSONObject intJson1 = (JSONObject) responseJson.get(i);
			//경기 날짜
			JSONObject fixtureJson = (JSONObject) intJson1.get("fixture");
			//경기 구단
			JSONObject teamsJson = (JSONObject) intJson1.get("teams");
			JSONObject homeJson = (JSONObject) teamsJson.get("home");
			JSONObject awayJson = (JSONObject) teamsJson.get("away");
				
			FixturesDto fixturesDto = new FixturesDto(fixtureJson, homeJson, awayJson);
			nextFixtures.add(fixturesDto);				
		}		
		return nextFixtures;	
	}
}