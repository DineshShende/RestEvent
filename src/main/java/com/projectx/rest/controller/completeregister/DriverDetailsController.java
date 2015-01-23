package com.projectx.rest.controller.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.services.completeregister.DriverDetailsService;

@RestController
@RequestMapping(value="/vendor/driver")
public class DriverDetailsController {

	@Autowired
	DriverDetailsService driverDetailsService;
	
	@RequestMapping(method=RequestMethod.POST)
	public DriverDetails addDriver(@RequestBody DriverDetails driverDetails)
	{
		DriverDetails savedDriver=driverDetailsService.addDriver(driverDetails);
		
		return savedDriver;
		
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public DriverDetails updateDriver(@RequestBody DriverDetails driverDetails)
	{
		DriverDetails savedDriver=driverDetailsService.updateDriver(driverDetails);
		
		return savedDriver;
		
	}
	
	@RequestMapping(value="/getByDriverId/{driverId}")
	public DriverDetails getByDriverId(@PathVariable Long driverId)
	{
		DriverDetails savedDriver=driverDetailsService.getDriverById(driverId);
		
		return savedDriver;
		
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
