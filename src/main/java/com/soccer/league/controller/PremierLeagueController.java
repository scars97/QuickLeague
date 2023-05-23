package com.soccer.league.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.league.dto.FixturesDto;
import com.soccer.league.dto.StandingsDto;
import com.soccer.league.dto.TopScorersDto;
import com.soccer.league.service.LeagueService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/epl")
public class PremierLeagueController {

	private final LeagueService leagueService;

	@GetMapping("/standings/{id}")
	public String getStandings(@PathVariable("id") int leaguId, Model model) throws IOException {

		List<StandingsDto> standings = leagueService.getStandings(leaguId);

		model.addAttribute("standings",standings);
		return "epl/standings";
	}
	
	@GetMapping("/fixtures/{id}")
	public String getFixtures(@PathVariable("id") int leagueId, Model model) throws IOException, ParseException{
		
		List<FixturesDto> lastFixtures = leagueService.getLastFixtures(leagueId);
		//List<FixturesDto> nextFixtures = leagueService.getNextFixtures(leagueId);
		
		model.addAttribute("lastfixtures",lastFixtures);
		//model.addAttribute("nextfixtures",nextFixtures);
		return "epl/fixtures";
	}
	
	@GetMapping("/topscorers/{id}")
	public String getTopScorers(@PathVariable("id") int leagueId, Model model) {
		
		List<TopScorersDto> topscorers = leagueService.getTopScorers(leagueId);
		
		model.addAttribute("topscorers", topscorers);
		return "epl/topscorers";
	}
}
