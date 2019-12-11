
package com.mbhit.kyancafe.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mbhit.kyancafe.config.ResourceNotFoundException;
import com.mbhit.kyancafe.entity.Ord;
import com.mbhit.kyancafe.entity.Orders;
import com.mbhit.kyancafe.entity.User;
import com.mbhit.kyancafe.repository.HistoryRepository;
import com.mbhit.kyancafe.repository.RepOrd;
import com.mbhit.kyancafe.repository.OrderRepository;
import com.mbhit.kyancafe.repository.UserRepository;

@RestController
@RequestMapping("/kyancafe")
public class HistoryController {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	RepOrd ordRep;

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@GetMapping("Orders/all")
	public List<Orders> getAll() {
		return orderRepository.findAll();
	}
	@GetMapping("/orders/particular/{user_id}")
	public Optional<User> getUserById(
			 @PathVariable(value = "user_id") int user_id)
			throws ResourceNotFoundException {
		Optional<User> user = userRepository.findById(user_id);			
		return user;
	}
	
	@GetMapping("orders/orderDetails")
	public List<Ord> orderDetails(@RequestParam("id") int id) {
		return ordRep.findByOrderId(id);
		
	}
	
	}
	

