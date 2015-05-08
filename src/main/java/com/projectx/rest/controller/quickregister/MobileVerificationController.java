package com.projectx.rest.controller.quickregister;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.data.domain.quickregister.SendResendResetEmailHashDTO;
import com.projectx.data.domain.quickregister.SendResendResetMobilePinDTO;
import com.projectx.mvc.domain.quickregister.UpdateMobilePinDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobilePinDTO;
import com.projectx.rest.domain.comndto.ResponseDTO;
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
	
	@Value("${SEND_REQUEST}")
	private Integer SEND_REQUEST;
	
	@Value("${RESEND_REQUEST}")
	private Integer RESEND_REQUEST;
	
	@Value("${RESET_REQUEST}")
	private Integer RESET_REQUEST;
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> verifyMobilePin(@Valid @RequestBody VerifyMobilePinDTO verifyMobile,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
			
		try{
			Boolean status=mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(verifyMobile.getCustomerId(),verifyMobile.getCustomerType(),
					verifyMobile.getMobileType(), verifyMobile.getMobilePin(),verifyMobile.getRequestBy(),verifyMobile.getRequestById());
			
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",status), HttpStatus.OK);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		catch(ResourceNotFoundException |ValidationFailedException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null),HttpStatus.OK);
			
		}		
		
	}
	
	
	@RequestMapping(value="/sendOrResendOrResetMobilePin",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> sendMobilePin(@Valid @RequestBody SendResendResetMobilePinDTO updateMobilePin,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			Boolean status=null;
			
			if(updateMobilePin.getSendOrResendOrResetFlag().equals(SEND_REQUEST))
			{
			
				status=mobileVerificationService.sendMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
						updateMobilePin.getMobileType(),updateMobilePin.getRequestedBy(),updateMobilePin.getRequestedById());
			}else if(updateMobilePin.getSendOrResendOrResetFlag().equals(RESEND_REQUEST))
			{
				status= mobileVerificationService.reSendMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
						updateMobilePin.getMobileType(),updateMobilePin.getRequestedBy(),updateMobilePin.getRequestedById());
				
			}else if(updateMobilePin.getSendOrResendOrResetFlag().equals(RESET_REQUEST))
			{
				status=mobileVerificationService.reSetMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
						updateMobilePin.getMobileType(),updateMobilePin.getRequestedBy(),updateMobilePin.getRequestedById());
			}
			
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",status), HttpStatus.OK);
		}catch(ResourceNotFoundException | ValidationFailedException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/getMobileVerificationDetails",method=RequestMethod.POST)
	public ResponseEntity<MobileVerificationDetails> getMobileVerificationDetails(@Valid @RequestBody CustomerIdTypeMobileTypeDTO mobileDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
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
	
	/*
	@RequestMapping(value="/resetMobilePin",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> updateMobilePin(@Valid @RequestBody CustomerIdTypeMobileTypeRequestedByDTO updateMobilePin,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			Boolean status=mobileVerificationService.reSetMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
					updateMobilePin.getMobileType(),updateMobilePin.getRequestedBy(),updateMobilePin.getRequestedById());
			
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",status), HttpStatus.OK);
		}catch(ResourceNotFoundException | ValidationFailedException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null), HttpStatus.OK);
		}
		
	}
	
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> reSendMobilePin(@Valid @RequestBody CustomerIdTypeMobileTypeRequestedByDTO updateMobilePin,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
		
			Boolean result= mobileVerificationService.reSendMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
					updateMobilePin.getMobileType(),updateMobilePin.getRequestedBy(),updateMobilePin.getRequestedById());
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",result), HttpStatus.OK);
		}catch(ResourceNotFoundException | ValidationFailedException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null), HttpStatus.OK);
		}
		
	}
	*/

	
	
	
	
}
