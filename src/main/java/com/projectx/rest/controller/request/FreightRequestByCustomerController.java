package com.projectx.rest.controller.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.services.request.FreightRequestByCustomerService;

@RestController
@RequestMapping(value="/request/freightRequestByCustomer")
public class FreightRequestByCustomerController {

	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@RequestMapping(method=RequestMethod.POST)
	public FreightRequestByCustomer newRequest(@RequestBody FreightRequestByCustomer freightRequestByCustomer)
	{
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.newRequest(freightRequestByCustomer);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getById/{requestId}")
	public FreightRequestByCustomer getRequestById(@PathVariable Long requestId){
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.getRequestById(requestId);
		
		return savedEntity;
		
	}
	
	@RequestMapping(value="/getByCustomerId/{customerId}")
	public List<FreightRequestByCustomer> getAllRequestForCustomer(@PathVariable Long customerId)
	{
		List<FreightRequestByCustomer> savedEntity=freightRequestByCustomerService.getAllRequestForCustomer(customerId);
		
		return savedEntity;
		
	}
	
	@RequestMapping(value="/getMatchingCustomerReqForVendorReq",method=RequestMethod.POST)
	public List<FreightRequestByCustomer> getMatchingCustomerReqForVendorReq(@RequestBody FreightRequestByVendor freightRequestByVendor)
	{
		List<FreightRequestByCustomer> savedEntity=freightRequestByCustomerService.getMatchingCustReqForVendorReq(freightRequestByVendor);
		
		return savedEntity;
		
	}
	
	@RequestMapping(value="/deleteById/{requestId}")
	public Boolean deleteRequestById(@PathVariable Long requestId)
	{
		Boolean result=freightRequestByCustomerService.deleteRequestById(requestId);
		
		return result;
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
