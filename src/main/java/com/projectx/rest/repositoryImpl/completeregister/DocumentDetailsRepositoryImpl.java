package com.projectx.rest.repositoryImpl.completeregister;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotSavedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.repository.completeregister.DocumentDetailsRepository;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")

public class DocumentDetailsRepositoryImpl implements
		DocumentDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public DocumentDetails saveCustomerDocument(
			DocumentDetails documentDetails) throws DocumentDetailsNotSavedException,ValidationFailedException {
		
		HttpEntity<DocumentDetails> entity=new HttpEntity<DocumentDetails>(documentDetails);
		
		ResponseEntity<DocumentDetails> result=null;
		
		try
		{
			result=restTemplate.exchange(env.getProperty("data.url")+"/document/saveCustomerDocument", HttpMethod.POST, 
					entity, DocumentDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new DocumentDetailsNotSavedException();
	
	}

	@Override
	public DocumentDetails getByCustomerId(DocumentKey documentKey) throws DocumentDetailsNotFoundException{

		HttpEntity<DocumentKey> key=new HttpEntity<DocumentKey>(documentKey);
		
		ResponseEntity<DocumentDetails> savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/document/getCustomerDocumentByKey",
				HttpMethod.POST, key, DocumentDetails.class);
				
		if(savedEntity.getStatusCode()==HttpStatus.FOUND)		
			return savedEntity.getBody();
		else
			throw new DocumentDetailsNotFoundException();
	
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
