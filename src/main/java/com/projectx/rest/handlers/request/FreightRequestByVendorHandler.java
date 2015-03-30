package com.projectx.rest.handlers.request;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.projectx.data.domain.request.UpdateReservationStatus;
import com.projectx.mvc.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.async.RetriggerDTO;
import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.repository.request.FreightRequestByVendorRepository;
import com.projectx.rest.services.async.RetriggerService;
import com.projectx.rest.services.completeregister.VehicleDetailsService;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@Component
public class FreightRequestByVendorHandler implements
		FreightRequestByVendorService {

	@Autowired
	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	
	
	@Autowired
	Gson gson;
	
	@Override
	public FreightRequestByVendor newRequest(
			FreightRequestByVendor freightRequestByCustomer) throws ResourceAlreadyPresentException,ValidationFailedException{
		
		return freightRequestByVendorRepository.save(freightRequestByCustomer);
	}

	@Override
	public FreightRequestByVendor getRequestById(Long requestId) throws ResourceNotFoundException{
		
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
		
		List<FreightRequestByVendor> result= freightRequestByVendorRepository.getMatchingVendorReqFromCustomerReq(freightRequestByCustomer);

		/*
		if(result.size()==0)
		{
			retriggerService.requestRetry(new RetriggerDTO("/request/freightRequestByVendor/getMatchingVendorReqFromCustomerReq",
					gson.toJson(freightRequestByCustomer)));
		}
		*/	
		return result;	
	}

	@Override
	public Integer updateReservationStatusWithReservedFor(
			Long freightRequestByVendorId,String oldStatus, String reservationStatus,
			Long reservedFor) {

		
		return freightRequestByVendorRepository.updateReservationStatusWithReservedFor(freightRequestByVendorId, oldStatus, reservationStatus, reservedFor);
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
