package com.mbhit.kyancafe.repository;

import org.springframework.data.repository.CrudRepository;

import com.mbhit.kyancafe.entity.ConfirmationToken;

public interface ConfirmationRepository extends CrudRepository<ConfirmationToken,Integer> {

	ConfirmationToken findByConfirmationToken(String confirmationToken);

}
