package com.soccer.league.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.soccer.league.api.ApiKey;
import com.soccer.league.api.OkHttpConnection;
import com.soccer.league.api.RestTemplateConnection;
import com.soccer.league.config.HttpConnectionConfig;

import okhttp3.OkHttpClient;

@SpringBootTest
public class ConfigurationSingletonTest {

	@Test
	void restConfigTset() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(HttpConnectionConfig.class);
		
		RestTemplateConnection restConnect = ac.getBean("httpConnectionPolicy", RestTemplateConnection.class);
		ApiKey apiKey = ac.getBean("apiKey", ApiKey.class);
		RestTemplate restTemplate = ac.getBean("restTemplate", RestTemplate.class);
		
		ApiKey apiKey1 = restConnect.getApiKey();
		RestTemplate restTemplate1 = restConnect.getRestTemplate();
		
		System.out.println("Bean-ApiKey: " +apiKey);
		System.out.println("restConnect-ApiKey: " +apiKey1);
		System.out.println("Bean-restTemplate: " +restTemplate);
		System.out.println("restConnect-restTemplate1: " +restTemplate1);
		
		assertThat(restConnect.getApiKey()).isSameAs(apiKey);
		assertThat(restConnect.getRestTemplate()).isSameAs(restTemplate);
	}
	
	//@Test
	void okHttpConfigTset() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(HttpConnectionConfig.class);
		
		OkHttpConnection okConnect = ac.getBean("httpConnectionPolicy", OkHttpConnection.class);
		ApiKey apiKey = ac.getBean("apiKey", ApiKey.class);
		OkHttpClient okHttpClient = ac.getBean("okHttpClient", OkHttpClient.class);
		
		ApiKey apiKey1 = okConnect.getApiKey();
		OkHttpClient okHttpClient1 = okConnect.getOkHttpClient();
		
		System.out.println("Bean-ApiKey: " +apiKey);
		System.out.println("okConnect-ApiKey: " +apiKey1);
		System.out.println("Bean-okHttpClient: " +okHttpClient);
		System.out.println("okConnect-okHttpClient1: " +okHttpClient1);
		
		assertThat(apiKey.getApiKey()).isSameAs(apiKey);
		assertThat(okConnect.getOkHttpClient()).isSameAs(okHttpClient);
	}
}
