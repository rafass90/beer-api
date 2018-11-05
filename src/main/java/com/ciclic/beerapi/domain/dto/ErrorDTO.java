package com.ciclic.beerapi.domain.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = -5568840316369322893L;

	private HttpStatus httpStatus;
	
	private String message;
	
	public ErrorDTO(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

}
