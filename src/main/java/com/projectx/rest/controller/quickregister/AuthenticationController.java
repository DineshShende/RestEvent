package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_CHANGED;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.AuthenticationService.LoginVerificationFailedException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.rabbitmq.client.AMQP.Exchange.Bind;

@RestController
@RequestMapping(value="/customer/quickregister")
public class AuthenticationController {


	@Autowired
	AuthenticationService authenticationService;
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> verifyLoginDetails(@Valid @RequestBody LoginVerificationDTO loginVerificationDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	public ResponseEntity<AuthenticationDetails> verifyLoginDefaultEmailPassword(@Valid @RequestBody LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	public ResponseEntity<Boolean> resetPassword(@Valid @RequestBody CustomerIdTypeUpdatedByDTO customerIdDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	public ResponseEntity<Boolean> resendPassword(@Valid @RequestBody CustomerIdTypeUpdatedByDTO customerIdDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	public ResponseEntity<QuickRegisterEntity> resetPasswordRedirect(@Valid @RequestBody ResetPasswordRedirectDTO passwordRedirectDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	public ResponseEntity<Boolean> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		UpdatePasswordAndPasswordTypeDTO updatePassword=new UpdatePasswordAndPasswordTypeDTO(
				updatePasswordDTO.getCustomerId(),updatePasswordDTO.getCustomerType(),updatePasswordDTO.getPassword(), 
				CUST_PASSWORD_TYPE_CHANGED,updatePasswordDTO.getRequestedBy());	
		
		try{
			Boolean result=authenticationService.updatePassword(updatePassword);
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}catch(ValidationFailedException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		
	}

	@RequestMapping(value="/getAuthenticationDetailsById",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> getAuthenticationDetailsByCustomerId(@Valid @RequestBody CustomerIdTypeDTO customerId,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
