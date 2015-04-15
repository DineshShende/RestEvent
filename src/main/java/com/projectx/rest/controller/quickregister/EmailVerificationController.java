package com.projectx.rest.controller.quickregister;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.mvc.domain.quickregister.VerifyEmailHashDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.utils.HandleVerificationService;

@RestController
@RequestMapping(value="/customer/quickregister")
public class EmailVerificationController {

	@Autowired
	EmailVerificationService emailVerificationService; 
	
	@Autowired
	HandleVerificationService handleCustomerVerification; 
	
	@RequestMapping(value="/verifyEmailHash",method=RequestMethod.POST)
	public ResponseEntity<Boolean> verifyEmailHash(@Valid @RequestBody VerifyEmailHashDTO verifyEmail,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			Boolean status=emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(verifyEmail.getCustomerId(),verifyEmail.getCustomerType(),
					verifyEmail.getEmailType(), verifyEmail.getEmailHash(),verifyEmail.getUpdatedBy());
			
			result=new ResponseEntity<Boolean>(status, HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			result= new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(ValidationFailedException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return result;
	}
	
	@RequestMapping(value="/sendEmailHash",method=RequestMethod.POST)
	public ResponseEntity<Boolean> sendEmailHash(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO updateEmailHash,BindingResult  bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			Boolean result=emailVerificationService
					.sendEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType(),updateEmailHash.getRequestedBy());
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}

	
	@RequestMapping(value="/resetEmailHash",method=RequestMethod.POST)
	public ResponseEntity<Boolean> updateEmailHash(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO updateEmailHash,BindingResult  bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			Boolean result=emailVerificationService
					.reSetEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType(),updateEmailHash.getRequestedBy());
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public ResponseEntity<Boolean> reSendEmailHash(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO updateEmailHash,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())	
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			
		
		try{
			Boolean result= emailVerificationService.reSendEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),
					updateEmailHash.getEmailType(),updateEmailHash.getRequestedBy());
			
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	


	@RequestMapping(value="/getEmailVerificationDetails",method=RequestMethod.POST)
	public ResponseEntity<EmailVerificationDetails> getEmailVerificationDetails(@Valid @RequestBody CustomerIdTypeEmailTypeDTO emailDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	

}
