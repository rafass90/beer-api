package com.ciclic.beerapi.repository;

public class ResourceDuplicatedException extends Exception {

	private static final long serialVersionUID = 4223366420353631429L;

	public ResourceDuplicatedException(String message) {
		super(message);
	}
}
