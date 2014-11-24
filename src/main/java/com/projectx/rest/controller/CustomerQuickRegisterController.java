package com.projectx.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.CustomerIdEmailDTO;
import com.projectx.data.domain.CustomerIdMobileDTO;
import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerDocument;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.CustomerQuickRegisterStringStatusEntity;
import com.projectx.web.domain.LoginVerificationWithDefaultEmailPasswordDTO;
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

	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByCustomerId(@RequestBody CustomerIdDTO customerIdDTO)
	{
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerIdDTO.getCustomerId());
		
		return fetchedEntity;
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
	
	@RequestMapping(value="/resendMobilePin",method=RequestMethod.POST)
	public Boolean reSendMobilePin(@RequestBody UpdateMobilePinDTO updateMobilePin)
	{
		Boolean result= customerQuickRegisterService.reSendMobilePin(updateMobilePin.getCustomerId(),updateMobilePin.getMobile());
		
		return result;
	}
	
	@RequestMapping(value="/resendEmailHash",method=RequestMethod.POST)
	public Boolean reSendEmailHash(@RequestBody UpdateEmailHashDTO updateEmailHash)
	{
				
		Boolean result= customerQuickRegisterService.reSendEmailHash(updateEmailHash.getCustomerId(),updateEmailHash.getEmail());
		
		return result;
	}
	
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public CustomerAuthenticationDetails verifyLoginDetails(@RequestBody LoginVerificationDTO loginVerificationDTO)
	{
		CustomerAuthenticationDetails verifiedEntity= customerQuickRegisterService.verifyLoginDetails(loginVerificationDTO);
		
		return verifiedEntity;
	}
	
	@RequestMapping(value="/verifyLoginDefaultEmailPassword")
	public CustomerAuthenticationDetails verifyLoginDefaultEmailPassword(@RequestBody LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO)
	{
		CustomerAuthenticationDetails verifiedEntity= customerQuickRegisterService.verifyDefaultEmailLoginDetails(emailPasswordDTO);
		
		return verifiedEntity;
		
	}

	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public Boolean resetPassword(@RequestBody CustomerIdDTO customerIdDTO)
	{
		Boolean result=customerQuickRegisterService.resetPassword(customerIdDTO);
		
		return result;
	}
	
	@RequestMapping(value="/resendPassword",method=RequestMethod.POST)
	public Boolean resendPassword(@RequestBody CustomerIdDTO customerIdDTO)
	{
		Boolean result=customerQuickRegisterService.resendPassword(customerIdDTO);
		
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

	
	@RequestMapping(value="/getEmailVerificationDetails",method=RequestMethod.POST)
	public CustomerEmailVerificationDetails getEmailVerificationDetails(@RequestBody CustomerIdEmailDTO emailDTO)
	{
		CustomerEmailVerificationDetails fetchedResult=customerQuickRegisterService.
				getCustomerEmailVerificationDetailsByCustomerIdAndEmail(emailDTO.getCustomerId(), emailDTO.getEmail());
		
		return fetchedResult;
	}
	
	@RequestMapping(value="/getMobileVerificationDetails",method=RequestMethod.POST)
	public CustomerMobileVerificationDetails getMobileVerificationDetails(@RequestBody CustomerIdMobileDTO mobileDTO)
	{
		CustomerMobileVerificationDetails fetchedResult=customerQuickRegisterService.
				getCustomerMobileVerificationDetailsByCustomerIdAndMobile(mobileDTO.getCustomerId(), mobileDTO.getMobile());
		
		return fetchedResult;
	}
	
	@RequestMapping(value="/getAuthenticationDetailsById",method=RequestMethod.POST)
	public CustomerAuthenticationDetails getAuthenticationDetailsByCustomerId(@RequestBody CustomerIdDTO customerId)
	{
		CustomerAuthenticationDetails verifiedEntity= customerQuickRegisterService.getLoginDetailsByCustomerId(customerId.getCustomerId());
		
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
	
	
	@RequestMapping(value="/customer")
	public CustomerMobileVerificationDetails show()
	{
		return new CustomerMobileVerificationDetails(CUST_ID, CUST_MOBILE_TYPE_PRIMARY, CUST_MOBILE, CUST_MOBILEPIN, 0, 0);
	}
	
}
