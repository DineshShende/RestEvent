package com.projectx.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerDocument;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.CustomerQuickRegisterStringStatusEntity;
import com.projectx.web.domain.ResetPasswordRedirectDTO;
import com.projectx.web.domain.LoginVerificationDTO;
import com.projectx.web.domain.UpdateEmailHashDTO;
import com.projectx.web.domain.UpdateMobilePinDTO;
import com.projectx.web.domain.UpdatePasswordDTO;
import com.projectx.web.domain.VerifyEmailHashDTO;
import com.projectx.web.domain.VerifyMobilePinDTO;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.*;

@RestController
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;
	
	@RequestMapping(value="/checkifexist",method=RequestMethod.POST)
	public CustomerQuickRegisterStringStatusEntity checkIfCustomerAlreadyExist(@RequestBody CustomerQuickRegisterEntityDTO customer) throws Exception
	{
		return customerQuickRegisterService.checkIfAlreadyRegistered(customer);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerQuickRegisterStatusEntity addNewCustomerQuickRegister(@RequestBody CustomerQuickRegisterEntityDTO newCustomer) throws Exception
	{		
		CustomerQuickRegisterStatusEntity newCustomerEntity=customerQuickRegisterService.handleNewCustomerQuickRegister(newCustomer);
						
		return newCustomerEntity;
	}

	
	@RequestMapping(value="/verifyEmailHash",method=RequestMethod.POST)
	public Boolean verifyEmailHash(@RequestBody VerifyEmailHashDTO verifyEmail)
	{
		if(customerQuickRegisterService.verifyEmailHash(verifyEmail.getCustomerId(),verifyEmail.getEmail(), verifyEmail.getEmailHash()))
			return true;
		else
			return false;
	}
	
	@RequestMapping(value="/verifyMobilePin",method=RequestMethod.POST)
	public Boolean verifyMobilePin(@RequestBody VerifyMobilePinDTO verifyMobile)
	{
		if(customerQuickRegisterService.verifyMobilePin(verifyMobile.getCustomerId(),verifyMobile.getMobile(), verifyMobile.getMobilePin()))
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

	
	@RequestMapping(value="/resetMobilePin",method=RequestMethod.POST)
	public Boolean updateMobilePin(@RequestBody UpdateMobilePinDTO updateMobilePin)
	{
		return customerQuickRegisterService.reSetMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getMobile());
	}
	
	@RequestMapping(value="/resetEmailHash",method=RequestMethod.POST)
	public Boolean updateEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
		return customerQuickRegisterService.reSetEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getEmail());
	}
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public Boolean resetPassword(@RequestBody CustomerIdDTO customerIdDTO)
	{
		Boolean result=customerQuickRegisterService.resetPassword(customerIdDTO);
		
		return result;
	}
	
	
	@RequestMapping(value="/resetPasswordRedirect",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity resetPasswordRedirect(@RequestBody ResetPasswordRedirectDTO passwordRedirectDTO)
	{
		CustomerQuickRegisterEntity quickRegisterEntity=customerQuickRegisterService.resetPasswordByEmailOrMobileRedirect(passwordRedirectDTO.getEntity());
		
		return quickRegisterEntity;
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public Boolean updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO)
	{
		UpdatePasswordAndPasswordTypeDTO updatePassword=new UpdatePasswordAndPasswordTypeDTO(updatePasswordDTO.getCustomerId(), 
																					updatePasswordDTO.getPassword(), CUST_PASSWORD_TYPE_CHANGED	);	
		Boolean result=customerQuickRegisterService.updatePassword(updatePassword);
		
		return result;
	}
	
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public CustomerAuthenticationDetails verifyLoginDetails(@RequestBody LoginVerificationDTO loginVerificationDTO)
	{
		CustomerAuthenticationDetails verifiedEntity= customerQuickRegisterService.verifyLoginDetails(loginVerificationDTO);
		
		return verifiedEntity;
	}
	
	@RequestMapping(value="/cleartestdata")
	public Boolean clearTestData()
	{
		customerQuickRegisterService.clearDataForTesting();
		return true;
	}
	//Document
	
	@RequestMapping(value="/saveCustomerDocument",method=RequestMethod.POST)
	public CustomerDocument saveCustomerDocument(@RequestBody CustomerDocument customerDocument)
	{
		return customerQuickRegisterService.saveCustomerDocument(customerDocument);
	}
	
	@RequestMapping(value="/getCustomerDocumentById",method=RequestMethod.POST)
	public CustomerDocument getCustomerDocumentById(@RequestBody CustomerIdDTO customerIdDTO)
	{
		return customerQuickRegisterService.getCustomerDocumentById(customerIdDTO.getCustomerId());
	}
	
	
//	@RequestMapping(value="/customer")
//	public ResetPasswordRedirectDTO show()
//	{
//		return new ResetPasswordRedirectDTO("dineshshe@gmail.com");
//	}
	
}
