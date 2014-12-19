package com.projectx.rest.controller.quickregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.CustomerDocument;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.web.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.web.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.quickregister.CustomerQuickRegisterStringStatusEntity;
import com.projectx.web.domain.quickregister.LoginVerificationDTO;
import com.projectx.web.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.web.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.web.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.web.domain.quickregister.UpdateMobilePinDTO;
import com.projectx.web.domain.quickregister.UpdatePasswordDTO;
import com.projectx.web.domain.quickregister.VerifyEmailHashDTO;
import com.projectx.web.domain.quickregister.VerifyMobilePinDTO;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

@RestController
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
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
	public QuickRegisterEntity getCustomerByCustomerId(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerIdDTO.getCustomerId());
		
		return fetchedEntity;
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
	public CustomerDocument getCustomerDocumentById(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		return customerQuickRegisterService.getCustomerDocumentById(customerIdDTO.getCustomerId());
	}
	
	
	/*
	@RequestMapping(value="/customer")
	public MobileVerificationDetails show()
	{
		return new MobileVerificationDetails(CUST_ID, CUST_MOBILE_TYPE_PRIMARY, CUST_MOBILE, CUST_MOBILEPIN, 0, 0);
	}
	*/
	
}
