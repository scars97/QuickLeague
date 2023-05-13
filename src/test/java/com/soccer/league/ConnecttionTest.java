package com.soccer.league;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SpringBootTest
public class ConnecttionTest {
	
	@Test
	void connection() throws IOException {
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url("https://api-football-v1.p.rapidapi.com/v3/standings?league=39&season=2022").get()
				.addHeader("X-RapidAPI-Key", "00dff05ec1msh5a3fcefada34491p12c058jsn7a0d02c8ef99")
				.addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com").build();

		Response response = client.newCall(request).execute();
		
		String result = response.body().string();
		
		if (response.isSuccessful()) {
			assertThat(result).isNotNull();
		}
		
	}
}
