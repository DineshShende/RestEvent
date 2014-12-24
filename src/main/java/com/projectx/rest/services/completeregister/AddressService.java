package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.Address;

@Service
public interface AddressService {

	Address save(Address address);
	
	Address findById(Long addressId);
	
	Boolean deleteById(Long addressId);	
	
}
