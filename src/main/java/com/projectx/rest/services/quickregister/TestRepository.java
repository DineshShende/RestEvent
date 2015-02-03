package com.projectx.rest.services.quickregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;

@Repository
public interface TestRepository {
	
	public VendorDetails test();
	

}
