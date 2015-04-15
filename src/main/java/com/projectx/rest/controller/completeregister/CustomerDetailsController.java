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
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.mvc.domain.completeregister.EntityIdDTO;
import com.projectx.mvc.domain.completeregister.VerifyEmailDTO;
import com.projectx.mvc.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

@RestController
@RequestMapping(value="/customer")
public class CustomerDetailsController {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	
	@RequestMapping(value="/createFromQuickRegister",method=RequestMethod.POST)
	public ResponseEntity<CustomerDetails> createCustomerDetailsFromQuickRegisterEntity(@Valid @RequestBody EntityIdDTO entityIdDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<CustomerDetails> result=null;
		QuickRegisterEntity entity=null;
		
		try{
			entity=quickRegisterService.getByEntityId(entityIdDTO.getEntityId());
			
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		try{
			CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(entity);
			result=new ResponseEntity<CustomerDetails>(savedEntity, HttpStatus.OK);
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		return result;
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CustomerDetails> merge(@Valid @RequestBody CustomerDetails customerDetails,BindingResult  bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<CustomerDetails> result=null;
		
		try{
			CustomerDetails savedEntity=customerDetailsService.mergeCustomerDetails(customerDetails);
			result=new ResponseEntity<CustomerDetails>(savedEntity, HttpStatus.OK);
		}catch(CustomerDetailsTransactionalUpdateFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getCustomerDetailsById/{customerId}",method=RequestMethod.GET)
	public ResponseEntity<CustomerDetails> getCustomerDetailsById(@PathVariable Long customerId)
	{
		ResponseEntity<CustomerDetails> result=null;
		
		try{
			CustomerDetails fetchedEntity=customerDetailsService.findById(customerId);
			result=new ResponseEntity<CustomerDetails>(fetchedEntity, HttpStatus.FOUND);
		}catch(CustomerDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
	}
	
	

	@RequestMapping(value="/count",method=RequestMethod.GET)
	public  Integer getCount()
	{
		return customerDetailsService.count();
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		customerDetailsService.clearTestData();
		
		return true;
	}
	
	
	
	
}
