package com.projectx.rest.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.mvc.domain.completeregister.VerifyEmailDTO;
import com.projectx.mvc.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.services.completeregister.VendorDetailsService;


@RestController
@RequestMapping(value="/vendor")
public class VendorDetailsController {

	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@RequestMapping(value="/createFromQuickRegister",method=RequestMethod.POST)
	public ResponseEntity<VendorDetails> createCustomerDetailsFromQuickRegisterEntity(@RequestBody QuickRegisterEntity quickRegisterEntity)
	{
		ResponseEntity<VendorDetails> result=null;
		try{
			VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
			result=new ResponseEntity<VendorDetails>(savedEntity, HttpStatus.OK);
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
				
		return result;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<VendorDetails> update(@RequestBody VendorDetails vendorDetails)
	{
		ResponseEntity<VendorDetails> result=null;
		
		try{
			VendorDetails savedEntity=vendorDetailsService.updateVendorDetails(vendorDetails);
			result=new ResponseEntity<VendorDetails>(savedEntity, HttpStatus.OK);
		}catch(VendorDetailsTransactionalUpdateFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getVendorDetailsById/{vendorId}",method=RequestMethod.GET)
	public ResponseEntity<VendorDetails> getCustomerDetailsById(@PathVariable Long vendorId)
	{
		ResponseEntity<VendorDetails> result=null;
		
		try{
			VendorDetails fetchedEntity=vendorDetailsService.findById(vendorId);
			result=new ResponseEntity<VendorDetails>(fetchedEntity, HttpStatus.FOUND);
		}catch(VendorDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
	public Boolean verifyMobileDetails(@RequestBody VerifyMobileDTO verifyMobileDTO)
	{
		Boolean result=vendorDetailsService
				.verifyMobileDetails(verifyMobileDTO.getEntityId(), verifyMobileDTO.getEntityType(), 
						verifyMobileDTO.getMobileType(), verifyMobileDTO.getMobilePin(),verifyMobileDTO.getRequestBy());
		
		return result;
	}
	
	@RequestMapping(value="/verifyEmailDetails",method=RequestMethod.POST)
	public Boolean verifyEmailDetails(@RequestBody VerifyEmailDTO verifyEmailDTO)
	{
		Boolean result=vendorDetailsService
						.verifyEmailDetails(verifyEmailDTO.getCustomerId(), verifyEmailDTO.getCustomerType(),
								verifyEmailDTO.getEmailType(),  verifyEmailDTO.getEmailHash(),verifyEmailDTO.getUpdatedBy());
		return result;
	}
	
	@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
	public Boolean sendMobileVerificationDetails(@RequestBody CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileDTO)
	{
		Boolean result=vendorDetailsService
				.sendMobileVerificationDetails(customerIdTypeMobileDTO.getCustomerId(), customerIdTypeMobileDTO.getCustomerType(),
						customerIdTypeMobileDTO.getMobileType(),customerIdTypeMobileDTO.getUpdatedBy());
		
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
