package com.projectx.rest.repositoryImpl.quickregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.projectx.data.domain.quickregister.UpdateEmailPassword;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.VerifyLoginDetailsDataDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.GetByEmailDTO;
import com.projectx.mvc.domain.quickregister.GetByMobileDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class AuthenticationDetailsRepositoryImpl implements AuthenticationDetailsRepository{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;


	@Override
	public AuthenticationDetails save(
			AuthenticationDetails authenticationDetails) throws ResourceAlreadyPresentException,ValidationFailedException{
		
		HttpEntity<AuthenticationDetails> entity=new HttpEntity<AuthenticationDetails>(authenticationDetails);
		
		ResponseEntity<AuthenticationDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/saveLoginDetails",HttpMethod.POST,
					entity, AuthenticationDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new ResourceAlreadyPresentException();
			
		
	}

	@Override
	public AuthenticationDetails getByCustomerIdType(Long customerId,Integer customerType) throws AuthenticationDetailsNotFoundException {
		
		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
	
		HttpEntity<CustomerIdTypeDTO> entity=new HttpEntity<CustomerIdTypeDTO>(customerIdDTO);
		
		ResponseEntity<AuthenticationDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByCustomerIdType",
					HttpMethod.POST, entity, AuthenticationDetails.class);
		
		

		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new AuthenticationDetailsNotFoundException();
		
	}

	@Override
	public Integer updatePasswordEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,String password,
			String emailPassword, String passwordType,Integer updatedBy,Long updatedById) throws ValidationFailedException{
		
		UpdatePasswordEmailPasswordAndPasswordTypeDTO passwordAndPasswordTypeDTO=new UpdatePasswordEmailPasswordAndPasswordTypeDTO(customerId,customerType, password, 
				emailPassword,passwordType,updatedBy,updatedById);

		ResponseEntity<Integer> updateStatus=null;
		
		try{
		
		updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/updatePasswordEmailPasswordAndPasswordTypeAndCounts",
					HttpMethod.POST,new HttpEntity<UpdatePasswordEmailPasswordAndPasswordTypeDTO>(passwordAndPasswordTypeDTO), Integer.class);
		
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		return updateStatus.getBody();
		
	}


	@Override
	public Integer incrementResendCount(Long customerId,Integer customerType,Integer updatedBy,Long updatedById) {

		CustomerIdTypeUpdatedByDTO customerIdDTO=new CustomerIdTypeUpdatedByDTO(customerId,customerType,updatedBy,updatedById);
		
		ResponseEntity<Integer> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/incrementResendCount",
					HttpMethod.POST,new HttpEntity<CustomerIdTypeUpdatedByDTO>(customerIdDTO), Integer.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return updateStatus.getBody();
	}

	@Override
	public Integer incrementLastUnsucessfullAttempts(Long customerId,Integer customerType,Integer updatedBy,Long updatedById) {

		CustomerIdTypeUpdatedByDTO customerIdDTO=new CustomerIdTypeUpdatedByDTO(customerId,customerType,updatedBy,updatedById);
		
		ResponseEntity<Integer> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/incrementLastUnsucessfullAttempts", 
					HttpMethod.POST,new HttpEntity<CustomerIdTypeUpdatedByDTO>(customerIdDTO), Integer.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return updateStatus.getBody();
	}

	@Override
	public AuthenticationDetails getByEmail(
			String email) throws AuthenticationDetailsNotFoundException{
		GetByEmailDTO getByEmailDTO=new GetByEmailDTO(email);
		
		HttpEntity<GetByEmailDTO> entity=new HttpEntity<GetByEmailDTO>(getByEmailDTO);
		
		
		ResponseEntity<AuthenticationDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByEmail",
					HttpMethod.POST, entity, AuthenticationDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new AuthenticationDetailsNotFoundException();
		
	}

	@Override
	public AuthenticationDetails getByMobile(
			Long mobile) throws AuthenticationDetailsNotFoundException{

		GetByMobileDTO getByMobilelDTO=new GetByMobileDTO(mobile);
		
		HttpEntity<GetByMobileDTO> entity=new HttpEntity<GetByMobileDTO>(getByMobilelDTO);
		
		ResponseEntity<AuthenticationDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByMobile",
				HttpMethod.POST, entity, AuthenticationDetails.class);
		
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new AuthenticationDetailsNotFoundException();
		
	}

	
	@Override
	public Integer count() {
		Integer count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getCount", Integer.class);
		
		return count;
	}

	@Override
	public Boolean clearLoginDetailsForTesting() {
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/clearForTesting", Boolean.class);
		
		return status;
	}
	

	

}
