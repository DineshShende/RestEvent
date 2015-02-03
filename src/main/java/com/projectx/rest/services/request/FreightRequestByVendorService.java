package com.projectx.rest.services.request;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.request.FreightRequestByVendor;

@Service
public interface FreightRequestByVendorService {

	FreightRequestByVendor newRequest(FreightRequestByVendor freightRequestByCustomer);
	
	FreightRequestByVendor getRequestById(Long requestId);
	
	List<FreightRequestByVendor> getAllRequestForVendor(Long vendorId);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	
}
