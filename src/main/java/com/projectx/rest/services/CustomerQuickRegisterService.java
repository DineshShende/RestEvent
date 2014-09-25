package com.projectx.rest.services;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

public interface CustomerQuickRegisterService {
	
	String checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntityDTO populateStatus(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(CustomerQuickRegisterEntityDTO customer);
	
	CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer) throws Exception;
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(Long customerId);
	
	Integer updateEmailHash(Long customerId,Long emailHash);
	
	Integer updateMobilePin(Long customerId,Integer mobilePin);
	
	Boolean verifyEmailHash(Long customerId,Long emailHash);
	
	Boolean verifyMobilePin(Long customerId,Integer mobilePin);
	
	Long generateEmailHash(CustomerQuickRegisterEntityDTO customer);
	
	Integer genarateMobilePin(CustomerQuickRegisterEntityDTO customer);
	
	String composeSMS(CustomerQuickRegisterEntity customer);
	
	String composeEmail(CustomerQuickRegisterEntity customer);
	
	Boolean sendPinSMS();
	
	Boolean sendHashEmail();
	
	
	//Testing
	void clearDataForTesting();
	
	//CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	//CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
		

}
