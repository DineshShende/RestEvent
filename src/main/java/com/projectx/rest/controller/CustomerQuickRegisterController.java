package com.projectx.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.projectx.data.domain.VerifyEmailHashDTO;
import com.projectx.data.domain.VerifyMobilePinDTO;
import com.projectx.rest.domain.CustomerQuickDetailsSentStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerIdDTO;

@RestController
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;
	
	@RequestMapping(value="/checkifexist",method=RequestMethod.POST)
	public String checkIfCustomerAlreadyExist(@RequestBody CustomerQuickRegisterEntityDTO customer) throws Exception
	{
		return customerQuickRegisterService.checkIfAlreadyRegistered(customer);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerQuickDetailsSentStatusEntity addNewCustomerQuickRegister(@RequestBody CustomerQuickRegisterEntityDTO newCustomer) throws Exception
	{		
		CustomerQuickDetailsSentStatusEntity newCustomerEntity=customerQuickRegisterService.handleNewCustomerQuickRegister(newCustomer);
						
		return newCustomerEntity;
	}

	
	@RequestMapping(value="/verifyEmailHash",method=RequestMethod.POST)
	public Boolean verifyEmailHash(@RequestBody VerifyEmailHashDTO verifyEmail)
	{
		if(customerQuickRegisterService.verifyEmailHash(verifyEmail.getCustomerId(), verifyEmail.getEmailHash()))
			return true;
		else
			return false;
	}
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public Boolean verifyMobilePin(@RequestBody VerifyMobilePinDTO verifyMobile)
	{
		if(customerQuickRegisterService.verifyMobilePin(verifyMobile.getCustomerId(), verifyMobile.getMobilePin()))
			return true;
		else
			return false;
	}
	
	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByCustomerId(@RequestBody CustomerIdDTO customerIdDTO)
	{
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerIdDTO.getCustomerId());
		
		return fetchedEntity;
	}

	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public Boolean updateMobilePin(@RequestBody CustomerIdDTO updateMobilePin) throws UnirestException
	{
		return customerQuickRegisterService.reSendMobilePin(updateMobilePin.getCustomerId());
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public Boolean updateEmailHash(@RequestBody CustomerIdDTO updateEmailHash)
	{
		return customerQuickRegisterService.reSendEmailHash(updateEmailHash.getCustomerId());
	}
		
	@RequestMapping(value="/cleartestdata")
	public void clearTestData()
	{
		customerQuickRegisterService.clearDataForTesting();
	}
	
	
}
