package com.projectx.rest.services.request;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.mvc.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

@Service
public interface FreightRequestByVendorService {

	FreightRequestByVendor newRequest(FreightRequestByVendor freightRequestByCustomer);
	
	FreightRequestByVendor getRequestById(Long requestId) throws ResourceNotFoundException;
	
	List<FreightRequestByVendor> getAllRequestForVendor(Long vendorId);
	
	List<FreightRequestByVendor> getMatchingVendorReqFromCustomerReq(FreightRequestByCustomer freightRequestByCustomer); 
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	
}
