package com.projectx.rest.repository;


import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projectx.rest.config.Application;

@ActiveProfiles("Test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class CustomerQuickRegisterRepositoryTest {

	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterMemRepository;
	
	@Before
	public void clearExistingRecords()
	{
		customerQuickRegisterMemRepository.clearCustomerQuickRegister();
	}
	
	@Test
	public void countByEmailMobileNewEmailMobileCustomer() throws Exception {
		
		
		assertEquals(0, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(1, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		
	}

	@Test
	public void countByEmailMobileNewEmailCustomer() throws Exception {
		
		
		assertEquals(0, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardEmailCustomer());
		
		assertEquals(1, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		
	}

	
	@Test
	public void countByEmailMobileNewMobileCustomer() throws Exception {
		
		
		assertEquals(0, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(0, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(0, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(1, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		
	}

	@Test
	public void findByEmailMobileWithEmailMobileCustomer() throws Exception {
		
		assertNull(customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertNull(customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardEmailMobileCustomer());
		
		assertEquals(standardEmailMobileCustomer(),customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(standardEmailMobileCustomer(),customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
		
		
	}
	
	@Test
	public void findByEmailMobileWithEmailCustomer() throws Exception {
		
		assertNull(customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertNull(customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardEmailCustomer());
		
		assertEquals(standardEmailCustomer(),customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertNull(customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
	}
	
	@Test
	public void findByEmailMobileWithMobileCustomer() throws Exception {
		
		assertNull(customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertNull(customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertNull(customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(standardMobileCustomer(),customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
	}
	
	@Test
	public void deleteByEmailWithEmailCustomer() throws Exception
	{
		assertNull(customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertNull(customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardEmailCustomer());
		
		assertEquals(1, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(1, customerQuickRegisterMemRepository.deleteByEmail(CUST_EMAIL).intValue());
		
		assertEquals(0, customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertNull(customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
	}
	
	@Test
	public void deleteByMobileWithMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterMemRepository.findByEmail(CUST_EMAIL));
		
		assertNull(customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(1, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		assertEquals(1, customerQuickRegisterMemRepository.deleteByMobile(CUST_MOBILE).intValue());
		
		assertEquals(0, customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		assertNull(customerQuickRegisterMemRepository.findByMobile(CUST_MOBILE));
		
	}

}
