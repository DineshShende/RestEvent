package com.projectx.rest.repository.quickregister;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;

import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")

public class MobileVerificationDetailsRepositoryTest {

	
	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	
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
		
		assertNull(customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdTypeAndMobile(standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerId(),
				standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerType(),
				standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getMobile()).getKey());
		
		MobileVerificationDetails savedEntity=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(standardCustomerMobileVerificationDetails(), savedEntity);
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(savedEntity,customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdTypeAndMobile(standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerId(),
				standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerType(),
				standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getMobile()));
	}
	
	
	@Test
	public void updateMobilePinAndMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerType(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(), standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
		
		MobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		
		assertEquals(CUST_MOBILEPIN, customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdTypeAndMobile(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobile()).getMobilePin());
		
		
		assertEquals(1, customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerType(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobile(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(), standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount()).intValue());
		
		assertEquals(CUST_MOBILEPIN_UPDATED, customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdTypeAndMobile(oldSaved.getKey().getCustomerId(),
				oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobile()).getMobilePin());
		
		
	}
	
	
	@Test
	public void updateMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.incrementMobileVerificationAttempts(
				standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getCustomerType(),
				standardCustomerIdMobileDTO().getMobile()));
		
		MobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdTypeAndMobile(
				oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobile()).getMobileVerificationAttempts());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.incrementMobileVerificationAttempts(standardCustomerIdMobileDTO().getCustomerId()
				,standardCustomerIdMobileDTO().getCustomerType(),
				standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobile()).getMobileVerificationAttempts());
		
	}
	
	@Test
	public void updateResendCount()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.incrementResendCount(
				standardCustomerIdMobileDTO().getCustomerId(),standardCustomerIdMobileDTO().getCustomerType(),
				standardCustomerIdMobileDTO().getMobile()));
		
		MobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobile()).getResendCount());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.incrementResendCount(standardCustomerIdMobileDTO().getCustomerId(),
				standardCustomerIdMobileDTO().getCustomerType(),
				standardCustomerIdMobileDTO().getMobile()));
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobile()).getResendCount());
	}
	
	
	@Test
	public void countTest()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
		
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
	
		assertTrue(customerMobileVerificationDetailsRepository.clearTestData());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
	}
	
	@Test
	public void deleteByKey()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails oldSaved=customerMobileVerificationDetailsRepository.save(standardCustomerMobileVerificationDetails());
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
	
		assertTrue(customerMobileVerificationDetailsRepository.delete(oldSaved.getKey()));
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
	}
	
}
