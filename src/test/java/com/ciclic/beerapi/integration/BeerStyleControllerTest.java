package com.ciclic.beerapi.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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
public class BeerStyleControllerTest{
    
	@LocalServerPort
    private Integer port;

    @Autowired
    private BeerStyleRepository beerStyleRepository;

	@Test
	public void givenBeerStyleToAdd_thenStatusCreatedIsReceived() throws ClientProtocolException, IOException {
		// Given
		HttpPost request = new HttpPost("http://localhost:"+port+"/api/v1/admin/beers");

		StringEntity entity = new StringEntity("{ \n" + "\"name\":\"TestBeerStyle\",\n"
				+ "\"minTemperature\":\"-1.0\",\n" + "\"maxTemperature\":\"10.00\"\n" + "}",
				ContentType.APPLICATION_JSON);
		request.setEntity(entity);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		HttpEntity ent = httpResponse.getEntity();

		// Then
		String idCreated = EntityUtils.toString(ent, "UTF-8");
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_CREATED));
		
		beerStyleRepository.deleteById(idCreated);
	}

	@Test
	public void givenBeerStyleToAddWithWrongParameters_thenStatusReceivedIsBadRequest() throws ClientProtocolException, IOException {
		// Given
		HttpPost request = new HttpPost("http://localhost:"+port+"/api/v1/admin/beers");

		StringEntity entity = new StringEntity("{ \n" + "\"name\":\"TestBeerStyle\",\n"
				+ "\"minTemperature\":\"-1.0\",\n" + "\"maxTemperature\":\"10.0055\"\n" + "}",
				ContentType.APPLICATION_JSON);
		request.setEntity(entity);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_BAD_REQUEST));
	}

	@Test
	public void givenBeerStyleExisting_thenStatusReceivedIsOK() throws ClientProtocolException, IOException {
		BeerStyle bStyle = beerStyleRepository.insert(new BeerStyle("test", -1.0, 1.0));
		
		// Given
		HttpGet request = new HttpGet("http://localhost:"+port+"/api/v1/admin/beers/" + bStyle.getId());

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_OK));
		
		beerStyleRepository.deleteById(bStyle.getId());
	}

	@Test
	public void givenBeerStyleToEdit_thenStatusReceivedIsNoContent() throws ClientProtocolException, IOException {
		BeerStyle bStyle = beerStyleRepository.insert(new BeerStyle("test", -1.0, 1.0));
		
		// Given
		HttpPut request = new HttpPut("http://localhost:"+port+"/api/v1/admin/beers/" + bStyle.getId());

		StringEntity entity = new StringEntity("{ \n" + "\"name\":\"Edited\",\n" + "\"minTemperature\":\"-1.0\",\n"
				+ "\"maxTemperature\":\"10.1\"\n" + "}", ContentType.APPLICATION_JSON);
		request.setEntity(entity);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NO_CONTENT));
		
		beerStyleRepository.deleteById(bStyle.getId());
	}

	@Test
	public void givenBeerStyleExisting_whenDelete_thenStatusReceivedIsNoContent() throws ClientProtocolException, IOException {
		BeerStyle bStyle = beerStyleRepository.insert(new BeerStyle("test", -1.0, 1.0));
		
		// Given
		HttpDelete request = new HttpDelete("http://localhost:"+port+"/api/v1/admin/beers/" + bStyle.getId());

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NO_CONTENT));
		
	}

	@Test
	public void givenBeerStyleNoExisting_whenTryDelete_thenStatusReceivedIsNoContent() throws ClientProtocolException, IOException {
		// Given
		HttpDelete request = new HttpDelete("http://localhost:"+port+"/api/v1/admin/beers/" + "111555111");

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NO_CONTENT));
		
	}

}