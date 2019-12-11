package com.mbhit.kyancafe.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mbhit.kyancafe.entity.Category;

public interface Categoryrepository extends JpaRepository<Category, Integer> {

	
	
}
