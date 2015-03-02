package com.projectx.rest.handlers.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.async.RetriggerDTO;
import com.projectx.rest.services.async.RetriggerService;

@Component
@PropertySource(value="classpath:/application.properties")

public class RetriggerHandler implements RetriggerService {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public void requestRetry(RetriggerDTO retriggerDTO) {

			
		restTemplate.postForObject(env.getProperty("async.url")+"/retrigger", retriggerDTO, Boolean.class);
		
	}

}
