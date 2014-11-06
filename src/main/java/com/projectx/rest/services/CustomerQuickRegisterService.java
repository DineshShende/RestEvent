package com.projectx.rest.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.projectx.data.domain.LoginVerificationDTO;
import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;

import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerQuickRegisterStringStatusEntity;

public interface CustomerQuickRegisterService {
	
	CustomerQuickRegisterStringStatusEntity checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity populateStatus(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(CustomerQuickRegisterEntity customer);
	
	CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer) throws Exception;
	
	CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(Long customerId);
	
	Integer updateEmailHash(Long customerId);
	
	Integer updateMobilePin(Long customerId);
	
	Boolean verifyEmailHash(Long customerId,String emailHash);
	
	Boolean verifyMobilePin(Long customerId,Integer mobilePin);
			
	String composeSMSWithMobilePin(CustomerQuickRegisterEntity customer);
	
	String composeEmailWithEmailHash(CustomerQuickRegisterEntity customer);
	
	Boolean sendPinSMS(CustomerQuickRegisterEntity customer) throws UnirestException;
	
	Boolean sendHashEmail(CustomerQuickRegisterEntity customer);
	
	CustomerQuickRegisterStatusEntity sendVerificationDetails(CustomerQuickRegisterEntity customer) throws UnirestException;
	
	Boolean reSendEmailHash(Long customerId);
	
	Boolean reSendMobilePin(Long customerId) throws UnirestException;
	
	
	//Testing
	void clearDataForTesting();

	String composeMessageWithPassword(CustomerQuickRegisterEntity customer,CustomerAuthenticationDetails authenticationDetails);

	Boolean sendPasswordSMS(Long mobile,String message) ;

	Boolean sendPasswordEmail(String email,String message);

	//Boolean sendDefaultPassword(CustomerQuickRegisterEntity customer) throws UnirestException;

	Boolean resetPassword(CustomerIdDTO customerIdDTO);

	Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO);

	CustomerAuthenticationDetails saveCustomerAuthenticationDetails(
			CustomerQuickRegisterEntity entity);

	CustomerAuthenticationDetails getLoginDetailsByCustomerId(Long customerId);

	Boolean sendDefaultPassword(CustomerQuickRegisterEntity customer,
			Boolean resetFlag);
	
	
	CustomerAuthenticationDetails verifyLoginDetails(LoginVerificationDTO loginVerificationDTO);
	
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
	
	Boolean forgotPassword(String entity);
	
}