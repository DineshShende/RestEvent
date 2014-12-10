package com.projectx.rest.services.quickregister;

import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailMobileAuthenticationDetails;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_EMAILHASH;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_PASSWORD_CHANGED;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomerAfterSaving;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomerDTO;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.web.domain.quickregister.LoginVerificationDTO;
import com.projectx.web.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
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
	public void saveCustomerAuthenticationDetailsAndGetByCustomerId()
	{
		assertNull(authenticationService.getLoginDetailsByCustomerIdType(CUST_ID,CUST_TYPE_CUSTOMER).getKey());
		
		AuthenticationDetails authenticationDetails=authenticationService.saveCustomerAuthenticationDetails(standardCustomerEmailAuthenticationDetails());
		
		assertEquals(standardCustomerEmailAuthenticationDetails(),authenticationService
				.getLoginDetailsByCustomerIdType(authenticationDetails.getKey().getCustomerId(),authenticationDetails.getKey().getCustomerType()));
		
	}


	@Test
	public void saveAndGetVerificationDetailsByCustomerId()
	{
		authenticationService.saveCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails());
		
		AuthenticationDetails authenticationDetails=authenticationService.
				getLoginDetailsByCustomerIdType(standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerType());
				
		assertEquals(standardEmailMobileCustomer().getCustomerId(), authenticationDetails.getKey().getCustomerId());
		assertEquals(standardEmailMobileCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(standardEmailMobileCustomer().getMobile(), authenticationDetails.getMobile());
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		assertNotNull( authenticationDetails.getEmailPassword());
	}

	@Test
	public void getVerificationDetailsByCustomerIdFailingCase()
	{
		AuthenticationDetails authenticationDetails=authenticationService
				.getLoginDetailsByCustomerIdType(standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerType());
				
		assertNull( authenticationDetails.getKey());
		assertNull(authenticationDetails.getEmail());
		assertNull( authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());		
	}
	
	
}
