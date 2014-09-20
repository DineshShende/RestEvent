package com.projectx.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.GetByEmailDTO;
import com.projectx.web.domain.GetByMobileDTO;
import com.projectx.web.domain.VerifyMobileDTO;

@Controller
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;
	
	@RequestMapping(value="/checkifexist",method=RequestMethod.POST)
	@ResponseBody
	public String checkIfCustomerAlreadyExist(@RequestBody CustomerQuickRegisterEntityDTO customer) throws Exception
	{
		return customerQuickRegisterService.checkIfAlreadyRegistered(customer);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public CustomerQuickRegisterEntity addNewCustomerQuickRegister(@RequestBody CustomerQuickRegisterEntityDTO newCustomer) throws Exception
	{
		newCustomer=customerQuickRegisterService.populateStatus(newCustomer);
			
		CustomerQuickRegisterEntity newCustomerEntity=customerQuickRegisterService.handleNewCustomerQuickRegistration(newCustomer);
			
		CustomerQuickRegisterEntity savedCustomerEntity=customerQuickRegisterService.saveNewCustomerQuickRegisterEntity(newCustomerEntity);
			
		return savedCustomerEntity;
	}

	
	@RequestMapping(value="/verifyemail/{email}/hashValue/{emailHash}",method=RequestMethod.GET)
	public Boolean verifyEmail(@PathVariable Long emailHash,@PathVariable String email)
	{
		if(customerQuickRegisterService.verifyEmail(email, emailHash))
			return true;
		else
			return false;
	}
	
	@RequestMapping(value="/verifymobile",method=RequestMethod.POST)
	public Boolean verifyMobile(@RequestBody VerifyMobileDTO verifyMobile)
	{
		System.out.println(verifyMobile.getMobile());
		
		if(customerQuickRegisterService.verifyMobile(verifyMobile.getMobile(), verifyMobile.getMobilePin()))
			return true;
		else
			return false;
	}
	
	@RequestMapping(value="/getByEmail",method=RequestMethod.POST)
	@ResponseBody
	public CustomerQuickRegisterEntity getCustomerByEmail(@RequestBody GetByEmailDTO emailDTO)
	{
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByEmail(emailDTO.getEmail());
		
		return fetchedEntity;
	}
	
	@RequestMapping(value="/getByMobile",method=RequestMethod.POST)
	@ResponseBody
	public CustomerQuickRegisterEntity getCustomerByMobile(@RequestBody GetByMobileDTO mobileDTO)
	{
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByMobile(mobileDTO.getMobile());
		
		return fetchedEntity;
	}
	
	@RequestMapping(value="/cleartestdata")
	public void clearTestData()
	{
		customerQuickRegisterService.clearDataForTesting();
	}
	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public VerifyMobileDTO returnCustomerWithEmailMobileCheck()
	{
		return new VerifyMobileDTO(9960821869L, 101010);
	}
	
	
}
