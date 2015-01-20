package com.projectx.rest.services.quickregister;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

public interface AuthenticationService {
	
	Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO);

	AuthenticationDetails saveCustomerAuthenticationDetails(
			AuthenticationDetails entity);

	AuthenticationDetails getByEntityIdType(Long customerId,Integer customerType);
	
	AuthenticationDetails verifyLoginDetails(LoginVerificationDTO loginVerificationDTO);
	
	AuthenticationDetails verifyDefaultEmailLoginDetails(LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO);

	Boolean sendOrResendOrResetDefaultPassword(Long entityId,Integer entityType,Boolean resetFlag,Boolean resendFlag);
	
	//TODO
	QuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity);
	
	Boolean sendDefaultPassword(QuickRegisterEntity customer,Boolean resetFlag);
	
	Boolean resendDefaultPassword(QuickRegisterEntity customerQuickRegisterEntity);
	
	Boolean resetPassword(CustomerIdTypeDTO customerIdDTO);
	
	Boolean resendPassword(CustomerIdTypeDTO customerIdDTO);
	
	
	
	Boolean clearTestData();

	AuthenticationDetails createCustomerAuthenticationDetails(
			QuickRegisterEntity customerQuickRegisterEntity);
	
}
