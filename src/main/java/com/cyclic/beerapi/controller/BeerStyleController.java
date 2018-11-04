package com.cyclic.beerapi.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.cyclic.beerapi.dto.BeerStyleDTO;
import com.cyclic.beerapi.service.BeerStyleService;
import com.cyclic.beerapi.vo.BeerStyle;

@RestController
@RequestMapping(value = "/api/v1/admin/beer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BeerStyleController {

	private BeerStyleService beerStyleService;
	
	private ModelMapper modelMapper;
	
	@Autowired
	public BeerStyleController(BeerStyleService beerStyleService) {
		modelMapper = new ModelMapper();
		this.beerStyleService = beerStyleService;
	}

	@GetMapping
	public ResponseEntity<List<BeerStyleDTO>> listBeers() {
		List<BeerStyle> beers = beerStyleService.listAll();
		List<BeerStyleDTO> beersDTO = beers.parallelStream()
		.map(b -> modelMapper.map(b, BeerStyleDTO.class))
		.collect(toList());
		
		return new ResponseEntity<List<BeerStyleDTO>>(beersDTO, HttpStatus.OK);
	}

	@PostMapping(headers = "Accept=application/json")
	public ResponseEntity<String> addBeer(@Valid @RequestBody BeerStyleDTO beerStyleDTO) {
		String id = beerStyleService.add(modelMapper.map(beerStyleDTO, BeerStyle.class));
		return new ResponseEntity<String>(id, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BeerStyleDTO> findBeer(@PathVariable("id") String id) {
		beerStyleService.find(id);
		return new ResponseEntity<BeerStyleDTO>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> edit(@PathVariable("id") String id, @Valid @RequestBody BeerStyleDTO beerStyleDTO) throws Exception {
		beerStyleService.update(modelMapper.map(beerStyleDTO, BeerStyle.class), id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteBeer(@PathVariable("id") String id) {
		beerStyleService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
