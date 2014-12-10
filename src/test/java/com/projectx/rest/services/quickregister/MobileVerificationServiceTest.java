package com.projectx.rest.services.quickregister;

import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.standardCustomerEmailVerificationDetails;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.standardCustomerEmailVerificationDetailsWithOutPassword;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.standardCustomerMobileVerificationDetails;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomerAfterInitialization;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class MobileVerificationServiceTest {

	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Before
	public void clearTestData()
	{
		mobileVerificationService.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
		
	}
	
	
	@Test
	public void createCustomerMobileVerificationEntity()
	{
		MobileVerificationDetails emailVerificationDetails=mobileVerificationService
				.createCustomerMobileVerificationEntity(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerMobileVerificationDetails().getKey(), emailVerificationDetails.getKey());
	}
	

	@Test
	public void saveCustomerMobileVerificationEntityAndGetByCustomerIdAndEmail()
	{
		MobileVerificationDetails savedEntity=mobileVerificationService.saveCustomerMobileVerificationDetails(standardCustomerMobileVerificationDetails());
		
		MobileVerificationDetails fetchedEntity=mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getKey().getCustomerId(),
						savedEntity.getKey().getCustomerType(),savedEntity.getKey().getMobile());
		
		assertEquals(savedEntity, fetchedEntity);
		
	}
	

}
