package com.projectx.rest.repositoryImpl.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class TransactionalDetailsRepositoryImpl implements
		TransactionalUpdatesRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;


	
	@Override
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails) {

		
		CustomerDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/transactional/updateCustomerDetails",
				customerDetails, CustomerDetails.class);

		return savedEntity;

		
	}

	@Override
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails) {

		VendorDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/transactional/updateVendorDetails",
				vendorDetails, VendorDetails.class);

		return savedEntity;

	}

	@Override
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer mobileType) {

		CustomerIdTypeMobileTypeDTO customerIdTypeMobileTypeDTO=new CustomerIdTypeMobileTypeDTO(entityId, entityType, mobileType);
		
		Boolean result=restTemplate.postForObject(env.getProperty("data.url")+"/transactional/updateMobileInDetailsEnityAndAuthenticationDetails",
				customerIdTypeMobileTypeDTO, Boolean.class);

		return result;


	}

	@Override
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer emailType) {

		CustomerIdTypeEmailTypeDTO customerIdTypeEmailTypeDTO=new CustomerIdTypeEmailTypeDTO(entityId, entityType, emailType);
		
		Boolean result=restTemplate.postForObject(env.getProperty("data.url")+"/transactional/updateEmailInDetailsEnityAndAuthenticationDetails",
				customerIdTypeEmailTypeDTO, Boolean.class);

		return result;
	}

	@Override
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity) {
		
		CustomerQuickRegisterEmailMobileVerificationEntity result=restTemplate.postForObject(env.getProperty("data.url")+"/transactional/saveNewQuickRegisterEntity",
				quickRegisterEntity, CustomerQuickRegisterEmailMobileVerificationEntity.class);

		return result;

		
	}

	@Override
	public CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails(
			QuickRegisterEntity quickRegisterEntity) {

	
		CustomerOrVendorDetailsDTO result=restTemplate.postForObject(env.getProperty("data.url")+"/transactional/deleteQuickRegisterEntityCreateDetails",
				quickRegisterEntity, CustomerOrVendorDetailsDTO.class);

		return result;

	}


}
