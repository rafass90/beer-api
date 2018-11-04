package com.cyclic.beerapi.vo;

public class BeerStyleWithTempDifference {

	private BeerStyle beerStyle;
	
	private Double differenceTemperature;
	
	public BeerStyleWithTempDifference (BeerStyle beerStyle, Double temperature) {
		this.beerStyle = beerStyle;
		this.differenceTemperature = Math.abs(beerStyle.getAverageTemperature() - temperature);
	}

	public BeerStyle getBeer() {
		return beerStyle;
	}

	public Double getDifferenceTemperature() {
		return differenceTemperature;
	}

}
