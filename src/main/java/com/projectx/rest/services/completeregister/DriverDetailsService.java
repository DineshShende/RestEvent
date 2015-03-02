package com.projectx.rest.services.completeregister;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.DriverDetails;

@Service
public interface DriverDetailsService {
	
	DriverDetails addDriver(DriverDetails driverDetails);
	
	DriverDetails updateDriver(DriverDetails driverDetails);
	
	DriverDetails getDriverById(Long driverId);
	
	Boolean deleteDriver(Long driverId);
	
	Integer updateMobileAndVerificationStatus(Long driverId,Long mobile,Boolean status,String updatedBy);
	
	List<DriverDetails> driversByVendorId(Long vendorId);
	
	Integer count();
	
	Boolean clearTestData();

}
