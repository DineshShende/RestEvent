package com.projectx.rest.repositoryImpl.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.request.RequestTestList;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.TestRequest;
import com.projectx.rest.repository.request.TestRequestRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")
public class TestRequestRepositoryImpl implements TestRequestRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public TestRequest save(TestRequest testRequest) {
		
		TestRequest savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/request/testrequest",
				testRequest, TestRequest.class);

		return savedEntity;


		
	}

	@Override
	public TestRequest getById(Long requestId) {
		
		TestRequest result=restTemplate.getForObject(env.getProperty("data.url")+"/request/testrequest/getById/"+requestId,
				TestRequest.class);
		
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
	public List<TestRequest> findByVendor(Long vendorId) {
		
		RequestTestList result=restTemplate.getForObject(env.getProperty("data.url")+"/request/testrequest/findByVendorId/"+vendorId,
				RequestTestList.class);
		
		return result.getRequestList();


		
		
	}

	

}
