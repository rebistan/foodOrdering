package com.mbhit.kyancafe.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property = "id")
public class Product {

	@Id
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "name")
	private String productName;

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	@Column(name = "price")
	private double price=0;
	@Column(name="offer")
	private int offer;
	@Column(name="image")
	private String imageurl;
	
    public Product(int productId, String productName, double price, Category categoryId, String imageurl,int offer) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.categoryId = categoryId;
		this.imageurl = imageurl;
		this.offer=offer;

	}

	@ManyToOne
	  @JoinColumn(name = "category_id")
	
	private Category categoryId;
	
	
	public Product() {
	
	}

	
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}


}
