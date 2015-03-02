package com.projectx.rest.controller.request;

import java.util.List;

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

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.request.FreightRequestByCustomerService;

@RestController
@RequestMapping(value="/request/freightRequestByCustomer")
public class FreightRequestByCustomerController {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<FreightRequestByCustomer> newRequest(@Valid @RequestBody FreightRequestByCustomer freightRequestByCustomer,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<FreightRequestByCustomer> result=null;
		
		try{
			FreightRequestByCustomer savedEntity=freightRequestByCustomerService.newRequest(freightRequestByCustomer);
			result=new ResponseEntity<FreightRequestByCustomer>(savedEntity, HttpStatus.CREATED);
		}catch(ResourceAlreadyPresentException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}catch(ValidationFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getById/{requestId}")
	public ResponseEntity<FreightRequestByCustomer> getRequestById(@PathVariable Long requestId){
		
		try{
			FreightRequestByCustomer savedEntity=freightRequestByCustomerService.getRequestById(requestId);
			
			return new ResponseEntity<FreightRequestByCustomer>(savedEntity, HttpStatus.FOUND);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/getByCustomerId/{customerId}")
	public ResponseEntity<List<FreightRequestByCustomer>> getAllRequestForCustomer(@PathVariable Long customerId)
	{
		List<FreightRequestByCustomer> savedEntity=freightRequestByCustomerService.getAllRequestForCustomer(customerId);
		
		return new ResponseEntity<List<FreightRequestByCustomer>>(savedEntity, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getMatchingCustomerReqForVendorReq",method=RequestMethod.POST)
	public ResponseEntity<List<FreightRequestByCustomer>> getMatchingCustomerReqForVendorReq(@RequestBody FreightRequestByVendor freightRequestByVendor)
	{
		List<FreightRequestByCustomer> savedEntity=freightRequestByCustomerService.getMatchingCustReqForVendorReq(freightRequestByVendor);
		
		return new ResponseEntity<List<FreightRequestByCustomer>>(savedEntity, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public ResponseEntity<Boolean> deleteRequestById(@PathVariable Long requestId)
	{
		Boolean result=freightRequestByCustomerService.deleteRequestById(requestId);
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData(){
		
		Boolean result=freightRequestByCustomerService.clearTestData();
		
		return result;
	}
	
	@RequestMapping(value="/count")
	public Integer count(){
		
		Integer result=freightRequestByCustomerService.count();
		
		return result;
	}
	
}
