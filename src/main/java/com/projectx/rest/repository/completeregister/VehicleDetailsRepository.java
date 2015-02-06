package com.projectx.rest.repository.completeregister;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;


@Repository
public interface VehicleDetailsRepository {

	VehicleDetailsDTO save(VehicleDetailsDTO vehicleDetails);
	
	List<VehicleDetailsDTO> getVehiclesByVendorId(Long vendorId);
	
	Boolean deleteById( Long vehicleId);
	
	VehicleDetailsDTO findOne( Long vehicleId);
	
	VehicleDetailsDTO findByRegistrationNumber(String registrationNumber);
	
	Integer count();
	
	Boolean clearTestData();
}
