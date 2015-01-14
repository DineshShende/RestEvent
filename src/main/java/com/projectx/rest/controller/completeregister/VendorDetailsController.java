package com.projectx.rest.controller.completeregister;

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
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.completeregister.VendorDetailsService;


@RestController
@RequestMapping(value="/vendor")
public class VendorDetailsController {

	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@RequestMapping(value="/createFromQuickRegister",method=RequestMethod.POST)
	public VendorDetails createCustomerDetailsFromQuickRegisterEntity(@RequestBody QuickRegisterEntity quickRegisterEntity)
	{
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public VendorDetails update(@RequestBody VendorDetails vendorDetails)
	{
		VendorDetails savedEntity=vendorDetailsService.updateVendorDetails(vendorDetails);
		
		//System.out.println(savedEntity);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getVendorDetailsById/{vendorId}",method=RequestMethod.GET)
	public VendorDetails getCustomerDetailsById(@PathVariable Long vendorId)
	{
		VendorDetails fetchedEntity=vendorDetailsService.findById(vendorId);
		
		if(fetchedEntity!=null)
			return fetchedEntity;
		else
			return new VendorDetails();
	}
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	public Boolean verifyMobileDetails(@RequestBody VerifyMobileDTO verifyMobileDTO)
	{
		Boolean result=vendorDetailsService
				.verifyMobileDetails(verifyMobileDTO.getEntityId(), verifyMobileDTO.getEntityType(), 
						verifyMobileDTO.getMobileType(), verifyMobileDTO.getMobilePin());
		
		return result;
	}
	
	@RequestMapping(value="/verifyEmailDetails",method=RequestMethod.POST)
	public Boolean verifyEmailDetails(@RequestBody VerifyEmailDTO verifyEmailDTO)
	{
		Boolean result=vendorDetailsService
						.verifyEmailDetails(verifyEmailDTO.getEntityId(), verifyEmailDTO.getEntityType(),
								verifyEmailDTO.getEmailType(),  verifyEmailDTO.getEmailHash());
		return result;
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	public Boolean sendMobileVerificationDetails(@RequestBody CustomerIdTypeMobileTypeDTO customerIdTypeMobileDTO)
	{
		Boolean result=vendorDetailsService
				.sendMobileVerificationDetails(customerIdTypeMobileDTO.getCustomerId(), customerIdTypeMobileDTO.getCustomerType(), customerIdTypeMobileDTO.getMobileType());
		
		return result;
				
	}
	
	@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
	public Boolean sendEmailVerificationDetails(@RequestBody CustomerIdTypeEmailTypeDTO customerIdTypeEmailDTO)
	{
		Boolean result=vendorDetailsService
				.sendEmailVerificationDetails(customerIdTypeEmailDTO.getCustomerId(), customerIdTypeEmailDTO.getCustomerType(), customerIdTypeEmailDTO.getEmailType());
		
		return result;
				
	}
	
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public  Integer getCount()
	{
		return vendorDetailsService.count();
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		vendorDetailsService.clearTestData();
		
		return true;
	}

	
}
