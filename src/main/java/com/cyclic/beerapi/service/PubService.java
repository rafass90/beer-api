package com.cyclic.beerapi.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.domain.dto.DTO;
import com.cyclic.beerapi.domain.dto.PlaylistDTO;
import com.cyclic.beerapi.domain.vo.BeerStyle;
import com.cyclic.beerapi.domain.vo.BeerStyleWithTempDifference;

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
	
	public DTO beerWithMusic(Double temperature) {
		BeerStyle beerStyle = suggestIdealBeer(temperature);
		PlaylistDTO playlist = spotifyService.findPlaylistWith(beerStyle);
		
		DTO dto = new DTO(beerStyle.getName(), playlist);
		LOGGER.info("BeerStyle Style: " + beerStyle.getName() + "\nPlaylist: " + playlist.getName());
		return dto;
	}

	public BeerStyle suggestIdealBeer(Double temperature){
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
	
}
