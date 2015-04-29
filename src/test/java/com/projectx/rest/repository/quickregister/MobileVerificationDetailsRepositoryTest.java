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
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class MobileVerificationDetailsRepositoryTest {

	
	
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
	
	@Autowired
	EmailVericationDetailsRepository emailVericationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	@Before
	public void setUp()
	{
		authenticationDetailsRepository.clearLoginDetailsForTesting();
		quickRegisterRepository.clearCustomerQuickRegister();
		emailVericationDetailsRepository.clearTestData();
		customerMobileVerificationDetailsRepository.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveAndGetByCustomerIdAndMobile()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails mobileVerificationDetails=null;
		
		try{
			mobileVerificationDetails=customerMobileVerificationDetailsRepository.geByEntityIdTypeAndMobileType(standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerId(),
					standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerType(),
					MOB_TYPE_PRIMARY);
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		MobileVerificationDetails savedEntity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		assertEquals(standardCustomerMobileVerificationDetailsNull(), savedEntity);
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(savedEntity,customerMobileVerificationDetailsRepository.geByEntityIdTypeAndMobileType(savedEntity.getKey().getCustomerId(),
				standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO().getCustomerType(),
				MOB_TYPE_PRIMARY));
	}

	
	@Test
	public void saveAndGetByMobile()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails mobileVerificationDetails=null;
		
		try{
			mobileVerificationDetails=customerMobileVerificationDetailsRepository.getByMobile(
					CUST_MOBILE);
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		MobileVerificationDetails savedEntity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		assertEquals(standardCustomerMobileVerificationDetailsNull(), savedEntity);
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(savedEntity,customerMobileVerificationDetailsRepository.getByMobile(
				savedEntity.getMobile()));
	}

	
	@Test
	public void updateMobilePinAndMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerId(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerType(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileType(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(), standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getUpdatedBy(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getUpdatedById()).intValue());
		
		MobileVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		
		assertNull(customerMobileVerificationDetailsRepository.geByEntityIdTypeAndMobileType(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobileType()).getMobilePin());
		
		
		assertEquals(1, customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(
				oldSaved.getKey().getCustomerId(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getCustomerType(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileType(),standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobilePin(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getMobileVerificationAttempts(), standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getResendCount(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getUpdatedBy(),
				standardUpdateMobilePinAndMobileVerificationAttemptsDTO().getUpdatedById()).intValue());
		
		assertEquals(CUST_MOBILEPIN_UPDATED, customerMobileVerificationDetailsRepository.geByEntityIdTypeAndMobileType(oldSaved.getKey().getCustomerId(),
				oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobileType()).getMobilePin());
		
		
	}
	
	
	@Test
	public void updateMobileVerificationAttempts()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.incrementMobileVerificationAttempts(
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getCustomerId(),standardCustomerIdTypeMobileTypeUpdatedByDTO().getCustomerType(),
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getMobileType(),standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedBy(),
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedById()));
		
		MobileVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.geByEntityIdTypeAndMobileType(
				oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobileType()).getMobileVerificationAttempts());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.incrementMobileVerificationAttempts(oldSaved.getKey().getCustomerId()
				,standardCustomerIdTypeMobileTypeUpdatedByDTO().getCustomerType(),
				standardCustomerIdMobileDTO().getMobileType(),standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedBy(),
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedById()));
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository
				.geByEntityIdTypeAndMobileType(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobileType()).getMobileVerificationAttempts());
		
	}
	
	@Test
	public void updateResendCount()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository.incrementResendCount(
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getCustomerId(),standardCustomerIdTypeMobileTypeUpdatedByDTO().getCustomerType(),
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getMobileType(),standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedBy(),
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedById()));
		
		MobileVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		assertEquals(new Integer(0), customerMobileVerificationDetailsRepository
				.geByEntityIdTypeAndMobileType(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobileType()).getResendCount());
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository.incrementResendCount(
				oldSaved.getKey().getCustomerId(),
				oldSaved.getKey().getCustomerType(),
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getMobileType(),standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedBy(),
				standardCustomerIdTypeMobileTypeUpdatedByDTO().getRequestedById()));
		
		assertEquals(new Integer(1), customerMobileVerificationDetailsRepository
				.geByEntityIdTypeAndMobileType(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), oldSaved.getKey().getMobileType()).getResendCount());
	}
	
	
	@Test
	public void countTest()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
		
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
	
		assertTrue(customerMobileVerificationDetailsRepository.clearTestData());
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
	}
	
	@Test
	public void deleteByKey()
	{
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
		MobileVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerMobileVerificationDetails();
		
		assertEquals(1, customerMobileVerificationDetailsRepository.count().intValue());
	
		assertTrue(customerMobileVerificationDetailsRepository.delete(oldSaved.getKey()));
		
		assertEquals(0, customerMobileVerificationDetailsRepository.count().intValue());
		
	}
	
}
