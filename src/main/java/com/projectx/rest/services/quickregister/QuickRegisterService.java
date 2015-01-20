package com.projectx.rest.services.quickregister;


import java.util.Date;

import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterStringStatusEntity;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;

public interface QuickRegisterService {
	

	CustomerQuickRegisterStringStatusEntity checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity populateVerificationFields(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(QuickRegisterEntity customer);
	
	//AuthenticationDetails createCustomerAuthenticationDetails(QuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerQuickRegisterEmailMobileVerificationEntity saveNewCustomerQuickRegisterEntity(QuickRegisterEntity customer) throws Exception;
	
	QuickRegisterEntity saveCustomerQuickRegisterEntity(QuickRegisterEntity customerQuickRegisterEntity);
	
	CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity getByEntityId(Long customerId);
	
	//QuickRegisterEntity findByCustomerId(Long customerId);
	
	CustomerQuickRegisterStatusEntity sendVerificationDetails(QuickRegisterEntity customer,EmailVerificationDetails emailVerificationDetails,MobileVerificationDetails mobileVerificationDetails);
	
	QuickRegisterEntity getByEmail(String email);
	
	QuickRegisterEntity getByMobile(Long mobile);

	Integer updateMobileVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	
	Integer updateEmailVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	
	//Testing
	void clearDataForTesting();

	
	
	
	
	

	
}