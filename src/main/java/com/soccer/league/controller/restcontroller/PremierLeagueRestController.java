package com.soccer.league.controller.restcontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.dto.TopScorersDto;
import com.soccer.league.service.PremierLeagueService;

@RestController
@RequestMapping("/rest/epl")
public class PremierLeagueRestController {

	private final PremierLeagueService premierLeagueService;
	
	public PremierLeagueRestController(PremierLeagueService premierLeagueService) {
		this.premierLeagueService = premierLeagueService;
	}
	
	@GetMapping("/standings/{id}")
	public List<StandingsDto> getStandings(@PathVariable("id") int leaguId ) throws IOException {
		
		List<StandingsDto> standings = premierLeagueService.getStandings(leaguId);
		
		return standings;
	}
	
	@GetMapping("/lastfixtures/{id}")
	public List<FixturesDto> getLastFixtures(@PathVariable("id") int leagueId) throws IOException, ParseException{
		
		List<FixturesDto> lastFixtures = premierLeagueService.getLastFixtures(leagueId);
		
		return lastFixtures;
	}
	
	@GetMapping("/nextfixtures/{id}")
	public List<FixturesDto> getNextFixtures(@PathVariable("id") int leagueId) throws IOException, ParseException{
		
		List<FixturesDto> nextFixtures = premierLeagueService.getNextFixtures(leagueId);
		
		return nextFixtures;
	}
	
	@GetMapping("/topscorers/{id}")
	public List<TopScorersDto> getTopScorers(@PathVariable("id") int leagueId){
		
		List<TopScorersDto> topScorers = premierLeagueService.getTopScorers(leagueId);
		
		return topScorers;
	}
}
