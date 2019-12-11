package com.mbhitu.kyancafe.model;


import com.mbhit.kyancafe.entity.Product;

public class ProductInfo {

	private String productCode;
	private String productName;
	private double price;
    private int offer;
	private String imageurl;
	private int categoryId;

	public ProductInfo(Product product) {

		this.productCode = String.valueOf(product.getProductId());
		this.productName = product.getProductName();
		this.price = product.getPrice();
		this.categoryId = product.getCategoryId().getCategoryId();
		this.imageurl=product.getImageurl();
		this.offer=product.getOffer();

	}
	


	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
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

	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
