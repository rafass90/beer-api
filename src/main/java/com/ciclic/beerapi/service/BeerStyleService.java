package com.ciclic.beerapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclic.beerapi.domain.exception.ResourceDuplicatedException;
import com.ciclic.beerapi.domain.exception.ResourceNotFoundException;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.repository.BeerStyleRepository;

@Service
public class BeerStyleService {

	private BeerStyleRepository beerRepository;

	@Autowired
	public BeerStyleService(BeerStyleRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	public String add(BeerStyle beerStyle) throws ResourceDuplicatedException {
		if(beerRepository.existsByName(beerStyle.getName())){
			throw new ResourceDuplicatedException("BeerStyle already exists");
		}
		return this.beerRepository.insert(beerStyle).getId();
	}

	public List<BeerStyle> listAll() {
		return beerRepository.findAll();
	}

	public BeerStyle find(String id) throws ResourceNotFoundException {
		Optional<BeerStyle> beerStyle = beerRepository.findById(id);

		return beerStyle.orElseThrow(() -> new ResourceNotFoundException("BeerStyle not found"));
	}

	public BeerStyle update(BeerStyle beerStyle, String id) throws ResourceNotFoundException {
		if (!beerRepository.existsById(id)) {
			throw new ResourceNotFoundException("BeerStyle does not exist");
		}
		beerStyle.setId(id);
		beerStyle = beerRepository.save(beerStyle);
		return beerStyle;
	}

	public void remove(String id) throws ResourceNotFoundException {
		if (!beerRepository.existsById(id)) {
			throw new ResourceNotFoundException("BeerStyle does not exist");
		}
		beerRepository.deleteById(id);
	}

}