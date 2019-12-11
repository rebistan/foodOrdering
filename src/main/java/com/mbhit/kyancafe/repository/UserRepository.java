package com.mbhit.kyancafe.repository;

import org.springframework.data.repository.CrudRepository;

import com.mbhit.kyancafe.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByPhone(String qid);
	User findByPhoneIgnoreCase(String phone);

	/*
	 * @Query("from User where qid=:qid") User findQid(String qid);
	 */
	
}
