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
	
	String checkIfMobileSaved(Long vendorId,Integer entityType,Integer mobileType,Long mobile);
	
	MobileVerificationDetails saveMobileVerificationDetails(MobileVerificationDetails mobileVerificationDetails);
	
	String checkIfEmailSaved(Long vendorId,Integer entityType,Integer emailType,String email);
	
	EmailVerificationDetails saveEmailVerificationDetails(EmailVerificationDetails emailVerificationDetails);
	
	Boolean verifyMobileDetails(Long vendorId,Integer entityType,Integer mobileType,Integer mobilePin);
	
	Boolean verifyEmailDetails(Long vendorId,Integer entityType,Integer emailType,String emailHash);
	
	Boolean sendMobileVerificationDetails(Long vendorId,Integer entityType,Integer mobileType);
	
	Boolean sendEmailVerificationDetails(Long vendorId,Integer entityType,Integer emailType);
	
	void clearTestData();
	
	Integer count();
	
	
}
