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

import com.projectx.rest.domain.comndto.ResponseDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@RestController
@RequestMapping(value = "/request/freightRequestByVendor")
public class FreightRequestByVendorController {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO<FreightRequestByVendor>> newRequest(
			@Valid @RequestBody FreightRequestByVendor freightRequestByVendor,BindingResult bindingResult) {

		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<FreightRequestByVendor>> result=null;
		
		try{
			FreightRequestByVendor savedEntity = freightRequestByVendorService
				.newRequest(freightRequestByVendor);
			
			result=new ResponseEntity<ResponseDTO<FreightRequestByVendor>>(new ResponseDTO<FreightRequestByVendor>("",savedEntity), HttpStatus.CREATED);
			
		}
		catch(ResourceNotFoundException e)
		{
			result=new ResponseEntity<ResponseDTO<FreightRequestByVendor>>(new ResponseDTO<FreightRequestByVendor>(e.getMessage(),null), HttpStatus.OK);
		}catch (ValidationFailedException e) {
			result=new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		return result;
	}

	@RequestMapping(value = "/getById/{requestId}")
	public ResponseEntity<FreightRequestByVendorDTO> getRequestById(@PathVariable Long requestId) {
		
		try{
			
			FreightRequestByVendorDTO savedEntity = freightRequestByVendorService
					.getRequestById(requestId);
			
			return new ResponseEntity<FreightRequestByVendorDTO>(savedEntity, HttpStatus.FOUND);
		}catch(ResourceNotFoundException e)
		{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/findByVendorId/{vendorId}")
	public ResponseEntity<List<FreightRequestByVendorDTO>> getAllRequestForVendor(
			@PathVariable Long vendorId) {
		List<FreightRequestByVendorDTO> savedEntity = freightRequestByVendorService
				.getAllRequestForVendor(vendorId);

		return new ResponseEntity<List<FreightRequestByVendorDTO>>(savedEntity, HttpStatus.OK);

	}

	@RequestMapping(value = "/getMatchingVendorReqForCustomerReq",method=RequestMethod.POST)
	public ResponseEntity<List<FreightRequestByVendorDTO>> getMatchingVendorReqForCustomerReq(@Valid @RequestBody FreightRequestByCustomer freightRequestByCustomer,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		List<FreightRequestByVendorDTO> savedEntity = freightRequestByVendorService
				.getMatchingVendorReqFromCustomerReq(freightRequestByCustomer);

		return new ResponseEntity<List<FreightRequestByVendorDTO>>(savedEntity, HttpStatus.OK);

	}

	
	
	@RequestMapping(value = "/deleteById/{requestId}")
	public ResponseEntity<ResponseDTO<Boolean>> deleteRequestById(@PathVariable Long requestId) {
		
		
		try{
		Boolean result = freightRequestByVendorService
				.deleteRequestById(requestId);

			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",result), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null), HttpStatus.OK);
		}
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
