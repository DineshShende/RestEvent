package com.projectx.rest.handlers.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.services.completeregister.VehicleDetailsService;

@Component

public class VehicleDetailsHandler implements VehicleDetailsService {

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Override
	public VehicleDetails addVehicle(VehicleDetails vehicleDetails) throws VehicleDetailsAlreadyPresentException,ValidationFailedException{
		
		VehicleDetails addedVehicle=vehicleDetailsRepository.save(vehicleDetails);
		
		return addedVehicle;		
	}

	
	@Override
	public Boolean deleteVehicle(Long vehicleId) {
		
		Boolean result=vehicleDetailsRepository.deleteById(vehicleId);
		
		return result;
		
	}

	@Override
	public VehicleDetails getVehicleById(Long vehicleId) throws VehicleDetailsNotFoundException{

		VehicleDetails fetchedVehicle=vehicleDetailsRepository.findOne(vehicleId);
		
		return fetchedVehicle;
		
	}

	@Override
	public List<VehicleDetails> vehiclesByVendorId(Long vendorId) {

		List<VehicleDetails> vehicleList=vehicleDetailsRepository.getVehiclesByVendorId(vendorId);
		
		return vehicleList;
	}


	@Override
	public Integer count() {
		
		return vehicleDetailsRepository.count();
	}


	@Override
	public Boolean clearTestData() {
		
		return vehicleDetailsRepository.clearTestData();
	}


	@Override
	public VehicleDetails getVehicleByRegistartionNumber(
			String registartionNumber) {
		
		VehicleDetails fetchedVehicle=vehicleDetailsRepository.findByRegistrationNumber(registartionNumber);
		
		return fetchedVehicle;
	

		
	}

}
