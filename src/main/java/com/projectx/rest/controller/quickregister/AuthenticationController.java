package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_CHANGED;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.web.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.web.domain.quickregister.LoginVerificationDTO;
import com.projectx.web.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.web.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.web.domain.quickregister.UpdatePasswordDTO;

@RestController
@RequestMapping(value="/customer/quickregister")
public class AuthenticationController {


	@Autowired
	AuthenticationService authenticationService;
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public AuthenticationDetails verifyLoginDetails(@RequestBody LoginVerificationDTO loginVerificationDTO)
	{
		AuthenticationDetails verifiedEntity= authenticationService.verifyLoginDetails(loginVerificationDTO);
		
		return verifiedEntity;
	}
	
	@RequestMapping(value="/verifyLoginDefaultEmailPassword")
	public AuthenticationDetails verifyLoginDefaultEmailPassword(@RequestBody LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO)
	{
		AuthenticationDetails verifiedEntity= authenticationService.verifyDefaultEmailLoginDetails(emailPasswordDTO);
		
		return verifiedEntity;
		
	}

	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public Boolean resetPassword(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		Boolean result=authenticationService.resetPassword(customerIdDTO);
		
		return result;
	}
	
	@RequestMapping(value="/resendPassword",method=RequestMethod.POST)
	public Boolean resendPassword(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		Boolean result=authenticationService.resendPassword(customerIdDTO);
		
		return result;
	}
	
	@RequestMapping(value="/resetPasswordRedirect",method=RequestMethod.POST)
	public QuickRegisterEntity resetPasswordRedirect(@RequestBody ResetPasswordRedirectDTO passwordRedirectDTO)
	{
		QuickRegisterEntity quickRegisterEntity=authenticationService.resetPasswordByEmailOrMobileRedirect(passwordRedirectDTO.getEntity());
		
		return quickRegisterEntity;
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public Boolean updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO)
	{
		UpdatePasswordAndPasswordTypeDTO updatePassword=new UpdatePasswordAndPasswordTypeDTO(
				updatePasswordDTO.getCustomerId(),updatePasswordDTO.getCustomerType(),updatePasswordDTO.getPassword(), CUST_PASSWORD_TYPE_CHANGED);	
		Boolean result=authenticationService.updatePassword(updatePassword);
		
		return result;
	}

	@RequestMapping(value="/getAuthenticationDetailsById",method=RequestMethod.POST)
	public AuthenticationDetails getAuthenticationDetailsByCustomerId(@RequestBody CustomerIdTypeDTO customerId)
	{
		AuthenticationDetails verifiedEntity= authenticationService
				.getLoginDetailsByCustomerIdType(customerId.getCustomerId(),customerId.getCustomerType());
		
		return verifiedEntity;
	}


	@RequestMapping(value="/test",method=RequestMethod.GET)
	public AuthenticationDetails test()
	{
		return new AuthenticationDetails(new AuthenticationDetailsKey(212L, 1), "dineshshe@gmail.com", 9960821869L, "123456","Default", "sdfsfcccccccccccccccc", 0, 0, new Date(), new Date(), "CUST_ONLINE");
	}

	
}
