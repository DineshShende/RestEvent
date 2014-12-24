package com.projectx.rest.services.quickregister;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.standardCustomerEmailVerificationDetails;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.standardCustomerMobileVerificationDetails;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class QuickRegisterServiceTest {

	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Before()
	public void setUp()
	{
		quickRegisterService.clearDataForTesting();
	}
	
	
	@Test
	public void saveCustomerQuickRegisterEntity() throws Exception
	{
		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());
		
		QuickRegisterEntity savedEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(standardEmailMobileCustomer().getEmail(), savedEntity.getEmail());
		
		
	}
	
	
	@Test
	public void getCustomerQuickRegisterEntityByCustomerId() throws Exception
	{
		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());
		
		QuickRegisterEntity savedEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileCustomer());
		
		QuickRegisterEntity  fetchedEntity=quickRegisterService.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId());
		
		assertEquals(savedEntity.getCustomerId(),fetchedEntity.getCustomerId());
		assertEquals(savedEntity.getFirstName(), fetchedEntity.getFirstName());
		assertEquals(savedEntity.getLastName(), fetchedEntity.getLastName());
		assertEquals(savedEntity.getEmail(), fetchedEntity.getEmail());
		assertEquals(savedEntity.getMobile(), fetchedEntity.getMobile());
		assertEquals(savedEntity.getPincode(), fetchedEntity.getPincode());
		assertTrue( (fetchedEntity.getInsertTime().getTime()-savedEntity.getInsertTime().getTime())<60*1000);
		assertEquals(savedEntity.getUpdatedBy(), fetchedEntity.getUpdatedBy());
		assertTrue( (fetchedEntity.getUpdateTime().getTime()-savedEntity.getUpdateTime().getTime())<60*1000);
		
		
	}
	
	@Test
	public void populateStatusEmailCustomer() throws Exception {
		
		assertEquals(
				standardEmailCustomerAfterStatusPopulation(),
				(quickRegisterService
						.populateVerificationFields(standardEmailCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusEmailMobileCustomerWithStatus() throws Exception {
		assertEquals(
				standardEmailMobileCustomerAfterStatusPopulation(),
				(quickRegisterService
						.populateVerificationFields(standardEmailMobileCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusMobileCustomer() throws Exception {
		assertEquals(
				standardMobileCustomerAfterStatusPopulation(),
				(quickRegisterService
						.populateVerificationFields(standardMobileCustomerDTOWithOutStatus())));

	}
	
		
	@Test
	public void initializeNewCustomerQuickRegistrationEntityWithMobileCustomer()
	{
		QuickRegisterEntity initializedCustomer=quickRegisterService
						.initializeNewCustomerQuickRegistrationEntity(standardMobileCustomerAfterStatusPopulation());
		
		assertNull(initializedCustomer.getCustomerId());
		assertEquals(standardMobileCustomerAfterInitialization().getFirstName(), initializedCustomer.getFirstName());
		assertEquals(standardMobileCustomerAfterInitialization().getLastName(), initializedCustomer.getLastName());
		assertEquals(standardMobileCustomerAfterInitialization().getEmail(), initializedCustomer.getEmail());
		assertEquals(standardMobileCustomerAfterInitialization().getMobile(), initializedCustomer.getMobile());
		assertEquals(standardMobileCustomerAfterInitialization().getPincode(), initializedCustomer.getPincode());
		assertEquals(standardMobileCustomerAfterInitialization().getIsEmailVerified(), initializedCustomer.getIsEmailVerified());
		assertEquals(standardMobileCustomerAfterInitialization().getIsMobileVerified(), initializedCustomer.getIsMobileVerified());
		assertTrue(Math.abs(standardMobileCustomerAfterInitialization().getInsertTime().getTime()- initializedCustomer.getInsertTime().getTime())<60*1000);
		assertTrue(Math.abs(standardMobileCustomerAfterInitialization().getUpdateTime().getTime()- initializedCustomer.getUpdateTime().getTime())<60*1000);
		assertEquals(standardMobileCustomerAfterInitialization().getUpdatedBy(), initializedCustomer.getUpdatedBy());
		
				
		
	}
	
	

	@Test
	public void initializeNewCustomerQuickRegistrationEntityWithEmailMobileCustomer()
	{
		QuickRegisterEntity initializedCustomer=quickRegisterService
						.initializeNewCustomerQuickRegistrationEntity(standardEmailMobileCustomerAfterStatusPopulation());
		
		assertNull(initializedCustomer.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), initializedCustomer.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), initializedCustomer.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), initializedCustomer.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), initializedCustomer.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPincode(), initializedCustomer.getPincode());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getIsEmailVerified(), initializedCustomer.getIsEmailVerified());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getIsMobileVerified(), initializedCustomer.getIsMobileVerified());
		assertTrue(Math.abs(standardEmailMobileCustomerAfterInitialization().getInsertTime().getTime()- initializedCustomer.getInsertTime().getTime())<60*1000);
		assertTrue(Math.abs(standardEmailMobileCustomerAfterInitialization().getUpdateTime().getTime()- initializedCustomer.getUpdateTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getUpdatedBy(), initializedCustomer.getUpdatedBy());
		
		
		
	}
	
	
	@Test
	public void initializeNewCustomerQuickRegistrationEntityWithEmailCustomer()
	{
		QuickRegisterEntity initializedCustomer=quickRegisterService
						.initializeNewCustomerQuickRegistrationEntity(standardEmailCustomerAfterStatusPopulation());
		
		assertNull(initializedCustomer.getCustomerId());
		assertEquals(standardEmailCustomerAfterInitialization().getFirstName(), initializedCustomer.getFirstName());
		assertEquals(standardEmailCustomerAfterInitialization().getLastName(), initializedCustomer.getLastName());
		assertEquals(standardEmailCustomerAfterInitialization().getEmail(), initializedCustomer.getEmail());
		assertEquals(standardEmailCustomerAfterInitialization().getMobile(), initializedCustomer.getMobile());
		assertEquals(standardEmailCustomerAfterInitialization().getPincode(), initializedCustomer.getPincode());
		assertEquals(standardEmailCustomerAfterInitialization().getIsEmailVerified(), initializedCustomer.getIsEmailVerified());
		assertEquals(standardEmailCustomerAfterInitialization().getIsMobileVerified(), initializedCustomer.getIsMobileVerified());
		assertTrue(Math.abs(standardEmailCustomerAfterInitialization().getInsertTime().getTime()- initializedCustomer.getInsertTime().getTime())<60*1000);
		assertTrue(Math.abs(standardEmailCustomerAfterInitialization().getUpdateTime().getTime()- initializedCustomer.getUpdateTime().getTime())<60*1000);
		assertEquals(standardEmailCustomerAfterInitialization().getUpdatedBy(), initializedCustomer.getUpdatedBy());
		
		
	}

	@Test
	public void checkIfEmailCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());
		
		
		QuickRegisterEntity fetchedEntity=quickRegisterService.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();
		EmailVerificationDetails emailVerificationDetails=emailVerificationService.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail
															(fetchedEntity.getCustomerId(),fetchedEntity.getCustomerType(), fetchedEntity.getEmail());
		
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		assertTrue(emailVerificationService.verifyEmailHash(fetchedEntity.getCustomerId(),
				fetchedEntity.getCustomerType(),fetchedEntity.getEmail(), emailVerificationDetails.getEmailHash()));


		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		
		
	}

	
	@Test
	public void checkIfMobileCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());

		QuickRegisterEntity fetchedEntity=quickRegisterService.handleNewCustomerQuickRegister(standardMobileCustomerDTO()).getCustomer();
				
		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
		
		//TODO
	}

	
	@Test
	public void checkIfEmailMobileCustomerExistEmailVerfiedFirst() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		QuickRegisterEntity fetchedEntity=quickRegisterService.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail
				(fetchedEntity.getCustomerId(),fetchedEntity.getCustomerType(), fetchedEntity.getEmail());
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile
				(fetchedEntity.getCustomerId(),fetchedEntity.getCustomerType(), fetchedEntity.getMobile());
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
		
		assertTrue(emailVerificationService
				.verifyEmailHash(fetchedEntity.getCustomerId(),fetchedEntity.getCustomerType(),fetchedEntity.getEmail(),emailVerificationDetails.getEmailHash()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED ,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertTrue(mobileVerificationService.verifyMobilePin(fetchedEntity.getCustomerId(),
				fetchedEntity.getCustomerType(),fetchedEntity.getMobile() ,mobileVerificationDetails.getMobilePin()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
	}

	
	@Test
	public void checkIfEmailMobileCustomerExistMobileVerfiedFirst() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		QuickRegisterEntity fetchedEntity=quickRegisterService.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO()).getCustomer();
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail
				(fetchedEntity.getCustomerId(),fetchedEntity.getCustomerType(), fetchedEntity.getEmail());
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile
				(fetchedEntity.getCustomerId(),fetchedEntity.getCustomerType(), fetchedEntity.getMobile());
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
				
		assertTrue(mobileVerificationService.verifyMobilePin(fetchedEntity.getCustomerId(),
				fetchedEntity.getCustomerType(),fetchedEntity.getMobile() ,mobileVerificationDetails.getMobilePin()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED ,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertTrue(emailVerificationService.verifyEmailHash(fetchedEntity.getCustomerId(), 
				fetchedEntity.getCustomerType(),fetchedEntity.getEmail(),emailVerificationDetails.getEmailHash()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED,quickRegisterService
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
	}


	

	@Test
	public void saveAndGetByCustomerIdWithEmailMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity saved=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		QuickRegisterEntity fetced=quickRegisterService.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerQuickRegisterEntity().getCustomerId());
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardEmailMobileCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardEmailMobileCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		

		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(fetced.getCustomerId(),fetced.getCustomerType(),fetced.getEmail()); 
	
		assertEquals(fetced.getCustomerId(), emailVerificationDetails.getKey().getCustomerId());
		assertEquals(standardCustomerEmailVerificationDetails().getKey().getEmail(), emailVerificationDetails.getKey().getEmail());
		assertNotNull(emailVerificationDetails.getEmailHash());
		assertNotNull(emailVerificationDetails.getEmailHashSentTime());
		assertEquals(standardCustomerEmailVerificationDetails().getEmailType(), emailVerificationDetails.getEmailType());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), emailVerificationDetails.getResendCount());
		
	
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(fetced.getCustomerId(),fetced.getCustomerType(),fetced.getMobile());
		
		assertEquals(fetced.getCustomerId(), mobileVerificationDetails.getKey().getCustomerId());
		assertEquals(standardCustomerMobileVerificationDetails().getKey().getMobile(), mobileVerificationDetails.getKey().getMobile());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileType(), mobileVerificationDetails.getMobileType());
		assertNotNull(mobileVerificationDetails.getMobilePin());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts(), mobileVerificationDetails.getMobileVerificationAttempts());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), mobileVerificationDetails.getResendCount());
	
		AuthenticationDetails authenticationDetails=authenticationService
				.getLoginDetailsByCustomerIdType(fetced.getCustomerId(),fetced.getCustomerType());
		
		assertEquals(fetced.getCustomerId(), authenticationDetails.getKey().getCustomerId());
		assertEquals(fetced.getEmail(), authenticationDetails.getEmail());
		assertEquals(fetced.getMobile(), authenticationDetails.getMobile());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		assertEquals(0, authenticationDetails.getLastUnsucessfullAttempts().intValue());
		assertEquals(0, authenticationDetails.getResendCount().intValue());
		
		
	}
	
	
	@Test
	public void saveAndGetByCustomerIdWithEmailCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity saved=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailCustomerAfterInitialization());
		
		QuickRegisterEntity fetced=quickRegisterService.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerQuickRegisterEntity().getCustomerId());
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardEmailCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardEmailCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardEmailCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		

		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(fetced.getCustomerId(),fetced.getCustomerType(),fetced.getEmail()); 
	
		assertEquals(fetced.getCustomerId(), emailVerificationDetails.getKey().getCustomerId());
		assertEquals(standardCustomerEmailVerificationDetails().getKey().getEmail(), emailVerificationDetails.getKey().getEmail());
		assertNotNull(emailVerificationDetails.getEmailHash());
		assertNotNull(emailVerificationDetails.getEmailHashSentTime());
		assertEquals(standardCustomerEmailVerificationDetails().getEmailType(), emailVerificationDetails.getEmailType());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), emailVerificationDetails.getResendCount());
	
		AuthenticationDetails authenticationDetails=authenticationService
				.getLoginDetailsByCustomerIdType(fetced.getCustomerId(),fetced.getCustomerType());
		
		assertEquals(fetced.getCustomerId(), authenticationDetails.getKey().getCustomerId());
		assertEquals(fetced.getEmail(), authenticationDetails.getEmail());
		assertEquals(fetced.getMobile(), authenticationDetails.getMobile());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		assertEquals(0, authenticationDetails.getLastUnsucessfullAttempts().intValue());
		assertEquals(0, authenticationDetails.getResendCount().intValue());
	
		
	}


	
	@Test
	public void saveAndGetByCustomerIdWithMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity saved=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardMobileCustomerAfterInitialization());
		
		QuickRegisterEntity fetced=quickRegisterService.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerQuickRegisterEntity().getCustomerId());
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardMobileCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardMobileCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardMobileCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardMobileCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(fetced.getCustomerId(),fetced.getCustomerType(),fetced.getMobile());
		
		assertEquals(fetced.getCustomerId(), mobileVerificationDetails.getKey().getCustomerId());
		assertEquals(standardCustomerMobileVerificationDetails().getKey().getMobile(), mobileVerificationDetails.getKey().getMobile());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileType(), mobileVerificationDetails.getMobileType());
		assertNotNull(mobileVerificationDetails.getMobilePin());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts(), mobileVerificationDetails.getMobileVerificationAttempts());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), mobileVerificationDetails.getResendCount());
	
		AuthenticationDetails authenticationDetails=authenticationService.getLoginDetailsByCustomerIdType(fetced.getCustomerId(),fetced.getCustomerType());
		
		assertEquals(fetced.getCustomerId(), authenticationDetails.getKey().getCustomerId());
		assertEquals(fetced.getEmail(), authenticationDetails.getEmail());
		assertEquals(fetced.getMobile(), authenticationDetails.getMobile());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		assertEquals(0, authenticationDetails.getLastUnsucessfullAttempts().intValue());
		assertEquals(0, authenticationDetails.getResendCount().intValue());
	
		
	}


	@Test
	public void getCustomerQuickRegisterEntityByEmailAndMobile() throws Exception
	{
		assertNull(quickRegisterService.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getCustomerId());
		
		assertNull(quickRegisterService.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE).getCustomerId());
		
		CustomerQuickRegisterEmailMobileVerificationEntity saved=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		
		QuickRegisterEntity fetced=quickRegisterService.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL);
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardEmailMobileCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardEmailMobileCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		

		fetced=quickRegisterService.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE);
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardEmailMobileCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardEmailMobileCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);		
		
		
	}

	

	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailMobileCustomer() throws Exception {

		CustomerQuickRegisterStatusEntity handledEntity=quickRegisterService
						.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		
		assertEquals(standardEmailMobileCustomerAfterSaving().getCustomerType(), handledEntity.getCustomer().getCustomerType());
		assertEquals(standardEmailMobileCustomerAfterSaving().getEmail(), handledEntity.getCustomer().getEmail());
		assertEquals(standardEmailMobileCustomerAfterSaving().getFirstName(), handledEntity.getCustomer().getFirstName());
		assertEquals(standardEmailMobileCustomerAfterSaving().getLastName(), handledEntity.getCustomer().getLastName());
		assertEquals(standardEmailMobileCustomerAfterSaving().getIsEmailVerified(), handledEntity.getCustomer().getIsEmailVerified());
		assertEquals(standardEmailMobileCustomerAfterSaving().getIsMobileVerified(), handledEntity.getCustomer().getIsMobileVerified());
		assertEquals(standardEmailMobileCustomerAfterSaving().getMobile(), handledEntity.getCustomer().getMobile());
		assertEquals(standardEmailMobileCustomerAfterSaving().getPincode(), handledEntity.getCustomer().getPincode());
		
		
		AuthenticationDetails authenticationDetails=authenticationService.getLoginDetailsByCustomerIdType(
				handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertEquals(handledEntity.getCustomer().getCustomerId(), authenticationDetails.getKey().getCustomerId());
		assertEquals(handledEntity.getCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(handledEntity.getCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}

	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailCustomer() throws Exception {

		CustomerQuickRegisterStatusEntity handledEntity=quickRegisterService
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		
		assertEquals(standardEmailCustomerAfterSaving().getCustomerType(), handledEntity.getCustomer().getCustomerType());
		assertEquals(standardEmailCustomerAfterSaving().getEmail(), handledEntity.getCustomer().getEmail());
		assertEquals(standardEmailCustomerAfterSaving().getFirstName(), handledEntity.getCustomer().getFirstName());
		assertEquals(standardEmailCustomerAfterSaving().getLastName(), handledEntity.getCustomer().getLastName());
		assertEquals(standardEmailCustomerAfterSaving().getIsEmailVerified(), handledEntity.getCustomer().getIsEmailVerified());
		assertEquals(standardEmailCustomerAfterSaving().getIsMobileVerified(), handledEntity.getCustomer().getIsMobileVerified());
		assertEquals(standardEmailCustomerAfterSaving().getMobile(), handledEntity.getCustomer().getMobile());
		assertEquals(standardEmailCustomerAfterSaving().getPincode(), handledEntity.getCustomer().getPincode());
		
		AuthenticationDetails authenticationDetails=authenticationService.getLoginDetailsByCustomerIdType(
				handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertEquals(handledEntity.getCustomer().getCustomerId(), authenticationDetails.getKey().getCustomerId());
		assertEquals(handledEntity.getCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(handledEntity.getCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}


	@Test
	public void handleNewCustomerQuickRegistrationWithMobileCustomer() throws Exception {

		CustomerQuickRegisterStatusEntity handledEntity=quickRegisterService
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		
		assertEquals(standardMobileCustomerAfterSaving().getCustomerType(), handledEntity.getCustomer().getCustomerType());
		assertEquals(standardMobileCustomerAfterSaving().getEmail(), handledEntity.getCustomer().getEmail());
		assertEquals(standardMobileCustomerAfterSaving().getFirstName(), handledEntity.getCustomer().getFirstName());
		assertEquals(standardMobileCustomerAfterSaving().getLastName(), handledEntity.getCustomer().getLastName());
		assertEquals(standardMobileCustomerAfterSaving().getIsEmailVerified(), handledEntity.getCustomer().getIsEmailVerified());
		assertEquals(standardMobileCustomerAfterSaving().getIsMobileVerified(), handledEntity.getCustomer().getIsMobileVerified());
		assertEquals(standardMobileCustomerAfterSaving().getMobile(), handledEntity.getCustomer().getMobile());
		assertEquals(standardMobileCustomerAfterSaving().getPincode(), handledEntity.getCustomer().getPincode());
		
		AuthenticationDetails authenticationDetails=authenticationService.getLoginDetailsByCustomerIdType(
				handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getCustomerType());
		
		assertEquals(handledEntity.getCustomer().getCustomerId(), authenticationDetails.getKey().getCustomerId());
		assertEquals(handledEntity.getCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(handledEntity.getCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}


}