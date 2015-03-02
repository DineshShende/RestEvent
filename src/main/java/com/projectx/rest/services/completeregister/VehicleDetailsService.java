package com.projectx.rest.services.completeregister;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.VehicleDetails;

@Service

public interface VehicleDetailsService {
	
	VehicleDetails addVehicle(VehicleDetails vehicleDetails);
	
	
	Boolean deleteVehicle(Long vehicleId);
	
	VehicleDetails getVehicleById(Long vehicleId);
	
	VehicleDetails getVehicleByRegistartionNumber(String registartionNumber);
	
	List<VehicleDetails> vehiclesByVendorId(Long vendorId);
	
	Integer count();
	
	Boolean clearTestData();

}
