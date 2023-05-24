package com.soccer.league.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.dto.TopScorersDto;

public interface LeagueService {

	List<StandingsDto> getStandings(int leagueId) throws IOException;
	
	List<FixturesDto> getLastFixtures(int leagueId) throws IOException, ParseException;
	
	List<FixturesDto> getNextFixtures(int leagueId) throws IOException, ParseException;
	
	List<TopScorersDto> getTopScorers(int leagueId) throws IOException;

}
