package com.projectx.rest.repository.completeregister;


import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;



@Repository
public interface VendorDetailsRepository {


	VendorDetails save(VendorDetails vendorDetails) throws VendorDetailsAlreadyPresentException,ValidationFailedException;

	VendorDetails findOne( Long vendorId) throws VendorDetailsNotFoundException;
	
	Integer count();
	
	Boolean clearTestData();
	
	
}
