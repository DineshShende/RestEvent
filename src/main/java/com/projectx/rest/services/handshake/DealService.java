package com.projectx.rest.services.handshake;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.handshake.DealDetails;

@Service
public interface DealService {
	
	DealDetails save (DealDetails dealDetails);
	
	DealDetails getByDealId (Long dealId);
	
	DealDetails getByCustomerRequestId (Long freightRequestByCustomerId);
	
	DealDetails getByVendorRequestId (Long freightRequestByVendorId);
	
	Integer count();
	
	Boolean clearTestData();
	
	DealDetails triggerDeal(Long freightRequestByCustomer,Long freightRequestByVendor,String deductionMode,Integer amount,String triggeredBy);

	Boolean exchangeContact(Long dealId,Long freightRequestByCustomer,Long freightRequestByVendor);
	
	DealDetails triggerDealAndExchangeContact(Long freightRequestByCustomer,Long freightRequestByVendor,String deductionMode,Integer amount
			,String triggeredBy);
	
}
