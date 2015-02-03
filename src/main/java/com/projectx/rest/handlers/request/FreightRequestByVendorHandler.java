package com.projectx.rest.handlers.request;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.request.FreightRequestByVendorRepository;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@Component
@Profile(value="Dev")
public class FreightRequestByVendorHandler implements
		FreightRequestByVendorService {

	@Autowired
	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@Override
	public FreightRequestByVendor newRequest(
			FreightRequestByVendor freightRequestByCustomer) {
		
		return freightRequestByVendorRepository.save(freightRequestByCustomer);
	}

	@Override
	public FreightRequestByVendor getRequestById(Long requestId) {
		
		return freightRequestByVendorRepository.getById(requestId);
	}

	@Override
	public List<FreightRequestByVendor> getAllRequestForVendor(Long vendorId) {

		return freightRequestByVendorRepository.findByVendor(vendorId);
	}

	@Override
	public Boolean deleteRequestById(Long requestId) {

		return freightRequestByVendorRepository.deleteById(requestId);
	}

	@Override
	public Boolean clearTestData() {

		return freightRequestByVendorRepository.clearTestData();
	}

	@Override
	public Integer count() {

		return freightRequestByVendorRepository.count();
	}

}
