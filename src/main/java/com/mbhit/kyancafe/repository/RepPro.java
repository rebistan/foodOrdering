package com.mbhit.kyancafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mbhit.kyancafe.entity.ProductForFilter;

public interface RepPro extends JpaRepository<ProductForFilter, Integer>{
@Query("from ProductForFilter where offer > 0")
	ProductForFilter showOffers();
@Query("from ProductForFilter where productId=:id")
ProductForFilter findById1(Integer id);

}
