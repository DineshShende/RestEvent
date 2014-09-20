package com.projectx.rest.services;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

public interface CustomerQuickRegisterService {
	
	String checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntityDTO populateStatus(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(CustomerQuickRegisterEntityDTO customer);
	
	CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer) throws Exception;
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
	
	Long generateEmailHash(CustomerQuickRegisterEntityDTO customer);
	
	Integer genarateMobilePin(CustomerQuickRegisterEntityDTO customer);
	
	String composeSMS(CustomerQuickRegisterEntity customer);
	
	String composeEmail(CustomerQuickRegisterEntity customer);
	
	Boolean sendPinSMS();
	
	Boolean sendHashEmail();
	
	Boolean verifyEmail(String email,Long emailHash);
	
	Boolean verifyMobile(Long mobile,Integer mobilePin);
	
	void clearDataForTesting();
	

}
