package com.projectx.rest.controller.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsUpdateFailedException;
import com.projectx.rest.services.completeregister.DriverDetailsService;

@RestController
@RequestMapping(value="/vendor/driver")
public class DriverDetailsController {

	@Autowired
	DriverDetailsService driverDetailsService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<DriverDetails> addDriver(@RequestBody DriverDetails driverDetails)
	{
		ResponseEntity<DriverDetails> result=null;
		
		try{
			DriverDetails savedDriver=driverDetailsService.addDriver(driverDetails);
			result=new ResponseEntity<DriverDetails>(savedDriver, HttpStatus.CREATED);
		}catch(DriverDetailsAlreadyPresentException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<DriverDetails> updateDriver(@RequestBody DriverDetails driverDetails)
	{
		ResponseEntity<DriverDetails> result=null;
		
		try{
			DriverDetails savedDriver=driverDetailsService.updateDriver(driverDetails);
			result=new ResponseEntity<DriverDetails>(savedDriver, HttpStatus.OK);
		}catch(DriverDetailsUpdateFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		return result;
		
	}
	
	@RequestMapping(value="/getByDriverId/{driverId}")
	public ResponseEntity<DriverDetails> getByDriverId(@PathVariable Long driverId)
	{
		ResponseEntity<DriverDetails> result=null;
		try{
			DriverDetails savedDriver=driverDetailsService.getDriverById(driverId);
			result=new ResponseEntity<DriverDetails>(savedDriver, HttpStatus.FOUND);
		}catch(DriverDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/deleteByDriverId/{driverId}")
	public Boolean deleteByDriverId(@PathVariable Long driverId)
	{
		Boolean result=driverDetailsService.deleteDriver(driverId);
		
		return result;
		
	}
	
	@RequestMapping(value="/getDriversByVendor/{vendorId}")
	public List<DriverDetails> getDriversByVendor(@PathVariable Long vendorId)
	{
		List<DriverDetails> driverList=driverDetailsService.driversByVendorId(vendorId);
		
		return driverList;
		
	}
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer count=driverDetailsService.count();
		
		return count;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		return driverDetailsService.clearTestData();
	}
}
