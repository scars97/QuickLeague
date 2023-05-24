package com.soccer.league.api;

import java.io.IOException;

public interface HttpConnectionPolicy {

	String standingsConnect(int leagueId) throws IOException;
	
	String lastFixturesConnect(int leagueId) throws IOException;
	
	String nextFixturesConnect(int leagueId) throws IOException;
	
	String topScorersConnect(int leagueId) throws IOException;
}
