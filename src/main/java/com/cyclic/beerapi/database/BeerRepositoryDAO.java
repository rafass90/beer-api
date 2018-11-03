package com.cyclic.beerapi.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.vo.Beer;

@Service
public class BeerRepositoryDAO{
	
	private BeerRepository beerRepository;

	private MongoTemplate mongoTemplate;
	
	@Autowired
	public BeerRepositoryDAO(BeerRepository beerRepository, MongoTemplate mongoTemplate) {
		this.beerRepository = beerRepository;
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<Beer> findBeerByTemperature(Double temperature){
		Query query = new Query();
		query.addCriteria(Criteria.where("minTemperature").lte(temperature));
		query.addCriteria(Criteria.where("maxTemperature").gte(temperature));
		List<Beer> beers = mongoTemplate.find(query,Beer.class);
		return beers;
	}

	public List<Beer> findAll() {
		return beerRepository.findAll();
	}

	public Beer insert(Beer beer) {
		return this.beerRepository.insert(beer);
	}

	public Beer save(Beer beer) {
		return beerRepository.save(beer);
	}

	public Optional<Beer> findById(String id) {
		Optional<Beer> beer = beerRepository.findById(id);
		return beer;
	}

	public void deleteById(String id) {
		beerRepository.deleteById(id);
	}
	
}