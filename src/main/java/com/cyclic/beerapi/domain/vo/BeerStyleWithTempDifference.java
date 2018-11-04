package com.cyclic.beerapi.domain.vo;

public class BeerStyleWithTempDifference implements Comparable<BeerStyleWithTempDifference>{

	private BeerStyle beerStyle;
	
	private Double differenceTemperature;
	
	public BeerStyleWithTempDifference (BeerStyle beerStyle, Double temperature) {
		this.beerStyle = beerStyle;
		this.differenceTemperature = calculateDifferenceTemperature(beerStyle, temperature);
	}

	public BeerStyle getBeer() {
		return beerStyle;
	}

	public Double getDifferenceTemperature() {
		return differenceTemperature;
	}

	private double calculateDifferenceTemperature(BeerStyle beerStyle, Double temperature) {
		return Math.abs(beerStyle.getAverageTemperature() - temperature);
	}

	@Override
	public int compareTo(BeerStyleWithTempDifference o) {
		int tempDifference = this.getDifferenceTemperature().compareTo(o.getDifferenceTemperature());

        if (tempDifference != 0) {
           return tempDifference;
        } 

        return this.getBeer().getName().compareTo(o.getBeer().getName());
	}
	
}