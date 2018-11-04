package com.cyclic.beerapi.repository.spotify;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyclic.beerapi.domain.config.SpotifyProperties;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Service
public class SpotifyRepository{
	private final static Logger LOGGER = Logger.getLogger(SpotifyRepository.class.getName());

	private SpotifyProperties spotifyProperties;

	private SpotifyApi spotifyApi;

	@Autowired
	public SpotifyRepository(SpotifyProperties spotifyProperties) throws SpotifyWebApiException, IOException {
		this.spotifyProperties = spotifyProperties;

		spotifyApi = new SpotifyApi.Builder()
				.setClientId(this.spotifyProperties.getClientID())
				.setClientSecret(this.spotifyProperties.getClientSecret())
				.setRedirectUri(URI.create(this.spotifyProperties.getRedirectURI()))
				.build();

		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
		spotifyApi.setAccessToken(clientCredentials.getAccessToken());
	}

	public PlaylistSimplified getPlaylistByName(String name) {
		PlaylistSimplified playlistSimplified = null;
		try {
			Paging<PlaylistSimplified> playlist = spotifyApi.searchPlaylists(name)
					.market(CountryCode.BR)
					.limit(1)
					.build()
					.execute();
			
			playlistSimplified = playlist.getItems()[0];

		} catch (IOException | SpotifyWebApiException e) {
			LOGGER.warning(e.getMessage());
		}
		return playlistSimplified;
	}

	public Stream<Track> getTracksByPlaylist(String playlistId) {
		Stream<Track> tracks = null;
		try {
			Paging<PlaylistTrack> pTracks = spotifyApi.getPlaylistsTracks(playlistId)
					.market(CountryCode.BR)
					.build()
					.execute();
			
			tracks = Arrays.asList(pTracks.getItems())
					.parallelStream()
					.map(PlaylistTrack::getTrack);
		} catch (IOException | SpotifyWebApiException e) {
			LOGGER.warning(e.getMessage());
		}
		
		return tracks;
	}
}
