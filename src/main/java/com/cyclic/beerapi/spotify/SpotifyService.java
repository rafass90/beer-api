package com.cyclic.beerapi.spotify;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Service
public class SpotifyService {
	private final static Logger LOGGER = Logger.getLogger(SpotifyService.class.getName());

	private SpotifyProperties spotifyProperties;

	private SpotifyApi spotifyApi;

	@Autowired
	public SpotifyService(SpotifyProperties spotifyProperties) throws SpotifyWebApiException, IOException {
		this.spotifyProperties = spotifyProperties;

		LOGGER.info(this.spotifyProperties.getClientID());
		LOGGER.info(this.spotifyProperties.getClientSecret());

		spotifyApi = new SpotifyApi.Builder()
				.setClientId(this.spotifyProperties.getClientID())
				.setClientSecret(this.spotifyProperties.getClientSecret())
				.setRedirectUri(URI.create(this.spotifyProperties.getRedirectURI()))
				.build();

		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
		spotifyApi.setAccessToken(clientCredentials.getAccessToken());

		spotifyApi = spotifyApi;
	}

	public String getPlaylist_Sync() {
		String any = null;
		try {
			final Paging<PlaylistSimplified> playlist = spotifyApi.searchPlaylists("RAP").market(CountryCode.BR).build()
					.execute();

			LOGGER.info(String.valueOf(playlist.getTotal()));
			LOGGER.info(String.valueOf(playlist.getItems()[0]));

			any = String.valueOf(playlist.getItems()[0]);
		} catch (IOException | SpotifyWebApiException e) {
			LOGGER.warning(e.getMessage());
		}
		return any;
	}
}
