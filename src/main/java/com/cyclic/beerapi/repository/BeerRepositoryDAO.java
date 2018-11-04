package com.cyclic.beerapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.vo.BeerStyle;

@Service
public class BeerRepositoryDAO{
	
	private BeerRepository beerRepository;

	private MongoTemplate mongoTemplate;
	
	@Autowired
	public BeerRepositoryDAO(BeerRepository beerRepository, MongoTemplate mongoTemplate) {
		this.beerRepository = beerRepository;
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<BeerStyle> findBeerByTemperature(Double temperature){
		Query query = new Query();
		query.addCriteria(Criteria.where("minTemperature").lte(temperature));
		query.addCriteria(Criteria.where("maxTemperature").gte(temperature));
		List<BeerStyle> beerStyles = mongoTemplate.find(query,BeerStyle.class);
		return beerStyles;
	}

	public List<BeerStyle> findAll() {
		return beerRepository.findAll();
	}

	public BeerStyle insert(BeerStyle beerStyle) {
		return this.beerRepository.insert(beerStyle);
	}

	public BeerStyle save(BeerStyle beerStyle) {
		return beerRepository.save(beerStyle);
	}

	public Optional<BeerStyle> findById(String id) {
		Optional<BeerStyle> beerStyle = beerRepository.findById(id);
		return beerStyle;
	}

	public void deleteById(String id) {
		beerRepository.deleteById(id);
	}
	
}