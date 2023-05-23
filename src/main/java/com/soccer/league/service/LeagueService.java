package com.soccer.league.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.soccer.league.api.OkHttpConnection;
import com.soccer.league.api.RestTemplateConnection;
import com.soccer.league.config.DateFormatConfig;
import com.soccer.league.config.FixturesComparator;
import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.dto.TopScorersDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeagueService {

	private final RestTemplateConnection restConnection;
	private final FixturesComparator fixturesComparator;
	private final DateFormatConfig dateFormatConfig;
//	private final OkHttpConnection okHttpConnection;

	//구단 순위
	public List<StandingsDto> getStandings(int leagueId) throws IOException {

		// API Connection
		String result = restConnection.standingsConnect(leagueId);
			
		// Json 변환
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

	//지난 경기 일정
	public List<FixturesDto> getLastFixtures(int leagueId) throws IOException, ParseException {

		String result = restConnection.lastFixturesConnect(leagueId);

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
			List<String> dateResult = dateFormatConfig.dateFormat(dateSort.getDate());
			dateSort.setDate(dateResult.get(0));
			dateSort.setTime(dateResult.get(1));
		}
		
		return lastFixtures;
	}

	//다음 경기 일정
	public List<FixturesDto> getNextFixtures(int leagueId) throws IOException, ParseException {

		String result = restConnection.nextFixturesConnect(leagueId);

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
	
	//선수 순위
	public List<TopScorersDto> getTopScorers(int leagueId){
		
		String result = restConnection.topScorers(leagueId);
		
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
}
