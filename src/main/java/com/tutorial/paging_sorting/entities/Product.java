package com.tutorial.paging_sorting.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_products")
public class Product {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private int quality;
	
	private long price;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String name, int quality, long price) {
		super();
		this.id = id;
		this.name = name;
		this.quality = quality;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
}
