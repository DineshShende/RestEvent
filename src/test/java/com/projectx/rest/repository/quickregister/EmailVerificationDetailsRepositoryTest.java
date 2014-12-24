package com.projectx.rest.repository.quickregister;

import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")

public class EmailVerificationDetailsRepositoryTest {

	
	@Autowired
	EmailVericationDetailsRepository customerEmailVericationDetailsRepository;
	
	
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
		
		assertNull(customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdTypeAndEmail(standardCustomerEmailVerificationDetails().getKey().getCustomerId(),
				standardCustomerEmailVerificationDetails().getKey().getCustomerType(),standardCustomerEmailVerificationDetails().getKey().getEmail()).getKey());
		
		EmailVerificationDetails savedEntity=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(standardCustomerEmailVerificationDetails(), savedEntity);
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
		
		assertEquals(savedEntity, customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdTypeAndEmail(standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getCustomerId(),
				standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getCustomerType(),
				standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getEmail()));
	}
	
	
	

	@Test
	public void updateEmailHashAndEmailHashSentTime()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		
		
		assertEquals(0, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerType(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
		EmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		String oldEmailHash=oldSaved.getEmailHash();
		
		assertEquals(1, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerType(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmail(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount()).intValue());
		
		EmailVerificationDetails newFetched=customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdTypeAndEmail(standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getCustomerId(),
				standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getCustomerType(),
				standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getEmail());
		
		String newEmailHash=newFetched.getEmailHash();
		
		assertFalse(oldEmailHash.equals(newEmailHash));
		
		
	}
	
	@Test
	public void updateResendCountByCustomerIdAndEmail()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		assertEquals(0, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdEmailDTO().getCustomerId(),
				standardCustomerIdEmailDTO().getCustomerType(),standardCustomerIdEmailDTO().getEmail()).intValue());
		
		EmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(0, oldSaved.getResendCount().intValue());
		
		assertEquals(1, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdEmailDTO().getCustomerId(),
				standardCustomerIdEmailDTO().getCustomerType(),standardCustomerIdEmailDTO().getEmail()).intValue());
		
		assertEquals(1, customerEmailVericationDetailsRepository.getEmailVerificationDetailsByCustomerIdTypeAndEmail(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getEmail()).getResendCount().intValue());
		
	}
	
	@Test
	public void countTest()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		EmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
		
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		EmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
	
		assertTrue(customerEmailVericationDetailsRepository.clearTestData());
		
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
	}
	
	@Test
	public void deleteByKey()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		EmailVerificationDetails oldSaved=customerEmailVericationDetailsRepository.save(standardCustomerEmailVerificationDetails());
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
	
		assertTrue(customerEmailVericationDetailsRepository.delete(oldSaved.getKey()));
		
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
	}
	
}