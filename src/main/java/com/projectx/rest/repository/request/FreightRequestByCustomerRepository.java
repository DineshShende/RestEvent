package com.projectx.rest.repository.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;



@Repository
public interface FreightRequestByCustomerRepository {
	
	FreightRequestByCustomer save(FreightRequestByCustomer freightRequestByCustomer)
					throws ResourceAlreadyPresentException,ValidationFailedException;
	
	FreightRequestByCustomer getById(Long requestId) throws ResourceNotFoundException;
	
	Boolean deleteById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	List<FreightRequestByCustomer> findByCustomerId(Long customerId);
	
	Integer updateReservationStatusWithReservedFor(Long freightRequestByCustomerId,String oldStatus,String reservationStatus,Long reservedBy);
	
	List<FreightRequestByCustomer> getMatchingCustReqForVendorReq(FreightRequestByVendor freightRequestByVendor,String allocationStatus);

}
