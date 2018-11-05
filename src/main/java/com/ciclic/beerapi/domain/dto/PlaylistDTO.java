package com.ciclic.beerapi.domain.dto;

import java.util.List;

public class PlaylistDTO {

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
