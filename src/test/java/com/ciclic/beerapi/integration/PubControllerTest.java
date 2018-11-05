package com.ciclic.beerapi.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ciclic.beerapi.BeerApiApplication;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.repository.BeerStyleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeerApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-dev.properties")
public class PubControllerTest {

	@LocalServerPort
	private Integer port;

	@Autowired
	private BeerStyleRepository beerStyleRepository;

	private BeerStyle bStyle;
	@Before
	public void createBeerStyle() {
		bStyle = beerStyleRepository.insert(new BeerStyle("test", -1.0, 1.0));
	}
	
	@After
	public void deleteBeerStyle() {
		beerStyleRepository.deleteById(bStyle.getId());
	}
	
	@Test
	public void givenTemperature_thenStatusReceivedIsOK() throws ClientProtocolException, IOException {
		// Given
		HttpGet request = new HttpGet("http://localhost:" + port + "/api/v1/beers/temperature/1");

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_OK));
	}
	
	@Test
	public void givenTemperatureWithNoBeerStyle_thenStatusReceivedIsNotFound() throws ClientProtocolException, IOException {
		beerStyleRepository.deleteAll();
		// Given
		HttpGet request = new HttpGet("http://localhost:" + port + "/api/v1/beers/temperature/1");

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NOT_FOUND));
	}

}