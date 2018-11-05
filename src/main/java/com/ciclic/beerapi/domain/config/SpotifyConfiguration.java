package com.ciclic.beerapi.domain.config;

import java.io.IOException;
import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Component
@ConfigurationProperties(prefix = "spotify")
public class SpotifyConfiguration{

	private String clientID;

	private String clientSecret;

	private String redirectURI;

	@Bean
	public SpotifyApi spotifyApi() throws SpotifyWebApiException, IOException {
		SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(this.clientID)
				.setClientSecret(this.clientSecret)
				.setRedirectUri(URI.create(this.redirectURI)).build();

		ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
		final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
		spotifyApi.setAccessToken(clientCredentials.getAccessToken());
		return spotifyApi;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
	
}