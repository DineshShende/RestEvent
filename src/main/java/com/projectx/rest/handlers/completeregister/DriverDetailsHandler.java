package com.projectx.rest.handlers.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.repository.completeregister.DriverDetailsRepository;
import com.projectx.rest.services.completeregister.DriverDetailsService;

@Component
@Profile(value="Dev")
public class DriverDetailsHandler implements DriverDetailsService {

	@Autowired
	DriverDetailsRepository driverDetailsRepository;
	
	@Override
	public DriverDetails addDriver(DriverDetails driverDetails) {
		
		DriverDetails addedDriver=driverDetailsRepository.save(driverDetails);
		
		return addedDriver;
	}

	@Override
	public DriverDetails updateDriver(DriverDetails driverDetails) {

		DriverDetails updatedDriver=driverDetailsRepository.update(driverDetails);
		
		return updatedDriver;

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
			Long mobile, Boolean status) {
		
		Integer result=driverDetailsRepository.updateMobileAndMobileVerificationStatus(driverId, mobile, status);
		
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
