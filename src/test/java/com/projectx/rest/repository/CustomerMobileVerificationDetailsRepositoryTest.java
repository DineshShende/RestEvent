package com.projectx.rest.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;

import static com.projectx.rest.fixture.CustomerMobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")

public class CustomerMobileVerificationDetailsRepositoryTest {

	
	@Autowired
	CustomerMobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	
	@Before
	public void setUp()
	{
		customerMobileVerificationDetailsRepository.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveAndGetByCustomerIdAndMobile()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertNull(customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerId(),
				standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getMobile()).getCustomerId());
		
		CustomerMobileVerificationDetails savedEntity=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(standardCustomerMobileVerificationDetails(), savedEntity);
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(savedEntity, customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerId(),
				standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getMobile()));
	}
	
	
	@Test
	public void updateMobilePinAndMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(), standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
		
		CustomerMobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		
		assertEquals(CUST_MOBILEPIN, customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(oldSaved.getCustomerId(), oldSaved.getMobile()).getMobilePin());
		
		
		assertEquals(1, customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(), standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
		
		assertEquals(CUST_MOBILEPIN_UPDATED, customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(oldSaved.getCustomerId(), oldSaved.getMobile()).getMobilePin());
		
		
	}
	
	
	@Test
	public void updateMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.incrementMobileVerificationAttempts(standardCustomerIdMobileDTO().getCustomerId(),
				standardCustomerIdMobileDTO().getMobile()));
		
		CustomerMobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(oldSaved.getCustomerId(), oldSaved.getMobile()).getMobileVerificationAttempts());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.incrementMobileVerificationAttempts(standardCustomerIdMobileDTO().getCustomerId(),
				standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(oldSaved.getCustomerId(), oldSaved.getMobile()).getMobileVerificationAttempts());
		
	}
	
	@Test
	public void updateResendCount()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(),
				standardCustomerIdMobileDTO().getMobile()));
		
		CustomerMobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(oldSaved.getCustomerId(), oldSaved.getMobile()).getResendCount());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(),
				standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(oldSaved.getCustomerId(), oldSaved.getMobile()).getResendCount());
	}
	
	
	@Test
	public void countTest()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		CustomerMobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
		
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		CustomerMobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
	
		assertTrue(customerMobileVerificationDetailsRepository.clearTestData());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
	}
	
}
