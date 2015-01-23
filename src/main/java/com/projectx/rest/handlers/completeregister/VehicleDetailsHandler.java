package com.projectx.rest.handlers.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.services.completeregister.VehicleDetailsService;

@Component
@Profile(value="Dev")
public class VehicleDetailsHandler implements VehicleDetailsService {

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Override
	public VehicleDetailsDTO addVehicle(VehicleDetailsDTO vehicleDetails) {
		
		VehicleDetailsDTO addedVehicle=vehicleDetailsRepository.save(vehicleDetails);
		
		return addedVehicle;		
	}

	
	@Override
	public Boolean deleteVehicle(Long vehicleId) {
		
		Boolean result=vehicleDetailsRepository.deleteById(vehicleId);
		
		return result;
		
	}

	@Override
	public VehicleDetailsDTO getVehicleById(Long vehicleId) {

		VehicleDetailsDTO fetchedVehicle=vehicleDetailsRepository.findOne(vehicleId);
		
		return fetchedVehicle;
		
	}

	@Override
	public List<VehicleDetailsDTO> vehiclesByVendorId(Long vendorId) {

		List<VehicleDetailsDTO> vehicleList=vehicleDetailsRepository.getVehiclesByVendorId(vendorId);
		
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

}
