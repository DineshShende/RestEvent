package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_CHANGED;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.AuthenticationService.LoginVerificationFailedException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.services.quickregister.AuthenticationService;

@RestController
@RequestMapping(value="/customer/quickregister")
public class AuthenticationController {


	@Autowired
	AuthenticationService authenticationService;
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> verifyLoginDetails(@RequestBody LoginVerificationDTO loginVerificationDTO)
	{
		ResponseEntity<AuthenticationDetails> result=null;
		
		try{
			AuthenticationDetails verifiedEntity= authenticationService.verifyLoginDetails(loginVerificationDTO);
			result=new ResponseEntity<AuthenticationDetails>(verifiedEntity, HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException | LoginVerificationFailedException e2 )
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
				
		return result;
	}
	
	@RequestMapping(value="/verifyLoginDefaultEmailPassword")
	public ResponseEntity<AuthenticationDetails> verifyLoginDefaultEmailPassword(@RequestBody LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO)
	{
		ResponseEntity<AuthenticationDetails> result=null;
		
		try{
			AuthenticationDetails verifiedEntity= authenticationService.verifyDefaultEmailLoginDetails(emailPasswordDTO);
			result=new ResponseEntity<AuthenticationDetails>(verifiedEntity, HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
		
	}

	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public ResponseEntity<Boolean> resetPassword(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		ResponseEntity<Boolean> result=null;
		
		try{
			Boolean status=authenticationService.resetPassword(customerIdDTO);
			result=new ResponseEntity<Boolean>(status, HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException  | QuickRegisterEntityNotFoundException | CustomerDetailsNotFoundException 
				|VendorDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			
		return result;
	}
	
	@RequestMapping(value="/resendPassword",method=RequestMethod.POST)
	public ResponseEntity<Boolean> resendPassword(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		ResponseEntity<Boolean> result=null;
		
		try{
			Boolean status=authenticationService.resendPassword(customerIdDTO);
			result=new ResponseEntity<Boolean>(status, HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException  | QuickRegisterEntityNotFoundException | CustomerDetailsNotFoundException 
				|VendorDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			
		return result;
	}
	
	@RequestMapping(value="/resetPasswordRedirect",method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> resetPasswordRedirect(@RequestBody ResetPasswordRedirectDTO passwordRedirectDTO)
	{
		ResponseEntity<QuickRegisterEntity> result=null;
		
		try{
			QuickRegisterEntity quickRegisterEntity=authenticationService.resetPasswordByEmailOrMobileRedirect(passwordRedirectDTO.getEntity());
			result=new ResponseEntity<QuickRegisterEntity>(quickRegisterEntity, HttpStatus.OK);
		}catch(QuickRegisterEntityNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
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
	public ResponseEntity<AuthenticationDetails> getAuthenticationDetailsByCustomerId(@RequestBody CustomerIdTypeDTO customerId)
	{
		ResponseEntity<AuthenticationDetails> result=null;
		
		try{
			AuthenticationDetails verifiedEntity= authenticationService
					.getByEntityIdType(customerId.getCustomerId(),customerId.getCustomerType());
			
			result=new ResponseEntity<AuthenticationDetails>(verifiedEntity, HttpStatus.FOUND);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
	}

	
}
