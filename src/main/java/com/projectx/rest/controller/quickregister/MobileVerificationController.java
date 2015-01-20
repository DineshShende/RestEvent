package com.projectx.rest.controller.quickregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.mvc.domain.quickregister.UpdateMobilePinDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobilePinDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.services.quickregister.MobileVerificationService;

@RestController
@RequestMapping(value="/customer/quickregister")
public class MobileVerificationController {


	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public Boolean verifyMobilePin(@RequestBody VerifyMobilePinDTO verifyMobile)
	{
		if(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(verifyMobile.getCustomerId(),verifyMobile.getCustomerType(),
				verifyMobile.getMobileType(), verifyMobile.getMobilePin()))
			return true;
		else
			return false;
	}
	
	
	@RequestMapping(value="/resetMobilePin",method=RequestMethod.POST)
	public Boolean updateMobilePin(@RequestBody UpdateMobilePinDTO updateMobilePin)
	{
		return mobileVerificationService.reSetMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
				updateMobilePin.getMobileType());
	}
	
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public Boolean reSendMobilePin(@RequestBody UpdateMobilePinDTO updateMobilePin)
	{
		Boolean result= mobileVerificationService.reSendMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getCustomerType(),
				updateMobilePin.getMobileType());
		
		return result;
	}
	

	@RequestMapping(value="/getMobileVerificationDetails",method=RequestMethod.POST)
	public MobileVerificationDetails getMobileVerificationDetails(@RequestBody CustomerIdTypeMobileTypeDTO mobileDTO)
	{
		MobileVerificationDetails fetchedResult=mobileVerificationService.
				getByEntityIdTypeAndMobileType(mobileDTO.getCustomerId(),mobileDTO.getCustomerType(),
						mobileDTO.getMobileType());
		
		return fetchedResult;
	}

	
}
