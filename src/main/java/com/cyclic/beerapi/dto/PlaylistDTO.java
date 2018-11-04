package com.cyclic.beerapi.dto;

import java.util.List;

public class PlaylistDTO {

	private String name;
	
	private List<TrackDTO> tracks;

	public PlaylistDTO(String name, List<TrackDTO> tracks) {
		super();
		this.name = name;
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrackDTO> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackDTO> tracks) {
		this.tracks = tracks;
	}
	
}
