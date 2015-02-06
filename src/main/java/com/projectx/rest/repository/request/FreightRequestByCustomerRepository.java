package com.projectx.rest.repository.request;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;



@Repository
public interface FreightRequestByCustomerRepository {
	
	FreightRequestByCustomer save(FreightRequestByCustomer freightRequestByCustomer);
	
	FreightRequestByCustomer getById(Long requestId);
	
	Boolean deleteById(Long requestId);
	
	Boolean clearTestData();
	
	Integer count();
	
	List<FreightRequestByCustomer> findByCustomerId(Long customerId);
	
	List<FreightRequestByCustomer> getMatchingCustReqForVendorReq(FreightRequestByVendor freightRequestByVendor);

}
