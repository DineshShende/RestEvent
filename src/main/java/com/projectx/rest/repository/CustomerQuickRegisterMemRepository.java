package com.projectx.rest.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterKey;

@Repository
@Profile("Test")
public class CustomerQuickRegisterMemRepository implements
		CustomerQuickRegisterRepository {

	Map<CustomerQuickRegisterKey, CustomerQuickRegisterEntity> customerList;

	public CustomerQuickRegisterMemRepository() {

		this.customerList = new HashMap<CustomerQuickRegisterKey, CustomerQuickRegisterEntity>();
	}

	@Override
	public CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer) {
		return customerList.put(customer.getKey(), customer);
	}

	@Override
	public Boolean checkIfAlreadyExist(CustomerQuickRegisterKey key) {
		
		if (customerList.containsKey(key))
			return true;
		else
			return false;
		
		

	}

	@Override
	public CustomerQuickRegisterEntity getByKey(CustomerQuickRegisterKey key) {
		
		return customerList.get(key);
		
	}

//	@Override
	public void clearCustomerQuickRegister() {
		customerList.clear();
		
	}

	
	
	
}
