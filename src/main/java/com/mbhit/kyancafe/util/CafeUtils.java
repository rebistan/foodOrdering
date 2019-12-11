package com.mbhit.kyancafe.util;



import javax.servlet.http.HttpServletRequest;

import com.mbhitu.kyancafe.model.ShoppingCart;

public class CafeUtils {

	
	public static ShoppingCart getOrderFromSession(HttpServletRequest httpServletRequest){
		
		ShoppingCart cart = (ShoppingCart) httpServletRequest.getSession().getAttribute("order");
		
		if(cart==null){
			cart =  new ShoppingCart();
			httpServletRequest.getSession().setAttribute("order", cart);
		}
		
		return cart;
		
	}
	
	
	public static void removeOrderFromSession(HttpServletRequest httpServletRequest){
		
		httpServletRequest.getSession().removeAttribute("order");
	}
	
	public static void addLastOrderToSession(HttpServletRequest httpServletRequest){
		
		httpServletRequest.getSession().setAttribute("lastOrder", httpServletRequest.getSession().getAttribute("order"));
	}
	
}
