package com.mbhit.kyancafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbhit.kyancafe.entity.PlaceOrder;



public interface DeliveryRepository extends JpaRepository<PlaceOrder, Integer>{

}
