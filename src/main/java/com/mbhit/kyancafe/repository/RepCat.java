package com.mbhit.kyancafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbhit.kyancafe.entity.CategoryForFilter;

public interface RepCat extends JpaRepository<CategoryForFilter, Integer>{

}
