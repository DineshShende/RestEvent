package com.projectx.rest.repository;

import static com.projectx.rest.fixture.CustomerEmailVerificationDetailsFixtures.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")

public class CustomerEmailVerificationDetailsRepositoryTest {

	
	@Autowired
	CustomerEmailVericationDetailsRepository customerEmailVericationDetailsRepository;
	
	
	@Before
	public void setUp()
	{
		customerEmailVericationDetailsRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveAndGetByCustomerIdAndEmail()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		assertNull(customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdAndEmail(standardCustomerEmailVerificationDetails().getCustomerId(),
				standardCustomerEmailVerificationDetails().getEmail()).getCustomerId());
		
		CustomerEmailVerificationDetails savedEntity=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(standardCustomerEmailVerificationDetails(), savedEntity);
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
		
		assertEquals(savedEntity, customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdAndEmail(standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getCustomerId(),
				standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getEmail()));
	}
	
	
	

	@Test
	public void updateEmailHashAndEmailHashSentTime()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		
		
		assertEquals(0, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
		CustomerEmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		String oldEmailHash=oldSaved.getEmailHash();
		
		assertEquals(1, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
		CustomerEmailVerificationDetails newFetched=customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdAndEmail(standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getCustomerId(),
				standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getEmail());
		
		String newEmailHash=newFetched.getEmailHash();
		
		assertFalse(oldEmailHash.equals(newEmailHash));
		
		
	}
	
	@Test
	public void updateResendCountByCustomerIdAndEmail()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		assertEquals(0, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdEmailDTO().getCustomerId(),
				standardCustomerIdEmailDTO().getEmail()).intValue());
		
		CustomerEmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(0, oldSaved.getResendCount().intValue());
		
		assertEquals(1, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdEmailDTO().getCustomerId(),
				standardCustomerIdEmailDTO().getEmail()).intValue());
		
		assertEquals(1, customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdAndEmail(oldSaved.getCustomerId(), oldSaved.getEmail()).getResendCount().intValue());
		
	}
	
	@Test
	public void countTest()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		CustomerEmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
		
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		CustomerEmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
	
		assertTrue(customerEmailVericationDetailsRepository.clearTestData());
		
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
	}
	
}
