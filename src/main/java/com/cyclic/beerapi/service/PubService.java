package com.cyclic.beerapi.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.spotify.SpotifyService;
import com.cyclic.beerapi.vo.Beer;
import com.cyclic.beerapi.vo.BeerWithTempDifference;
import com.wrapper.spotify.model_objects.specification.Playlist;

@Service
public class PubService {

	Comparator<BeerWithTempDifference> comparatorDifferenceTemperature = new Comparator<BeerWithTempDifference>() {
	    public int compare(BeerWithTempDifference beer1, BeerWithTempDifference beer2) {
	        int tempDifference = beer1.getDifferenceTemperature().compareTo(beer2.getDifferenceTemperature());

            if (tempDifference != 0) {
               return tempDifference;
            } 

            return beer1.getBeer().getName().compareTo(beer2.getBeer().getName());
	    }
	};
	
	private final static Logger LOGGER = Logger.getLogger(PubService.class.getName());
	
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
		
		LOGGER.info("Beer Style: " + beer.getName() + "\nPlaylist: " + playlist.getName());
		return null;
	}

	private Beer suggestIdealBeer(Double temperature){
		List<Beer> beers = beerService.findByTemperature(temperature);

		beers = computeMinimumDifference(beers, temperature);
		
		return beers.get(0);
	}
	
	private List<Beer> computeMinimumDifference(List<Beer> beers, Double temperature) {
		return beers.stream()
		.map(b -> new BeerWithTempDifference(b, temperature))
		.sorted(comparatorDifferenceTemperature)
		.map(bW -> bW.getBeer())
		.collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
	}

	private Playlist findPlaylistWith(Beer beer){
		return spotifyService.getPlaylistByName(beer.getName());
	}	
}
