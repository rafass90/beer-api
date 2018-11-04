package com.cyclic.beerapi;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyclic.beerapi.vo.BeerStyle;

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
	
	@Test
	public void shouldCalculateAverageTemperature(){
		BeerStyle beer1 = new BeerStyle("b1", -3.0, 5.0);
		BeerStyle beer2 = new BeerStyle("b2", -2.0, 14.0);
		BeerStyle beer3 = new BeerStyle("b3", -0.0, 4.5);
		
		assertThat(beer1.getAverageTemperature(), is(Double.valueOf("1.0")));
		assertThat(beer2.getAverageTemperature(), is(Double.valueOf("6.0")));
		assertThat(beer3.getAverageTemperature(), is(Double.valueOf("2.25")));
		
	}
	
	@Test(expected=NullPointerException.class)
	public void shouldThrowNullPointerException(){
		BeerStyle beer1 = new BeerStyle("b1", -2.0, null);
		
		assertThat(beer1.getAverageTemperature(), is(Double.valueOf("1.0")));
		
	}
	
	
}