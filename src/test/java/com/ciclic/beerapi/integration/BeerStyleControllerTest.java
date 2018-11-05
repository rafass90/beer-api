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
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.ciclic.beerapi.domain.vo.BeerStyle;

@RunWith(SpringRunner.class)

public class BeerStyleControllerTest extends ControllerTest{
    

	@Test
	public void givenBeerStyleToAdd_thenStatusCreatedIsReceived() throws ClientProtocolException, IOException {
		// Given
		HttpPost request = new HttpPost("http://localhost:"+port+"/api/v1/admin/beers");
		request.setEntity(createPostBody("TestBeerStyle", -1.0, 10.00));

		// When
		HttpResponse httpResponse = executeRequest(request);
		HttpEntity ent = httpResponse.getEntity();
		String idCreated = EntityUtils.toString(ent, "UTF-8");
		
		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_CREATED));
		
		deleteTestById(idCreated);
	}

	@Test
	public void givenBeerStyleAlreadExistingToAdd_thenStatusCreatedIsConflict() throws ClientProtocolException, IOException {
		// Given
		BeerStyle bStyle = insertTest("TestBeerStyle");
		
		// When
		HttpPost request = new HttpPost("http://localhost:"+port+"/api/v1/admin/beers");
		request.setEntity(createPostBody("TestBeerStyle", -1.0, 10.00));
		HttpResponse httpResponse = executeRequest(request);
		HttpEntity ent = httpResponse.getEntity();

		// Then
		String idCreated = EntityUtils.toString(ent, "UTF-8");
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_CONFLICT));
		deleteTestById(idCreated);
		deleteTestById(bStyle.getId());
	}

	@Test
	public void givenBeerStyleToAddWithWrongParameters_thenStatusReceivedIsBadRequest() throws ClientProtocolException, IOException {
		// Given
		HttpPost request = new HttpPost("http://localhost:"+port+"/api/v1/admin/beers");
		request.setEntity(createPostBody("TestBeerStyle", -1.0, 10.0055));

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_BAD_REQUEST));
	}

	@Test
	public void givenBeerStyleExisting_thenStatusReceivedIsOK() throws ClientProtocolException, IOException {
		// Given
		BeerStyle bStyle = insertTest("test");
		HttpGet request = new HttpGet("http://localhost:"+port+"/api/v1/admin/beers/" + bStyle.getId());

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_OK));
		deleteTestById(bStyle.getId());
	}

	@Test
	public void givenBeerStyleToEdit_thenStatusReceivedIsNoContent() throws ClientProtocolException, IOException {
		// Given
		BeerStyle bStyle = insertTest("test");
		HttpPut request = new HttpPut("http://localhost:"+port+"/api/v1/admin/beers/" + bStyle.getId());
		request.setEntity(createPostBody("Edited", -1.0, 10.1));

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NO_CONTENT));
		deleteTestById(bStyle.getId());
	}
	
	@Test
	public void givenBeerStyleNoExistingToEdit_thenStatusReceivedIsNotFound() throws ClientProtocolException, IOException {
		// Given
		HttpPut request = new HttpPut("http://localhost:"+port+"/api/v1/admin/beers/" + "idNotExisting");
		request.setEntity(createPostBody("Edited", -1.0, 10.1));

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NOT_FOUND));
	}

	@Test
	public void givenBeerStyleExisting_whenDelete_thenStatusReceivedIsNoContent() throws ClientProtocolException, IOException {
		// Given
		BeerStyle bStyle = insertTest("test");
		HttpDelete request = new HttpDelete("http://localhost:"+port+"/api/v1/admin/beers/" + bStyle.getId());

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NO_CONTENT));
	}

	@Test
	public void givenBeerStyleNoExisting_whenTryDelete_thenStatusReceivedIsNoContent() throws ClientProtocolException, IOException {
		// Given
		HttpDelete request = new HttpDelete("http://localhost:"+port+"/api/v1/admin/beers/" + "111555111");

		// When
		HttpResponse httpResponse = executeRequest(request);

		// Then
		assertThat(httpResponse.getStatusLine().getStatusCode(), is(HttpStatus.SC_NOT_FOUND));
	}
	
	private StringEntity createPostBody(String name, Double minTemperature, Double maxTemperature) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{ \n");
		stringBuilder.append("\"name\":\"");
		stringBuilder.append(name);
		stringBuilder.append("\",\n\"minTemperature\":\"");
		stringBuilder.append(String.valueOf(minTemperature));
		stringBuilder.append("\",\n\"maxTemperature\":\"");
		stringBuilder.append(String.valueOf(maxTemperature));
		stringBuilder.append("\"\n}");
		StringEntity entity = new StringEntity(stringBuilder.toString(), ContentType.APPLICATION_JSON);
		return entity;
	}

}