package com.projectx.rest.repositoryImpl.async;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.async.RetriggerList;
import com.projectx.rest.domain.async.RetriggerDetails;
import com.projectx.rest.repository.async.RetriggerDetailsRepository;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class RetriggerDetailsRepositoryImpl implements
		RetriggerDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public RetriggerDetails save(RetriggerDetails retriggerDetails) {

		HttpEntity<RetriggerDetails> entity=new HttpEntity<RetriggerDetails>(retriggerDetails);
		
		ResponseEntity<RetriggerDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/retriggerDetails",
				HttpMethod.POST,entity, RetriggerDetails.class);
		
		return result.getBody();
	
	}

	@Override
	public List<RetriggerDetails> findAll() {
		
		
		RetriggerList savedEntity=restTemplate.getForObject(env.getProperty("data.url")+"/retriggerDetails/findAll",  RetriggerList.class);
		
		return savedEntity.getList();
		
		
	}

	@Override
	public Boolean deleteById(Long retriggerId) {

		Boolean savedEntity=restTemplate.getForObject(env.getProperty("data.url")+"/retriggerDetails/deleteById/"+retriggerId,  Boolean.class);
		
		return savedEntity;
		

	}

	@Override
	public void clearTestData() {

		restTemplate.getForObject(env.getProperty("data.url")+"/retriggerDetails/clearTestData", Boolean.class);
	}
	
	

}
