package com.soccer.league.controller.restcontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.dto.TopScorersDto;
import com.soccer.league.service.LeagueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/epl")
public class PremierLeagueRestController {

	private final LeagueService leagueService;
	
	@GetMapping("/standings/{id}")
	public List<StandingsDto> getStandings(@PathVariable("id") int leaguId ) throws IOException {
		
		List<StandingsDto> standings = leagueService.getStandings(leaguId);
		
		return standings;
	}
	
	@GetMapping("/lastfixtures/{id}")
	public List<FixturesDto> getLastFixtures(@PathVariable("id") int leagueId) throws IOException, ParseException{
		
		List<FixturesDto> lastFixtures = leagueService.getLastFixtures(leagueId);
		
		return lastFixtures;
	}
	
	@GetMapping("/nextfixtures/{id}")
	public List<FixturesDto> getNextFixtures(@PathVariable("id") int leagueId) throws IOException, ParseException{
		
		List<FixturesDto> nextFixtures = leagueService.getNextFixtures(leagueId);
		
		return nextFixtures;
	}
	
	@GetMapping("/topscorers/{id}")
	public List<TopScorersDto> getTopScorers(@PathVariable("id") int leagueId) throws IOException{
		
		List<TopScorersDto> topScorers = leagueService.getTopScorers(leagueId);
		
		return topScorers;
	}
}
