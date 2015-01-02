package com.projectx.rest.repositoryImpl.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.repository.completeregister.DocumentDetailsRepository;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")

public class DocumentDetailsRepositoryImpl implements
		DocumentDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public DocumentDetails saveCustomerDocument(
			DocumentDetails documentDetails) {
		
		DocumentDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/document/saveCustomerDocument",
				documentDetails, DocumentDetails.class);
		
		return savedEntity;
	
	}

	@Override
	public DocumentDetails getCustomerDocumentByCustomerId(DocumentKey documentKey) {
		
		DocumentDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/document/getCustomerDocumentByKey",
				documentKey, DocumentDetails.class);

		return savedEntity;
	
	}

	@Override
	public Integer count() {
		
		Integer count=restTemplate.getForObject(env.getProperty("data.url")+"/document/count",
				 Integer.class);

		return count;

		
	}

	@Override
	public Boolean clearTestData() {

		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/document/clearTestData",
				 Boolean.class);

		return status;

	}

}
