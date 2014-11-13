package com.projectx.rest.services;


import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerDocument;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerQuickRegisterStringStatusEntity;
import com.projectx.web.domain.LoginVerificationDTO;

public interface CustomerQuickRegisterService {
	
	CustomerQuickRegisterStringStatusEntity checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity populateVerificationFields(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(CustomerQuickRegisterEntity customer);
	
	CustomerMobileVerificationDetails createCustomerMobileVerificationEntity(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerEmailVerificationDetails createCustomerEmailVerificationEntity(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerAuthenticationDetails createCustomerAuthenticationDetails(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerQuickRegisterEmailMobileVerificationEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer) throws Exception;
	
	CustomerEmailVerificationDetails saveCustomerEmailVerificationDetails(CustomerEmailVerificationDetails emailVerificationDetails);
	
	CustomerQuickRegisterEntity saveCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerMobileVerificationDetails saveCustomerMobileVerificationDetails(CustomerMobileVerificationDetails mobileVerificationDetails);
	
	CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(Long customerId);
	
	Integer updateEmailHash(Long customerId,String email);
	
	Integer updateMobilePin(Long customerId,Long mobile);
	
	Boolean verifyEmailHash(Long customerId,String email,String emailHash);
	
	Boolean verifyMobilePin(Long customerId,Long mobile,Integer mobilePin);
			
	String composeSMSWithMobilePin(CustomerQuickRegisterEntity customer,CustomerMobileVerificationDetails mobileVerificationDetails);
	
	String composeEmailWithEmailHash(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails);
	
	Boolean sendPinSMS(CustomerQuickRegisterEntity customer,CustomerMobileVerificationDetails mobileVerificationDetails);
	
	Boolean sendHashEmail(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails);
	
	CustomerQuickRegisterStatusEntity sendVerificationDetails(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails,CustomerMobileVerificationDetails mobileVerificationDetails);
	
	Boolean reSetEmailHash(Long customerId,String email);
	
	Boolean reSetMobilePin(Long customerId,Long mobile);
	
	Boolean reSendEmailHash(Long customerId,String email);
	
	Boolean reSendMobilePin(Long customerId,Long mobile);

	
	
	//Testing
	void clearDataForTesting();

	String composeMessageWithPassword(CustomerQuickRegisterEntity customer,CustomerAuthenticationDetails authenticationDetails);

	Boolean sendPasswordSMS(Long mobile,String message) ;

	Boolean sendPasswordEmail(String email,String message);


	Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO);

	CustomerAuthenticationDetails saveCustomerAuthenticationDetails(
			CustomerAuthenticationDetails entity);

	CustomerAuthenticationDetails getLoginDetailsByCustomerId(Long customerId);

	Boolean sendDefaultPassword(CustomerQuickRegisterEntity customer,
			Boolean resetFlag);
	
	
	CustomerAuthenticationDetails verifyLoginDetails(LoginVerificationDTO loginVerificationDTO);
	
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
	
	CustomerQuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity);
	
	Boolean resetPassword(CustomerIdDTO customerIdDTO);
	
	CustomerEmailVerificationDetails getCustomerEmailVerificationDetailsByCustomerIdAndEmail(Long customerId,String email);
	
	CustomerMobileVerificationDetails getCustomerMobileVerificationDetailsByCustomerIdAndMobile(Long customerId,Long mobile);
	
	
	//Document
	
	CustomerDocument saveCustomerDocument(CustomerDocument customerDocument);
	
	CustomerDocument getCustomerDocumentById(Long customerId);
	
}