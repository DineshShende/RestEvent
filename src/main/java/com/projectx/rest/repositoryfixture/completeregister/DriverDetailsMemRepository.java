package com.projectx.rest.repositoryfixture.completeregister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.async.RetriggerDetails;
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.repository.completeregister.DriverDetailsRepository;

@Component
@Profile(value="Test")
public class DriverDetailsMemRepository implements DriverDetailsRepository {

	Map<Long,DriverDetails> list=new HashMap<Long,DriverDetails>();
	
	@Override
	public DriverDetails save(DriverDetails driverDetails) {
		
		if(driverDetails.getDriverId()==null)
			driverDetails.setDriverId(123L);
		
		list.put(driverDetails.getDriverId(), driverDetails);
		
		return driverDetails;
	}

	@Override
	public DriverDetails update(DriverDetails driverDetails) {

		list.replace(driverDetails.getDriverId(), driverDetails);
		
		return driverDetails;
	}

	@Override
	public List<DriverDetails> getDriversByVendorId(Long vendorId) {
		
		List<DriverDetails> driverList=new ArrayList<DriverDetails>();
		
		
		for(Long key:list.keySet())
		{
			if(list.get(key).getVendorId().equals(vendorId))
			driverList.add(list.get(key));
		}
		
		return driverList;
		
				
	}

	@Override
	public Integer updateMobileAndMobileVerificationStatus(Long driverId,
			Long mobile, Boolean status, String updatedBy) {
		

		
		DriverDetails oldRecord=list.get(driverId);
		
		if(oldRecord!=null)
		{	
			list.remove(driverId);
		
			oldRecord.setMobile(mobile);
			oldRecord.setIsMobileVerified(status);			
			oldRecord.setUpdatedBy(updatedBy);
					
			return 1;
		}
		else
			return 0;
		
	}

	@Override
	public DriverDetails findOne(Long driverId) {
		
		return list.get(driverId);
	}

	@Override
	public Boolean deleteById(Long driverId) {

		list.remove(driverId);
		
		return true;
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
