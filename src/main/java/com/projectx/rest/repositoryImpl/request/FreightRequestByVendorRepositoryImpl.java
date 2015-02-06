package com.projectx.rest.repositoryImpl.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.request.FreightRequestByVendorList;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.request.FreightRequestByVendorRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByVendorRepositoryImpl implements
		FreightRequestByVendorRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	
	@Override
	public FreightRequestByVendor save(
			FreightRequestByVendor freightRequestByVendor) {

		FreightRequestByVendor savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/request/testrequest",
				freightRequestByVendor, FreightRequestByVendor.class);

		return savedEntity;

		
		
	}

	@Override
	public FreightRequestByVendor getById(Long requestId) {
		

		FreightRequestByVendor result=restTemplate.getForObject(env.getProperty("data.url")+"/request/testrequest/getById/"+requestId,
				FreightRequestByVendor.class);
		
		return result;

	}

	@Override
	public Boolean deleteById(Long requestId) {
		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/request/testrequest/deleteById/"+requestId,
				Boolean.class);
		
		return result;
		
	}

	@Override
	public Boolean clearTestData() {
		

		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/request/testrequest/clearTestData",
				Boolean.class);
		
		return result;

	}

	@Override
	public Integer count() {
		
		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/request/testrequest/count",
				Integer.class);
		
		return result;
		
		
	}

	@Override
	public List<FreightRequestByVendor> findByVendor(Long vendorId) {

		FreightRequestByVendorList result=restTemplate.getForObject(env.getProperty("data.url")+"/request/testrequest/findByVendorId/"+vendorId,
				FreightRequestByVendorList.class);
		
		return result.getRequestList();


		
	}

	@Override
	public List<FreightRequestByVendor> getMatchingVendorReqFromCustomerReq(
			FreightRequestByCustomer freightRequestByCustomer) {
		
		FreightRequestByVendorList result=restTemplate.postForObject(env.getProperty("data.url")+"/request/testrequest/getMatchingVendorReqFromCustomerReq",
				freightRequestByCustomer, FreightRequestByVendorList.class);
		
		return result.getRequestList();


		
		
	}

}
