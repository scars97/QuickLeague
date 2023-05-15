package com.soccer.league.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.service.PremierLeagueService;

@RestController
@RequestMapping("/rest/epl")
public class PremierLeagueRestController {

	private final PremierLeagueService premierLeagueService;
	
	public PremierLeagueRestController(PremierLeagueService premierLeagueService) {
		this.premierLeagueService = premierLeagueService;
	}
	
	@GetMapping("/standings/{leagueId}")
	public List<StandingsDto> getStandings(@PathVariable("leagueId") int leaguId ) throws IOException {
		
		List<StandingsDto> standings = premierLeagueService.getStandings(leaguId);
		
		return standings;
	}
	
	@GetMapping("/lastfixtures/{leagueId}")
	public List<FixturesDto> getLastFixtures(@PathVariable("leagueId") int leagueId) throws IOException, ParseException{
		
		List<FixturesDto> lastFixtures = premierLeagueService.getLastFixtures(leagueId);
		
		return lastFixtures;
	}
	
	@GetMapping("/nextfixtures/{leagueId}")
	public List<FixturesDto> getNextFixtures(@PathVariable("leagueId") int leagueId) throws IOException{
		
		List<FixturesDto> nextFixtures = premierLeagueService.getNextFixtures(leagueId);
		
		return nextFixtures;
	}
}
