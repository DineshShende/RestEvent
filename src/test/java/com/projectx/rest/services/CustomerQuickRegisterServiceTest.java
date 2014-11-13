package com.projectx.rest.services;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.CustomerEmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.CustomerMobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.CustomerAuthenticationDetailsDataFixtures.*;
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

import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.rest.repository.CustomerEmailVericationDetailsRepository;
import com.projectx.rest.repository.CustomerMobileVerificationDetailsRepository;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.LoginVerificationDTO;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Test")
public class CustomerQuickRegisterServiceTest {

	@Autowired
	public CustomerQuickRegisterService customerQuickRegisterHandler;

	@Autowired
	public CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	public CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	@Autowired
	public CustomerEmailVericationDetailsRepository customerEmailVericationDetailsRepository;
	
	@Autowired
	public CustomerMobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	@Before
	public void cleanAllRecords()
	{
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		customerAuthenticationDetailsRepository.clearLoginDetailsForTesting();
		customerEmailVericationDetailsRepository.clearTestData();
		customerMobileVerificationDetailsRepository.clearTestData();
	}

	
	@Test
	public void checkIfEmailCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());
		
		
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail
															(fetchedEntity.getCustomerId(), fetchedEntity.getEmail());
		
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(fetchedEntity.getCustomerId(),fetchedEntity.getEmail(), emailVerificationDetails.getEmailHash()));


		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		
		
	}

	
	@Test
	public void checkIfMobileCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());

		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.handleNewCustomerQuickRegister(standardMobileCustomerDTO()).getCustomer();
				
		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
		
		//TODO
	}

	
	@Test
	public void checkIfEmailMobileCustomerExistEmailVerfiedFirst() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail
				(fetchedEntity.getCustomerId(), fetchedEntity.getEmail());
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile
				(fetchedEntity.getCustomerId(), fetchedEntity.getMobile());
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(fetchedEntity.getCustomerId(),fetchedEntity.getEmail(),emailVerificationDetails.getEmailHash()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED ,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(fetchedEntity.getCustomerId(),fetchedEntity.getMobile() ,mobileVerificationDetails.getMobilePin()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
	}

	
	@Test
	public void checkIfEmailMobileCustomerExistMobileVerfiedFirst() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO()).getCustomer();
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail
				(fetchedEntity.getCustomerId(), fetchedEntity.getEmail());
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile
				(fetchedEntity.getCustomerId(), fetchedEntity.getMobile());
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(fetchedEntity.getCustomerId(),fetchedEntity.getMobile() ,mobileVerificationDetails.getMobilePin()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED ,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(fetchedEntity.getCustomerId(), fetchedEntity.getEmail(),emailVerificationDetails.getEmailHash()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
	}

	
	@Test
	public void populateStatusEmailCustomer() throws Exception {
		
		assertEquals(
				standardEmailCustomerAfterStatusPopulation(),
				(customerQuickRegisterHandler
						.populateVerificationFields(standardEmailCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusEmailMobileCustomerWithStatus() throws Exception {
		assertEquals(
				standardEmailMobileCustomerAfterStatusPopulation(),
				(customerQuickRegisterHandler
						.populateVerificationFields(standardEmailMobileCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusMobileCustomer() throws Exception {
		assertEquals(
				standardMobileCustomerAfterStatusPopulation(),
				(customerQuickRegisterHandler
						.populateVerificationFields(standardMobileCustomerDTOWithOutStatus())));

	}
	
		
	@Test
	public void initializeNewCustomerQuickRegistrationEntityWithMobileCustomer()
	{
		CustomerQuickRegisterEntity initializedCustomer=customerQuickRegisterHandler
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
		CustomerQuickRegisterEntity initializedCustomer=customerQuickRegisterHandler
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
		CustomerQuickRegisterEntity initializedCustomer=customerQuickRegisterHandler
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
	public void createCustomerEmailVerificationEntityWithEmailMobileCustomer()
	{
		CustomerQuickRegisterEntity savedCustomerQuickRegisterEntity=customerQuickRegisterHandler.saveCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.createCustomerEmailVerificationEntity(savedCustomerQuickRegisterEntity);
		
		assertEquals(standardCustomerEmailVerificationDetails().getCustomerId(), emailVerificationDetails.getCustomerId());
		assertEquals(standardCustomerEmailVerificationDetails().getEmail(), emailVerificationDetails.getEmail());
		assertNotNull(emailVerificationDetails.getEmailHash());
		assertNotNull(emailVerificationDetails.getEmailHashSentTime());
		assertEquals(standardCustomerEmailVerificationDetails().getEmailType(), emailVerificationDetails.getEmailType());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), emailVerificationDetails.getResendCount());
	}
	
	
	@Test
	public void createCustomerMobileVerificationEntityWithEmailMobileCustomer()
	{
		CustomerQuickRegisterEntity savedCustomerQuickRegisterEntity=customerQuickRegisterHandler.saveCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.createCustomerMobileVerificationEntity(savedCustomerQuickRegisterEntity);
		
		assertEquals(standardCustomerMobileVerificationDetails().getCustomerId(), mobileVerificationDetails.getCustomerId());
		assertEquals(standardCustomerMobileVerificationDetails().getMobile(), mobileVerificationDetails.getMobile());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileType(), mobileVerificationDetails.getMobileType());
		assertNotNull(mobileVerificationDetails.getMobilePin());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts(), mobileVerificationDetails.getMobileVerificationAttempts());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), mobileVerificationDetails.getResendCount());
		
	}
	
	
	@Test
	public void saveCustomerQuickRegisterEnityAndGetByCustomerId()
	{
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler.saveCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId());
		
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
	public void saveCustomerEmailVerificationEntityAndGetByCustomerIdAndEmail()
	{
		CustomerEmailVerificationDetails savedEntity=customerQuickRegisterHandler.saveCustomerEmailVerificationDetails(standardCustomerEmailVerificationDetails());
		
		CustomerEmailVerificationDetails fetchedEntity=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(savedEntity.getCustomerId(), savedEntity.getEmail());
		
		assertEquals(savedEntity, fetchedEntity);
	}
	
	@Test
	public void saveCustomerMobileVerificationEntityAndGetByCustomerIdAndEmail()
	{
		CustomerMobileVerificationDetails savedEntity=customerQuickRegisterHandler.saveCustomerMobileVerificationDetails(standardCustomerMobileVerificationDetails());
		
		CustomerMobileVerificationDetails fetchedEntity=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile(savedEntity.getCustomerId(),savedEntity.getMobile());
		
		assertEquals(savedEntity, fetchedEntity);
		
	}
	
	
	@Test
	public void saveAndGetByCustomerIdWithEmailMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerQuickRegisterEntity().getCustomerId());
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardEmailMobileCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardEmailMobileCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		

		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(fetced.getCustomerId(),fetced.getEmail()); 
	
		assertEquals(standardCustomerEmailVerificationDetails().getCustomerId(), emailVerificationDetails.getCustomerId());
		assertEquals(standardCustomerEmailVerificationDetails().getEmail(), emailVerificationDetails.getEmail());
		assertNotNull(emailVerificationDetails.getEmailHash());
		assertNotNull(emailVerificationDetails.getEmailHashSentTime());
		assertEquals(standardCustomerEmailVerificationDetails().getEmailType(), emailVerificationDetails.getEmailType());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), emailVerificationDetails.getResendCount());
		
	
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile(fetced.getCustomerId(),fetced.getMobile());
		
		assertEquals(standardCustomerMobileVerificationDetails().getCustomerId(), mobileVerificationDetails.getCustomerId());
		assertEquals(standardCustomerMobileVerificationDetails().getMobile(), mobileVerificationDetails.getMobile());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileType(), mobileVerificationDetails.getMobileType());
		assertNotNull(mobileVerificationDetails.getMobilePin());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts(), mobileVerificationDetails.getMobileVerificationAttempts());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), mobileVerificationDetails.getResendCount());
	
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(fetced.getCustomerId());
		
		assertEquals(fetced.getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(fetced.getEmail(), authenticationDetails.getEmail());
		assertEquals(fetced.getMobile(), authenticationDetails.getMobile());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		assertEquals(0, authenticationDetails.getLoginVerificationCount().intValue());
		assertEquals(0, authenticationDetails.getResendCount().intValue());
		
		
	}
	
	
	@Test
	public void saveAndGetByCustomerIdWithEmailCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailCustomerAfterInitialization());
		
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerQuickRegisterEntity().getCustomerId());
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardEmailCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardEmailCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardEmailCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		

		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(fetced.getCustomerId(),fetced.getEmail()); 
	
		assertEquals(standardCustomerEmailVerificationDetails().getCustomerId(), emailVerificationDetails.getCustomerId());
		assertEquals(standardCustomerEmailVerificationDetails().getEmail(), emailVerificationDetails.getEmail());
		assertNotNull(emailVerificationDetails.getEmailHash());
		assertNotNull(emailVerificationDetails.getEmailHashSentTime());
		assertEquals(standardCustomerEmailVerificationDetails().getEmailType(), emailVerificationDetails.getEmailType());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), emailVerificationDetails.getResendCount());
	
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(fetced.getCustomerId());
		
		assertEquals(fetced.getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(fetced.getEmail(), authenticationDetails.getEmail());
		assertEquals(fetced.getMobile(), authenticationDetails.getMobile());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		assertEquals(0, authenticationDetails.getLoginVerificationCount().intValue());
		assertEquals(0, authenticationDetails.getResendCount().intValue());
	
		
	}


	
	@Test
	public void saveAndGetByCustomerIdWithMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerQuickRegisterEntity().getCustomerId());
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardMobileCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardMobileCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardMobileCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardMobileCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile(fetced.getCustomerId(),fetced.getMobile());
		
		assertEquals(standardCustomerMobileVerificationDetails().getCustomerId(), mobileVerificationDetails.getCustomerId());
		assertEquals(standardCustomerMobileVerificationDetails().getMobile(), mobileVerificationDetails.getMobile());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileType(), mobileVerificationDetails.getMobileType());
		assertNotNull(mobileVerificationDetails.getMobilePin());
		assertEquals(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts(), mobileVerificationDetails.getMobileVerificationAttempts());
		assertEquals(standardCustomerEmailVerificationDetails().getResendCount(), mobileVerificationDetails.getResendCount());
	
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(fetced.getCustomerId());
		
		assertEquals(fetced.getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(fetced.getEmail(), authenticationDetails.getEmail());
		assertEquals(fetced.getMobile(), authenticationDetails.getMobile());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		assertEquals(0, authenticationDetails.getLoginVerificationCount().intValue());
		assertEquals(0, authenticationDetails.getResendCount().intValue());
	
		
	}


	@Test
	public void getCustomerQuickRegisterEntityByEmailAndMobile() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getCustomerId());
		
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE).getCustomerId());
		
		CustomerQuickRegisterEmailMobileVerificationEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL);
		
		assertEquals(saved.getCustomerQuickRegisterEntity().getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPincode(), fetced.getPincode());
		assertTrue( (fetced.getInsertTime().getTime()-standardEmailMobileCustomerAfterInitialization().getInsertTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getUpdatedBy(), fetced.getUpdatedBy());
		assertTrue( (fetced.getUpdateTime().getTime()-standardEmailMobileCustomerAfterInitialization().getUpdateTime().getTime())<60*1000);
		

		fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE);
		
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
	public void createCustomerAuthenticationDetails()
	{
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.createCustomerAuthenticationDetails(standardEmailMobileCustomer());
		
		assertEquals(standardEmailMobileCustomerAfterSaving().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterSaving().getEmail(), authenticationDetails.getEmail());
		assertEquals(standardEmailMobileCustomerAfterSaving().getMobile(), authenticationDetails.getMobile());
		assertNull(authenticationDetails.getEmailPassword());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertEquals(0,authenticationDetails.getLoginVerificationCount().intValue());
		
		
	}
	
	
	@Test
	public void saveCustomerAuthenticationDetailsAndGetByCustomerId()
	{
		assertNull(customerQuickRegisterHandler.getLoginDetailsByCustomerId(CUST_ID).getCustomerId());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.saveCustomerAuthenticationDetails(standardCustomerEmailAuthenticationDetails());
		
		assertEquals(standardCustomerEmailAuthenticationDetails(),customerQuickRegisterHandler.getLoginDetailsByCustomerId(authenticationDetails.getCustomerId()));
		
	}
	
	@Test
	public void sendVerificationDetailsWithEmailMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),savedEntity.getCustomerMobileVerificationDetails());
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailMobileCustomerAfterSaving(), customerStatusEntity.getCustomer());
	}
	
	
	@Test
	public void sendVerificationDetailsWithEmailCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailCustomerAfterInitialization());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),savedEntity.getCustomerMobileVerificationDetails());
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailCustomerAfterSaving(), customerStatusEntity.getCustomer());
	}
	
	@Test
	public void sendVerificationDetailsWithMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),savedEntity.getCustomerMobileVerificationDetails());
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardMobileCustomerAfterSaving(), customerStatusEntity.getCustomer());
	}
	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailMobileCustomer() throws Exception {

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
						.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		assertEquals(standardEmailMobileCustomerAfterSaving(), handledEntity.getCustomer());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(handledEntity.getCustomer().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(handledEntity.getCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(handledEntity.getCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}

	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailCustomer() throws Exception {

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		assertEquals(standardEmailCustomerAfterSaving(), handledEntity.getCustomer());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(handledEntity.getCustomer().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(handledEntity.getCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(handledEntity.getCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}

	@Test
	public void handleNewCustomerQuickRegistrationWithMobileCustomer() throws Exception {

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		assertEquals(standardMobileCustomerAfterSaving(), handledEntity.getCustomer());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(handledEntity.getCustomer().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(handledEntity.getCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(handledEntity.getCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}

	
	@Test 
	public void verifyEmailWithEmailCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());
	
		CustomerQuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();
		
		
		CustomerEmailVerificationDetails emailVerificationDetail=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail
													(handledEntity.getCustomerId(), handledEntity.getEmail()); 
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
	
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomerId(),handledEntity.getEmail() ,emailVerificationDetail.getEmailHash()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
	
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomerId()).getIsEmailVerified());
		
		
	}
	
	
	
	@Test 
	public void verifyMobileWithMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());
	
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile
									(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getMobile());
		
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile(),mobileVerificationDetails.getMobilePin()));
	
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		
		
	}
		
	 
	 
	@Test 
	public void verifyMobileEmailWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail
																	(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getEmail());
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile
																	(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile());
		
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
	

		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile(),
																mobileVerificationDetails.getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		String oldEmailPassword=authenticationDetails.getEmailPassword();
		
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		assertFalse(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());


		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getEmail(),
																emailVerificationDetails.getEmailHash()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(oldPassword,authenticationDetails.getPassword());
		assertEquals(oldPasswordType,authenticationDetails.getPasswordType());
		assertEquals(oldEmailPassword, authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());

	}

	
			
	@Test 
	public void verifyEmailHashMobilePinWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler.getCustomerEmailVerificationDetailsByCustomerIdAndEmail
																	(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getEmail());
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler.getCustomerMobileVerificationDetailsByCustomerIdAndMobile
																	(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile());
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getEmail(), emailVerificationDetails.getEmailHash()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		String oldEmailPassword=authenticationDetails.getEmailPassword();
		
		assertFalse(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());

		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile(),
																				mobileVerificationDetails.getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(oldPassword,authenticationDetails.getPassword());
		assertEquals(oldPasswordType,authenticationDetails.getPasswordType());
		assertEquals(oldEmailPassword, authenticationDetails.getEmailPassword());
		
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsMobileVerified());
		assertTrue(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getIsEmailVerified());

		
	}
	
	@Test
	public void verifyEmailAndMobileFailedCase()
	{
		assertFalse(customerQuickRegisterHandler.verifyEmailHash(CUST_ID,CUST_EMAIL, CUST_EMAILHASH));
		
		assertFalse(customerQuickRegisterHandler.verifyMobilePin(CUST_ID,CUST_MOBILE, CUST_MOBILEPIN));
	}
	
	
	/*
	@Test
	public void verifyMobilePinWithFailingCase() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile() ,101010));
		
		assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile() ,101010));
		
		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));

		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));
		
		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		assertNull(authenticationDetails.getEmailPassword());
		
		//TODO
		assertTrue(customerQuickRegisterHandler.reSendMobilePin(handledEntity.getCustomer().getCustomerId()));
		
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), fetchedEntity.getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());

	}
	
*/
	

	@Test
	public void verifyLoginDetailsWithMobileVerifiedFirst() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
	
		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler
				.getCustomerMobileVerificationDetailsByCustomerIdAndMobile(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getMobile(),mobileVerificationDetails.getMobilePin()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		assertNotNull(authenticationDetails.getEmailPassword());
	
	
		assertEquals(authenticationDetails, customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(),
																								authenticationDetails.getPassword())));
		
		assertEquals(authenticationDetails, customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(Long.toString(authenticationDetails.getMobile()),
				authenticationDetails.getPassword())));
		
		assertNull( customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(Long.toString(authenticationDetails.getMobile()),
				CUST_PASSWORD_DEFAULT)).getCustomerId());
		
		assertEquals(new Integer(1), customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId()).getLoginVerificationCount());
	
		assertNull(customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(),
				CUST_PASSWORD_CHANGED)).getCustomerId());
		
		assertEquals(new Integer(2), customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId()).getLoginVerificationCount());
		

	}

	
	@Test
	public void verifyLoginDetailsWithEmailVerifiedFirst() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmail());
	
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getEmail(),emailVerificationDetails.getEmailHash()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
	
	
		assertEquals(authenticationDetails, customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(),
																								authenticationDetails.getPassword())));
	}

	
	@Test
	public void saveAndGetVerificationDetailsByCustomerId()
	{
		customerQuickRegisterHandler.saveCustomerAuthenticationDetails(standardCustomerEmailMobileAuthenticationDetails());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(standardEmailMobileCustomer().getCustomerId());
				
		assertEquals(standardEmailMobileCustomer().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(standardEmailMobileCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(standardEmailMobileCustomer().getMobile(), authenticationDetails.getMobile());
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		assertNotNull( authenticationDetails.getEmailPassword());
	}

	@Test
	public void getVerificationDetailsByCustomerIdFailingCase()
	{
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(standardEmailMobileCustomer().getCustomerId());
				
		assertNull( authenticationDetails.getCustomerId());
		assertNull(authenticationDetails.getEmail());
		assertNull( authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());		
	}

	
	
	
	
	@Test
	public void sendDefaultPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmail());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getEmailPassword());
		assertNull( authenticationDetails.getPasswordType());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getEmail(),
				emailVerificationDetails.getEmailHash()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
			
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getEmailPassword());
		assertNotNull( authenticationDetails.getPasswordType());
				
	}

	
	@Test
	public void resetPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmail());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),handledEntity.getCustomer().getEmail(), 
				emailVerificationDetails.getEmailHash()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertTrue(customerQuickRegisterHandler.resetPassword(new CustomerIdDTO(authenticationDetails.getCustomerId())));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotEquals(oldPassword, authenticationDetails.getPassword());
	//	assertNotEquals(oldPasswordType, authenticationDetails.getPasswordType());
		assertEquals(oldPasswordType, authenticationDetails.getPasswordType());
		
	}

	
	
	@Test
	public void updatePassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getEmail(),emailVerificationDetails.getEmailHash()));
		
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler
				.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());

		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertTrue(customerQuickRegisterHandler
				.updatePassword(new UpdatePasswordAndPasswordTypeDTO(authenticationDetails.getCustomerId(), CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED)));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotEquals(oldPassword, authenticationDetails.getPassword());
		assertNotEquals(oldPasswordType, authenticationDetails.getPasswordType());
	
	}
	

	
	@Test
	public void setDefaultPasswordWithEmailMobileCustomerCalledAfterEmailVerifiedAndPasswordChange() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail());

		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getEmail(),emailVerificationDetails.getEmailHash()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());

		CustomerQuickRegisterEntity updatedEntity=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		assertTrue(customerQuickRegisterHandler.updatePassword(new UpdatePasswordAndPasswordTypeDTO(authenticationDetails.getCustomerId(), CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED)));
		
		assertTrue(customerQuickRegisterHandler.sendDefaultPassword(updatedEntity, false));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
	
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
	
		
		assertEquals(oldPassword, authenticationDetails.getPassword());
		assertEquals(oldPasswordType, authenticationDetails.getPasswordType());
		
		
	}
	
	
	@Test
	public void UpdateEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail());
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		Date oldEmailHashSentTime=emailVerificationDetails.getEmailHashSentTime();
		
		assertEquals(1, customerQuickRegisterHandler.updateEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getEmail()).intValue());
		
		emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail());
		
		
		
		assertNotEquals(oldEmailHash,emailVerificationDetails.getEmailHash());
		assertNotEquals(oldEmailHashSentTime, emailVerificationDetails.getEmailHashSentTime());
		
	}
	
	
	
	@Test
	public void UpdateMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());

		CustomerMobileVerificationDetails mobileVerificationDetails=customerQuickRegisterHandler
				.getCustomerMobileVerificationDetailsByCustomerIdAndMobile(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getMobile());
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
				
		assertEquals(1, customerQuickRegisterHandler.updateMobilePin(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getMobile()).intValue());
		
		mobileVerificationDetails=customerQuickRegisterHandler
				.getCustomerMobileVerificationDetailsByCustomerIdAndMobile(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getMobile());
		
		
		
		assertNotEquals(oldMobilePin,mobileVerificationDetails.getMobilePin());
				
	}
	
	
	
	@Test
	public void reSetEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail());
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertEquals(true, customerQuickRegisterHandler.reSendEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getEmail()));
		
		assertNotEquals(oldEmailHash,emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail()));
	}
	
	
	
	@Test
	public void reSetMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdAndMobile(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getMobile());
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(true, customerQuickRegisterHandler.reSendMobilePin(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getMobile()));
		
		assertNotEquals(oldMobilePin,
				customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdAndMobile(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getMobile()));

	}
	

	@Test
	public void reSendEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		CustomerEmailVerificationDetails emailVerificationDetails=customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail());
		
		String oldEmailHash=emailVerificationDetails.getEmailHash();
		
		assertEquals(true, customerQuickRegisterHandler.reSendEmailHash(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getEmail()));
		
		assertEquals(oldEmailHash,customerQuickRegisterHandler
				.getCustomerEmailVerificationDetailsByCustomerIdAndEmail(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getEmail()).getEmailHash());
	}
	
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		CustomerMobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdAndMobile(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getMobile());
		
		Integer oldMobilePin=mobileVerificationDetails.getMobilePin();
		
		assertEquals(true, customerQuickRegisterHandler.reSendMobilePin(handledEntity.getCustomer().getCustomerId(),
				handledEntity.getCustomer().getMobile()));
		
		assertEquals(oldMobilePin,
				customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdAndMobile(handledEntity.getCustomer().getCustomerId(),
						handledEntity.getCustomer().getMobile()).getMobilePin());

	}
	
	
	@Test
	public void resetPasswordByEmailOrMobile() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		//TODO
	
	}
	
}
