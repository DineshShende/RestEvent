package com.projectx.rest.controller.quickregister;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.mvc.domain.completeregister.EmailMessageDTO;
import com.projectx.mvc.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.mvc.domain.quickregister.VerifyEmailHashDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.utils.HandleCustomerVerification;

@RestController
@RequestMapping(value="/customer/quickregister")
public class EmailVerificationController {

	@Autowired
	EmailVerificationService emailVerificationService; 
	
	@Autowired
	HandleCustomerVerification handleCustomerVerification; 
	
	@RequestMapping(value="/verifyEmailHash",method=RequestMethod.POST)
	public ResponseEntity<Boolean> verifyEmailHash(@RequestBody VerifyEmailHashDTO verifyEmail)
	{
	
		ResponseEntity<Boolean> result=null;
		
		try{
			Boolean status=emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(verifyEmail.getCustomerId(),verifyEmail.getCustomerType(),
					verifyEmail.getEmailType(), verifyEmail.getEmailHash(),verifyEmail.getUpdatedBy());
			
			result=new ResponseEntity<Boolean>(status, HttpStatus.OK);
			
		}catch(ResourceNotFoundException | ValidationFailedException e)
		{
			result= new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
	}
	
	@RequestMapping(value="/resetEmailHash",method=RequestMethod.POST)
	public ResponseEntity<Boolean> updateEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
		try{
			Boolean result=emailVerificationService
					.reSetEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType());
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public ResponseEntity<Boolean> reSendEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
			
		try{
			Boolean result= emailVerificationService.reSendEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType());
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	


	@RequestMapping(value="/getEmailVerificationDetails",method=RequestMethod.POST)
	public ResponseEntity<EmailVerificationDetails> getEmailVerificationDetails(@RequestBody CustomerIdTypeEmailTypeDTO emailDTO)
	{
		ResponseEntity<EmailVerificationDetails> result=null;
		
		try{
			EmailVerificationDetails fetchedResult=emailVerificationService.
					getByEntityIdTypeAndEmailType(emailDTO.getCustomerId(),emailDTO.getCustomerType(),emailDTO.getEmailType());
			result=new ResponseEntity<EmailVerificationDetails>(fetchedResult, HttpStatus.FOUND);
			
		}catch(EmailVerificationDetailNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
		return result;
	}
	
	@RequestMapping(value="/sendEmail",method=RequestMethod.GET)
	public DeferredResult<Boolean> sendEmail()
	{
		DeferredResult<Boolean> result=new DeferredResult<Boolean>();
		
		//ListenableFuture<Boolean> resultFuture=handleCustomerVerification.sendEmail("dineshshe@gmail.com", "Hi");
		
		//return handleCustomerVerification.sendEmail("dineshshe@gmail.com", "Hi");
		return result;
	}

}
