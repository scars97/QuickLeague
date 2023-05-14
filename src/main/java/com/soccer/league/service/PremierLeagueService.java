package com.soccer.league.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.soccer.league.api.Connection;
import com.soccer.league.api.RestTemplateConnection;
import com.soccer.league.dto.StandingDto;

@Service
public class PremierLeagueService {
	
	private final RestTemplateConnection restConnection;
	
	//생성자 주입
	public PremierLeagueService(RestTemplateConnection restConnection) {
		this.restConnection = restConnection;
	}

	public List<StandingDto> getStandings(int leagueId) throws IOException{

		// API Connection
		String result = restConnection.restConnection(leagueId);
		
		// Json 변환
		JSONObject json = new JSONObject(result);
			
		JSONArray responseJson = json.getJSONArray("response");
		JSONObject intJson = (JSONObject) responseJson.get(0);
		JSONObject leagueJson = (JSONObject) intJson.get("league");//league 선택
		JSONArray standingsJson = (JSONArray) leagueJson.get("standings");//standings 배열
		JSONArray intJson2 = (JSONArray) standingsJson.get(0);
			
		List<StandingDto> standings = new ArrayList<>();
		
		//PL 구단 순위
		for (int i = 0; i < intJson2.length(); i++) {
			JSONObject intJson3 = (JSONObject) intJson2.get(i); // 순위,팀점수
			JSONObject teamJson = intJson3.getJSONObject("team"); // 팀명,팀로고
			JSONObject allJson = intJson3.getJSONObject("all"); // 경기수,성적
				
			StandingDto preDto = new StandingDto(intJson3, teamJson, allJson);
			standings.add(preDto);
		}
			
		return standings; 
	}
}
