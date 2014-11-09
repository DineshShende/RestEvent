package com.projectx.rest.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerDocument;
import com.projectx.rest.repository.CustomerDocumentRepository;

@Component
@Profile(value={"Dev","Test"})
@PropertySource(value="classpath:/application.properties")

public class CustomerDocumentRepositoryImpl implements
		CustomerDocumentRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public CustomerDocument saveCustomerDocument(
			CustomerDocument customerDocument) {
		
		CustomerDocument savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/saveCustomerDocument",
																		customerDocument, CustomerDocument.class);
		
		return savedEntity;
	
	}

	@Override
	public CustomerDocument getCustomerDocumentByCustomerId(Long customerId) {
		
		CustomerDocument savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getCustomerDocumentById",
				customerId, CustomerDocument.class);

		return savedEntity;
	
	}

}
