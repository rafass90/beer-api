package com.cyclic.beerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyclic.beerapi.spotify.SpotifyService;

@RestController
public class BeerController {

	@Autowired
	private SpotifyService spotifyService;
	
	@RequestMapping("/tests")
	public ResponseEntity<String> test() {
		
		return new ResponseEntity<String>(spotifyService.getPlaylist_Sync(), HttpStatus.OK);
	}
}
