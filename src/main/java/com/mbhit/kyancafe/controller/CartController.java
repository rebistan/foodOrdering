package com.mbhit.kyancafe.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbhit.kyancafe.config.ResourceNotFoundException;
import com.mbhit.kyancafe.entity.Product;
import com.mbhit.kyancafe.repository.MenuRepository;
import com.mbhit.kyancafe.util.CafeUtils;
import com.mbhitu.kyancafe.model.ProductInfo;
import com.mbhitu.kyancafe.model.ShoppingCart;

/**
 * @author REBISTAN
 *MBH Information Technology
 */

@RestController
@RequestMapping("/kyancafe")
public class CartController {

	@Autowired
	MenuRepository menuRepository;
	
	/*
	 * ------------ADD ITEMS TO CART---------------
	 */
	
	@GetMapping("addCart")
	public ShoppingCart addCart(@RequestParam(value="productId") int productId,@RequestParam(value="quantity") int quantity,HttpServletRequest httpServletRequest)
			throws ResourceNotFoundException{
		
		Product product=menuRepository.getProductDetails(productId);
		ProductInfo productInfo = new ProductInfo(product);
		ShoppingCart cart=new ShoppingCart();
		cart = CafeUtils.getOrderFromSession(httpServletRequest);
		cart.addItemToCart(productInfo, quantity);
		return cart;	 
	}
	/*
	 * ---------------VIEW CART----------------------------
	 */
	
	@RequestMapping(value = "viewCart", method = RequestMethod.GET)
	public ShoppingCart shoppingCart(HttpServletRequest httpServletRequest, ModelMap map) {
		ShoppingCart cart = CafeUtils.getOrderFromSession(httpServletRequest);
		map.addAttribute("cart", cart);
		return cart;
	}
	
	/*
	 * -------------------REMOVE All ITEMS FFROM CART---------
	 */
	
	@RequestMapping(value = "deleteCart", method = RequestMethod.GET)
	public ShoppingCart delete(@RequestParam(value = "code", required = true) String code,
			HttpServletRequest httpServletRequest) {

		ShoppingCart cart = CafeUtils.getOrderFromSession(httpServletRequest);
		cart.removeItemFromCart(code);
		return cart;
	}
	
	/*
	 * ----------------UDATE CART ITEMS-------------------
	 */
	
	@PutMapping("/updateCart")
	public ShoppingCart updateCart(@RequestParam(value="productId") int productId,@RequestParam(value="quantity") int quantity,HttpServletRequest httpServletRequest) {
		Product product =menuRepository.getProductDetails(productId);
		ProductInfo productInfo = new ProductInfo(product);
		ShoppingCart cart=CafeUtils.getOrderFromSession(httpServletRequest);
		cart.updateItemToCart(productInfo,quantity);
				
		return cart;
		
	}
}
