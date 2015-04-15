package com.projectx.rest.repositoryImpl.completeregister;

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

import com.projectx.data.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.completeregister.UpdateEmailInDetailsAndAuthenticationDetailsFailedException;
import com.projectx.rest.exception.repository.completeregister.UpdateMobileInDetailsAndAuthentionDetailsFailedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class TransactionalDetailsRepositoryImpl implements
		TransactionalUpdatesRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;


	
	@Override
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails) 
			throws CustomerDetailsTransactionalUpdateFailedException,ValidationFailedException {

		HttpEntity<CustomerDetails> entity=new HttpEntity<CustomerDetails>(customerDetails);
		
		ResponseEntity<CustomerDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/transactional/updateCustomerDetails",
					HttpMethod.POST, entity, CustomerDetails.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new CustomerDetailsTransactionalUpdateFailedException();

		
	}

	@Override
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails) throws VendorDetailsTransactionalUpdateFailedException,
																					ValidationFailedException			
	{

		HttpEntity<VendorDetails> entity=new HttpEntity<VendorDetails>(vendorDetails);
		
		ResponseEntity<VendorDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/transactional/updateVendorDetails",
					HttpMethod.POST, entity, VendorDetails.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new VendorDetailsTransactionalUpdateFailedException();

	}

	@Override
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails (
			Long entityId, Integer entityType, Integer mobileType,String updatedBy) 
					throws UpdateMobileInDetailsAndAuthentionDetailsFailedException,ValidationFailedException{

		CustomerIdTypeMobileTypeRequestedByDTO customerIdTypeMobileTypeDTO=
				new CustomerIdTypeMobileTypeRequestedByDTO(entityId, entityType, mobileType,updatedBy);
		
		HttpEntity<CustomerIdTypeMobileTypeRequestedByDTO> entity=new HttpEntity<CustomerIdTypeMobileTypeRequestedByDTO>(customerIdTypeMobileTypeDTO);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/transactional/updateMobileInDetailsEnityAndAuthenticationDetails",
					HttpMethod.POST, entity, Boolean.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new UpdateMobileInDetailsAndAuthentionDetailsFailedException();


	}

	@Override
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer emailType,String updatedBy) 
					throws UpdateEmailInDetailsAndAuthenticationDetailsFailedException,ValidationFailedException{

		CustomerIdTypeEmailTypeUpdatedByDTO customerIdTypeEmailTypeDTO=
				new CustomerIdTypeEmailTypeUpdatedByDTO(entityId, entityType, emailType,updatedBy);
		
		HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO> entity=new HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO>(customerIdTypeEmailTypeDTO);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/transactional/updateEmailInDetailsEnityAndAuthenticationDetails",
					HttpMethod.POST, entity, Boolean.class);

		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new UpdateEmailInDetailsAndAuthenticationDetailsFailedException();
	}

	@Override
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity)throws QuickRegisterDetailsAlreadyPresentException,ValidationFailedException {
		
		HttpEntity<QuickRegisterEntity> entity=new HttpEntity<QuickRegisterEntity>(quickRegisterEntity);
		
		ResponseEntity<CustomerQuickRegisterEmailMobileVerificationEntity> result=null;
		
		try
		{
			result=restTemplate.exchange(env.getProperty("data.url")+"/transactional/saveNewQuickRegisterEntity",
					HttpMethod.POST, entity, CustomerQuickRegisterEmailMobileVerificationEntity.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
						
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new QuickRegisterDetailsAlreadyPresentException();

		
	}

	@Override
	public CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails(
			QuickRegisterEntity quickRegisterEntity) throws DeleteQuickCreateDetailsEntityFailedException,ValidationFailedException{

		HttpEntity<QuickRegisterEntity> entity=new HttpEntity<QuickRegisterEntity>(quickRegisterEntity);
		
		ResponseEntity<CustomerOrVendorDetailsDTO> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/transactional/deleteQuickRegisterEntityCreateDetails",
					HttpMethod.POST, entity, CustomerOrVendorDetailsDTO.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new DeleteQuickCreateDetailsEntityFailedException();

	}


}
