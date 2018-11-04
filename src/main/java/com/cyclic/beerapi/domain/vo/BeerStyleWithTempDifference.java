package com.cyclic.beerapi.domain.vo;

public class BeerStyleWithTempDifference implements Comparable<BeerStyleWithTempDifference>{

	private BeerStyle beerStyle;
	
	private Double temperature;
	
	public BeerStyleWithTempDifference (BeerStyle beerStyle, Double temperature) {
		this.beerStyle = beerStyle;
		this.temperature = temperature;
	}

	public BeerStyle getBeer() {
		return beerStyle;
	}

	public Double getDifferenceTemperature() {
		return calculateDifferenceTemperature(beerStyle, temperature);
	}
	
	public Double calculateAverageTemperature() {
		return Double.sum(beerStyle.getMinTemperature(), beerStyle.getMaxTemperature()) / 2;
	}

	private double calculateDifferenceTemperature(BeerStyle beerStyle, Double temperature) {
		return Math.abs(calculateAverageTemperature() - temperature);
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