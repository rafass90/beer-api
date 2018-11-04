package com.ciclic.beerapi.domain.vo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class BeerStyle {

	private static final String temperatureRange = "Temperature shoud be in range -99.99 at 99.99 and only 2 decimal digits";
	
	@Id
	private String id;
	
	@NotNull
	@Indexed(unique = true, dropDups=true)
	@Size(min=3, message="BeerStyle name should have at least 3 characters")
	private String name;
	
	@NotNull
	@Digits(fraction=2, integer=2, message=temperatureRange)
	private Double minTemperature;
	
	@NotNull
	@Digits(fraction=2, integer=2, message=temperatureRange)
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

	public Double getMinTemperature() {
		return minTemperature;
	}

	public Double getMaxTemperature() {
		return maxTemperature;
	}
	
}
