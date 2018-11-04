package com.cyclic.beerapi.controller;

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

import com.cyclic.beerapi.service.BeerService;
import com.cyclic.beerapi.vo.BeerStyle;

@RestController
@RequestMapping(value = "/api/v1/admin/beer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BeerController {

	private BeerService beerService;

	@Autowired
	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping
	public ResponseEntity<Object> listBeers() {
		return new ResponseEntity<Object>(beerService.listAll(), HttpStatus.OK);
	}

	@PostMapping(headers = "Accept=application/json")
	public ResponseEntity<String> addBeer(@Valid @RequestBody BeerStyle beerStyle) {
		String id = beerService.add(beerStyle);
		return new ResponseEntity<String>(id, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> getBeer(@PathVariable("id") String id) {
		beerService.find(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> edit(@PathVariable("id") String id, @Valid @RequestBody BeerStyle beerStyle) throws Exception {
		beerService.update(beerStyle, id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
