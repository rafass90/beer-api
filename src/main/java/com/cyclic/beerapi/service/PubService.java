package com.cyclic.beerapi.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.spotify.SpotifyService;
import com.cyclic.beerapi.vo.BeerStyle;
import com.cyclic.beerapi.vo.BeerStyleWithTempDifference;
import com.wrapper.spotify.model_objects.specification.Playlist;

@Service
public class PubService {

	private final static Logger LOGGER = Logger.getLogger(PubService.class.getName());
	
	private BeerStyleService beerStyleService;
	
	private SpotifyService spotifyService;
	
	@Autowired
	public PubService(BeerStyleService beerStyleService, SpotifyService spotifyService) {
		this.beerStyleService = beerStyleService;
		this.spotifyService = spotifyService;
	}
	
	public BeerStyle beerWithMusic(Double temperature) {
		BeerStyle beerStyle = suggestIdealBeer(temperature);
		Playlist playlist = findPlaylistWith(beerStyle);
		
		LOGGER.info("BeerStyle Style: " + beerStyle.getName() + "\nPlaylist: " + playlist.getName());
		return null;
	}

	private BeerStyle suggestIdealBeer(Double temperature){
		List<BeerStyle> beerStyles = beerStyleService.findByTemperature(temperature);

		BeerStyle beerStyle = findOnceBeerStyleWithMinimumDifference(beerStyles, temperature);
		
		return beerStyle;
	}
	
	private BeerStyle findOnceBeerStyleWithMinimumDifference(List<BeerStyle> beerStyles, Double temperature) {
		return beerStyles.stream()
		.map(b -> new BeerStyleWithTempDifference(b, temperature))
		.sorted()
		.map(bW -> bW.getBeer())
		.findFirst().get();
	}

	private Playlist findPlaylistWith(BeerStyle beerStyle){
		return spotifyService.getPlaylistByName(beerStyle.getName());
	}	
}
