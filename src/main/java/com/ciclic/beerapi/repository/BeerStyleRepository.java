package com.ciclic.beerapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ciclic.beerapi.domain.vo.BeerStyle;

public interface BeerStyleRepository extends MongoRepository<BeerStyle, String>{
	
	public boolean existsByName(String name);
}