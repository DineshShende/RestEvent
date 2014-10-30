package com.projectx.rest.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.projectx.rest.domain.CustomerQuickDetailsSentStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

public interface CustomerQuickRegisterService {
	
	String checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity populateStatus(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(CustomerQuickRegisterEntity customer);
	
	CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer) throws Exception;
	
	CustomerQuickDetailsSentStatusEntity handleNewCustomerQuickRegister(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(Long customerId);
	
	Integer updateEmailHash(Long customerId);
	
	Integer updateMobilePin(Long customerId);
	
	Boolean verifyEmailHash(Long customerId,String emailHash);
	
	Boolean verifyMobilePin(Long customerId,Integer mobilePin);
			
	String composeSMSWithMobilePin(CustomerQuickRegisterEntity customer);
	
	String composeEmailWithEmailHash(CustomerQuickRegisterEntity customer);
	
	Boolean sendPinSMS(CustomerQuickRegisterEntity customer) throws UnirestException;
	
	Boolean sendHashEmail(CustomerQuickRegisterEntity customer);
	
	CustomerQuickDetailsSentStatusEntity sendVerificationDetails(CustomerQuickRegisterEntity customer) throws UnirestException;
	
	Boolean reSendEmailHash(Long customerId);
	
	Boolean reSendMobilePin(Long customerId) throws UnirestException;
	
	
	//Testing
	void clearDataForTesting();

	String composeMessageWithPassword(CustomerQuickRegisterEntity customer);

	Boolean sendPasswordSMS(CustomerQuickRegisterEntity customer) throws UnirestException;

	Boolean sendPasswordEmail(CustomerQuickRegisterEntity customer);

	Boolean sendDefaultPassword(CustomerQuickRegisterEntity customer);
	
	//String generateEmailHash();
	
	//Integer genarateMobilePin();

	//CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	//CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
		

}
