package com.projectx.rest.repository.completeregister;


import static org.junit.Assert.*;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class CustomerDetailsRepositoryTest {

	@Autowired
	CustomerDetailsRepository customerDetailsCustomRepository;
	
	@Autowired
	EmailVericationDetailsRepository emailVericationDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Before
	public void clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		emailVericationDetailsRepository.clearTestData();
		mobileVerificationDetailsRepository.clearTestData();
		authenticationDetailsRepository.clearLoginDetailsForTesting();
		
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGetCustomerDetails()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		try
		{
			customerDetailsCustomRepository.findOne(212L);
		}
		catch(CustomerDetailsNotFoundException e)
		{
			
		}
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()), customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}
	
	@Test
	public void saveCustomerDetailsAlreadyPresent()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));

		try{
			
			savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntityDuplicate()));
			
		}
		catch(CustomerDetailsAlreadyPresentException e)
		{
			
		}
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()), savedEntity);
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}
	

	@Test
	public void saveCustomerDetailsError()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=null;

		try{
			
			savedEntity=customerDetailsCustomRepository.save(standardCustomerDetailsError(standardCustomerFromQuickEntity()));
			
		}
		catch(ValidationFailedException e)
		{
			assertNull(savedEntity);
		}
		
	}
	

	
	@Test
	public void copiedDataFromQuickRegisterEntityAndSaveAndUpdateAfterWards()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerFromQuickEntity());
		
		assertEquals(standardCustomerFromQuickEntity(), 
				customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails newEntityToMerge=savedEntity;
		
		newEntityToMerge.setDateOfBirth(CUST_DATE);
		newEntityToMerge.setHomeAddressId(standardCustomerDetails(standardCustomerFromQuickEntity()).getHomeAddressId());
		newEntityToMerge.setFirmAddressId(standardCustomerDetails(standardCustomerFromQuickEntity()).getFirmAddressId());
		newEntityToMerge.setLanguage(CUST_LANG);
		newEntityToMerge.setBusinessDomain(CUST_BUSINESS_DOMAIN);
		newEntityToMerge.setNameOfFirm(CUST_NAME_OF_FIRM);
		newEntityToMerge.setFirmAddressId(standardAddress());
		newEntityToMerge.setSecondaryMobile(CUST_SEC_MOBILE);
		newEntityToMerge.setSecondaryEmail(CUST_SEC_EMAIL);
		
		
		CustomerDetails mergeResult=customerDetailsCustomRepository.save(newEntityToMerge);
		
		newEntityToMerge.setSecondaryMobile(null);
		
		assertEquals(newEntityToMerge, mergeResult);
	}
	
	

}
