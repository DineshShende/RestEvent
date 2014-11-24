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
import com.projectx.web.domain.LoginVerificationWithDefaultEmailPasswordDTO;

public interface CustomerQuickRegisterService {
	
	//QuickRegisterEntitySave
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
	
	
	//Verification Of Entity
	Integer updateEmailHash(Long customerId,String email);
	
	Integer updateMobilePin(Long customerId,Long mobile);
	
	Boolean verifyEmailHash(Long customerId,String email,String emailHash);
	
	Boolean verifyMobilePin(Long customerId,Long mobile,Integer mobilePin);
			
	Boolean reSetEmailHash(Long customerId,String email);
	
	Boolean reSetMobilePin(Long customerId,Long mobile);
	
	Boolean reSendEmailHash(Long customerId,String email);
	
	Boolean reSendMobilePin(Long customerId,Long mobile);

	//Sending Verification Details
	String composeSMSWithMobilePin(CustomerQuickRegisterEntity customer,CustomerMobileVerificationDetails mobileVerificationDetails);
	
	String composeEmailWithEmailHash(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails);
	
	Boolean sendPinSMS(CustomerQuickRegisterEntity customer,CustomerMobileVerificationDetails mobileVerificationDetails);
	
	Boolean sendHashEmail(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails);
	
	CustomerQuickRegisterStatusEntity sendVerificationDetails(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails,CustomerMobileVerificationDetails mobileVerificationDetails);
	

	//LoginAuthentication
	Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO);

	CustomerAuthenticationDetails saveCustomerAuthenticationDetails(
			CustomerAuthenticationDetails entity);

	CustomerAuthenticationDetails getLoginDetailsByCustomerId(Long customerId);
	
	CustomerAuthenticationDetails verifyLoginDetails(LoginVerificationDTO loginVerificationDTO);
	
	CustomerAuthenticationDetails verifyDefaultEmailLoginDetails(LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO);

	CustomerQuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity);
	
	Boolean sendDefaultPassword(CustomerQuickRegisterEntity customer,Boolean resetFlag);
	
	Boolean resendDefaultPassword(CustomerQuickRegisterEntity customerQuickRegisterEntity);
	
	Boolean resetPassword(CustomerIdDTO customerIdDTO);
	
	Boolean resendPassword(CustomerIdDTO customerIdDTO);
	

//Sending Authentication Details
	String composeSMSWithPassword(CustomerQuickRegisterEntity customer,CustomerAuthenticationDetails authenticationDetails);
	
	String composeEmailWithPassword(CustomerQuickRegisterEntity customer,CustomerAuthenticationDetails authenticationDetails);

	Boolean sendPasswordSMS(Long mobile,String message) ;

	Boolean sendPasswordEmail(String email,String message);


//Getters	
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);
	
	CustomerEmailVerificationDetails getCustomerEmailVerificationDetailsByCustomerIdAndEmail(Long customerId,String email);
	
	CustomerMobileVerificationDetails getCustomerMobileVerificationDetailsByCustomerIdAndMobile(Long customerId,Long mobile);

	
	
	//Document
	
	CustomerDocument saveCustomerDocument(CustomerDocument customerDocument);
	
	CustomerDocument getCustomerDocumentById(Long customerId);
	
	
	//Testing
	void clearDataForTesting();
	
	

	
}