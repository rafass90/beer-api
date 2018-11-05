package com.ciclic.beerapi.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclic.beerapi.domain.dto.BusinessDTO;
import com.ciclic.beerapi.domain.dto.PlaylistDTO;
import com.ciclic.beerapi.domain.exception.ResourceNotFoundException;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.domain.vo.BeerStyleWithTempDifference;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

@Service
public class PubService {

	private BeerStyleService beerStyleService;

	private SpotifyService spotifyService;

	@Autowired
	public PubService(BeerStyleService beerStyleService, SpotifyService spotifyService) {
		this.beerStyleService = beerStyleService;
		this.spotifyService = spotifyService;
	}

	public BusinessDTO beerWithMusic(Double temperature) throws ResourceNotFoundException, SpotifyWebApiException, IOException {
		BeerStyle beerStyle = suggestIdealBeer(temperature);
		if(beerStyle == null) {
			throw new ResourceNotFoundException("BeerStyles not found");
		}
		PlaylistDTO playlist = spotifyService.findPlaylistWith(beerStyle);

		BusinessDTO businessDTO = new BusinessDTO(beerStyle.getName(), playlist);
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
