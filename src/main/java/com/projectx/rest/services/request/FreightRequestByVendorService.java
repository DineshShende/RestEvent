package com.projectx.rest.services.request;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

@Service
public interface FreightRequestByVendorService {

	FreightRequestByVendor newRequest(FreightRequestByVendor freightRequestByCustomer);
	
	FreightRequestByVendorDTO getRequestById(Long requestId) throws ResourceNotFoundException;
	
	List<FreightRequestByVendorDTO> getAllRequestForVendor(Long vendorId);
	
	List<FreightRequestByVendorDTO> getMatchingVendorReqFromCustomerReq(FreightRequestByCustomer freightRequestByCustomer); 
	
	Integer updateReservationStatusWithReservedFor(Long freightRequestByVendorId,String oldStatus,String reservationStatus,Long reservedFor,
			Integer updatedBy,Long updatedById);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	
}
