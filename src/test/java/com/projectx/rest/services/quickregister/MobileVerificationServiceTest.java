package com.projectx.rest.services.quickregister;


import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.standardCustomerMobileVerificationDetails;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
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
				standardEmailMobileCustomer().getUpdatedBy(),standardEmailMobileCustomer().getInsertedById());
		
		
		assertEquals(standardCustomerMobileVerificationDetails().getKey(), emailVerificationDetails.getKey());
	}
	

}
