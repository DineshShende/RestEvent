package com.projectx.rest.controller.quickregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.UpdateMobilePinDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobilePinDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.quickregister.MobileVerificationService;

@RestController
@RequestMapping(value="/customer/quickregister")
public class MobileVerificationController {


	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public ResponseEntity<Boolean> verifyMobilePin(@RequestBody VerifyMobilePinDTO verifyMobile)
	{
			
		try{
			Boolean status=mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(verifyMobile.getCustomerId(),verifyMobile.getCustomerType(),
					verifyMobile.getMobileType(), verifyMobile.getMobilePin(),verifyMobile.getRequestBy());
			
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@RequestMapping(value="/resetMobilePin",method=RequestMethod.POST)
	public ResponseEntity<Boolean> updateMobilePin(@RequestBody CustomerIdTypeMobileTypeUpdatedByDTO updateMobilePin)
	{
		try{
			Boolean status=mobileVerificationService.reSetMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
					updateMobilePin.getMobileType(),updateMobilePin.getUpdatedBy());
			
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public ResponseEntity<Boolean> reSendMobilePin(@RequestBody CustomerIdTypeMobileTypeUpdatedByDTO updateMobilePin)
	{
		try{
		
			Boolean result= mobileVerificationService.reSendMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
					updateMobilePin.getMobileType(),updateMobilePin.getUpdatedBy());
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	

	@RequestMapping(value="/getMobileVerificationDetails",method=RequestMethod.POST)
	public ResponseEntity<MobileVerificationDetails> getMobileVerificationDetails(@RequestBody CustomerIdTypeMobileTypeDTO mobileDTO)
	{
		ResponseEntity<MobileVerificationDetails> result=null;
		
		try{
			MobileVerificationDetails fetchedResult=mobileVerificationService.
					getByEntityIdTypeAndMobileType(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(),
							mobileDTO.getMobileType());
			result=new ResponseEntity<MobileVerificationDetails>(fetchedResult, HttpStatus.FOUND);
			
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
				
		return result;
	}

	
}
