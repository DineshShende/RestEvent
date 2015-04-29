package com.projectx.rest.services.quickregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class EmailVerificationServiceTest {

	@Autowired
	EmailVerificationService emailVerificationService;

	@Autowired
	QuickRegisterService customerQuickRegisterHandler;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	AuthenticationService 	authenticationService;
	
	@Value("${PASSWORD_TYPE_DEFAULT}")
	private String PASSWORD_TYPE_DEFAULT;
	
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
				.createEntity(standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerType(),
						standardEmailMobileCustomer().getEmail(),CUST_EMAIL_TYPE_PRIMARY,standardEmailMobileCustomer().getUpdatedBy(),
						standardEmailMobileCustomer().getUpdatedById());
		
		assertEquals(standardCustomerEmailVerificationDetailsWithOutPassword().getKey(), emailVerificationDetails.getKey());
	}

	
	@Test 
	public void verifyEmailHashMobilePinWithEmailMobileCustomer() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=null;
		
		try{
			quickRegisterEntity=customerQuickRegisterHandler.getByEntityId(CUST_ID);
		}catch(ResourceNotFoundException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
	
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),1);
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),1);
		assertNull(authenticationDetails.getPassword());
		assertEquals(PASSWORD_TYPE_DEFAULT,authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),1, emailVerificationDetails.getEmailHash(),emailVerificationDetails.getUpdatedBy(),
				emailVerificationDetails.getInsertedById()));
		
		authenticationDetails=authenticationService.
				getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		String oldEmailPassword=authenticationDetails.getEmailPassword();
		
		assertFalse(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());
	
		
		assertTrue(mobileVerificationService
				.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(),
						1,mobileVerificationDetails.getMobilePin(),mobileVerificationDetails.getUpdatedBy(),handledEntity.getCustomer().getCustomerId()));
		
		authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertEquals(oldPassword,authenticationDetails.getPassword());
		assertEquals(oldPasswordType,authenticationDetails.getPasswordType());
		assertEquals(oldEmailPassword, authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());
	
		
	}
	
	@Test 
	public void verifyEmailWithEmailCustomer() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=null;
		
		try{
			quickRegisterEntity=customerQuickRegisterHandler.getByEntityId(CUST_ID);
		}catch(ResourceNotFoundException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();
		
		
		EmailVerificationDetails emailVerificationDetail=emailVerificationService.getByEntityIdTypeAndEmailType
													(handledEntity.getCustomerId(),handledEntity.getCustomerType(), 1); 
		
		
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomerId(),handledEntity.getCustomerType());
		
		assertNull(authenticationDetails.getPassword());
		assertEquals(PASSWORD_TYPE_DEFAULT,authenticationDetails.getPasswordType());
	
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomerId(),
				handledEntity.getCustomerType(),1 ,emailVerificationDetail.getEmailHash(),emailVerificationDetail.getUpdatedBy(),
				handledEntity.getCustomerId()));
		
		authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomerId(),handledEntity.getCustomerType());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
	
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomerId()).getIsEmailVerified());
		
		
	}
	
	
	@Test
	public void resendEmailHash() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=null;
		
		try{
			quickRegisterEntity=customerQuickRegisterHandler.getByEntityId(CUST_ID);
		}catch(ResourceNotFoundException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();
		
		
		EmailVerificationDetails emailVerificationDetail=emailVerificationService.getByEntityIdTypeAndEmailType
													(handledEntity.getCustomerId(),handledEntity.getCustomerType(), 1); 
		
		Boolean status=emailVerificationService.sendEmailHash(handledEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, 
				ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY,handledEntity.getCustomerId());
		
		assertTrue(status);
	
	}
	
}
