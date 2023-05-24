package com.soccer.league.date;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soccer.league.api.RestTemplateConnection;
import com.soccer.league.dto.FixturesDto;
import com.soccer.league.service.LeagueServiceImpl;

@SpringBootTest
public class DateSortTest {

	@Autowired
	LeagueServiceImpl leagueService;
	@Autowired
	RestTemplateConnection restTemplateConnection;

	@Test
	void test() throws IOException, ParseException {
		int leagueId = 39;
		
		List<FixturesDto> lastFixtures = leagueService.getLastFixtures(leagueId);
		
		TestComparator tc = new TestComparator();
		
		//오름차순
		Collections.sort(lastFixtures, tc);
		for (FixturesDto fixturesDto : lastFixtures) {
			System.out.println("date: " + fixturesDto.getDate());
			System.out.println("home: " + fixturesDto.getHomeTeam());
			System.out.println("away: " + fixturesDto.getAwayTeam() + "\n");	

		}
	}
	
	static class TestComparator implements Comparator<FixturesDto>{

		@Override
		public int compare(FixturesDto o1, FixturesDto o2) {
			
			String day1 = o1.getDate();
			String day2 = o2.getDate();
			
			int result = day1.compareTo(day2);
			
			return result;
		}
		
		
	}
	
}
