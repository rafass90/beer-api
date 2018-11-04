package com.cyclic.beerapi.domain.vo;

import org.springframework.data.annotation.Id;

public class BeerStyle {

	@Id
	private String id;
	
	private String name;
	
	private Double minTemperature;
	
	private Double maxTemperature;
	
	public BeerStyle() {
	}
	
	public BeerStyle(String name, Double minTemperature, Double maxTemperature) {
		this.name = name;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(Double minTemperature) {
		this.minTemperature = minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public Double getAverageTemperature() {
		return Double.sum(minTemperature, maxTemperature) / 2;
	}

}
