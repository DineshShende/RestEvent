package com.projectx.rest.controller.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.services.completeregister.VehicleDetailsService;

@RestController
@RequestMapping(value="/vendor/vehicle")
public class VehicleDetailsController {

	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	@RequestMapping(method=RequestMethod.POST)
	public VehicleDetailsDTO addVehicle(@RequestBody VehicleDetailsDTO vehicleDetails)
	{
		VehicleDetailsDTO savedVehicle=vehicleDetailsService.addVehicle(vehicleDetails);
		
		return savedVehicle;
		
	}
	
	@RequestMapping(value="/getByVehicleId/{vehicleId}")
	public VehicleDetailsDTO getByVehicleId(@PathVariable Long vehicleId)
	{
		VehicleDetailsDTO fetchedVehicle=vehicleDetailsService.getVehicleById(vehicleId);
		
		return fetchedVehicle;
		
	}
	
	@RequestMapping(value="/deleteByVehicleId/{vehicleId}")
	public Boolean deleteByVehicleId(@PathVariable Long vehicleId)
	{
		Boolean result=vehicleDetailsService.deleteVehicle(vehicleId);
		
		return result;
		
	}
	
	@RequestMapping(value="/getVehicleByVendor/{vendorId}")
	public List<VehicleDetailsDTO> getDriversByVendor(@PathVariable Long vendorId)
	{
		List<VehicleDetailsDTO> driverList=vehicleDetailsService.vehiclesByVendorId(vendorId);
		
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
