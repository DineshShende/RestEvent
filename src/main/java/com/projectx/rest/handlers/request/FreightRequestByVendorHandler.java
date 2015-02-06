package com.projectx.rest.handlers.request;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.mvc.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.repository.request.FreightRequestByVendorRepository;
import com.projectx.rest.services.completeregister.VehicleDetailsService;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@Component
@Profile(value="Dev")
public class FreightRequestByVendorHandler implements
		FreightRequestByVendorService {

	@Autowired
	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@Autowired
	VehicleDetailsService 	vehicleDetailsService;
	
	
	@Override
	public FreightRequestByVendor newRequest(
			FreightRequestByVendor freightRequestByCustomer) {
		
		return freightRequestByVendorRepository.save(freightRequestByCustomer);
	}

	@Override
	public FreightRequestByVendor getRequestById(Long requestId) {
		
		return freightRequestByVendorRepository.getById(requestId);
	}

	@Override
	public List<FreightRequestByVendor> getAllRequestForVendor(Long vendorId) {

		return freightRequestByVendorRepository.findByVendor(vendorId);
	}

	@Override
	public Boolean deleteRequestById(Long requestId) {

		return freightRequestByVendorRepository.deleteById(requestId);
	}

	@Override
	public Boolean clearTestData() {

		return freightRequestByVendorRepository.clearTestData();
	}

	@Override
	public Integer count() {

		return freightRequestByVendorRepository.count();
	}

	@Override
	public List<FreightRequestByVendor> getMatchingVendorReqFromCustomerReq(
			FreightRequestByCustomer freightRequestByCustomer) {
		
		return freightRequestByVendorRepository.getMatchingVendorReqFromCustomerReq(freightRequestByCustomer);
	}

	/*
	@Override
	public FreightRequestByVendor toFreightRequestByVendor(
			FreightRequestByVendorDTO freightRequestByVendorDTO) {
		
		VehicleDetailsDTO vehicleDetailsDTO=vehicleDetailsService.getVehicleByRegistartionNumber(freightRequestByVendorDTO.getVehicleRegistrationNumber());
		
		FreightRequestByVendor freightRequestByVendor=
				new FreightRequestByVendor(freightRequestByVendorDTO.getRequestId(),vehicleDetailsDTO , freightRequestByVendorDTO.getSource(),
						freightRequestByVendorDTO.getDestination(), freightRequestByVendorDTO.getDriverId(), freightRequestByVendorDTO.getAvailableDate(),
						freightRequestByVendorDTO.getAvailableTime(), freightRequestByVendorDTO.getPickupRangeInKm(), freightRequestByVendorDTO.getVendorId(),
						freightRequestByVendorDTO.getStatus(), freightRequestByVendorDTO.getInsertTime(), freightRequestByVendorDTO.getUpdateTime(),freightRequestByVendorDTO.getUpdatedBy());
		
		return freightRequestByVendor;
	}

	@Override
	public FreightRequestByVendorDTO toFreightRequestByVendorDTO(
			FreightRequestByVendor freightRequestByVendor) {

		FreightRequestByVendorDTO freightRequestByVendorDTO=
				new FreightRequestByVendorDTO(freightRequestByVendor.getVehicleDetailsId().getRegistrationNumber(),
						freightRequestByVendor.getSource(),freightRequestByVendor.getDestination(),freightRequestByVendor.getDriverId(),
						freightRequestByVendor.getAvailableDate(),freightRequestByVendor.getAvailableTime(),freightRequestByVendor.getPickupRangeInKm(),
						freightRequestByVendor.getVendorId(), freightRequestByVendor.getStatus(), freightRequestByVendor.getInsertTime(),freightRequestByVendor.getUpdateTime(),
						freightRequestByVendor.getUpdatedBy());
		
		
		return freightRequestByVendorDTO;
	}
	*/
}
