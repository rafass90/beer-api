package com.cyclic.beerapi;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyclic.beerapi.domain.vo.BeerStyle;
import com.cyclic.beerapi.domain.vo.BeerStyleWithTempDifference;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerStyleDecoratorTest {
	
	@Test
	public void testConstructor() {
		String name = "Valuee1";
		Double minTemperature = 10.0;
		Double maxTemperature = 15.8;
		BeerStyle beer1 = new BeerStyle(name, minTemperature, maxTemperature);

		Double desiredTemperature = 3.0;
		BeerStyleWithTempDifference beerDecorator = new BeerStyleWithTempDifference(beer1, desiredTemperature);
		assertThat(beer1, is(beerDecorator.getBeer()));
	}
	
	@Test
	public void shouldCalculateDifferenceTemperature(){
		BeerStyle beer1 = new BeerStyle("One", -3.0, 5.0);
		BeerStyle beer2 = new BeerStyle("Two", -2.0, 14.0);
		BeerStyle beer3 = new BeerStyle("Two", -0.0, 4.5);
		
		Double desiredTemperature = 3.0;
		
		BeerStyleWithTempDifference beerWith1 = new BeerStyleWithTempDifference(beer1, desiredTemperature);
		BeerStyleWithTempDifference beerWith2 = new BeerStyleWithTempDifference(beer2, desiredTemperature);
		BeerStyleWithTempDifference beerWith3 = new BeerStyleWithTempDifference(beer3, desiredTemperature);
		
		assertThat(beerWith1.getDifferenceTemperature(), is(equalTo(2)));
		assertThat(beerWith2.getDifferenceTemperature(), is(equalTo(3)));
		assertThat(beerWith3.getDifferenceTemperature(), is(equalTo(0.65)));
		
	}
	
	
}