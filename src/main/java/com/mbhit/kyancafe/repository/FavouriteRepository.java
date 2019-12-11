package com.mbhit.kyancafe.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mbhit.kyancafe.entity.Favourites;


public interface FavouriteRepository extends JpaRepository<Favourites, Integer> {

	List<Favourites> getByUserid(Integer id);

	
}

