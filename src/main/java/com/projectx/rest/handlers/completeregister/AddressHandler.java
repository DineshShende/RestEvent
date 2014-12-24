package com.projectx.rest.handlers.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.repository.completeregister.AddressRepository;
import com.projectx.rest.services.completeregister.AddressService;

@Component
@Profile(value="Dev")
public class AddressHandler implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	
	@Override
	public Address save(Address address) {
		
		Address savedEntity=addressRepository.save(address);
		
		return savedEntity;		
	}

	@Override
	public Address findById(Long addressId) {
		
		Address fetchedEntity=addressRepository.findOne(addressId);

		return fetchedEntity;
	}

	@Override
	public Boolean deleteById(Long addressId) {

		Boolean updateStatus=addressRepository.deleteById(addressId);
		
		return updateStatus;
		
	}

}
