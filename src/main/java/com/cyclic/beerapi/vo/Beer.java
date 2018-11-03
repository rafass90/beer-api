package com.cyclic.beerapi.vo;

import org.springframework.data.annotation.Id;

public class Beer {

	@Id
	private String id;
	
	private String name;
	
	private Double minTemperature;
	
	private Double maxTemperature;
	
	public Beer() {
	}
	
	public Beer(String name, Double minTemperature, Double maxTemperature) {
		this.name = name;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public Double getAverageTemperature() {
		return Double.sum(minTemperature, maxTemperature) / 2;
	}
	
}
