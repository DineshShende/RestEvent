package com.projectx.rest.repositoryfixture.completeregister;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.repository.completeregister.AddressRepository;

@Component
@Profile(value="Test")
public class AddressMemRepository implements AddressRepository {

	
	Map<Long,Address> addressList=new HashMap<Long,Address>();
	
	@Override
	public Address save(Address address) {
		
		addressList.put(address.getAddressId(), address);
		
		return address;
	}

	@Override
	public Address findOne(Long addressId) {

		Address fetchedEntity=addressList.get(addressId);
		
		return fetchedEntity;
		
	}

	@Override
	public Boolean deleteById(Long addressId) {

		addressList.remove(addressId);
		
		return true;
	}

}
