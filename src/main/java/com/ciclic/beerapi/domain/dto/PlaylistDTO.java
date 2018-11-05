package com.ciclic.beerapi.domain.dto;

import java.io.Serializable;
import java.util.List;

public class PlaylistDTO implements Serializable{

	private static final long serialVersionUID = 387567319113641373L;

	private String name;
	
	private List<TrackDTO> tracks;

	public PlaylistDTO(String name, List<TrackDTO> tracks) {
		this.name = name;
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public List<TrackDTO> getTracks() {
		return tracks;
	}

}
