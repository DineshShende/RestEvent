package com.projectx.rest.controller.completeregister;

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
import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsNotFoundException;
import com.projectx.rest.services.completeregister.VehicleDetailsService;

@RestController
@RequestMapping(value="/vendor/vehicle")
public class VehicleDetailsController {

	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<VehicleDetails>> addVehicle(@Valid @RequestBody VehicleDetails vehicleDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			VehicleDetails savedVehicle=vehicleDetailsService.addVehicle(vehicleDetails);
			return new ResponseEntity<ResponseDTO<VehicleDetails>>(new ResponseDTO<VehicleDetails>("",savedVehicle), HttpStatus.CREATED);
		}catch(ValidationFailedException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}catch(VehicleDetailsAlreadyPresentException e)
		{
			return new ResponseEntity<ResponseDTO<VehicleDetails>>(new ResponseDTO<VehicleDetails>(e.getMessage(),null),HttpStatus.ALREADY_REPORTED);
		}
		
				
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
	
	@RequestMapping(value="/getByRegistrationNumber/{vehicleRegistrationNumber}")
	public ResponseEntity<VehicleDetails> getByRegistration(@PathVariable String vehicleRegistrationNumber)
	{
		ResponseEntity<VehicleDetails> result=null;
		
		try{
			VehicleDetails fetchedVehicle=vehicleDetailsService.getVehicleByRegistartionNumber(vehicleRegistrationNumber);
			result=new ResponseEntity<VehicleDetails>(fetchedVehicle, HttpStatus.FOUND);
		}catch(VehicleDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/deleteByVehicleId/{vehicleId}")
	public ResponseEntity<Boolean> deleteByVehicleId(@PathVariable Long vehicleId)
	{
		Boolean result=vehicleDetailsService.deleteVehicle(vehicleId);
			
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		
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
