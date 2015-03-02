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
import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsNotFoundException;
import com.projectx.rest.services.completeregister.VehicleDetailsService;

@RestController
@RequestMapping(value="/vendor/vehicle")
public class VehicleDetailsController {

	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	@RequestMapping(method=RequestMethod.POST)
	public VehicleDetails addVehicle(@RequestBody VehicleDetails vehicleDetails)
	{
		VehicleDetails savedVehicle=vehicleDetailsService.addVehicle(vehicleDetails);
		
		return savedVehicle;
		
	}
	
	@RequestMapping(value="/getByVehicleId/{vehicleId}")
	public ResponseEntity<VehicleDetails> getByVehicleId(@PathVariable Long vehicleId)
	{
		ResponseEntity<VehicleDetails> result=null;
		
		try{
			VehicleDetails fetchedVehicle=vehicleDetailsService.getVehicleById(vehicleId);
			result=new ResponseEntity<VehicleDetails>(fetchedVehicle, HttpStatus.FOUND);
		}catch(VehicleDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/deleteByVehicleId/{vehicleId}")
	public Boolean deleteByVehicleId(@PathVariable Long vehicleId)
	{
		Boolean result=vehicleDetailsService.deleteVehicle(vehicleId);
		
		return result;
		
	}
	
	@RequestMapping(value="/getVehicleByVendor/{vendorId}")
	public List<VehicleDetails> getDriversByVendor(@PathVariable Long vendorId)
	{
		List<VehicleDetails> driverList=vehicleDetailsService.vehiclesByVendorId(vendorId);
		
		return driverList;
		
	}
	
	@RequestMapping(value="/count")
	public Integer count()
	{
		Integer count=vehicleDetailsService.count();
		
		return count;
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData()
	{
		return vehicleDetailsService.clearTestData();
	}
	
}
