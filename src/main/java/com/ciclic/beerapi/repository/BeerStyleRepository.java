package com.ciclic.beerapi.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ciclic.beerapi.domain.vo.BeerStyle;

import reactor.core.publisher.Mono;

public interface BeerStyleRepository extends ReactiveMongoRepository<BeerStyle, String>{
	
	public Mono<Boolean> existsByName(String name);
}