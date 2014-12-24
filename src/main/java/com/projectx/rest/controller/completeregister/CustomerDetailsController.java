package com.projectx.rest.controller.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileDTO;
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
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	public Boolean verifyMobileDetails(@RequestBody VerifyMobileDTO verifyMobileDTO)
	{
		Boolean result=customerDetailsService
				.verifyMobileDetails(verifyMobileDTO.getCustomerId(), verifyMobileDTO.getCustomerType(), 
						verifyMobileDTO.getMobile(), verifyMobileDTO.getMobileType(), verifyMobileDTO.getMobilePin());
		
		return result;
	}
	
	@RequestMapping(value="/verifyEmailDetails",method=RequestMethod.POST)
	public Boolean verifyEmailDetails(@RequestBody VerifyEmailDTO verifyEmailDTO)
	{
		Boolean result=customerDetailsService
						.verifyEmailDetails(verifyEmailDTO.getCustomerId(), verifyEmailDTO.getCustomerType(),
								verifyEmailDTO.getEmail(),  verifyEmailDTO.getEmailHash());
		return result;
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	public Boolean sendMobileVerificationDetails(@RequestBody CustomerIdTypeMobileDTO customerIdTypeMobileDTO)
	{
		Boolean result=customerDetailsService
				.sendMobileVerificationDetails(customerIdTypeMobileDTO.getCustomerId(), customerIdTypeMobileDTO.getCustomerType(), customerIdTypeMobileDTO.getMobile());
		
		return result;
				
	}
	
	@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
	public Boolean sendEmailVerificationDetails(@RequestBody CustomerIdTypeEmailDTO customerIdTypeEmailDTO)
	{
		Boolean result=customerDetailsService
				.sendEmailVerificationDetails(customerIdTypeEmailDTO.getCustomerId(), customerIdTypeEmailDTO.getCustomerType(), customerIdTypeEmailDTO.getEmail());
		
		return result;
				
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
