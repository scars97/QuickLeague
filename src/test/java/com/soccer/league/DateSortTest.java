package com.soccer.league;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soccer.league.api.RestTemplateConnection;
import com.soccer.league.dto.FixturesDto;
import com.soccer.league.service.LeagueService;

@SpringBootTest
public class DateSortTest {

	@Autowired
	LeagueService leagueService;
	@Autowired
	RestTemplateConnection restTemplateConnection;

	void test() throws IOException, ParseException {
		int leagueId = 39;
		
		List<FixturesDto> lastFixtures = leagueService.getLastFixtures(leagueId);
		
		
		Collections.sort(lastFixtures, null);
	}
	
	static class TestComparator implements Comparator<FixturesDto>{
		
		public int compare(FixturesDto fix1, FixturesDto fix2) {
			
		}
	}
	
}
