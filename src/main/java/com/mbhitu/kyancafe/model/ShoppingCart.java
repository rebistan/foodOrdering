package com.mbhitu.kyancafe.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 2110487200031034011L;

	Logger logger = Logger.getLogger(ShoppingCart.class.getName());
    
	
	private int orderNumber;

	private List<ItemInfo> cartItem;
	

	private double subTotal;

	private double totalPrice;

	
	private CustomerInfo customerInfo;
	
	public ShoppingCart() {

		this.cartItem = new ArrayList<ItemInfo>();
		this.totalPrice = 0;
	}
	
	public void addItemToCart(ProductInfo productInfo, int quantity) {

		logger.log(Level.FINE, "Adding item to cart");

		ItemInfo item = this.findItemInCart(productInfo.getProductCode());

		if (item == null) {
			item = new ItemInfo();
			item.setQuantity(0);
			item.setProductInfo(productInfo);
			this.cartItem.add(item);
			
		}

		int newQuantity = item.getQuantity() + quantity;
		if (newQuantity <= 0) {
			this.cartItem.remove(item);
		} else {
			item.setQuantity(newQuantity);
			if(productInfo.getOffer()==0) {
			item.setSubTotal((item.getQuantity()*item.getProductInfo().getPrice()));
			}else
			{  
			    double off=productInfo.getOffer();
				double per=100;
				double s=(off/per);	
				double ded=( (item.getQuantity()*item.getProductInfo().getPrice()));	
				item.setDeduction(ded*s);
				item.setSubTotal(ded-(ded*s));
			}
			}
		
		this.rePrice();
	}

public void updateItemToCart(ProductInfo productInfo, int quantity) {
	ItemInfo itemInfo = this.findItemInCart(productInfo.getProductCode());

	if (itemInfo != null) {
		this.cartItem.remove(itemInfo);
		this.rePrice();
	}
	addItemToCart(productInfo, quantity);
	}

	public double formatPrice(double price)
	  {  
	  return price;
	  
	  }
	 
	public void rePrice(){
		this.subTotal = this.calculatePrice();	
		this.totalPrice = this.subTotal;
	}
	
	public double calculatePrice() {
		double amount = 0;

		for (ItemInfo info : this.cartItem) {

			amount =  amount + info.getSubTotal();
		}		
		return amount;
	}

	public ItemInfo findItemInCart(String productCode) {
		for (ItemInfo info2 : this.cartItem) {
			if (info2.getProductInfo().getProductCode().equalsIgnoreCase(productCode)) {
				return info2;
			}
		}
		return null;
	}

	public void removeItemFromCart(String productCode) {
		ItemInfo itemInfo = this.findItemInCart(productCode);

		if (itemInfo != null) {
			this.cartItem.remove(itemInfo);
			this.rePrice();
		}
	}
	
	public boolean isEmpty() {
		if(this.cartItem==null){
			return true;
		}
		return this.cartItem.isEmpty();
	}

	public List<ItemInfo> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<ItemInfo> cartItem) {
		this.cartItem = cartItem;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}


	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	

}
