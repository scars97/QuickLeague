package com.soccer.league.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.league.dto.PremierLeagueDto;
import com.soccer.league.service.PremierLeagueService;

@RestController
@RequestMapping("/epl")
public class PremierLeagueController {

	private final PremierLeagueService premierLeagueService;
	
	public PremierLeagueController(PremierLeagueService premierLeagueService) {
		this.premierLeagueService = premierLeagueService;
	}
	
	@GetMapping("/standing")
	public List<PremierLeagueDto> getStandings() throws IOException {
		
		List<PremierLeagueDto> standings = premierLeagueService.getStandings();
		
		return standings;
	}
}
