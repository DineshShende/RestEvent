package com.projectx.rest.repository;


import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;

@ActiveProfiles("Test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class CustomerQuickRegisterRepositoryTest {

	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterMemRepository;
	
	@Test
	public void checkIfExistNewEmailMobileCustomer() {
		
		
		assertFalse(customerQuickRegisterMemRepository.checkIfAlreadyExist(standardEmailMobileCustomerKey()));
		customerQuickRegisterMemRepository.save(standardEmailMobileCustomer());
		
		assertTrue(customerQuickRegisterMemRepository.checkIfAlreadyExist(standardEmailMobileCustomerKey()));
		
		
		
	}

	
	@Test
	public void getByKeyEmailCustomer() {
		
		assertNull(customerQuickRegisterMemRepository.getByKey(standardEmailCustomerKey()));
		
		customerQuickRegisterMemRepository.save(standardEmailCustomer());
		
		assertEquals(standardEmailCustomer(),customerQuickRegisterMemRepository.getByKey(standardEmailCustomerKey()));
		
	}

}
