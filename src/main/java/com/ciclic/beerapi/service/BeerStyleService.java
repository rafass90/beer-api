package com.ciclic.beerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.repository.BeerStyleRepository;

@Service
public class BeerStyleService {

	private BeerStyleRepository beerRepository;
	
	@Autowired
	public BeerStyleService(BeerStyleRepository beerRepository) {
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

	public BeerStyle update(BeerStyle beerStyle, String id) {
		beerStyle.setId(id);
		return beerRepository.save(beerStyle);
	}
	
	public void remove(String id) {
		beerRepository.deleteById(id);
	}
	
}