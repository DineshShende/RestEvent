package com.projectx.rest.repositoryImpl.quickregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.quickregister.ResponseCustomerList;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.services.quickregister.TestRepository;

@Component
@Profile("Dev")
@PropertySource(value="classpath:/application.properties")
public class TestRepositoryImpl implements TestRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public VendorDetails test() {


		ResponseEntity<VendorDetails> response=restTemplate.exchange(env.getProperty("data.url")+"/testing/save/test",HttpMethod.GET,null,VendorDetails.class);
		
		System.out.println(response.getBody());
		
		System.out.println(response.getStatusCode());
		
		return response.getBody();
		
		
	}

}
