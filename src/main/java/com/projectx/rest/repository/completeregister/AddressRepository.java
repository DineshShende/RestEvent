package com.projectx.rest.repository.completeregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.Address;

@Repository
public interface AddressRepository {

	Address save(Address address);
	
	Address findOne(Long addressId);
	
	Boolean deleteById(Long addressId);
	
}
