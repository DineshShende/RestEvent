package com.projectx.rest.repository.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;



@Repository
public interface FreightRequestByVendorRepository {

	FreightRequestByVendor save(FreightRequestByVendor freightRequestByVendor) 
						throws ResourceAlreadyPresentException,ValidationFailedException;
	
	FreightRequestByVendor getById(Long requestId)throws ResourceNotFoundException;
	
	Boolean deleteById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	Integer updateReservationStatusWithReservedFor(Long freightRequestByVendorId,String oldStatus,String reservationStatus,Long reservedFor);
	
	List<FreightRequestByVendor> findByVendor(Long vendorId) ;
	
	List<FreightRequestByVendor> getMatchingVendorReqFromCustomerReq(FreightRequestByCustomer freightRequestByCustomer); 
}
