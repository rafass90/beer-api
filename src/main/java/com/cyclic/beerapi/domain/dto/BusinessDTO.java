package com.cyclic.beerapi.domain.dto;

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

	public void setBeerStyle(String beerStyle) {
		this.beerStyle = beerStyle;
	}

	public PlaylistDTO getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlaylistDTO playlist) {
		this.playlist = playlist;
	}

}