package com.projectx.rest.controller.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@RestController
@RequestMapping(value = "/request/freightRequestByVendor")
public class FreightRequestByVendorController {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;

	@RequestMapping(method = RequestMethod.POST)
	public FreightRequestByVendor newRequest(
			@RequestBody FreightRequestByVendor freightRequestByVendor) {

		FreightRequestByVendor savedEntity=null;
		
		try{
		savedEntity = freightRequestByVendorService
				.newRequest(freightRequestByVendor);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return savedEntity;
	}

	@RequestMapping(value = "/getById/{requestId}")
	public FreightRequestByVendor getRequestById(@PathVariable Long requestId) {
		FreightRequestByVendor savedEntity = freightRequestByVendorService
				.getRequestById(requestId);

		return savedEntity;
	}

	@RequestMapping(value = "/findByVendorId/{vendorId}")
	public List<FreightRequestByVendor> getAllRequestForVendor(
			@PathVariable Long vendorId) {
		List<FreightRequestByVendor> savedEntity = freightRequestByVendorService
				.getAllRequestForVendor(vendorId);

		return savedEntity;

	}

	@RequestMapping(value = "/getMatchingVendorReqForCustomerReq",method=RequestMethod.POST)
	public List<FreightRequestByVendor> getMatchingVendorReqForCustomerReq(@RequestBody FreightRequestByCustomer freightRequestByCustomer) {
		
		List<FreightRequestByVendor> savedEntity = freightRequestByVendorService
				.getMatchingVendorReqFromCustomerReq(freightRequestByCustomer);

		return savedEntity;

	}

	
	
	@RequestMapping(value = "/deleteById/{requestId}")
	public Boolean deleteRequestById(@PathVariable Long requestId) {
		Boolean result = freightRequestByVendorService
				.deleteRequestById(requestId);

		return result;
	}

	@RequestMapping(value = "/clearTestData")
	public Boolean clearTestData() {

		Boolean result = freightRequestByVendorService.clearTestData();

		return result;
	}

	@RequestMapping(value = "/count")
	public Integer count() {

		Integer result = freightRequestByVendorService.count();

		return result;
	}

}
