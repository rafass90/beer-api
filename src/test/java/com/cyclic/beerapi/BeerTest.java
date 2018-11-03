package com.cyclic.beerapi;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyclic.beerapi.vo.Beer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerTest {

	@Test
	public void testGetAverageTemperature(){
		Beer beer1 = new Beer("One", -3.0, 5.0);
		Beer beer2 = new Beer("Two", -2.0, 14.0);
		Beer beer3 = new Beer("Two", -0.0, 4.5);
		
		assertThat(beer1.getAverageTemperature(), is(Double.valueOf("1.0")));
		assertThat(beer2.getAverageTemperature(), is(Double.valueOf("6.0")));
		assertThat(beer3.getAverageTemperature(), is(Double.valueOf("2.25")));
		
	}
}