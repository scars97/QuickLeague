package com.soccer.league.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soccer.league.dto.StandingsDto;
import com.soccer.league.service.PremierLeagueService;

@Controller
@RequestMapping("/epl")
public class PremierLeagueController {

	private final PremierLeagueService premierLeagueService;

	public PremierLeagueController(PremierLeagueService premierLeagueService) {
		this.premierLeagueService = premierLeagueService;
	}

	@GetMapping("/standings/{id}")
	public String getStandings(@PathVariable("id") int leaguId, Model model) throws IOException {

		List<StandingsDto> standings = premierLeagueService.getStandings(leaguId);

		model.addAttribute("standings",standings);
		return "epl/standings";
	}
}
