package com.mbhit.kyancafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbhit.kyancafe.entity.PlaceOrder;
import com.mbhit.kyancafe.repository.DeliveryRepository;
import com.mbhit.kyancafe.repository.OrderRepository;

@RestController
@RequestMapping("/kyancafe")
public class DeliveryController {

	@Autowired
	DeliveryRepository deliveryRepository;
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping("delivery")
	public PlaceOrder addDelivery(PlaceOrder del)
	
	{  
		Integer oid = orderRepository.findOrderId();
		if(del.getPm().equals("TAKEAWAY")) {
			del.setCar("NA");
			del.setTable("NA");
			del.setNum(oid);
			return deliveryRepository.save(del);
		
	}
		else if(del.getPm().equals("DINEIN")) {
			del.setCar("NA");
			del.setTakeaway("NA");
			del.setNum(oid);
			return deliveryRepository.save(del);
		
	}
		else
			del.setTable("NA");
		    del.setTakeaway("NA");
		    del.setNum(oid);
		return deliveryRepository.save(del);
		
	}
}
