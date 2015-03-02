package com.projectx.rest.services.quickregister;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.AuthenticationService.LoginVerificationFailedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;

public interface AuthenticationService {
	
	Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO);

	AuthenticationDetails saveCustomerAuthenticationDetails(
			AuthenticationDetails entity) throws ResourceAlreadyPresentException,ValidationFailedException;

	AuthenticationDetails getByEntityIdType(Long customerId,Integer customerType);
	
	AuthenticationDetails verifyLoginDetails(LoginVerificationDTO loginVerificationDTO) 
			throws AuthenticationDetailsNotFoundException,LoginVerificationFailedException;
	
	AuthenticationDetails verifyDefaultEmailLoginDetails(LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO)
			throws AuthenticationDetailsNotFoundException,LoginVerificationFailedException;

	Boolean sendOrResendOrResetDefaultPassword(Long entityId,Integer entityType,Boolean resetFlag,Boolean resendFlag) throws ResourceAlreadyPresentException;
	
	//TODO
	QuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity) throws ResourceAlreadyPresentException;
	
	Boolean sendDefaultPassword(QuickRegisterEntity customer,Boolean resetFlag) throws ResourceAlreadyPresentException;
	
	Boolean resendDefaultPassword(QuickRegisterEntity customerQuickRegisterEntity) throws ResourceAlreadyPresentException;
	
	Boolean resetPassword(CustomerIdTypeDTO customerIdDTO) throws ResourceAlreadyPresentException;
	
	Boolean resendPassword(CustomerIdTypeDTO customerIdDTO) throws ResourceAlreadyPresentException;
	
	
	
	Boolean clearTestData();

	AuthenticationDetails createCustomerAuthenticationDetails(
			QuickRegisterEntity customerQuickRegisterEntity);
	
}
