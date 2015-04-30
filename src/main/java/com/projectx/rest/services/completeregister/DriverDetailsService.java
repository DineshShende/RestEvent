package com.projectx.rest.services.completeregister;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.DriverDetails;

@Service
public interface DriverDetailsService {
	
	DriverDetails save(DriverDetails driverDetails);
	
	DriverDetails getDriverById(Long driverId);
	
	DriverDetails getDriverByLicenceNumber(String licenceNumber);
	
	Boolean deleteDriver(Long driverId);
	
	Integer updateMobileAndVerificationStatus(Long driverId,Long mobile,Boolean status,Integer updatedBy,Long updatedById);
	
	List<DriverDetails> driversByVendorId(Long vendorId);
	
	Integer count();
	
	Boolean clearTestData();

}
