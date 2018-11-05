package com.ciclic.beerapi.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ciclic.beerapi.controller.PubHandler;

@Configuration
public class PubRouter {

	@Bean
	public RouterFunction<ServerResponse> route(PubHandler pubHandler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/api/v1/beers/temperatures/")
						.and(RequestPredicates
								.accept(MediaType.APPLICATION_JSON_UTF8)), pubHandler::findBeerByTemperatures);
	}
}