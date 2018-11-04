package com.cyclic.beerapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cyclic.beerapi.domain.vo.BeerStyle;

public interface BeerStyleRepository extends MongoRepository<BeerStyle, String>{
}