package com.projectx.rest.repository.completeregister;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsUpdateFailedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;



@Repository
public interface DriverDetailsRepository {

	DriverDetails save(DriverDetails driverDetails) throws DriverDetailsAlreadyPresentException,ValidationFailedException;
	
	//DriverDetails update(DriverDetails driverDetails) throws DriverDetailsUpdateFailedException,ValidationFailedException;
	
	List<DriverDetails> getDriversByVendorId(Long vendorId);
	
	Integer updateMobileAndMobileVerificationStatus(Long driverId,Long mobile,Boolean status
			,Integer updatedBy,Long updatedById) throws ValidationFailedException;
	
	DriverDetails findOne(Long driverId) throws DriverDetailsNotFoundException;
	
	Boolean deleteById(Long driverId);
	
	Integer count();
	
	Boolean clearTestData();
}
