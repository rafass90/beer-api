package com.ciclic.beerapi.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclic.beerapi.domain.dto.BusinessDTO;
import com.ciclic.beerapi.domain.dto.PlaylistDTO;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.domain.vo.BeerStyleWithTempDifference;

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

	public BusinessDTO beerWithMusic(Double temperature) {
		BeerStyle beerStyle = suggestIdealBeer(temperature);
		if(beerStyle == null) {
			return null;
		}
		PlaylistDTO playlist = spotifyService.findPlaylistWith(beerStyle);

		BusinessDTO businessDTO = new BusinessDTO(beerStyle.getName(), playlist);
		LOGGER.info("BeerStyle Style: " + beerStyle.getName() + "\nPlaylist: " + playlist.getName());
		return businessDTO;
	}

	public BeerStyle suggestIdealBeer(Double temperature) {
		List<BeerStyle> beerStyles = beerStyleService.listAll();

		BeerStyle beerStyle = findFirstBeerStyle(beerStyles, temperature);

		return beerStyle;
	}

	private BeerStyle findFirstBeerStyle(List<BeerStyle> beerStyles, Double temperature) {
		return beerStyles
				.stream()
				.map(b -> new BeerStyleWithTempDifference(b, temperature))
				.sorted()
				.map(bW -> bW.getBeer())
				.findFirst()
				.orElse(null);
	}

}
