package com.cyclic.beerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.database.BeerRepositoryDAO;
import com.cyclic.beerapi.vo.Beer;

@Service
public class BeerService {

	private BeerRepositoryDAO beerRepository;
	
	@Autowired
	public BeerService(BeerRepositoryDAO beerRepository) {
		this.beerRepository = beerRepository;
	}
	
	public String add(Beer beer) {
		return this.beerRepository.insert(beer).getId();
	}
	
	public List<Beer> listAll(){
		return beerRepository.findAll();
	}
	
	public Beer find(String id) {
		Optional<Beer> beer = beerRepository.findById(id);
		return beer.get();
	}

	public List<Beer> findByTemperature(Double temperature) {
		List<Beer> beer = beerRepository.findBeerByTemperature(temperature);
		return beer;
	}
	
	public Beer update(Beer beer, String id) {
		return beerRepository.save(beer);
	}
	
	public void remove(String id) {
		beerRepository.deleteById(id);
	}
	
}
