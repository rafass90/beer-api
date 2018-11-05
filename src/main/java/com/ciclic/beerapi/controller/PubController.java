package com.ciclic.beerapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciclic.beerapi.domain.dto.BusinessDTO;
import com.ciclic.beerapi.domain.exception.ResourceNotFoundException;
import com.ciclic.beerapi.service.PubService;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

@RestController
@RequestMapping(value = "/api/v1/beers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PubController {

	private PubService pubService;

	@Autowired
	public PubController(PubService pubService) {
		this.pubService = pubService;
	}

	@GetMapping(value = "/temperature/{temperature}")
	public ResponseEntity<BusinessDTO> findBeerByTemperature(@PathVariable(value = "temperature") Double temperature) throws ResourceNotFoundException, SpotifyWebApiException, IOException{
		BusinessDTO businessDTO = pubService.beerWithMusic(temperature);
		
		return ResponseEntity.ok(businessDTO);
	}
}