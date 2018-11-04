package com.cyclic.beerapi.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.dto.PlaylistDTO;
import com.cyclic.beerapi.dto.TrackDTO;
import com.cyclic.beerapi.spotify.SpotifyRepository;
import com.cyclic.beerapi.vo.BeerStyle;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

@Service
public class SpotifyService {

	private SpotifyRepository spotifyRepository;
	
	@Autowired
	public SpotifyService(SpotifyRepository spotifyRepository) {
		this.spotifyRepository = spotifyRepository;
	}
	
	public PlaylistDTO findPlaylistWith(BeerStyle beerStyle){
		PlaylistSimplified playlistByName = spotifyRepository.getPlaylistByName(beerStyle.getName());
		Stream<Track> tracksByPlaylist = spotifyRepository.getTracksByPlaylist(playlistByName.getId());
		
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
