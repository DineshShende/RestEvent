package com.projectx.rest.controller.quickregister;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.mvc.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.mvc.domain.quickregister.VerifyEmailHashDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.services.quickregister.EmailVerificationService;

@RestController
@RequestMapping(value="/customer/quickregister")
public class EmailVerificationController {

	@Autowired
	EmailVerificationService emailVerificationService; 
	
	@RequestMapping(value="/verifyEmailHash",method=RequestMethod.POST)
	public Boolean verifyEmailHash(@RequestBody VerifyEmailHashDTO verifyEmail)
	{
	
		if(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(verifyEmail.getCustomerId(),verifyEmail.getCustomerType(),verifyEmail.getEmailType(), verifyEmail.getEmailHash()))
			return true;
		else
			return false;
	}
	
	@RequestMapping(value="/resetEmailHash",method=RequestMethod.POST)
	public Boolean updateEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
		return emailVerificationService.reSetEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType());
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public Boolean reSendEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
				
		Boolean result= emailVerificationService.reSendEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getCustomerType(),updateEmailHash.getEmailType());
		
		return result;
	}
	


	@RequestMapping(value="/getEmailVerificationDetails",method=RequestMethod.POST)
	public EmailVerificationDetails getEmailVerificationDetails(@RequestBody CustomerIdTypeEmailTypeDTO emailDTO)
	{
		EmailVerificationDetails fetchedResult=emailVerificationService.
				getByEntityIdTypeAndEmailType(emailDTO.getCustomerId(),emailDTO.getCustomerType(),emailDTO.getEmailType());
		
		return fetchedResult;
	}

}
