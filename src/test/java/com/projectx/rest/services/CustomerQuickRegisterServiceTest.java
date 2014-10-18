package com.projectx.rest.services;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerQuickDetailsSentStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class CustomerQuickRegisterServiceTest {

	@Autowired
	public CustomerQuickRegisterService customerQuickRegisterHandler;

	@Autowired
	public CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@After
	public void cleanAllRecords()
	{
		customerQuickRegisterRepository.clearCustomerQuickRegister();
	}

	
	@Test
	public void checkIfEmailCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()));

		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailCustomer());

		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()));

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()));

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()));
	}

	
	@Test
	public void checkIfMobileCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()));

		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardMobileCustomer());

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()));

		
		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()));

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()));
	}

	
	@Test
	public void checkIfEmailMobileCustomerExist() throws Exception {

		assertEquals(REGISTER_NOT_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()));

		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer());

		assertEquals(REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()));

		
		assertEquals(REGISTER_EMAIL_ALREADY_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()));

		assertEquals(REGISTER_MOBILE_ALREADY_REGISTERED,customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()));
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
	public void sendVerificationDetailsWithEmailMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomerAfterInitialization());
		
		CustomerQuickDetailsSentStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity);
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailMobileCustomerAfterVerificatinDetailsSent(), customerStatusEntity.getCustomer());
	}
	
	
	@Test
	public void sendVerificationDetailsWithEmailCustomer() throws Exception
	{
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardEmailCustomerAfterVerificatinDetailsSent());
		
		CustomerQuickDetailsSentStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity);
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardEmailCustomerAfterVerificatinDetailsSent(), customerStatusEntity.getCustomer());
	}
	
	
	@Test
	public void sendVerificationDetailsWithMobileCustomer() throws Exception
	{
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler.saveNewCustomerQuickRegisterEntity(standardMobileCustomerAfterVerificatinDetailsSent());
		
		CustomerQuickDetailsSentStatusEntity customerStatusEntity=customerQuickRegisterHandler.sendVerificationDetails(savedEntity);
		
		assertTrue(customerStatusEntity.getStatus());
		
		assertEquals(standardMobileCustomerAfterVerificatinDetailsSent(), customerStatusEntity.getCustomer());
	}
	

	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailMobileCustomer() throws Exception {

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
						.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		assertEquals(standardEmailMobileCustomerAfterVerificatinDetailsSent(), handledEntity.getCustomer());
	}

	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailCustomer() throws Exception {

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		assertEquals(standardEmailCustomerAfterVerificatinDetailsSent(), handledEntity.getCustomer());
	}

	@Test
	public void handleNewCustomerQuickRegistrationWithMobileCustomer() throws Exception {

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		assertTrue(handledEntity.getStatus());
		assertEquals(standardMobileCustomerAfterVerificatinDetailsSent(), handledEntity.getCustomer());
	}


		

	
	@Test 
	public void verifyEmailHashMobilePinWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		assertEquals(STATUS_EMAIL_VERFIED_MOBILE_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),  handledEntity.getCustomer().getMobilePin()));
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
	}
	
	
	@Test 
	public void verifyMobileEmailWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO());

		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(),  handledEntity.getCustomer().getMobilePin()));
		
		assertEquals(STATUS_MOBILE_VERFIED_EMAIL_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());

		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		

	}
	
	
	@Test 
	public void verifyMobileWithMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getMobilePin()));
		
		assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
	}

	
	
	@Test 
	public void verifyEmailWithEmailCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(handledEntity.getCustomer().getCustomerId(), handledEntity.getCustomer().getEmailHash()));
		
		assertEquals(STATUS_EMAIL_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(handledEntity.getCustomer().getCustomerId()).getStatus());
		
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

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
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

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
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

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
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

		CustomerQuickDetailsSentStatusEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO());
		
		CustomerQuickRegisterEntity entity=handledEntity.getCustomer().createCopy();

		assertEquals(true, customerQuickRegisterHandler.reSendMobilePin(entity.getCustomerId()));
		
		assertNotEquals(entity.getMobilePin(),
				customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(entity.getCustomerId()).getMobilePin());

	}
	
	
	
}
