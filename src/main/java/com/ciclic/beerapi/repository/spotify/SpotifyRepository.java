package com.ciclic.beerapi.repository.spotify;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;

@Service
public class SpotifyRepository{

	private SpotifyApi spotifyApi;

	@Autowired
	public SpotifyRepository(SpotifyApi spotifyApi) throws SpotifyWebApiException, IOException {
		this.spotifyApi = spotifyApi;
	}

	public PlaylistSimplified getPlaylistByName(String name) throws SpotifyWebApiException, IOException {
		PlaylistSimplified playlistSimplified = null;
		Paging<PlaylistSimplified> playlist = spotifyApi.searchPlaylists(name)
				.market(CountryCode.BR)
				.limit(1)
				.build()
				.execute();
		
		playlistSimplified = playlist.getItems()[0];

		return playlistSimplified;
	}

	public Stream<Track> getTracksByPlaylist(String playlistId) throws SpotifyWebApiException, IOException {
		Stream<Track> tracks = null;
		Paging<PlaylistTrack> pTracks = spotifyApi.getPlaylistsTracks(playlistId)
				.market(CountryCode.BR)
				.build()
				.execute();
		
		tracks = Arrays.asList(pTracks.getItems())
				.parallelStream()
				.map(PlaylistTrack::getTrack);
		
		return tracks;
	}
}
