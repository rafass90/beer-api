package com.ciclic.beerapi.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ciclic.beerapi.BeerApiApplication;
import com.ciclic.beerapi.domain.vo.BeerStyle;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeerApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-dev.properties")
public class PubControllerTest extends ControllerTest{

	
	@Test
	public void givenTemperatureWithPlaylist_thenStatusReceivedIsOK() throws ClientProtocolException, IOException {
		// Given
		deleteAllBeerStyles();
		BeerStyle bStyle = insertTest("Rock");
		HttpGet request = new HttpGet("http://localhost:" + port + "/api/v1/beers/temperature/1");

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_OK));
		deleteTestById(bStyle.getId());
	}
	
	@Test
	public void givenTemperature_thenStatusReceivedIsOK() throws ClientProtocolException, IOException {
		// Given
		BeerStyle bStyle = insertTest("test");
		HttpGet request = new HttpGet("http://localhost:" + port + "/api/v1/beers/temperature/1");

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_OK));
		deleteTestById(bStyle.getId());
	}
	
	@Test
	public void givenTemperatureWithNoBeerStyle_thenStatusReceivedIsNotFound() throws ClientProtocolException, IOException {
		// Given
		deleteAllBeerStyles();
		HttpGet request = new HttpGet("http://localhost:" + port + "/api/v1/beers/temperature/1");

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NOT_FOUND));
	}
	
	@Test
	public void givenTemperatureWithNoPlaylist_thenStatusReceivedIsOK() throws ClientProtocolException, IOException {
		deleteAllBeerStyles();
		BeerStyle bStyle = insertTest("tempnoplaystname111");
		// Given
		HttpGet request = new HttpGet("http://localhost:" + port + "/api/v1/beers/temperature/1");

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_OK));
		deleteTestById(bStyle.getId());
	}

}