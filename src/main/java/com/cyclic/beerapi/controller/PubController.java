package com.cyclic.beerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyclic.beerapi.service.PubService;

@RestController
@RequestMapping(value = "/api/v1/beers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PubController {

	private PubService pubService;
	
	@Autowired
	public PubController(PubService pubService) {
		this.pubService = pubService;
	}
	
	@GetMapping(value = "/temperature/{temperature}")
    public ResponseEntity<Object> findBeerByTemperature(@PathVariable(value = "temperature") Double temperature) throws Exception {
        
		return ResponseEntity.ok(pubService.beerWithMusic(temperature));
    }
}