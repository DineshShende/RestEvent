package com.projectx.rest.repositoryImpl.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.repository.completeregister.AddressRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")
public class AddressRepositoryImpl implements AddressRepository {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	
	@Override
	public Address save(Address address) {
	
		Address savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/address",
				address, Address.class);

		return savedEntity;
		
		
	}

	@Override
	public Address findOne(Long addressId) {
	
		Address result=restTemplate.getForObject(env.getProperty("data.url")+"/address/"+addressId,
				Address.class);
		
		return result;

		
		
	}

	@Override
	public Boolean deleteById(Long addressId) {
		
		restTemplate.delete(env.getProperty("data.url")+"/address/"+addressId);
		
		return true;
		
	}

}
