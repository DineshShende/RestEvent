package com.projectx.rest.repositoryfixture.completeregister;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.repository.completeregister.VendorDetailsRepository;

@Component
@Profile(value="Test")
public class VendorDetailsMemRepository implements VendorDetailsRepository {

	
	Map<Long,VendorDetails> vendorList=new HashMap<Long,VendorDetails>();
	
	
	@Override
	public VendorDetails save(VendorDetails vendorDetails) {

		vendorList.put(vendorDetails.getVendorId(), vendorDetails);
		return vendorDetails;
	}

	
	@Override
	public VendorDetails findOne(Long vendorId) {

		VendorDetails fetchedEntity=vendorList.get(vendorId);
		
		return fetchedEntity;
	}

	@Override
	public Integer count() {

		Integer count=vendorList.size();
		
		return count;
		
	}

	@Override
	public Boolean clearTestData() {

		vendorList.clear();

		return true;
	}

}
