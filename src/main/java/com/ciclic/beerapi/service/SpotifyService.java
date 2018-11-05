package com.ciclic.beerapi.service;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciclic.beerapi.domain.dto.PlaylistDTO;
import com.ciclic.beerapi.domain.dto.TrackDTO;
import com.ciclic.beerapi.domain.vo.BeerStyle;
import com.ciclic.beerapi.repository.spotify.SpotifyRepository;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
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
		PlaylistSimplified playlistByName;
		Stream<Track> tracksByPlaylist;

		try {
			playlistByName = spotifyRepository.getPlaylistByName(beerStyle.getName());
			tracksByPlaylist = spotifyRepository.getTracksByPlaylist(playlistByName.getId());
		} catch (SpotifyWebApiException | IOException e) {
			e.printStackTrace();
			return null;
		}
		
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
