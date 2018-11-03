package com.cyclic.beerapi.service;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.spotify.SpotifyService;
import com.cyclic.beerapi.vo.Beer;
import com.wrapper.spotify.model_objects.specification.Playlist;

@Service
public class PubService {

	Comparator comparatorName = new Comparator<Beer>() {
	    public int compare(Beer beer1, Beer beer2) {
	        return beer1.getName().compareTo(beer2.getName());
	    }
	};
	
	private final static Logger LOGGER = Logger.getLogger(BeerService.class.getName());
	
	private BeerService beerService;
	
	private SpotifyService spotifyService;
	
	@Autowired
	public PubService(BeerService beerService, SpotifyService spotifyService) {
		this.beerService = beerService;
		this.spotifyService = spotifyService;
	}
	
	public Beer beerWithMusic(Double temperature) {
		Beer beer = suggestIdealBeer(temperature);
		Playlist playlist = findPlaylistWith(beer);
		LOGGER.info(beer.toString());
		LOGGER.info(playlist.toString());
		return null;
	}

	private Beer suggestIdealBeer(Double temperature){
		List<Beer> beers = beerService.findByTemperature(temperature);
		beers.stream().forEach(b -> LOGGER.info(String.valueOf(b.getAverageTemperature())));

		beers.stream().sorted(comparator)
		return null;
	}

	private Playlist findPlaylistWith(Beer beer){
		return spotifyService.getPlaylistByName(beer.getName());
	}	
}
