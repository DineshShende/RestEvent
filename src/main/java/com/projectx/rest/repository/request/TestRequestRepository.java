package com.projectx.rest.repository.request;

import java.util.List;


import com.projectx.rest.domain.request.TestRequest;

public interface TestRequestRepository {
	
	TestRequest save(TestRequest testRequest);
	
	TestRequest getById(Long requestId);
	
	Boolean deleteById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	List<TestRequest> findByVendor(Long vendorId);
	
}
