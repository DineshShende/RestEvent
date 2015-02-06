package com.projectx.rest.services.completeregister;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;

@Service

public interface VehicleDetailsService {
	
	VehicleDetailsDTO addVehicle(VehicleDetailsDTO vehicleDetails);
	
	
	Boolean deleteVehicle(Long vehicleId);
	
	VehicleDetailsDTO getVehicleById(Long vehicleId);
	
	VehicleDetailsDTO getVehicleByRegistartionNumber(String registartionNumber);
	
	List<VehicleDetailsDTO> vehiclesByVendorId(Long vendorId);
	
	Integer count();
	
	Boolean clearTestData();

}
