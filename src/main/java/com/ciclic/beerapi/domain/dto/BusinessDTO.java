package com.ciclic.beerapi.domain.dto;

import java.io.Serializable;

public class BusinessDTO implements Serializable{

	private static final long serialVersionUID = 6696723584906705378L;

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