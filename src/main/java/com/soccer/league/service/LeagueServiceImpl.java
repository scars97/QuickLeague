package com.soccer.league.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.stereotype.Service;

import com.soccer.league.api.HttpConnectionPolicy;
import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.dto.TopScorersDto;
import com.soccer.league.json.JsonParser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService{

	private final HttpConnectionPolicy connection;
	private final JsonParser parser;

	//구단 순위
	@Override
	public List<StandingsDto> getStandings(int leagueId) throws IOException {

		// API Connection
		String result = connection.standingsConnect(leagueId);
			
		// Json 변환
		List<StandingsDto> standings = parser.standingsParser(result);
		
		return standings;
	}

	//지난 경기 일정
	@Override
	public List<FixturesDto> getLastFixtures(int leagueId) throws IOException, ParseException {

		String result = connection.lastFixturesConnect(leagueId);

		List<FixturesDto> lastFixtures = parser.lastFixturesParser(result);
		
		return lastFixtures;
	}

	//다음 경기 일정
	@Override
	public List<FixturesDto> getNextFixtures(int leagueId) throws IOException, ParseException {

		String result = connection.nextFixturesConnect(leagueId);

		List<FixturesDto> nextFixtures = parser.nextFixturesParser(result);
		
		return nextFixtures;
	}
	
	//선수 순위
	@Override
	public List<TopScorersDto> getTopScorers(int leagueId) throws IOException{
		
		String result = connection.topScorersConnect(leagueId);
		
		List<TopScorersDto> topScorers = parser.topScorersParser(result);
		
		return topScorers;
	}

}
