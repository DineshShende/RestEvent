package com.projectx.rest.repository.completeregister;


import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.VendorDetails;



@Repository
public interface VendorDetailsRepository {

	VendorDetails save(VendorDetails vendorDetails);
	
	VendorDetails update( VendorDetails vendorDetails);
	
	VendorDetails findOne( Long vendorId);
	
	Integer updateEmailVerificationStatus( UpdateEmailVerificationStatusDTO updateVerificationStatusDTO);
	
	Integer updateMobileVerificationStatus( UpdateMobileVerificationStatusDTO updateVerificationStatusDTO);
	
	Integer count();
	
	Boolean clearTestData();
	
	
}
