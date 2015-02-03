package com.projectx.rest.services.request;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.request.FreightRequestByCustomer;

@Service
public interface FreightRequestByCustomerService {

	FreightRequestByCustomer newRequest(FreightRequestByCustomer freightRequestByCustomer);
	
	FreightRequestByCustomer getRequestById(Long requestId);
	
	List<FreightRequestByCustomer> getAllRequestForCustomer(Long customerId);
	
	Boolean deleteRequestById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
}
