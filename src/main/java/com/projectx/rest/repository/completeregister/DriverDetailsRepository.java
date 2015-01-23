package com.projectx.rest.repository.completeregister;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.DriverDetails;



@Repository
public interface DriverDetailsRepository {

	DriverDetails save(DriverDetails driverDetails);
	
	DriverDetails update(DriverDetails driverDetails);
	
	List<DriverDetails> getDriversByVendorId(Long vendorId);
	
	Integer updateMobileAndMobileVerificationStatus(Long driverId,Long mobile,Boolean status);
	
	DriverDetails findOne(Long driverId);
	
	Boolean deleteById(Long driverId);
	
	Integer count();
	
	Boolean clearTestData();
}
