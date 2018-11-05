package com.ciclic.beerapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciclic.beerapi.domain.exception.ResourceDuplicatedException;
import com.ciclic.beerapi.domain.exception.ResourceNotFoundException;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.service.BeerStyleService;

@RestController
@RequestMapping(value = "/api/v1/admin/beers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BeerStyleController {

	private BeerStyleService beerStyleService;
	
	@Autowired
	public BeerStyleController(BeerStyleService beerStyleService) {
		this.beerStyleService = beerStyleService;
	}

	@GetMapping
	public ResponseEntity<List<BeerStyle>> listBeers() {
		List<BeerStyle> beerStyles = beerStyleService.listAll();
		
		return new ResponseEntity<List<BeerStyle>>(beerStyles, HttpStatus.OK);
	}

	@PostMapping(headers = "Accept=application/json")
	public ResponseEntity<String> addBeer(@Valid @RequestBody BeerStyle beerStyle) throws ResourceDuplicatedException {
		String id = beerStyleService.add(beerStyle);
		
		return new ResponseEntity<String>(id, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BeerStyle> findBeer(@PathVariable("id") String id) throws ResourceNotFoundException {
		BeerStyle beerStyle = beerStyleService.find(id);
		
		return new ResponseEntity<BeerStyle>(beerStyle, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> edit(@PathVariable("id") String id, @Valid @RequestBody BeerStyle beerStyle) throws ResourceNotFoundException {
		beerStyleService.update(beerStyle, id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteBeer(@PathVariable("id") String id) throws ResourceNotFoundException {
		beerStyleService.remove(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
