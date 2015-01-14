package com.projectx.rest.controller.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.mvc.domain.completeregister.VerifyEmailDTO;
import com.projectx.mvc.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.completeregister.CustomerDetailsService;

@RestController
@RequestMapping(value="/customer")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@RequestMapping(value="/createFromQuickRegister",method=RequestMethod.POST)
	public CustomerDetails createCustomerDetailsFromQuickRegisterEntity(@RequestBody QuickRegisterEntity quickRegisterEntity)
	{
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		return savedEntity;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerDetails merge(@RequestBody CustomerDetails customerDetails)
	{
		CustomerDetails savedEntity=customerDetailsService.mergeCustomerDetails(customerDetails);
		
		System.out.println(savedEntity);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getCustomerDetailsById/{customerId}",method=RequestMethod.GET)
	public CustomerDetails getCustomerDetailsById(@PathVariable Long customerId)
	{
		CustomerDetails fetchedEntity=customerDetailsService.findById(customerId);
		
		if(fetchedEntity!=null)
			return fetchedEntity;
		else
			return new CustomerDetails();
	}
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	public Boolean verifyMobileDetails(@RequestBody VerifyMobileDTO verifyMobileDTO)
	{
		Boolean result=customerDetailsService
				.verifyMobileDetails(verifyMobileDTO.getEntityId(), verifyMobileDTO.getEntityType(), 
						verifyMobileDTO.getMobileType(), verifyMobileDTO.getMobilePin());
		
		return result;
	}
	
	@RequestMapping(value="/verifyEmailDetails",method=RequestMethod.POST)
	public Boolean verifyEmailDetails(@RequestBody VerifyEmailDTO verifyEmailDTO)
	{
		Boolean result=customerDetailsService
						.verifyEmailDetails(verifyEmailDTO.getEntityId(), verifyEmailDTO.getEntityType(),
								verifyEmailDTO.getEmailType(),  verifyEmailDTO.getEmailHash());
		return result;
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	public Boolean sendMobileVerificationDetails(@RequestBody CustomerIdTypeMobileTypeDTO customerIdTypeMobileDTO)
	{
		Boolean result=customerDetailsService
				.sendMobileVerificationDetails(customerIdTypeMobileDTO.getCustomerId(), customerIdTypeMobileDTO.getCustomerType(), customerIdTypeMobileDTO.getMobileType());
		
		return result;
				
	}
	
	@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
	public Boolean sendEmailVerificationDetails(@RequestBody CustomerIdTypeEmailTypeDTO customerIdTypeEmailDTO)
	{
		Boolean result=customerDetailsService
				.sendEmailVerificationDetails(customerIdTypeEmailDTO.getCustomerId(), customerIdTypeEmailDTO.getCustomerType(), customerIdTypeEmailDTO.getEmailType());
		
		return result;
				
	}
	
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public  Integer getCount()
	{
		return customerDetailsService.count();
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		customerDetailsService.clearTestData();
		
		return true;
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public QuickRegisterEntity quickRegisterEntity()
	{
		return new QuickRegisterEntity(1L, "ABC", "ABC", "ABC", 1111L, 1111, false, false, 1, new Date(), new Date(), "CUST");
		
	}
	
}
