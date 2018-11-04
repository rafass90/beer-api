package com.cyclic.beerapi.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.dto.DTO;
import com.cyclic.beerapi.dto.PlaylistDTO;
import com.cyclic.beerapi.dto.TrackDTO;
import com.cyclic.beerapi.spotify.SpotifyService;
import com.cyclic.beerapi.vo.BeerStyle;
import com.cyclic.beerapi.vo.BeerStyleWithTempDifference;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

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
		PlaylistDTO playlist = findPlaylistWith(beerStyle);
		
		DTO dto = new DTO(beerStyle.getName(), playlist);
		LOGGER.info("BeerStyle Style: " + beerStyle.getName() + "\nPlaylist: " + playlist.getName());
		return dto;
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

	private PlaylistDTO findPlaylistWith(BeerStyle beerStyle){
		PlaylistSimplified playlistByName = spotifyService.getPlaylistByName(beerStyle.getName());
		Stream<Track> tracksByPlaylist = spotifyService.getTracksByPlaylist(playlistByName.getId());
		
		return new PlaylistDTO(playlistByName.getName(), convertToTrackDTOList(tracksByPlaylist));
	}

	private List<TrackDTO> convertToTrackDTOList(Stream<Track> tracksByPlaylist) {
		return tracksByPlaylist
			.map(t -> {
				return new TrackDTO(
				t.getArtists()[0].getName(),
				t.getName(),
				t.getHref());
			})
			.collect(toList());
	}	
}
