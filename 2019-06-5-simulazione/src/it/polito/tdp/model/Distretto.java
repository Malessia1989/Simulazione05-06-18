package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {
	
	private int id;
	private LatLng lat;
	
	public Distretto(int id, LatLng lat) {
		super();
		this.id = id;
		this.lat = lat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LatLng getLat() {
		return lat;
	}
	public void setLat(LatLng lat) {
		this.lat = lat;
	}
	
	
	

	
	
	
}
