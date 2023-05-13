package com.soccer.league.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.league.dto.StandingDto;
import com.soccer.league.service.PremierLeagueService;

@RestController
@RequestMapping("/epl")
public class PremierLeagueController {

	private final PremierLeagueService premierLeagueService;
	
	public PremierLeagueController(PremierLeagueService premierLeagueService) {
		this.premierLeagueService = premierLeagueService;
	}
	
	@GetMapping("/{leagueId}")
	public List<StandingDto> getStandings(@PathVariable("leagueId") int leaguId ) throws IOException {
		
		List<StandingDto> standings = premierLeagueService.getStandings(leaguId);
		
		return standings;
	}
}
