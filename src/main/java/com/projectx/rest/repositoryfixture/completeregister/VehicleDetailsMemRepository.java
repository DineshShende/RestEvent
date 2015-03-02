package com.projectx.rest.repositoryfixture.completeregister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;

@Component
@Profile(value="Test")
public class VehicleDetailsMemRepository implements VehicleDetailsRepository {

	Map<Long,VehicleDetails> list=new HashMap<Long,VehicleDetails>();
	
	@Override
	public VehicleDetails save(VehicleDetails vehicleDetails) {

		if(vehicleDetails.getVehicleId()==null)
			vehicleDetails.setVehicleId(123L);
		
		list.put(vehicleDetails.getVehicleId(), vehicleDetails);
		
		return vehicleDetails;
	}

	@Override
	public List<VehicleDetails> getVehiclesByVendorId(Long vendorId) {
	
		List<VehicleDetails> vehicleList=new ArrayList<VehicleDetails>();
		
		
		for(Long key:list.keySet())
		{
			if(list.get(key).getVendorId().equals(vendorId))
				vehicleList.add(list.get(key));
		}
		
		return vehicleList;
		

		
	}

	@Override
	public Boolean deleteById(Long vehicleId) {

		list.remove(vehicleId);
		return true;
	}

	@Override
	public VehicleDetails findOne(Long vehicleId)
			throws VehicleDetailsNotFoundException {

		
		return list.get(vehicleId);
	}

	@Override
	public VehicleDetails findByRegistrationNumber(String registrationNumber)
			throws VehicleDetailsNotFoundException {
		
		for(Long key:list.keySet())
		{
			if(list.get(key).getRegistrationNumber().equals(registrationNumber))
				return list.get(key);
		}
		
		throw new VehicleDetailsNotFoundException();
		
	}

	@Override
	public Integer count() {
		
		return list.size();
	}

	@Override
	public Boolean clearTestData() {

		list.clear();
		return true;
	}

}
