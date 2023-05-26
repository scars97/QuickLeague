package com.soccer.league.config;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.soccer.league.dto.FixturesDto;

public class FixturesComparator implements Comparator<FixturesDto>{

	@Override
	public int compare(FixturesDto o1, FixturesDto o2) {
		
		String day1 = o1.getDate();
		String day2 = o2.getDate();
		
		int result = day1.compareTo(day2);
		
		return result;
	}

}
