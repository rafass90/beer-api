package com.cyclic.beerapi.database;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cyclic.beerapi.vo.Beer;

public interface BeerRepository extends MongoRepository<Beer, Long> {

}
