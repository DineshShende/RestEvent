package com.projectx.rest.services.quickregister;

import com.projectx.data.quickregister.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.web.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.web.domain.quickregister.LoginVerificationDTO;
import com.projectx.web.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;

public interface AuthenticationService {
	
	Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO);

	AuthenticationDetails saveCustomerAuthenticationDetails(
			AuthenticationDetails entity);

	AuthenticationDetails getLoginDetailsByCustomerIdType(Long customerId,Integer customerType);
	
	AuthenticationDetails verifyLoginDetails(LoginVerificationDTO loginVerificationDTO);
	
	AuthenticationDetails verifyDefaultEmailLoginDetails(LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO);

	QuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity);
	
	Boolean sendDefaultPassword(QuickRegisterEntity customer,Boolean resetFlag);
	
	Boolean resendDefaultPassword(QuickRegisterEntity customerQuickRegisterEntity);
	
	Boolean resetPassword(CustomerIdTypeDTO customerIdDTO);
	
	Boolean resendPassword(CustomerIdTypeDTO customerIdDTO);
	
	Boolean clearTestData();

	AuthenticationDetails createCustomerAuthenticationDetails(
			QuickRegisterEntity customerQuickRegisterEntity);
	
}
