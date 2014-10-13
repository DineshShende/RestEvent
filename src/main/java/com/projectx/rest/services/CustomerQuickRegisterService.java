package com.projectx.rest.services;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

public interface CustomerQuickRegisterService {
	
	String checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntityDTO populateStatus(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(CustomerQuickRegisterEntityDTO customer);
	
	CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer) throws Exception;
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(Long customerId);
	
	Integer updateEmailHash(Long customerId);
	
	Integer updateMobilePin(Long customerId);
	
	Boolean verifyEmailHash(Long customerId,String emailHash);
	
	Boolean verifyMobilePin(Long customerId,Integer mobilePin);
	
	//String generateEmailHash();
	
	//Integer genarateMobilePin();
	
	String composeSMS(CustomerQuickRegisterEntity customer);
	
	String composeEmail(CustomerQuickRegisterEntity customer);
	
	void sendPinSMS(CustomerQuickRegisterEntity customer);
	
	void sendHashEmail(CustomerQuickRegisterEntity customer);
	
	
	//Testing
	void clearDataForTesting();
	
	//CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	//CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
		

}
