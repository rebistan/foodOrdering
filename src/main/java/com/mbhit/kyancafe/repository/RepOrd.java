package com.mbhit.kyancafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbhit.kyancafe.entity.Ord;

public interface RepOrd extends JpaRepository<Ord, Integer> {
	@Query("from Ord where orderId=:orderId")
	List<Ord> findByOrderId(int orderId);
}


