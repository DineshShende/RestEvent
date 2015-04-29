package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Service
public interface VendorDetailsService {

	VendorDetails createCustomerDetailsFromQuickRegisterEntity(QuickRegisterEntity quickRegisterEntity);
	
	VendorDetails updateVendorDetails(VendorDetails vendorDetails);
	
	VendorDetails findById(Long vendorId);
	
	void clearTestData();
	
	Integer count();
	
	
}
