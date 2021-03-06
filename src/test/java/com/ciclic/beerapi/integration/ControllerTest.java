package com.ciclic.beerapi.integration;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.ciclic.beerapi.BeerApiApplication;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.repository.BeerStyleRepository;

@SpringBootTest(classes = BeerApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-dev.properties")
public class ControllerTest {

	@LocalServerPort
	protected Integer port;
	
    @Autowired
	private BeerStyleRepository beerStyleRepository;
   
	protected CloseableHttpResponse executeRequest(HttpRequestBase request) throws IOException, ClientProtocolException {
		return HttpClientBuilder.create().build().execute(request);
	}
	
	protected void deleteTestById(String id) {
		beerStyleRepository.deleteById(id);
	}

	protected void deleteAllBeerStyles() {
		beerStyleRepository.deleteAll();
	}
	
	protected BeerStyle insertTest(String name) {
		return beerStyleRepository.insert(new BeerStyle(name, -1.0, 1.0));
	}
}

