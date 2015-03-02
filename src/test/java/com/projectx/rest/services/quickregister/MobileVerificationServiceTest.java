package com.projectx.rest.services.quickregister;


import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
public class MobileVerificationServiceTest {

	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	QuickRegisterService customerQuickRegisterHandler;
	
	@Before
	public void clearTestData()
	{
		mobileVerificationService.clearTestData();
		customerQuickRegisterHandler.clearDataForTesting();
	}
	
	
	@Test
	public void environmentTest() {
		
		
	}
	
	
	@Test
	public void createCustomerMobileVerificationEntity()
	{
		
		MobileVerificationDetails emailVerificationDetails=mobileVerificationService.createEntity(standardEmailMobileCustomer().getCustomerId(),
				standardEmailMobileCustomer().getCustomerType(), standardEmailMobileCustomer().getMobile(),standardEmailMobileCustomer().getCustomerType(),
				standardEmailMobileCustomer().getUpdatedBy());
		
		
		assertEquals(standardCustomerMobileVerificationDetails().getKey(), emailVerificationDetails.getKey());
	}
	

	@Test
	public void saveCustomerMobileVerificationEntityAndGetByCustomerIdAndEmail()
	{
		MobileVerificationDetails savedEntity=mobileVerificationService.saveDetails(standardCustomerMobileVerificationDetails());
		
		MobileVerificationDetails fetchedEntity=mobileVerificationService
				.getByEntityIdTypeAndMobileType(savedEntity.getKey().getCustomerId(),
						savedEntity.getKey().getCustomerType(),savedEntity.getKey().getMobileType());
		
		assertEquals(savedEntity, fetchedEntity);
		
	}
	


	
	@Test
	public void deleteKey()
	{
		MobileVerificationDetails savedEntity=mobileVerificationService.saveDetails(standardCustomerMobileVerificationDetails());
		
		assertTrue(mobileVerificationService
				.deleteByKey(savedEntity.getKey()));
		
	}
}
