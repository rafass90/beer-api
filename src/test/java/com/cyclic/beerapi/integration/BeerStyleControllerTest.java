package com.cyclic.beerapi.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class BeerStyleControllerTest {

	private String idCreated;

	@Before
	public void create() throws ParseException, IOException {
		// Given
		HttpPost requestCreate = new HttpPost("http://localhost:8080/api/v1/admin/beer");

		StringEntity entity = new StringEntity("{ \n" + "\"name\":\"TestBeerStyle\",\n"
				+ "\"minTemperature\":\"-1.0\",\n" + "\"maxTemperature\":\"10.00\"\n" + "}",
				ContentType.APPLICATION_JSON);
		requestCreate.setEntity(entity);

		// When
		HttpResponse httpResponseCreate = HttpClientBuilder.create().build().execute(requestCreate);
		HttpEntity ent = httpResponseCreate.getEntity();

		// Then
		idCreated = EntityUtils.toString(ent, "UTF-8");
	}

	@Test
	public void givenBeerStyleToAdd_thenStatusCreatedIsReceived() throws ClientProtocolException, IOException {

		// Given
		HttpPost request = new HttpPost("http://localhost:8080/api/v1/admin/beer");

		StringEntity entity = new StringEntity("{ \n" + "\"name\":\"TestBeerStyle\",\n"
				+ "\"minTemperature\":\"-1.0\",\n" + "\"maxTemperature\":\"10.00\"\n" + "}",
				ContentType.APPLICATION_JSON);
		request.setEntity(entity);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		HttpEntity ent = httpResponse.getEntity();

		// Then
		idCreated = EntityUtils.toString(ent, "UTF-8");
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_CREATED));
	}

	@Test
	public void givenBeerStyleToAddWithWrongParameters_thenStatusReceivedIsBadRequest()
			throws ClientProtocolException, IOException {

		// Given
		HttpPost request = new HttpPost("http://localhost:8080/api/v1/admin/beer");

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
	public void givenBeerStyleToEdit_thenStatusReceivedIsNoContent() throws ClientProtocolException, IOException {

		// Given
		HttpPut request = new HttpPut("http://localhost:8080/api/v1/admin/beer/" + idCreated);

		StringEntity entity = new StringEntity("{ \n" + "\"name\":\"Edited\",\n" + "\"minTemperature\":\"-1.0\",\n"
				+ "\"maxTemperature\":\"10.1\"\n" + "}", ContentType.APPLICATION_JSON);
		request.setEntity(entity);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NO_CONTENT));
	}

	@Test
	public void givenBeerStyleExisting_whenDelete_thenStatusReceivedIsNoContent()
			throws ClientProtocolException, IOException {

		// Given
		HttpGet request = new HttpGet("http://localhost:8080/api/v1/admin/beer/" + idCreated);

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NO_CONTENT));
	}
}
