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
import com.cyclic.beerapi.vo.BeerStyle;
import com.cyclic.beerapi.vo.BeerStyleWithTempDifference;
import com.wrapper.spotify.model_objects.specification.Playlist;

@Service
public class PubService {

	Comparator<BeerStyleWithTempDifference> comparatorDifferenceTemperature = new Comparator<BeerStyleWithTempDifference>() {
	    public int compare(BeerStyleWithTempDifference beer1, BeerStyleWithTempDifference beer2) {
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
	
	public BeerStyle beerWithMusic(Double temperature) {
		BeerStyle beerStyle = suggestIdealBeer(temperature);
		Playlist playlist = findPlaylistWith(beerStyle);
		
		LOGGER.info("BeerStyle Style: " + beerStyle.getName() + "\nPlaylist: " + playlist.getName());
		return null;
	}

	private BeerStyle suggestIdealBeer(Double temperature){
		List<BeerStyle> beerStyles = beerService.findByTemperature(temperature);

		beerStyles = computeMinimumDifference(beerStyles, temperature);
		
		return beerStyles.get(0);
	}
	
	private List<BeerStyle> computeMinimumDifference(List<BeerStyle> beerStyles, Double temperature) {
		return beerStyles.stream()
		.map(b -> new BeerStyleWithTempDifference(b, temperature))
		.sorted(comparatorDifferenceTemperature)
		.map(bW -> bW.getBeer())
		.collect(Collectors.collectingAndThen(toList(), Collections::unmodifiableList));
	}

	private Playlist findPlaylistWith(BeerStyle beerStyle){
		return spotifyService.getPlaylistByName(beerStyle.getName());
	}	
}
