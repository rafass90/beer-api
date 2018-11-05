package com.ciclic.beerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclic.beerapi.domain.exception.ResourceDuplicatedException;
import com.ciclic.beerapi.domain.exception.ResourceNotFoundException;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.repository.BeerStyleRepository;

import reactor.core.publisher.Mono;

@Service
public class BeerStyleService {

	private BeerStyleRepository beerRepository;

	@Autowired
	public BeerStyleService(BeerStyleRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	public String add(BeerStyle beerStyle) throws ResourceDuplicatedException {
		if(beerRepository.existsByName(beerStyle.getName()).block()){
			throw new ResourceDuplicatedException("BeerStyle already exists");
		}
		return this.beerRepository.insert(beerStyle).block().getId();
	}

	public Mono<List<BeerStyle>> listAll() {
		return beerRepository.findAll().collectList();
	}

	public BeerStyle find(String id) throws ResourceNotFoundException {
		Mono<BeerStyle> beerStyle = beerRepository.findById(id);

		return beerStyle
				.blockOptional()
				.orElseThrow(() -> new ResourceNotFoundException("BeerStyle not found"));
	}

	public BeerStyle update(BeerStyle beerStyle, String id) throws ResourceNotFoundException {
		if (!beerRepository.existsById(id).block()) {
			throw new ResourceNotFoundException("BeerStyle does not exist");
		}
		beerStyle.setId(id);
		beerStyle = beerRepository.save(beerStyle).block();
		return beerStyle;
	}

	public void remove(String id) throws ResourceNotFoundException {
		if (!beerRepository.existsById(id).block()) {
			throw new ResourceNotFoundException("BeerStyle does not exist");
		}
		beerRepository.deleteById(id);
	}

}