package com.projectx.rest.services.quickregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailAuthenticationDetails;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailAuthenticationDetailsWithOutPassword;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailMobileAuthenticationDetails;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.ENTITY_TYPE_CUSTOMER;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class AuthenticationServiceTest {

	@Autowired
	AuthenticationService authenticationService;
	
	@Before
	public void clearTestData()
	{
		authenticationService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	

	
	
	@Test
	public void createCustomerAuthenticationDetails()
	{
		
		AuthenticationDetails authenticationDetails=authenticationService
				.createCustomerAuthenticationDetails(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerEmailAuthenticationDetailsWithOutPassword(),authenticationDetails );
	}
	

	@Test
	public void getVerificationDetailsByCustomerIdFailingCase()
	{
		AuthenticationDetails authenticationDetails=null;
		
		try{
			authenticationDetails=authenticationService
					.getByEntityIdType(standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerType());
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
				
	}
	
	
}
