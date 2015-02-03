package com.projectx.rest.repositoryImpl.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.request.FreightRequestByCustomerList;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.repository.request.FreightRequestByCustomerRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByCustomerRepositoryImpl implements
		FreightRequestByCustomerRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	
	@Override
	public FreightRequestByCustomer save(
			FreightRequestByCustomer freightRequestByCustomer) {

		FreightRequestByCustomer savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer",
				freightRequestByCustomer, FreightRequestByCustomer.class);

		return savedEntity;

		
		
	}

	@Override
	public FreightRequestByCustomer getById(Long requestId) {
		

		FreightRequestByCustomer result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/getById/"+requestId,
				FreightRequestByCustomer.class);
		
		return result;

	}

	@Override
	public Boolean deleteById(Long requestId) {

		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/deleteById/"+requestId,
				Boolean.class);
		
		return result;


		
	}

	@Override
	public Boolean clearTestData() {

		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/clearTestData",
				Boolean.class);
		
		return result;


		
	}

	@Override
	public Integer count() {

		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/count",
				Integer.class);
		
		return result;



		
	}

	@Override
	public List<FreightRequestByCustomer> findByCustomerId(Long customerId) {
	
		
		FreightRequestByCustomerList result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/findByCustomer/"+customerId,
				FreightRequestByCustomerList.class);
		
		return result.getRequestList();

		
	}

}
