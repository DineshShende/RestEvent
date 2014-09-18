package com.projectx.rest.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;

@Component
public interface CustomerQuickRegisterRepository {

	 CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer) throws Exception;
	
	 CustomerQuickRegisterEntity findByEmail(String email);
	
	 CustomerQuickRegisterEntity findByMobile(Long mobile);
	 
	 List<CustomerQuickRegisterEntity> findAll();
	 
	 int countByEmail(String email);
	 
	 int countByMobile(Long mobile);
	 
	 Long deleteByEmail(String email);
	 
	 Long deleteByMobile(Long mobile);
	
	 void clearCustomerQuickRegister(); 
	 
	
/*
	 void clearCustomerQuickRegister();
	 Boolean checkIfAlreadyExist(CustomerQuickRegisterKey key);
	 CustomerQuickRegisterEntity getByKey(CustomerQuickRegisterKey key);
*/	 
		
	 
	
}
