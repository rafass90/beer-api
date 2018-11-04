package com.cyclic.beerapi.vo;

public class BeerWithTempDifference {

	private Beer beer;
	
	private Double differenceTemperature;
	
	public BeerWithTempDifference (Beer beer, Double temperature) {
		this.beer = beer;
		this.differenceTemperature = Math.abs(beer.getAverageTemperature() - temperature);
	}

	public Beer getBeer() {
		return beer;
	}

	public Double getDifferenceTemperature() {
		return differenceTemperature;
	}

}
