package com.projectx.rest.repository.handshake;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.handshake.DealDetails;

@Repository
public interface DealDetailsRepository {
	
	DealDetails save (DealDetails dealDetails);
	
	DealDetails getByDealId (Long dealId);
	
	DealDetails getByCustomerRequestId (Long freightRequestByCustomerId);
	
	DealDetails getByVendorRequestId (Long freightRequestByVendorId);
	
	Integer count();
	
	Boolean clearTestData();

}
