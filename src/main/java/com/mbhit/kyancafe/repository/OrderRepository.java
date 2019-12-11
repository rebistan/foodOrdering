
package com.mbhit.kyancafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbhit.kyancafe.entity.Orders;
import com.mbhitu.kyancafe.model.ShoppingCart;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

	void save(ShoppingCart cart);

	@Query("Select max(o.orderId) as value  from Orders o")
	Integer findOrderId();

	/*
	 * @Query("from Orders where orderId=:orderId") Orders getOrderDetails(int
	 * orderId);
	 */
	
}
