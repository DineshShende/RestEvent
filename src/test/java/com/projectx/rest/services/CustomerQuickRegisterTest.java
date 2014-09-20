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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

public class CustomerQuickRegisterTest {

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
	public void handleNewCustomerQuickRegistrationWithEmail() {


		assertEquals(
				standardEmailCustomer(),
				customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardEmailCustomerDTO()));
	}

	@Test
	public void handleNewCustomerQuickRegistrationWithMobile() {

		assertEquals(
				standardMobileCustomer(),
				customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardMobileCustomerDTO()));
	}

	@Test
	public void handleNewCustomerQuickRegistrationWithEmailMobile() {
		assertEquals(
				standardEmailMobileCustomer(),
				customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));

	}

	
	@Test
	public void getCustomerByEmailMobileWithEmailMobileCustomer() throws Exception {
		
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
		
		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));
		
		assertEquals(standardEmailMobileCustomer(),customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertEquals(standardEmailMobileCustomer(),customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
				
	}

	@Test
	public void getCustomerByEmailMobileWithEmailCustomer() throws Exception {
		
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
		
		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardEmailCustomerDTO()));
		
		assertEquals(standardEmailCustomer(),customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
				
	}
	
	@Test
	public void getCustomerByEmailMobileWithMobileCustomer() throws Exception {
		
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
		
		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardMobileCustomerDTO()));
		
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertEquals(standardMobileCustomer(),customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
				
	}
	
	@Test 
	public void verifyEmailMobileWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
		
		customerQuickRegisterHandler
		.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
				.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));
		
		

		assertTrue(customerQuickRegisterHandler.verifyEmail(CUST_EMAIL, CUST_EMAILHASH));
		
		assertEquals(STATUS_EMAIL_VERFIED_MOBILE_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getStatus());
		
		customerQuickRegisterHandler.verifyMobile(CUST_MOBILE, CUST_MOBILEPIN);
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getStatus());
		
	}
	
	@Test 
	public void verifyMobileEmailWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
		
		customerQuickRegisterHandler
		.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
				.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));
		
		assertTrue(customerQuickRegisterHandler.verifyMobile(CUST_MOBILE, CUST_MOBILEPIN));
		
		assertEquals(STATUS_MOBILE_VERFIED_EMAIL_PENDING,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getStatus());
		
		assertTrue(customerQuickRegisterHandler.verifyEmail(CUST_EMAIL, CUST_EMAILHASH));
		
		assertEquals(STATUS_EMAIL_MOBILE_VERIFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getStatus());
		
	}
	
	@Test 
	public void verifyMobileWithMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
		
		customerQuickRegisterHandler
		.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
				.handleNewCustomerQuickRegistration(standardMobileCustomerDTO()));
		
		assertTrue(customerQuickRegisterHandler.verifyMobile(CUST_MOBILE, CUST_MOBILEPIN));
		
		assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE).getStatus());
		
	}

	@Test 
	public void verifyEmailWithEmailCustomer() throws Exception
	{
		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL));

		assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByMobile(CUST_MOBILE));
		
		customerQuickRegisterHandler
		.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
				.handleNewCustomerQuickRegistration(standardEmailCustomerDTO()));
		
		assertTrue(customerQuickRegisterHandler.verifyEmail(CUST_EMAIL, CUST_EMAILHASH));
		
		assertEquals(STATUS_EMAIL_VERFIED,customerQuickRegisterHandler.getCustomerQuickRegisterEntityByEmail(CUST_EMAIL).getStatus());
		
	}
	
	@Test
	public void verifyEmailAndMobileFailedCase()
	{
		assertFalse(customerQuickRegisterHandler.verifyEmail(CUST_EMAIL, CUST_EMAILHASH));
		
		assertFalse(customerQuickRegisterHandler.verifyMobile(CUST_MOBILE, CUST_MOBILEPIN));
	}
	
}
