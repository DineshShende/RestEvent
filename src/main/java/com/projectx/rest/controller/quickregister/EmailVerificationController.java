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
import com.projectx.rest.domain.comndto.ResponseDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.exception.repository.completeregister.UpdateEmailInDetailsAndAuthenticationDetailsFailedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
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
	public ResponseEntity<ResponseDTO<Boolean>> verifyEmailHash(@Valid @RequestBody VerifyEmailHashDTO verifyEmail,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<Boolean>> result=null;
		
		try{
			Boolean status=emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(verifyEmail.getCustomerId(),verifyEmail.getCustomerType(),
					verifyEmail.getEmailType(), verifyEmail.getEmailHash(),verifyEmail.getUpdatedBy(),verifyEmail.getUpdatedById());
			
			result=new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",status), HttpStatus.OK);
			
		}catch(ValidationFailedException | UpdateEmailInDetailsAndAuthenticationDetailsFailedException 
				| EmailVerificationDetailNotFoundException |QuickRegisterEntityNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null), HttpStatus.OK);
		}
		
		return result;
	}
	
	@RequestMapping(value="/sendEmailHash",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> sendEmailHash(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO updateEmailHash,BindingResult  bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			Boolean result=emailVerificationService
					.sendEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType(),
							updateEmailHash.getRequestedBy(),updateEmailHash.getRequestedById());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",result), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null),HttpStatus.OK);
		}
		
	}

	
	@RequestMapping(value="/resetEmailHash",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> updateEmailHash(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO updateEmailHash,BindingResult  bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			Boolean result=emailVerificationService
					.reSetEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType(),
							updateEmailHash.getRequestedBy(),updateEmailHash.getRequestedById());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",result), HttpStatus.OK);
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null),HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> reSendEmailHash(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO updateEmailHash,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())	
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			
		
		try{
			Boolean result= emailVerificationService.reSendEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),
					updateEmailHash.getEmailType(),updateEmailHash.getRequestedBy(),updateEmailHash.getRequestedById());
			
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",result), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null),HttpStatus.OK);
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
