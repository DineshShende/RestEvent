package com.projectx.rest.services.quickregister;

import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.services.quickregister.QuickRegisterService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class QuickRegisterServiceIntegrationTest {

	@Autowired
	public QuickRegisterService customerQuickRegisterHandler;

	@Autowired
	public QuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	
	@Before
	public void cleanAllRecords()
	{
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		mobileVerificationService.clearTestData();
		emailVerificationService.clearTestData();
		authenticationService.clearTestData();
	}


	
	
	@Test
	public void sendVerificationDetailsWithEmailMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),savedEntity.getCustomerMobileVerificationDetails());
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailMobileCustomerAfterSaving().getCustomerType(), customerStatusEntity.getCustomer().getCustomerType());
		assertEquals(standardEmailMobileCustomerAfterSaving().getEmail(), customerStatusEntity.getCustomer().getEmail());
		assertEquals(standardEmailMobileCustomerAfterSaving().getFirstName(), customerStatusEntity.getCustomer().getFirstName());
		assertEquals(standardEmailMobileCustomerAfterSaving().getIsEmailVerified(), customerStatusEntity.getCustomer().getIsEmailVerified());
		assertEquals(standardEmailMobileCustomerAfterSaving().getIsMobileVerified(), customerStatusEntity.getCustomer().getIsMobileVerified());
		assertEquals(standardEmailMobileCustomerAfterSaving().getLastName(), customerStatusEntity.getCustomer().getLastName());
		assertEquals(standardEmailMobileCustomerAfterSaving().getMobile(), customerStatusEntity.getCustomer().getMobile());
		assertEquals(standardEmailMobileCustomerAfterSaving().getPincode(), customerStatusEntity.getCustomer().getPincode());
	}
	
	
	@Test
	public void sendVerificationDetailsWithEmailCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailCustomerAfterInitialization());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),savedEntity.getCustomerMobileVerificationDetails());
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailCustomerAfterSaving().getCustomerType(), customerStatusEntity.getCustomer().getCustomerType());
		assertEquals(standardEmailCustomerAfterSaving().getEmail(), customerStatusEntity.getCustomer().getEmail());
		assertEquals(standardEmailCustomerAfterSaving().getFirstName(), customerStatusEntity.getCustomer().getFirstName());
		assertEquals(standardEmailCustomerAfterSaving().getIsEmailVerified(), customerStatusEntity.getCustomer().getIsEmailVerified());
		assertEquals(standardEmailCustomerAfterSaving().getIsMobileVerified(), customerStatusEntity.getCustomer().getIsMobileVerified());
		assertEquals(standardEmailCustomerAfterSaving().getLastName(), customerStatusEntity.getCustomer().getLastName());
		assertEquals(standardEmailCustomerAfterSaving().getMobile(), customerStatusEntity.getCustomer().getMobile());
		assertEquals(standardEmailCustomerAfterSaving().getPincode(), customerStatusEntity.getCustomer().getPincode());
		
	}
	
	@Test
	public void sendVerificationDetailsWithMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),savedEntity.getCustomerMobileVerificationDetails());
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardMobileCustomerAfterSaving().getCustomerType(), customerStatusEntity.getCustomer().getCustomerType());
		assertEquals(standardMobileCustomerAfterSaving().getEmail(), customerStatusEntity.getCustomer().getEmail());
		assertEquals(standardMobileCustomerAfterSaving().getFirstName(), customerStatusEntity.getCustomer().getFirstName());
		assertEquals(standardMobileCustomerAfterSaving().getIsEmailVerified(), customerStatusEntity.getCustomer().getIsEmailVerified());
		assertEquals(standardMobileCustomerAfterSaving().getIsMobileVerified(), customerStatusEntity.getCustomer().getIsMobileVerified());
		assertEquals(standardMobileCustomerAfterSaving().getLastName(), customerStatusEntity.getCustomer().getLastName());
		assertEquals(standardMobileCustomerAfterSaving().getMobile(), customerStatusEntity.getCustomer().getMobile());
		assertEquals(standardMobileCustomerAfterSaving().getPincode(), customerStatusEntity.getCustomer().getPincode());

	}
	

	
	
	
	
	@Test 
	public void verifyMobileWithMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());
	
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService.getByEntityIdTypeAndMobileType
									(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(), MOB_TYPE_PRIMARY);
		
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertFalse(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY,CUST_MOBILEPIN));
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertFalse(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		
		mobileVerificationDetails=mobileVerificationService.getByEntityIdTypeAndMobileType
				(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(), MOB_TYPE_PRIMARY);
		
		assertEquals(1, mobileVerificationDetails.getMobileVerificationAttempts().intValue());
		
		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin()));
	
		authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		
		
	}
		
	 
	 
	@Test 
	public void verifyMobileEmailWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService.
				getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
	

		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin()));
		
		authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		String oldEmailPassword=authenticationDetails.getEmailPassword();
		
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		assertFalse(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());


		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(),
				EMAIL_TYPE_PRIMARY,emailVerificationDetails.getEmailHash()));
		
		authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertEquals(oldPassword,authenticationDetails.getPassword());
		assertEquals(oldPasswordType,authenticationDetails.getPasswordType());
		assertEquals(oldEmailPassword, authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		assertTrue(customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());

	}

	
		
	
	@Test
	public void verifyEmailAndMobileFailedCase()
	{
		assertFalse(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(CUST_ID,ENTITY_TYPE_CUSTOMER,EMAIL_TYPE_PRIMARY, CUST_EMAILHASH));
		
		assertFalse(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(CUST_ID,ENTITY_TYPE_CUSTOMER,MOB_TYPE_PRIMARY, CUST_MOBILEPIN));
	}
	

	
	@Test
	public void verifyMobilePinWithFailingCase() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		AuthenticationDetails authenticationDetails=authenticationService.
				getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertFalse(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY ,101010));
		
		assertFalse(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY ,101010));
		
		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));

		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));
		
		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getMobilePin()));
		
		authenticationDetails=authenticationService.getByEntityIdType(
				handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		
		assertTrue(mobileVerificationService.reSetMobilePin(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY));
		
		QuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.
				getByEntityId(handledEntity.getCustomer().getCustomerId());
		
		mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin()));
		
		authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());

	}
	

	
	
	@Test
	public void verifyLoginDetailsWithMobileVerifiedFirst() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
	
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin()));
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
	
	
		assertEquals(authenticationDetails, authenticationService.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(),
																								authenticationDetails.getPassword())));
		
		assertEquals(authenticationDetails, authenticationService.verifyLoginDetails(new LoginVerificationDTO(Long.toString(authenticationDetails.getMobile()),
				authenticationDetails.getPassword())));
		
		assertNull( authenticationService.verifyLoginDetails(new LoginVerificationDTO(Long.toString(authenticationDetails.getMobile()),
				CUST_PASSWORD_DEFAULT)).getKey());
		
		assertEquals(new Integer(1), authenticationService.getByEntityIdType(
				handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType()).getLastUnsucessfullAttempts());
	
		assertNull(authenticationService.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(),
				CUST_PASSWORD_CHANGED)).getKey());
		
		assertEquals(new Integer(2), authenticationService.getByEntityIdType(
				handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType()).getLastUnsucessfullAttempts());
		

	}

	
	@Test
	public void verifyLoginDetailsWithEmailVerifiedFirst() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(
						handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(), EMAIL_TYPE_PRIMARY);
	
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY,emailVerificationDetails.getEmailHash()));
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
	
	
		assertEquals(authenticationDetails, authenticationService.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(),
																								authenticationDetails.getPassword())));
	}
	
	
	
	@Test
	public void verifyDefaultEmailPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(
						handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
	
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY,emailVerificationDetails.getEmailHash()));
		
		AuthenticationDetails authenticationDetails=authenticationService.
				getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
		
		assertNull(authenticationService.
				verifyDefaultEmailLoginDetails(new LoginVerificationWithDefaultEmailPasswordDTO(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),CUST_EMAILHASH)).getKey());
		
		assertEquals(authenticationDetails, authenticationService.
				verifyDefaultEmailLoginDetails(new LoginVerificationWithDefaultEmailPasswordDTO(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),authenticationDetails.getEmailPassword())));
	
		
	}

	

	
	@Test
	public void sendDefaultPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(
						handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		AuthenticationDetails authenticationDetails=authenticationService.
				getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getEmailPassword());
		assertNull( authenticationDetails.getPasswordType());
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY,
				emailVerificationDetails.getEmailHash()));
		
		authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
			
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getEmailPassword());
		assertNotNull( authenticationDetails.getPasswordType());
				
	}

	
	@Test
	public void resetPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY, 
				emailVerificationDetails.getEmailHash()));
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertTrue(authenticationService.resetPassword(new CustomerIdTypeDTO(authenticationDetails.getKey().getCustomerId(),
				authenticationDetails.getKey().getCustomerType())));
		
		authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType());
		
		assertNotEquals(oldPassword, authenticationDetails.getPassword());
	//	assertNotEquals(oldPasswordType, authenticationDetails.getPasswordType());
		assertEquals(oldPasswordType, authenticationDetails.getPasswordType());
		
	}

	
	@Test
	public void resendDefaultPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(
						handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType(),
						EMAIL_TYPE_PRIMARY);
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY, 
				emailVerificationDetails.getEmailHash()));
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		String oldEmailPassword=authenticationDetails.getEmailPassword();
		
		assertEquals(0, authenticationDetails.getResendCount().intValue());
		
		assertTrue(authenticationService.resendDefaultPassword(handledEntity.getCustomer()));
		
		authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType());
		
		assertEquals(oldPassword, authenticationDetails.getPassword());
		assertEquals(oldPasswordType, authenticationDetails.getPasswordType());
		assertEquals(oldEmailPassword, authenticationDetails.getEmailPassword());
		
		assertEquals(1, authenticationDetails.getResendCount().intValue());
		
		
	}
	
	
	@Test
	public void updatePassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY,emailVerificationDetails.getEmailHash()));
		
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());

		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertTrue(authenticationService
				.updatePassword(new UpdatePasswordAndPasswordTypeDTO(authenticationDetails.getKey().getCustomerId(),
						authenticationDetails.getKey().getCustomerType(),CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED)));
		
		authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType());
		
		assertNotEquals(oldPassword, authenticationDetails.getPassword());
		assertNotEquals(oldPasswordType, authenticationDetails.getPasswordType());
	
	}
	

	
	@Test
	public void setDefaultPasswordWithEmailMobileCustomerCalledAfterEmailVerifiedAndPasswordChange() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);

		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY,emailVerificationDetails.getEmailHash()));
		
		AuthenticationDetails authenticationDetails=authenticationService
				.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());

		QuickRegisterEntity updatedEntity=customerQuickRegisterHandler.getByEntityId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		assertTrue(authenticationService.updatePassword(new UpdatePasswordAndPasswordTypeDTO(authenticationDetails.getKey().getCustomerId(),
				authenticationDetails.getKey().getCustomerType(),CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED)));
		
		assertTrue(authenticationService.sendDefaultPassword(updatedEntity, false));
		
		authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType());
	
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
	
		
		assertEquals(oldPassword, authenticationDetails.getPassword());
		assertEquals(oldPasswordType, authenticationDetails.getPasswordType());
		
		
	}
	
	
	
	@Test
	public void UpdateEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		Date oldEmailHashSentTime=emailVerificationDetails.getEmailHashSentTime();
		
		assertEquals(1, emailVerificationService.updateEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY).intValue());
		
		emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		
		
		assertNotEquals(oldEmailHash,emailVerificationDetails.getEmailHash());
	//	assertNotEquals(oldEmailHashSentTime, emailVerificationDetails.getEmailHashSentTime());
		
	}
	
	
	
	@Test
	public void UpdateMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());

		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
				
		assertEquals(1, mobileVerificationService.updateMobilePin(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY).intValue());
		
		mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		
		
		assertNotEquals(oldMobilePin,mobileVerificationDetails.getMobilePin());
				
	}
	
	
	
	@Test
	public void reSetEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertEquals(true, emailVerificationService.reSendEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY));
		
		assertNotEquals(oldEmailHash,emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY));
	}
	
	
	
	@Test
	public void reSetMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
				
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(true, mobileVerificationService.reSendMobilePin(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY));
		
		
		assertNotEquals(oldMobilePin,
				mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY));

	}
	
	
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY);
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertEquals(0, emailVerificationDetails.getResendCount().intValue());
		
		assertEquals(true, emailVerificationService.reSendEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY));
		
		assertEquals(1,emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY).getResendCount().intValue());
		
		assertEquals(oldEmailHash,emailVerificationService
				.getByEntityIdTypeAndEmailType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),EMAIL_TYPE_PRIMARY).getEmailHash());
	}
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getByEntityId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
				
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY);
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(0, mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY).getResendCount().intValue());
		
		assertEquals(true, mobileVerificationService.reSendMobilePin(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY));
		
		assertEquals(oldMobilePin,
				mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY).getMobilePin());
		
		assertEquals(1, mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getCustomerType(),MOB_TYPE_PRIMARY).getResendCount().intValue());

	}
	
	
/*	
	@Test
	public void resetPasswordByEmailOrMobile() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		//TODO
	
	}
*/	
	
}
