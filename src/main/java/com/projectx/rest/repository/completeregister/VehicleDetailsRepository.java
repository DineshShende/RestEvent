package com.projectx.rest.repository.completeregister;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsNotFoundException;


@Repository
public interface VehicleDetailsRepository {

	VehicleDetails save(VehicleDetails vehicleDetails) throws VehicleDetailsAlreadyPresentException,ValidationFailedException;
	
	List<VehicleDetails> getVehiclesByVendorId(Long vendorId);
	
	Boolean deleteById( Long vehicleId);
	
	VehicleDetails findOne( Long vehicleId) throws VehicleDetailsNotFoundException;
	
	VehicleDetails findByRegistrationNumber(String registrationNumber) throws VehicleDetailsNotFoundException;
	
	VehicleDetails findByChassisNumber(String chassisNumber) throws VehicleDetailsNotFoundException;
	
	Integer count();
	
	Boolean clearTestData();
}
