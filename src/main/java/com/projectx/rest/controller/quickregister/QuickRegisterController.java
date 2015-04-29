package com.projectx.rest.controller.quickregister;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.MobilePinPasswordDTO;
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
import com.projectx.rest.domain.comndto.ResponseDTO;
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

		
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CustomerQuickRegisterStatusEntity> addNewCustomerQuickRegister(@Valid @RequestBody CustomerQuickRegisterEntityDTO newCustomer,
			BindingResult bindingResult) throws Exception
	{		
		if(bindingResult.hasErrors())
			return new ResponseEntity<CustomerQuickRegisterStatusEntity>(new CustomerQuickRegisterStatusEntity("VALIDATION_FAILED", null),HttpStatus.OK);
		
		CustomerQuickRegisterStatusEntity preCheck=customerQuickRegisterService.checkIfAlreadyRegistered(newCustomer);
		
		ResponseEntity<CustomerQuickRegisterStatusEntity> result=null;
		
		if(preCheck.getStatus().equals(REGISTER_NOT_REGISTERED) )
		{
						
			try{
				CustomerQuickRegisterStatusEntity newCustomerEntity=customerQuickRegisterService.handleNewCustomerQuickRegister(newCustomer);
				result=new ResponseEntity<CustomerQuickRegisterStatusEntity>(newCustomerEntity, HttpStatus.CREATED);
			}
			catch(ResourceAlreadyPresentException e)
			{
				return new ResponseEntity<CustomerQuickRegisterStatusEntity>(new CustomerQuickRegisterStatusEntity(e.getMessage(), null),HttpStatus.ALREADY_REPORTED);
			}
			catch(RestClientException e)
			{
				return new ResponseEntity<CustomerQuickRegisterStatusEntity>(new CustomerQuickRegisterStatusEntity("COMMUNICATION_PROB_DATA_LAYER", null),HttpStatus.OK);
			}
		}else
		{
			result=new ResponseEntity<CustomerQuickRegisterStatusEntity>(preCheck, HttpStatus.ALREADY_REPORTED);
		}
							
		return result;
	}

	@RequestMapping(value="/getByCustomerId",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<QuickRegisterEntity>> getCustomerByCustomerId(@Valid @RequestBody CustomerIdTypeDTO customerIdDTO,
			BindingResult bindingResult )
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<ResponseDTO<QuickRegisterEntity>>
							(new ResponseDTO<QuickRegisterEntity>("VALIDATION_FAILED", null),HttpStatus.OK);
		
		ResponseEntity<ResponseDTO<QuickRegisterEntity>> result=null;
		
		try{
			QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getByEntityId(customerIdDTO.getCustomerId());
			result=new ResponseEntity<ResponseDTO<QuickRegisterEntity>>(new ResponseDTO<QuickRegisterEntity>
											("", fetchedEntity), HttpStatus.OK);
		}catch(QuickRegisterEntityNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<QuickRegisterEntity>>
			(new ResponseDTO<QuickRegisterEntity>(e.getMessage(), null),HttpStatus.OK);
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
	
	
	
}
