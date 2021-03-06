package com.projectx.rest.services.request;

import java.util.List;


import org.springframework.stereotype.Service;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

@Service
public interface FreightRequestByCustomerService {

	FreightRequestByCustomer newRequest(FreightRequestByCustomer freightRequestByCustomer)
					throws ResourceAlreadyPresentException,ValidationFailedException;
	
	FreightRequestByCustomer getRequestById(Long requestId) throws ResourceNotFoundException;
	
	List<FreightRequestByCustomer> getAllRequestForCustomer(Long customerId);
	
	Integer updateAllocationStatus(Long freightRequestByCustomerId,String oldStatus,String allocationStatus,Long allocatedFor,
			Integer updatedBy,Long updatedById);
	
	List<FreightRequestByCustomer> getMatchingCustReqForVendorReq(FreightRequestByVendor freightRequestByVendor,String allocationStatus);
	
	void getMatchingCustReqForVendorReqAndProceedWithHandShake(FreightRequestByVendor freightRequestByVendor);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
}
