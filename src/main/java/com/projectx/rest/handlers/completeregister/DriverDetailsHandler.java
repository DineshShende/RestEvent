package com.projectx.rest.handlers.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.repository.completeregister.DriverDetailsRepository;
import com.projectx.rest.services.completeregister.DriverDetailsService;

@Component

public class DriverDetailsHandler implements DriverDetailsService {

	@Autowired
	DriverDetailsRepository driverDetailsRepository;
	
	@Override
	public DriverDetails save(DriverDetails driverDetails) throws DriverDetailsAlreadyPresentException,ValidationFailedException{
		
		DriverDetails addedDriver=driverDetailsRepository.save(driverDetails);
		
		return addedDriver;
	}

	@Override
	public DriverDetails getDriverById(Long driverId) {

		DriverDetails fetchedDriver=driverDetailsRepository.findOne(driverId);
		
		return fetchedDriver;

	}

	@Override
	public Boolean deleteDriver(Long driverId) {

		Boolean status=driverDetailsRepository.deleteById(driverId);
		
		return status;

	}

	@Override
	public Integer updateMobileAndVerificationStatus(Long driverId,
			Long mobile, Boolean status,Integer updatedBy,Long updatedById) {
		
		Integer result=driverDetailsRepository.updateMobileAndMobileVerificationStatus(driverId, mobile, status,updatedBy,updatedById);
		
		return result;
		
	}

	@Override
	public List<DriverDetails> driversByVendorId(Long vendorId) {

		List<DriverDetails> driverList=driverDetailsRepository.getDriversByVendorId(vendorId);
		
		return driverList;
		
	}

	@Override
	public Integer count() {

		return driverDetailsRepository.count();
	}

	@Override
	public Boolean clearTestData() {

		return driverDetailsRepository.clearTestData();
	}

}
