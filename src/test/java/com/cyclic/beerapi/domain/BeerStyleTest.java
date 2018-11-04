package com.cyclic.beerapi.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyclic.beerapi.domain.vo.BeerStyle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerStyleTest {
	
	@Test
	public void testConstructor() {
		String name = "Valuee1";
		Double minTemperature = Double.valueOf("10.0");
		Double maxTemperature = Double.valueOf("15.8");
		BeerStyle beer1 = new BeerStyle(name, minTemperature, maxTemperature);
		
		assertThat(beer1.getName(), is(equalTo(name)));
		assertThat(beer1.getMinTemperature(), is(equalTo(minTemperature)));
		assertThat(beer1.getMaxTemperature(), is(equalTo(maxTemperature)));
	}
	
}