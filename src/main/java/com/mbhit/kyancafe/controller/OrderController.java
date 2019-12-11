package com.mbhit.kyancafe.controller;

import java.sql.Timestamp;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mbhit.kyancafe.entity.OrderItems;
import com.mbhit.kyancafe.entity.Orders;
import com.mbhit.kyancafe.entity.User;
import com.mbhit.kyancafe.repository.OrderRepository;
import com.mbhit.kyancafe.repository.UserRepository;
import com.mbhit.kyancafe.util.CafeUtils;
import com.mbhitu.kyancafe.model.CustomerInfo;
import com.mbhitu.kyancafe.model.ItemInfo;
import com.mbhitu.kyancafe.model.ShoppingCart;

/**
 * @author REBISTAN
 * MBH Information Technology
 */

@RestController
@RequestMapping("/kyancafe")
public class OrderController {

	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	


	public UserRepository getUserRepositor() {
		return userRepository;
	}

	public void setUserRepositor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private SessionFactory sessionFactory;

	@Autowired
	public OrderController(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.sessionFactory = factory.unwrap(SessionFactory.class);
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
/*
 * --------------CHECKOUT-------------------------------
 */
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String checkout(HttpServletRequest httpServletRequest, Model map) {

		ShoppingCart cart = CafeUtils.getOrderFromSession(httpServletRequest);

		if (cart.isEmpty()) {
			return "redirect:shoppingCart";
		}

		CustomerInfo customerInfo = new CustomerInfo();
		map.addAttribute("customerForm", customerInfo);

		return "checkout";

	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkout(HttpServletRequest httpServletRequest, Model map,

			@ModelAttribute("customerForm") CustomerInfo customerForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "checkout";
		}

		ShoppingCart cart = CafeUtils.getOrderFromSession(httpServletRequest);
 
		cart.setCustomerInfo(customerForm);

		return "redirect:/orderConfirmation";

	}
/*
 * -----------------ORDER CONFIRMATION--------------------
 */
	@RequestMapping(value = "orderConfirmation", method = RequestMethod.GET)
	public ResponseEntity<ShoppingCart> orderConfirmation(HttpServletRequest httpServletRequest, ModelMap map) {

		ShoppingCart cart = CafeUtils.getOrderFromSession(httpServletRequest);
		if (cart.isEmpty()) {
			return ResponseEntity.ok(cart);
		}

		map.addAttribute("cart", cart);

		return ResponseEntity.ok(cart);

	}

	/*
	 * --------------------PLACE ORDER-------------------------
	 */
	
	@RequestMapping(value = "placeOrder", method = RequestMethod.GET)
	public String placeOrder(@Valid HttpServletRequest httpServletRequest, Model model) throws NotReadablePropertyException{
		
		ShoppingCart cart = CafeUtils.getOrderFromSession(httpServletRequest);
	try {
		if (cart.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		this.saveOrder(cart);
	    this.orderRepository.save(cart);
		
		CafeUtils.removeOrderFromSession(httpServletRequest);
		model.addAttribute("orderId", cart.getOrderNumber());
		return "orderPlaced";
	}
	catch (NotReadablePropertyException ex) {
	    throw new IllegalArgumentException(ex.getMessage());
	  }
	}
	
	private int getMaxOrderNum() {
		int value=orderRepository.findOrderId();
		return value;
		
	}
	
	public void saveOrder(ShoppingCart cart) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		User user =null;
		if(this.getUser(cart.getCustomerInfo().getPhone())==null) {
			user = new User();
			user.setFirstName(cart.getCustomerInfo().getfName());
			user.setPhone(cart.getCustomerInfo().getPhone());
			session.persist(user);
		}
		else
		{
			user = this.getUser(cart.getCustomerInfo().getPhone());
		}
			Orders order = new Orders(user);
		    order.setOrderId(this.getMaxOrderNum()+1);
			order.setPrice(cart.getTotalPrice());
			order.setDate(new Timestamp(System.currentTimeMillis()));	
			session.persist(order);
			
			for (ItemInfo info : cart.getCartItem()) {
				OrderItems items = new OrderItems(order);
			//	Ord o=new Ord();
				items.setQuantity(info.getQuantity());
				items.setName(info.getProductInfo().getProductName());
				
			/*
			 * o.setName(items.getName()); o.setOrderId(order.getOrderId());
			 * o.setQuantity(items.getQuantity());
			 * o.setPrice((items.getQuantity()*info.getProductInfo().getPrice())-info.
			 * getDeduction()); o.setDate(new Timestamp(System.currentTimeMillis()));
			 * session.persist(o);
			 */
				session.persist(items);
					
			
			}
			transaction.commit();
			session.close();	
			cart.setOrderNumber(order.getOrderId());
		
	}
	
	private User getUser(String a) {
		User user = userRepository.findByPhone(a);
		return (User) user;
	}
	
}
