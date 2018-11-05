package com.ciclic.beerapi.domain.exception;

public class ResourceNotFoundException extends Exception {
	
	private static final long serialVersionUID = -7868343574559115394L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
