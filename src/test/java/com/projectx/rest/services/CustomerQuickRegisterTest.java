package com.projectx.rest.services;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardEmailCustomer;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardEmailCustomerDTO;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardEmailCustomerDTOWithOutStatus;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardEmailMobileCustomerDTO;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardEmailMobileCustomerDTOWithOutStatus;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardEmailMobileCustomerKey;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardMobileCustomer;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardMobileCustomerDTO;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardMobileCustomerDTOWithOutStatus;
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
		//customerQuickRegisterRepository.clearCustomerQuickRegister();
	}

	@Test
	public void checkIfEmailCustomerExist() {

		assertFalse(customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()));

		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailCustomer());

		assertTrue(customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailCustomerDTO()));

	}

	@Test
	public void checkIfMobileCustomerExist() {

		assertFalse(customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()));

		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardMobileCustomer());

		assertTrue(customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardMobileCustomerDTO()));

	}

	@Test
	public void checkIfEmailMobileCustomerExist() {

		assertFalse(customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()));

		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer());

		assertTrue(customerQuickRegisterHandler
				.checkIfAlreadyRegistered(standardEmailMobileCustomerDTO()));

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
	public void getCustomerByKey() {
		
		//assertNull(customerQuickRegisterHandler.getCustomerQuickRegisterEntityByKey(standardEmailMobileCustomerKey()));

		
		customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(customerQuickRegisterHandler
						.handleNewCustomerQuickRegistration(standardEmailMobileCustomerDTO()));
		
		assertEquals(standardEmailMobileCustomer(),customerQuickRegisterHandler.getCustomerQuickRegisterEntityByKey(standardEmailMobileCustomerKey()));

				
	}
}
