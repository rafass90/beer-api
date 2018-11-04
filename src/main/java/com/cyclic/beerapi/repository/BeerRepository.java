package com.cyclic.beerapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cyclic.beerapi.vo.Beer;

public interface BeerRepository extends MongoRepository<Beer, String>{
}