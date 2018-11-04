package com.cyclic.beerapi.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spotify")
public class SpotifyProperties {

	private String clientID;
	
	private String clientSecret;
	
	private String redirectURI;
	
	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectURI() {
		return redirectURI;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
	
}