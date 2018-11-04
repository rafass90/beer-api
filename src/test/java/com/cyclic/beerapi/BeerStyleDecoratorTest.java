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
	public void shouldCalculateAverageTemperatureNoDecimal(){
		BeerStyle beer = new BeerStyle("beer", -3.0, 5.0);
		
		BeerStyleWithTempDifference b = new BeerStyleWithTempDifference(beer, null);

		assertThat(b.calculateAverageTemperature(), is(Double.parseDouble("1.0")));
		
	}
	
	@Test
	public void shouldCalculateAverageTemperatureWithDecimal(){
		BeerStyle beer = new BeerStyle("beer", 2.0, -4.5);
		
		BeerStyleWithTempDifference b = new BeerStyleWithTempDifference(beer, null);

		assertThat(b.calculateAverageTemperature(), is(Double.parseDouble("-1.25")));
		
	}

	@Test
	public void shouldCalculateDifferenceTemperatureNoDecimal(){
		BeerStyle beer = new BeerStyle("Beer", -3.0, 5.0);
		
		Double desiredTemperature = 3.0;
		
		BeerStyleWithTempDifference bW = new BeerStyleWithTempDifference(beer, desiredTemperature);
		
		assertThat(bW.getDifferenceTemperature(), is(equalTo(Double.parseDouble("2"))));
		
	}
	
	@Test
	public void shouldCalculateDifferenceTemperatureOnlyNegativeNumber(){
		BeerStyle beer = new BeerStyle("Two", -2.0, -1.0);
		
		Double desiredTemperature = -3.0;
		
		BeerStyleWithTempDifference bW = new BeerStyleWithTempDifference(beer, desiredTemperature);
		
		assertThat(bW.getDifferenceTemperature(), is(equalTo(Double.parseDouble("1.5"))));
		
	}
	
	@Test
	public void shouldCalculateDifferenceTemperatureWithDecimal(){
		BeerStyle beer = new BeerStyle("One", -3.5, 5.5);
		
		Double desiredTemperature = 3.0;
		
		BeerStyleWithTempDifference bW = new BeerStyleWithTempDifference(beer, desiredTemperature);
		
		assertThat(bW.getDifferenceTemperature(), is(equalTo(Double.parseDouble("2"))));
		
	}

	
	@Test(expected=NullPointerException.class)
	public void shouldThrowNullPointerException(){
		BeerStyle beer1 = new BeerStyle("b1", -2.0, null);
		
		BeerStyleWithTempDifference b1 = new BeerStyleWithTempDifference(beer1, null);
		
		assertThat(b1.calculateAverageTemperature(), is(Double.parseDouble("1.0")));
		
	}
	
}