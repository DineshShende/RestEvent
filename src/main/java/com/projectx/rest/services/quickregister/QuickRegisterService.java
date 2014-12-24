package com.projectx.rest.services.quickregister;


import java.util.Date;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerDocument;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.web.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.web.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.quickregister.CustomerQuickRegisterStringStatusEntity;
import com.projectx.web.domain.quickregister.LoginVerificationDTO;
import com.projectx.web.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;

public interface QuickRegisterService {
	

	CustomerQuickRegisterStringStatusEntity checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity populateVerificationFields(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(QuickRegisterEntity customer);
	
	//AuthenticationDetails createCustomerAuthenticationDetails(QuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerQuickRegisterEmailMobileVerificationEntity saveNewCustomerQuickRegisterEntity(QuickRegisterEntity customer) throws Exception;
	
	QuickRegisterEntity saveCustomerQuickRegisterEntity(QuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(Long customerId);
	
	//QuickRegisterEntity findByCustomerId(Long customerId);
	
	CustomerQuickRegisterStatusEntity sendVerificationDetails(QuickRegisterEntity customer,EmailVerificationDetails emailVerificationDetails,MobileVerificationDetails mobileVerificationDetails);
	
	QuickRegisterEntity getCustomerQuickRegisterEntityByEmail(String email);
	
	QuickRegisterEntity getCustomerQuickRegisterEntityByMobile(Long mobile);

	Integer updateMobileVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	
	Integer updateEmailVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	
	//Testing
	void clearDataForTesting();

	
	
	//Document
	
	CustomerDocument saveCustomerDocument(CustomerDocument customerDocument);
	
	CustomerDocument getCustomerDocumentById(Long customerId);
	
	
	
	

	
}