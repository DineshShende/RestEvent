package com.projectx.rest.services;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

public interface CustomerQuickRegisterService {
	
	Boolean checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntityDTO populateStatus(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer) throws Exception;
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
	
	CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(CustomerQuickRegisterEntityDTO customer);
	
	Long generateEmailHash(CustomerQuickRegisterEntityDTO customer);
	
	Integer genarateMobilePin(CustomerQuickRegisterEntityDTO customer);
	
	String composeSMS(CustomerQuickRegisterEntity customer);
	
	String composeEmail(CustomerQuickRegisterEntity customer);
	
	Boolean sendPinSMS();
	
	Boolean sendHashEmail();
	
	Boolean verifyEmail(String email,Long emailHash);
	
	Boolean verifyMobile(Long mobile,Integer mobilePin);
	

}
