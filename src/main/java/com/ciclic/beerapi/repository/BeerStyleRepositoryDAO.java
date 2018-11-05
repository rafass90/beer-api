package com.ciclic.beerapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ciclic.beerapi.domain.vo.BeerStyle;

@Service
public class BeerStyleRepositoryDAO{
	
	private BeerStyleRepository beerStyleRepository;

	private MongoTemplate mongoTemplate;
	
	@Autowired
	public BeerStyleRepositoryDAO(BeerStyleRepository beerStyleRepository, MongoTemplate mongoTemplate) {
		this.beerStyleRepository = beerStyleRepository;
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
		return beerStyleRepository.findAll();
	}

	public BeerStyle insert(BeerStyle beerStyle) {
		return this.beerStyleRepository.insert(beerStyle);
	}

	public BeerStyle save(BeerStyle beerStyle) {
		return beerStyleRepository.save(beerStyle);
	}

	public Optional<BeerStyle> findById(String id) {
		Optional<BeerStyle> beerStyle = beerStyleRepository.findById(id);
		return beerStyle;
	}

	public void deleteById(String id) {
		beerStyleRepository.deleteById(id);
	}
	
}