package com.projectx.rest.controller.async;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.projectx.mvc.domain.completeregister.EmailMessageDTO;
import com.projectx.mvc.domain.completeregister.MobileMessageDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.utils.HandleCustomerVerification;

@RestController
@RequestMapping(value="/asycn")
public class AsyncController {
	
	@Autowired
	HandleCustomerVerification handleCustomerVerification;
	
		
	@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
	public Boolean sendEmail(@RequestBody EmailMessageDTO emailMessageDTO)
	{
		
		Boolean result=handleCustomerVerification.sendEmailAsynchronous(emailMessageDTO.getEmail(), emailMessageDTO.getMessage());
		
		return result;
	}
	
	@RequestMapping(value="/sendSMS",method=RequestMethod.POST)
	public Boolean sendSMS(@RequestBody MobileMessageDTO messageDTO)
	{
		Boolean result=handleCustomerVerification.sendSMSAsynchronous(messageDTO.getMobile(), messageDTO.getMessage());
		
		return result;
	}
	
}
