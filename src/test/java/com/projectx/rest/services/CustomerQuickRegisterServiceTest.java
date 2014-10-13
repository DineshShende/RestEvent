package com.projectx.rest.services;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Test")
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

/*	
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
				standardEmailCustomerDTO(),
				(customerQuickRegisterHandler
						.populateStatus(standardEmailCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusEmailMobileCustomer() throws Exception {
		assertEquals(
				standardEmailMobileCustomerDTO(),
				(customerQuickRegisterHandler
						.populateStatus(standardEmailMobileCustomerDTOWithOutStatus())));

	}

	@Test
	public void populateStatusMobileCustomer() throws Exception {
		assertEquals(
				standardMobileCustomerDTO(),
				(customerQuickRegisterHandler
						.populateStatus(standardMobileCustomerDTOWithOutStatus())));

	}
	
	@Test
	public void handleNewCustomerQuickRegistrationWithEmailMobileCustomer() {

		CustomerQuickRegisterEntity handledEntity=customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO());
		
		assertNull(handledEntity.getCustomerId());
		assertEquals(CUST_FIRSTNAME,handledEntity.getFirstName());
		assertEquals(CUST_LASTNAME,handledEntity.getLastName());
		assertEquals(CUST_EMAIL,handledEntity.getEmail());
		assertEquals(CUST_MOBILE,handledEntity.getMobile());
		assertEquals(CUST_PIN,handledEntity.getPin());
		assertEquals(CUST_STATUS_EMAILMOBILE, handledEntity.getStatus());
		assertEquals(CUST_EMAILHASH,handledEntity.getEmailHash());
		assertEquals(CUST_MOBILEPIN,handledEntity.getMobilePin());
		
	}

	@Test
	public void handleNewCustomerQuickRegistrationWithEmailCustomer() {

		CustomerQuickRegisterEntity handledEntity=customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardEmailCustomerDTO());
		
		assertNull(handledEntity.getCustomerId());
		assertEquals(CUST_FIRSTNAME,handledEntity.getFirstName());
		assertEquals(CUST_LASTNAME,handledEntity.getLastName());
		assertEquals(CUST_EMAIL,handledEntity.getEmail());
		assertNull(handledEntity.getMobile());
		assertEquals(CUST_PIN,handledEntity.getPin());
		assertEquals(CUST_STATUS_EMAIL, handledEntity.getStatus());
		assertEquals(CUST_EMAILHASH,handledEntity.getEmailHash());
		assertNull(handledEntity.getMobilePin());
		
	}

	@Test
	public void handleNewCustomerQuickRegistrationWithMobileCustomer() {

		CustomerQuickRegisterEntity handledEntity=customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardMobileCustomerDTO());
		
		assertNull(handledEntity.getCustomerId());
		assertEquals(CUST_FIRSTNAME,handledEntity.getFirstName());
		assertEquals(CUST_LASTNAME,handledEntity.getLastName());
		assertNull(handledEntity.getEmail());
		assertEquals(CUST_MOBILE,handledEntity.getMobile());
		assertEquals(CUST_PIN,handledEntity.getPin());
		assertEquals(CUST_STATUS_MOBILE, handledEntity.getStatus());
		assertNull(handledEntity.getEmailHash());
		assertEquals(CUST_MOBILEPIN,handledEntity.getMobilePin());
		
	}

		
	@Test 
	public void getCustomerByCustomerIdWithEmailMobileCustomer() throws Exception
	{
		
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());
		

		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler
													.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
																.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));
		
		CustomerQuickRegisterEntity handledEntity=customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId());
		
		assertEquals(CUST_FIRSTNAME,handledEntity.getFirstName());
		assertEquals(CUST_LASTNAME,handledEntity.getLastName());
		assertEquals(CUST_EMAIL,handledEntity.getEmail());
		assertEquals(CUST_MOBILE,handledEntity.getMobile());
		assertEquals(CUST_PIN,handledEntity.getPin());
		assertEquals(CUST_STATUS_EMAILMOBILE, handledEntity.getStatus());
		assertEquals(CUST_EMAILHASH,handledEntity.getEmailHash());
		assertEquals(CUST_MOBILEPIN,handledEntity.getMobilePin());
		
	}
	

	
	@Test 
	public void verifyEmailHashMobilePinWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler
													.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
																.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH));
		
		assertEquals(STATUS_EMAIL_VERFIED_MOBILE_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN));
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId()).getStatus());
		
	}
	
	
	@Test 
	public void verifyMobileEmailWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler
													.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
																.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN));
		
		assertEquals(STATUS_MOBILE_VERFIED_EMAIL_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId()).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH));
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId()).getStatus());
		
	}
	
	
	@Test 
	public void verifyMobileWithMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler
													.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
																.handleNewCustomerQuickRegistration(standardMobileCustomerDTO()));
		
		assertTrue(customerQuickRegisterHandler.verifyMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN));
		
		assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId()).getStatus());
		
	}

	
	@Test 
	public void verifyEmailWithEmailCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(CUST_ID).getCustomerId());

		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
							.handleNewCustomerQuickRegistration(standardEmailCustomerDTO()));
		
		assertTrue(customerQuickRegisterHandler.verifyEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH));
		
		assertEquals(STATUS_EMAIL_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByCustomerId(savedEntity.getCustomerId()).getStatus());
		
	}
	
	@Test
	public void verifyEmailAndMobileFailedCase()
	{
		assertFalse(customerQuickRegisterHandler.verifyEmailHash(CUST_ID, CUST_EMAILHASH));
		
		assertFalse(customerQuickRegisterHandler.verifyMobilePin(CUST_ID, CUST_MOBILEPIN));
	}
	
	//public void UpdateEmailHash()
	
	//public void UpdateMobilePin()
	
	
	@Test
	public void generateMobilePin()
	{
		customerQuickRegisterHandler.genarateMobilePin(standardEmailCustomerDTO());
	}
	

	@Test
	public void generateEmailHash()
	{
		customerQuickRegisterHandler.generateEmailHash(standardEmailCustomerDTO());
	}
	*/
	@Test
	public void composeEmail()
	{
		customerQuickRegisterHandler.composeEmail(standardEmailCustomer());
	}
	
}
