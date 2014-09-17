package com.projectx.rest.repository;

import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterKey;

@Component
public interface CustomerQuickRegisterRepository {

	CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer);
	
	Boolean checkIfAlreadyExist(CustomerQuickRegisterKey key);
	
	CustomerQuickRegisterEntity getByKey(CustomerQuickRegisterKey key);
	
	void clearCustomerQuickRegister();
	
}
