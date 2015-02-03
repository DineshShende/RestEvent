package com.projectx.rest.handlers.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.repository.request.FreightRequestByCustomerRepository;
import com.projectx.rest.services.request.FreightRequestByCustomerService;

@Component
@Profile(value="Dev")
public class FreightRequestByCustomerHandler implements
		FreightRequestByCustomerService {

	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;
	
	@Override
	public FreightRequestByCustomer newRequest(
			FreightRequestByCustomer freightRequestByCustomer) {
		
		return freightRequestByCustomerRepository.save(freightRequestByCustomer);
	}

	@Override
	public FreightRequestByCustomer getRequestById(Long requestId) {

		return freightRequestByCustomerRepository.getById(requestId);
	}

	@Override
	public List<FreightRequestByCustomer> getAllRequestForCustomer(
			Long customerId) {
		
		return freightRequestByCustomerRepository.findByCustomerId(customerId);
	}

	@Override
	public Boolean deleteRequestById(Long requestId) {

		return freightRequestByCustomerRepository.deleteById(requestId);
	}

	@Override
	public Boolean clearTestData() {

		return freightRequestByCustomerRepository.clearTestData();
	}

	@Override
	public Integer count() {

		return freightRequestByCustomerRepository.count();
	}

}
