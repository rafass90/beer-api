package com.cyclic.beerapi.dto;

public class DTO {

	private String beerStyle;
	
	private PlaylistDTO playlist;
	
	public DTO(String beerStyle, PlaylistDTO playlist) {
		super();
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