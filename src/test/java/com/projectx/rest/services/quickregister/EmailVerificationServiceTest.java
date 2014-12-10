package com.projectx.rest.services.quickregister;

import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
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
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class EmailVerificationServiceTest {

	@Autowired
	EmailVerificationService emailVerificationService;

	@Autowired
	QuickRegisterService customerQuickRegisterHandler;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	AuthenticationService 	authenticationService;
	
	@Before
	public void clearTestData()
	{
		emailVerificationService.clearTestData();
		customerQuickRegisterHandler.clearDataForTesting();
		mobileVerificationService.clearTestData();
		authenticationService.clearTestData();
		
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void createCustomerEmailVerificationEntity()
	{
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.createCustomerEmailVerificationEntity(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerEmailVerificationDetailsWithOutPassword().getKey(), emailVerificationDetails.getKey());
	}

	
	@Test
	public void saveCustomerEmailVerificationEntityAndGetByCustomerIdAndEmail()
	{
		EmailVerificationDetails savedEntity=emailVerificationService.saveCustomerEmailVerificationDetails(standardCustomerEmailVerificationDetails());
		
		EmailVerificationDetails fetchedEntity=emailVerificationService.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(
				savedEntity.getKey().getCustomerId(),savedEntity.getKey().getCustomerType(), savedEntity.getKey().getEmail());
		
		assertEquals(savedEntity, fetchedEntity);
	}
	
	
@Test 
public void verifyEmailHashMobilePinWithEmailMobileCustomer() throws Exception
{
	assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

	CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
			.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

	AuthenticationDetails authenticationDetails=authenticationService
			.getLoginDetailsByCustomerIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
	
	EmailVerificationDetails emailVerificationDetails=emailVerificationService
			.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(handledEntity.getCustomer().getCustomerId(),
					handledEntity.getCustomer().getCustomerType(),handledEntity.getCustomer().getEmail());
	
	MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
			.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(handledEntity.getCustomer().getCustomerId(),
					handledEntity.getCustomer().getCustomerType(),handledEntity.getCustomer().getMobile());
	assertNull(authenticationDetails.getPassword());
	assertNull(authenticationDetails.getPasswordType());
	assertNull(authenticationDetails.getEmailPassword());
	
	assertTrue(emailVerificationService.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),
			handledEntity.getCustomer().getCustomerType(),handledEntity.getCustomer().getEmail(), emailVerificationDetails.getEmailHash()));
	
	authenticationDetails=authenticationService.
			getLoginDetailsByCustomerIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
	
	assertNotNull(authenticationDetails.getPassword());
	assertNotNull(authenticationDetails.getPasswordType());
	assertNotNull(authenticationDetails.getEmailPassword());
	
	String oldPassword=authenticationDetails.getPassword();
	String oldPasswordType=authenticationDetails.getPasswordType();
	String oldEmailPassword=authenticationDetails.getEmailPassword();
	
	assertFalse(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
	assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());

	
	assertTrue(mobileVerificationService
			.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(),
					handledEntity.getCustomer().getMobile(),mobileVerificationDetails.getMobilePin()));
	
	authenticationDetails=authenticationService
			.getLoginDetailsByCustomerIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
	
	assertEquals(oldPassword,authenticationDetails.getPassword());
	assertEquals(oldPasswordType,authenticationDetails.getPasswordType());
	assertEquals(oldEmailPassword, authenticationDetails.getEmailPassword());
	
	assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
	assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());

	
}

@Test 
public void verifyEmailWithEmailCustomer() throws Exception
{
	assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

	QuickRegisterEntity handledEntity=customerQuickRegisterHandler
			.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();
	
	
	EmailVerificationDetails emailVerificationDetail=emailVerificationService.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail
												(handledEntity.getCustomerId(),handledEntity.getCustomerType(), handledEntity.getEmail()); 
	
	AuthenticationDetails authenticationDetails=authenticationService
			.getLoginDetailsByCustomerIdType(handledEntity.getCustomerId(),handledEntity.getCustomerType());
	
	assertNull(authenticationDetails.getPassword());
	assertNull(authenticationDetails.getPasswordType());

	
	assertTrue(emailVerificationService.verifyEmailHash(handledEntity.getCustomerId(),
			handledEntity.getCustomerType(),handledEntity.getEmail() ,emailVerificationDetail.getEmailHash()));
	
	authenticationDetails=authenticationService.getLoginDetailsByCustomerIdType(handledEntity.getCustomerId(),handledEntity.getCustomerType());
	
	assertNotNull(authenticationDetails.getPassword());
	assertNotNull(authenticationDetails.getPasswordType());
	assertNotNull(authenticationDetails.getEmailPassword());

	assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomerId()).getIsEmailVerified());
	
	
}


	
}
