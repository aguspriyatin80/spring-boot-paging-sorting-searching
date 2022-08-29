package com.tutorial.paging_sorting.dto;

public class Search {

	private String name;
	private int quality;
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Search(String name, int quality) {
		super();
		this.name = name;
		this.quality = quality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	
	
}
