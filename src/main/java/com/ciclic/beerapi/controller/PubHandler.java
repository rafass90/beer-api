package com.ciclic.beerapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ciclic.beerapi.domain.exception.ResourceNotFoundException;
import com.ciclic.beerapi.service.BeerStyleService;
import com.ciclic.beerapi.service.PubService;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import reactor.core.publisher.Mono;

@Component
public class PubHandler {
	
	private PubService pubService;

	private BeerStyleService beerStyleService;

	@Autowired
	public PubHandler(PubService pubService) {
		this.pubService = pubService;
	}

	public Mono<ServerResponse> findBeerByTemperatures(ServerRequest request) {
		try {
			return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
				.body(BodyInserters.fromObject(pubService.beerWithMusic(1.0)));
		} catch (SpotifyWebApiException | ResourceNotFoundException | IOException e) {
			return ServerResponse.notFound().build();
		}
	}
	
	public Mono<ServerResponse> findBeerAllBeerStyles(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
			.body(BodyInserters.fromObject(beerStyleService.listAll()));
	}
}