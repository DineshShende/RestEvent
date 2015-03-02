package com.projectx.rest.controller.quickregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterStringStatusEntity;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.mvc.domain.quickregister.UpdateMobilePinDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.mvc.domain.quickregister.VerifyEmailHashDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobilePinDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

@RestController
@RequestMapping(value="/customer/quickregister")
public class QuickRegisterController {
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
	@RequestMapping(value="/checkifexist",method=RequestMethod.POST)
	public CustomerQuickRegisterStringStatusEntity checkIfCustomerAlreadyExist(@RequestBody CustomerQuickRegisterEntityDTO customer) throws Exception
	{
		return customerQuickRegisterService.checkIfAlreadyRegistered(customer);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CustomerQuickRegisterStatusEntity> addNewCustomerQuickRegister(@RequestBody CustomerQuickRegisterEntityDTO newCustomer) throws Exception
	{		
		ResponseEntity<CustomerQuickRegisterStatusEntity> result=null;
		
		try{
			CustomerQuickRegisterStatusEntity newCustomerEntity=customerQuickRegisterService.handleNewCustomerQuickRegister(newCustomer);
			result=new ResponseEntity<CustomerQuickRegisterStatusEntity>(newCustomerEntity, HttpStatus.CREATED);
		}catch(RestClientException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		catch(ResourceAlreadyPresentException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}catch (ResourceNotFoundException e) {
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
						
		return result;
	}

	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public ResponseEntity<QuickRegisterEntity> getCustomerByCustomerId(@RequestBody CustomerIdTypeDTO customerIdDTO)
	{
		ResponseEntity<QuickRegisterEntity> result=null;
		
		try{
			QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getByEntityId(customerIdDTO.getCustomerId());
			result=new ResponseEntity<QuickRegisterEntity>(fetchedEntity, HttpStatus.FOUND);
		}catch(QuickRegisterEntityNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
	}

	
	@RequestMapping(value="/cleartestdata")
	public Boolean clearTestData()
	{
		customerQuickRegisterService.clearDataForTesting();
		return true;
	}
	//Document
	
	
	
	
	/*
	
	@RequestMapping(value="/customer")
	public QuickRegisterEntity show()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN, false, false, CUST_EMAIL_TYPE_PRIMARY,
				new Date(), new Date(), "CUST_ONLINE");
	}
	
	@RequestMapping(value="/customer")
	public MobileVerificationDetails show()
	{
		return new MobileVerificationDetails(CUST_ID, CUST_MOBILE_TYPE_PRIMARY, CUST_MOBILE, CUST_MOBILEPIN, 0, 0);
	}
	*/
	
}
