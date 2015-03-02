package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;


import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Service
public interface VendorDetailsService {

	VendorDetails createCustomerDetailsFromQuickRegisterEntity(QuickRegisterEntity quickRegisterEntity);
	
	VendorDetails updateVendorDetails(VendorDetails vendorDetails);
	
	VendorDetails findById(Long vendorId);
	
	Boolean verifyMobileDetails(Long vendorId,Integer entityType,Integer mobileType,Integer mobilePin,String updatedBy);
	
	Boolean verifyEmailDetails(Long vendorId,Integer entityType,Integer emailType,String emailHash,String requestedBy);
	
	Boolean sendMobileVerificationDetails(Long vendorId,Integer entityType,Integer mobileType,String updatedBy);
	
	Boolean sendEmailVerificationDetails(Long vendorId,Integer entityType,Integer emailType);
	
	void clearTestData();
	
	Integer count();
	
	
}
