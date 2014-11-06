package com.projectx.rest.services;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.CustomerAuthenticationDetailsDataFixtures.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.domain.LoginVerificationDTO;
import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.web.domain.CustomerIdDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class CustomerQuickRegisterServiceTest {

	@Autowired
	public CustomerQuickRegisterService customerQuickRegisterHandler;

	@Autowired
	public CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	public CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	@Before
	public void cleanAllRecords()
	{
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		customerAuthenticationDetailsRepository.clearLoginDetailsForTesting();
	}

	
	@Test
	public void checkIfEmailCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());
		
		
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();

		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(fetchedEntity.getCustomerId(), fetchedEntity.getEmailHash()));


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
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(fetchedEntity.getCustomerId(), fetchedEntity.getEmailHash()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED ,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(fetchedEntity.getCustomerId(), fetchedEntity.getMobilePin()));
		
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
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()).getStatus());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(fetchedEntity.getCustomerId(), fetchedEntity.getMobilePin()));
		
		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED ,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(fetchedEntity.getCustomerId(), fetchedEntity.getEmailHash()));
		
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
						.populateStatus(standardEmailCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusEmailMobileCustomerWithStatus() throws Exception {
		assertEquals(
				standardEmailMobileCustomerAfterStatusPopulation(),
				(customerQuickRegisterHandler
						.populateStatus(standardEmailMobileCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusMobileCustomer() throws Exception {
		assertEquals(
				standardMobileCustomerAfterStatusPopulation(),
				(customerQuickRegisterHandler
						.populateStatus(standardMobileCustomerDTOWithOutStatus())));

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
		assertEquals(standardMobileCustomerAfterInitialization().getPin(), initializedCustomer.getPin());
		assertEquals(standardMobileCustomerAfterInitialization().getStatus(), initializedCustomer.getStatus());
		assertNotNull(initializedCustomer.getMobilePin());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmailHashSentTime(),initializedCustomer.getEmailHashSentTime());
		assertNull(standardMobileCustomerAfterInitialization().getEmailHashSentTime());
		assertEquals(standardMobileCustomerAfterInitialization().getMobilePinSentTime(), initializedCustomer.getMobilePinSentTime());
		assertEquals(standardMobileCustomerAfterInitialization().getMobileVerificationAttempts(), initializedCustomer.getMobileVerificationAttempts());
		assertTrue( (initializedCustomer.getLastStatusChangedTime().getTime()-standardMobileCustomerAfterInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardMobileCustomerAfterInitialization().getPassword(), initializedCustomer.getPassword());
		assertEquals(standardMobileCustomerAfterInitialization().getPasswordType(), initializedCustomer.getPasswordType());
		
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
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPin(), initializedCustomer.getPin());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getStatus(), initializedCustomer.getStatus());
		assertNotNull(initializedCustomer.getMobilePin());
		assertNotNull(initializedCustomer.getEmailHash());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmailHashSentTime(),initializedCustomer.getEmailHashSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobilePinSentTime(), initializedCustomer.getMobilePinSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobileVerificationAttempts(), initializedCustomer.getMobileVerificationAttempts());
		assertTrue( (initializedCustomer.getLastStatusChangedTime().getTime()-standardEmailMobileCustomerAfterInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPassword(), initializedCustomer.getPassword());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPasswordType(), initializedCustomer.getPasswordType());
		
	}
	
	
	@Test
	public void initializeNewCustomerQuickRegistrationEntityWithEmailCustomer()
	{
		CustomerQuickRegisterEntity initializedCustomer=customerQuickRegisterHandler
						.initializeNewCustomerQuickRegistrationEntity(standardEmailCustomerAfterStatusPopulation());
		
		assertNull(initializedCustomer.getCustomerId());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getFirstName(), initializedCustomer.getFirstName());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getLastName(), initializedCustomer.getLastName());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmail(), initializedCustomer.getEmail());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getMobile(), initializedCustomer.getMobile());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getPin(), initializedCustomer.getPin());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getStatus(), initializedCustomer.getStatus());
		assertNull(initializedCustomer.getMobilePin());
		assertNotNull(initializedCustomer.getEmailHash());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmailHashSentTime(),initializedCustomer.getEmailHashSentTime());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getMobilePinSentTime(), initializedCustomer.getMobilePinSentTime());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getMobileVerificationAttempts(), initializedCustomer.getMobileVerificationAttempts());
		assertTrue( (initializedCustomer.getLastStatusChangedTime().getTime()-standardEmailCustomerAfterStatusInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardEmailCustomerAfterStatusInitialization().getPassword(), initializedCustomer.getPassword());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getPasswordType(), initializedCustomer.getPasswordType());
		
	}
	
	
	
	@Test
	public void saveAndGetByCustomerIdWithEmailMobileCustomer() throws Exception
	{
		//System.out.println("Input"+standardEmailMobileCustomerAfterInitialization());
		CustomerQuickRegisterEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		//System.out.println(saved);
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerId());
		
		//System.out.println(fetced);

		assertEquals(saved.getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPin(), fetced.getPin());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getStatus(), fetced.getStatus());
		assertNotNull(fetced.getMobilePin());
		assertNotNull(fetced.getEmailHash());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmailHashSentTime(),fetced.getEmailHashSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobilePinSentTime(), fetced.getMobilePinSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobileVerificationAttempts(), fetced.getMobileVerificationAttempts());
		assertTrue( (fetced.getLastStatusChangedTime().getTime()-standardEmailMobileCustomerAfterInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPassword(), fetced.getPassword());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPasswordType(), fetced.getPasswordType());
		
	
	}
	
	@Test
	public void saveAndGetByCustomerIdWithEmailCustomer() throws Exception
	{
		CustomerQuickRegisterEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailCustomerAfterStatusInitialization());
		
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerId());
		
		

		assertEquals(saved.getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getPin(), fetced.getPin());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getStatus(), fetced.getStatus());
		assertNull(fetced.getMobilePin());
		assertNotNull(fetced.getEmailHash());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmailHashSentTime(),fetced.getEmailHashSentTime());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getMobilePinSentTime(), fetced.getMobilePinSentTime());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getMobileVerificationAttempts(), fetced.getMobileVerificationAttempts());
		assertTrue( (fetced.getLastStatusChangedTime().getTime()-standardEmailCustomerAfterStatusInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardEmailCustomerAfterStatusInitialization().getPassword(), fetced.getPassword());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getPasswordType(), fetced.getPasswordType());
		
	
	}


	@Test
	public void saveAndGetByCustomerIdWithMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(saved.getCustomerId());
		
		

		assertEquals(saved.getCustomerId(),fetced.getCustomerId());
		assertEquals(standardMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardMobileCustomerAfterInitialization().getPin(), fetced.getPin());
		assertEquals(standardMobileCustomerAfterInitialization().getStatus(), fetced.getStatus());
		assertNotNull(fetced.getMobilePin());
		assertNull(fetced.getEmailHash());
		assertEquals(standardMobileCustomerAfterInitialization().getEmailHashSentTime(),fetced.getEmailHashSentTime());
		assertEquals(standardMobileCustomerAfterInitialization().getMobilePinSentTime(), fetced.getMobilePinSentTime());
		assertEquals(standardMobileCustomerAfterInitialization().getMobileVerificationAttempts(), fetced.getMobileVerificationAttempts());
		assertTrue( (fetced.getLastStatusChangedTime().getTime()-standardMobileCustomerAfterInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardMobileCustomerAfterInitialization().getPassword(), fetced.getPassword());
		assertEquals(standardMobileCustomerAfterInitialization().getPasswordType(), fetced.getPasswordType());
		
	
	}

	@Test
	public void getCustomerQuickRegisterEntityByEmailAndMobile() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getCustomerId());
		
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE).getCustomerId());
		
		CustomerQuickRegisterEntity saved=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		
		CustomerQuickRegisterEntity fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL);
		
		assertEquals(saved.getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPin(), fetced.getPin());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getStatus(), fetced.getStatus());
		assertNotNull(fetced.getMobilePin());
		assertNotNull(fetced.getEmailHash());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmailHashSentTime(),fetced.getEmailHashSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobilePinSentTime(), fetced.getMobilePinSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobileVerificationAttempts(), fetced.getMobileVerificationAttempts());
		assertTrue( (fetced.getLastStatusChangedTime().getTime()-standardEmailMobileCustomerAfterInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPassword(), fetced.getPassword());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPasswordType(), fetced.getPasswordType());
		

		fetced=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE);
		
		assertEquals(saved.getCustomerId(),fetced.getCustomerId());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getFirstName(), fetced.getFirstName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getLastName(), fetced.getLastName());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getEmail(), fetced.getEmail());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobile(), fetced.getMobile());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPin(), fetced.getPin());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getStatus(), fetced.getStatus());
		assertNotNull(fetced.getMobilePin());
		assertNotNull(fetced.getEmailHash());
		assertEquals(standardEmailCustomerAfterStatusInitialization().getEmailHashSentTime(),fetced.getEmailHashSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobilePinSentTime(), fetced.getMobilePinSentTime());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getMobileVerificationAttempts(), fetced.getMobileVerificationAttempts());
		assertTrue( (fetced.getLastStatusChangedTime().getTime()-standardEmailMobileCustomerAfterInitialization().getLastStatusChangedTime().getTime())<60*1000);
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPassword(), fetced.getPassword());
		assertEquals(standardEmailMobileCustomerAfterInitialization().getPasswordType(), fetced.getPasswordType());
		
		
	}
	
	
	@Test
	public void getVerificationDetailsByCustomerId()
	{
		customerQuickRegisterHandler.saveCustomerAuthenticationDetails(standardEmailMobileCustomer());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(standardEmailMobileCustomer().getCustomerId());
				
		assertEquals(standardEmailMobileCustomer().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(standardEmailMobileCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(standardEmailMobileCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());		
	}

	@Test
	public void getVerificationDetailsByCustomerIdFailingCase()
	{
		//customerQuickRegisterHandler.saveCustomerAuthenticationDetails(standardEmailMobileCustomer());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(standardEmailMobileCustomer().getCustomerId());
				
		assertNull( authenticationDetails.getCustomerId());
		assertNull(authenticationDetails.getEmail());
		assertNull( authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());		
	}

	
	@Test
	public void saveVerificationDetails()
	{
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.saveCustomerAuthenticationDetails(standardEmailMobileCustomer());
		
		assertEquals(standardEmailMobileCustomer().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(standardEmailMobileCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(standardEmailMobileCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}
	
	@Test
	public void sendDefaultPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
			
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
				
	}
	
	
	
	@Test
	public void resetPassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertTrue(customerQuickRegisterHandler.resetPassword(new CustomerIdDTO(authenticationDetails.getCustomerId())));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotEquals(oldPassword, authenticationDetails.getPassword());
	//	assertNotEquals(oldPasswordType, authenticationDetails.getPasswordType());
		
	}

	
	@Test
	public void updatePassword() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
//		/*
//		assertNull( authenticationDetails.getPassword());
//		assertNull( authenticationDetails.getPasswordType());
//	
//		CustomerQuickRegisterEntity updatedEntity=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId());
//		
//		assertTrue(customerQuickRegisterHandler.sendDefaultPassword(updatedEntity, false));
//		
//		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
//		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertTrue(customerQuickRegisterHandler.updatePassword(new UpdatePasswordAndPasswordTypeDTO(authenticationDetails.getCustomerId(), CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED)));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotEquals(oldPassword, authenticationDetails.getPassword());
		assertNotEquals(oldPasswordType, authenticationDetails.getPasswordType());
	
	}
	

	
	@Test
	public void setDefaultPasswordWithEmailMobileCustomerCalledAfterEmailVerifiedAndPasswordChange() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
	
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
///*		
//		assertNull( authenticationDetails.getPassword());
//		assertNull( authenticationDetails.getPasswordType());
//	
//		CustomerQuickRegisterEntity updatedEntity=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId());
//		
//		assertTrue(customerQuickRegisterHandler.sendDefaultPassword(updatedEntity, false));
//		
//		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
//		

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
	public void verifyLoginDetailsWithMobileVerifiedFirst() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
	
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getMobilePin()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
	
	
		assertEquals(authenticationDetails, customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(), null,
																								authenticationDetails.getPassword())));
		
		assertEquals(authenticationDetails, customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(null, authenticationDetails.getMobile(),
				authenticationDetails.getPassword())));
		
		assertNull( customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(null, authenticationDetails.getMobile(),
				CUST_PASSWORD_DEFAULT)).getCustomerId());
	
		assertNull(customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(), null,
				CUST_PASSWORD_CHANGED)).getCustomerId());

	}

	
	@Test
	public void verifyLoginDetailsWithEmailVerifiedFirst() throws Exception
	{
		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
	
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull( authenticationDetails.getPassword());
		assertNotNull( authenticationDetails.getPasswordType());
	
	
		assertEquals(authenticationDetails, customerQuickRegisterHandler.verifyLoginDetails(new LoginVerificationDTO(authenticationDetails.getEmail(), null,
																								authenticationDetails.getPassword())));
	}
	
	
	@Test
	public void sendVerificationDetailsWithEmailMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity);
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailMobileCustomerAfterVerificatinDetailsSent(), customerStatusEntity.getCustomer());
	}
	
	
	@Test
	public void sendVerificationDetailsWithEmailCustomer() throws Exception
	{
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailCustomerAfterVerificatinDetailsSent());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity);
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailCustomerAfterVerificatinDetailsSent(), customerStatusEntity.getCustomer());
	}
	
	
	@Test
	public void sendVerificationDetailsWithMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardMobileCustomerAfterVerificatinDetailsSent());
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity);
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardMobileCustomerAfterVerificatinDetailsSent(), customerStatusEntity.getCustomer());
	}
	
	
	
	
	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailMobileCustomer() throws Exception {

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
						.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		assertEquals(standardEmailMobileCustomerAfterVerificatinDetailsSent(), handledEntity.getCustomer());
		
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
		assertEquals(standardEmailCustomerAfterVerificatinDetailsSent(), handledEntity.getCustomer());
		
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
		assertEquals(standardMobileCustomerAfterVerificatinDetailsSent(), handledEntity.getCustomer());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(handledEntity.getCustomer().getCustomerId(), authenticationDetails.getCustomerId());
		assertEquals(handledEntity.getCustomer().getEmail(), authenticationDetails.getEmail());
		assertEquals(handledEntity.getCustomer().getMobile(), authenticationDetails.getMobile());
		assertNull( authenticationDetails.getPassword());
		assertNull( authenticationDetails.getPasswordType());
	}


		
	@Test 
	public void verifyEmailHashMobilePinWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertEquals(STATUS_EMAIL_VERFIED_MOBILE_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),  handledEntity.getCustomer().getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(oldPassword,authenticationDetails.getPassword());
		assertEquals(oldPasswordType,authenticationDetails.getPasswordType());
		
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
	}
	
	
	@Test 
	public void verifyMobileEmailWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
	

		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),  handledEntity.getCustomer().getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());
		
		String oldPassword=authenticationDetails.getPassword();
		String oldPasswordType=authenticationDetails.getPasswordType();
		
		assertEquals(STATUS_MOBILE_VERFIED_EMAIL_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());

		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertEquals(oldPassword,authenticationDetails.getPassword());
		assertEquals(oldPasswordType,authenticationDetails.getPasswordType());
		
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		

	}

	
		
	@Test 
	public void verifyMobileWithMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
	
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getMobilePin()));

		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());

		
		assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
	}



	
	@Test 
	public void verifyEmailWithEmailCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
	
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());

		
		assertEquals(STATUS_EMAIL_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
	}

	
	
	@Test
	public void verifyMobilePinWithFailingCase() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		CustomerAuthenticationDetails authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());
	
		
		assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));
		
		assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));
		
		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));

		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), 101010));
		
		//assertFalse(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNull(authenticationDetails.getPassword());
		assertNull(authenticationDetails.getPasswordType());

		
		assertTrue(customerQuickRegisterHandler.reSendMobilePin(handledEntity.getCustomer().getCustomerId()));
		
		CustomerQuickRegisterEntity fetchedEntity=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), fetchedEntity.getMobilePin()));
		
		authenticationDetails=customerQuickRegisterHandler.getLoginDetailsByCustomerId(handledEntity.getCustomer().getCustomerId());
		
		assertNotNull(authenticationDetails.getPassword());
		assertNotNull(authenticationDetails.getPasswordType());

	}
	
	
	
	
	
	@Test
	public void verifyEmailAndMobileFailedCase()
	{
		assertFalse(customerQuickRegisterHandler.verifyEmailHash(CUST_ID, CUST_EMAILHASH));
		
		assertFalse(customerQuickRegisterHandler.verifyMobilePin(CUST_ID, CUST_MOBILEPIN));
	}
	
	@Test
	public void UpdateEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		assertEquals(handledEntity.getCustomer().getEmailHash(),
						customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId() ).getEmailHash());
		
		CustomerQuickRegisterEntity entity=handledEntity.getCustomer().createCopy();
		
		assertEquals(1, customerQuickRegisterHandler.updateEmailHash(handledEntity.getCustomer().getCustomerId()).intValue());
		
		assertNotEquals(entity.getEmailHash(),
						customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId() ).getEmailHash());
		
	}
	
	@Test
	public void UpdateMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		assertEquals(handledEntity.getCustomer().getMobilePin(),
						customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId() ).getMobilePin());
		
		CustomerQuickRegisterEntity entity=handledEntity.getCustomer().createCopy();
		
		assertEquals(1, customerQuickRegisterHandler.updateMobilePin(handledEntity.getCustomer().getCustomerId()).intValue());
		
		assertNotEquals(entity.getMobilePin(),
						customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId() ).getMobilePin());
		
	}
	
		
	@Test
	public void reSendEmailHash() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());

		CustomerQuickRegisterEntity entity=handledEntity.getCustomer().createCopy();
		
		assertEquals(true, customerQuickRegisterHandler.reSendEmailHash(entity.getCustomerId()));
		
		assertNotEquals(entity.getEmailHash(),
				customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(entity.getCustomerId() ).getEmailHash());
	}
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		CustomerQuickRegisterEntity entity=handledEntity.getCustomer().createCopy();

		assertEquals(true, customerQuickRegisterHandler.reSendMobilePin(entity.getCustomerId()));
		
		assertNotEquals(entity.getMobilePin(),
				customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(entity.getCustomerId()).getMobilePin());

	}
	
}
