package com.cyclic.beerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.domain.vo.BeerStyle;
import com.cyclic.beerapi.repository.BeerRepositoryDAO;

@Service
public class BeerStyleService {

	private BeerRepositoryDAO beerRepository;
	
	@Autowired
	public BeerStyleService(BeerRepositoryDAO beerRepository) {
		this.beerRepository = beerRepository;
	}
	
	public String add(BeerStyle beerStyle) {
		return this.beerRepository.insert(beerStyle).getId();
	}
	
	public List<BeerStyle> listAll(){
		return beerRepository.findAll();
	}
	
	public BeerStyle find(String id) {
		Optional<BeerStyle> beerStyle = beerRepository.findById(id);
		return beerStyle.get();
	}

	public List<BeerStyle> findByTemperature(Double temperature) {
		List<BeerStyle> beerStyles = beerRepository.findBeerByTemperature(temperature);
		if(beerStyles.isEmpty())
			beerStyles = beerRepository.findAll();
		return beerStyles;
	}
	
	public BeerStyle update(BeerStyle beerStyle, String id) {
		return beerRepository.save(beerStyle);
	}
	
	public void remove(String id) {
		beerRepository.deleteById(id);
	}
	
}