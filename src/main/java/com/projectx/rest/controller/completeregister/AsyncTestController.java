package com.projectx.rest.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.EmailMessageDTO;
import com.projectx.rest.utils.HandleCustomerVerification;

@RestController
@RequestMapping(value="/asycn")
public class AsyncTestController {
	
	@Autowired
	HandleCustomerVerification handleCustomerVerification;
	
	@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
	public Boolean sendEmail(@RequestBody EmailMessageDTO emailMessageDTO)
	{
		try {
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Boolean result=handleCustomerVerification.sendEmailAsynchronous(emailMessageDTO.getEmail(), emailMessageDTO.getMessage());
		
		return result;
	}

}
