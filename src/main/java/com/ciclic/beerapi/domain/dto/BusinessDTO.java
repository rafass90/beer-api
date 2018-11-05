package com.ciclic.beerapi.domain.dto;

public class BusinessDTO {

	private String beerStyle;
	
	private PlaylistDTO playlist;
	
	public BusinessDTO(String beerStyle, PlaylistDTO playlist) {
		this.beerStyle = beerStyle;
		this.playlist = playlist;
	}

	public String getBeerStyle() {
		return beerStyle;
	}

	public PlaylistDTO getPlaylist() {
		return playlist;
	}

}