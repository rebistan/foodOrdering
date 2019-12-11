package com.mbhit.kyancafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbhit.kyancafe.entity.OrderItems;

public interface HistoryRepository  extends JpaRepository<OrderItems, Integer>{

}
