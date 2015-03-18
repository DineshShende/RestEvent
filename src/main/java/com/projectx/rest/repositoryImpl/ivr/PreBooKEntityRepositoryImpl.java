package com.projectx.rest.repositoryImpl.ivr;

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

import com.projectx.rest.domain.ivr.PreBookEntity;
import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.ivr.PreBookEntityRepository;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class PreBooKEntityRepositoryImpl implements PreBookEntityRepository {


	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	

	
	@Override
	public PreBookEntity save(PreBookEntity preBookEntity) {
		
		HttpEntity<PreBookEntity> httpEntity=new HttpEntity<PreBookEntity>(preBookEntity);
		
		ResponseEntity<PreBookEntity> result=null;
		
		try
		{
			result=restTemplate.exchange(env.getProperty("data.url")+"/preBook",HttpMethod.POST, httpEntity, PreBookEntity.class); 
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(result.getStatusCode()==HttpStatus.CREATED)
				return result.getBody();
	
		throw new ResourceAlreadyPresentException();

	
	}

	@Override
	public PreBookEntity getByFreightRequestByCustomerId(
			Long freightRequestByCustomerId) {
		
		ResponseEntity<PreBookEntity> result=restTemplate.exchange(env.getProperty("data.url")+"/preBook/getByFreightRequestByCustomerId/"+
				freightRequestByCustomerId,HttpMethod.GET, null, PreBookEntity.class); 
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new ResourceNotFoundException();
		
	}

	@Override
	public Integer deleteByFreightRequestByCustomerId(
			Long freightRequestByCustomerId) {

		ResponseEntity<Integer> result=restTemplate.exchange(env.getProperty("data.url")+"/preBook/deleteByFreightRequestByCustomerId/"+
				freightRequestByCustomerId,HttpMethod.GET, null, Integer.class); 
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new ResourceNotFoundException();
	}

	@Override
	public Integer count() {
		
		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/preBook/count", Integer.class);
		
		return result;
	}

	@Override
	public Boolean clearTestData() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/preBook/clearTestData", Boolean.class);
		
		return result;
	}

}
