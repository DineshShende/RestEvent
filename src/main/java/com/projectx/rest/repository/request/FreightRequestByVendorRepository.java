package com.projectx.rest.repository.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.request.FreightRequestByVendor;



@Repository
public interface FreightRequestByVendorRepository {

	FreightRequestByVendor save(FreightRequestByVendor freightRequestByVendor);
	
	FreightRequestByVendor getById(Long requestId);
	
	Boolean deleteById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	List<FreightRequestByVendor> findByVendor(Long vendorId);
}
