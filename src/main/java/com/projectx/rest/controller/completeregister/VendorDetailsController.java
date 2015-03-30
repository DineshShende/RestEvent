package com.projectx.rest.controller.completeregister;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.mvc.domain.completeregister.VerifyEmailDTO;
import com.projectx.mvc.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.completeregister.VendorDetailsService;


@RestController
@RequestMapping(value="/vendor")
public class VendorDetailsController {

	@Autowired
	VendorDetailsService vendorDetailsService;

	
	@RequestMapping(value="/createFromQuickRegister",method=RequestMethod.POST)
	public ResponseEntity<VendorDetails> createCustomerDetailsFromQuickRegisterEntity(@Valid @RequestBody QuickRegisterEntity quickRegisterEntity,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	public ResponseEntity<VendorDetails> update(@Valid @RequestBody VendorDetails vendorDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<VendorDetails> result=null;
		
		try{
			VendorDetails savedEntity=vendorDetailsService.updateVendorDetails(vendorDetails);
			result=new ResponseEntity<VendorDetails>(savedEntity, HttpStatus.OK);
		}catch(ValidationFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		catch(VendorDetailsTransactionalUpdateFailedException e)
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

/*
@RequestMapping(value="/verifyMobileDetails",method=RequestMethod.POST)
public ResponseEntity<Boolean> verifyMobileDetails(@Valid @RequestBody VerifyMobileDTO verifyMobileDTO,BindingResult bindingResult)
{
	if(bindingResult.hasErrors())
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	
	try{
		Boolean result=vendorDetailsService
				.verifyMobileDetails(verifyMobileDTO.getEntityId(), verifyMobileDTO.getEntityType(), 
						verifyMobileDTO.getMobileType(), verifyMobileDTO.getMobilePin(),verifyMobileDTO.getRequestBy());
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}catch(ValidationFailedException e)
	{
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
	}catch (ResourceNotFoundException e) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}

@RequestMapping(value="/verifyEmailDetails",method=RequestMethod.POST)
public ResponseEntity<Boolean> verifyEmailDetails(@Valid @RequestBody VerifyEmailDTO verifyEmailDTO,BindingResult bindingResult)
{
	if(bindingResult.hasErrors())
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	
	try{
		Boolean result=vendorDetailsService
				.verifyEmailDetails(verifyEmailDTO.getCustomerId(), verifyEmailDTO.getCustomerType(),
						verifyEmailDTO.getEmailType(),  verifyEmailDTO.getEmailHash(),verifyEmailDTO.getUpdatedBy());
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}catch(ResourceNotFoundException e)
	{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}

@RequestMapping(value="/sendMobileVerificationDetails",method=RequestMethod.POST)
public ResponseEntity<Boolean> sendMobileVerificationDetails(@Valid @RequestBody CustomerIdTypeMobileTypeUpdatedByDTO customerIdTypeMobileDTO,
		BindingResult bindingResult)
{
	if(bindingResult.hasErrors())
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	
	try{
		Boolean result=vendorDetailsService
				.sendMobileVerificationDetails(customerIdTypeMobileDTO.getCustomerId(), customerIdTypeMobileDTO.getCustomerType(),
						customerIdTypeMobileDTO.getMobileType(),customerIdTypeMobileDTO.getUpdatedBy());
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		
	}catch(ResourceNotFoundException e)
	{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
			
}

@RequestMapping(value="/sendEmailVerificationDetails",method=RequestMethod.POST)
public ResponseEntity<Boolean> sendEmailVerificationDetails(@Valid @RequestBody CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailDTO,
		BindingResult bindingResult)
{
	if(bindingResult.hasErrors())
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	
	try{
		Boolean result=vendorDetailsService
				.sendEmailVerificationDetails(customerIdTypeEmailDTO.getCustomerId(), customerIdTypeEmailDTO.getCustomerType(),
						customerIdTypeEmailDTO.getEmailType(),customerIdTypeEmailDTO.getUpdatedBy());
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}catch(ResourceNotFoundException e)
	{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
			
}
*/