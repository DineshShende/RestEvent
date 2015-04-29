package com.projectx.rest.repository.quickregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class EmailVerificationDetailsRepositoryTest {

	
	@Autowired
	EmailVericationDetailsRepository customerEmailVericationDetailsRepository;
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	
	@Before
	public void setUp()
	{
		authenticationDetailsRepository.clearLoginDetailsForTesting();
		quickRegisterRepository.clearCustomerQuickRegister();
		customerEmailVericationDetailsRepository.clearTestData();
		customerMobileVerificationDetailsRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
		
	}
	
	
	
	@Test
	public void getWithOutFailure()
	{
		EmailVerificationDetails emailVerificationDetails=null;
		
		try{
			emailVerificationDetails=customerEmailVericationDetailsRepository.getByEmail(CUST_EMAIL);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetails);
		}
		
		try{
			emailVerificationDetails=customerEmailVericationDetailsRepository.getByEntityIdTypeAndEmailType(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_CUSTOMER);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetails);
		}
	
	}
	
	@Test
	public void saveAndGetByEmail()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		
		EmailVerificationDetails emailVerificationDetails=null;
		
		try{
			emailVerificationDetails=customerEmailVericationDetailsRepository.getByEmail(standardCustomerEmailVerificationDetails().getEmail());
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetails);
		}
		
		EmailVerificationDetails savedEntity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerEmailVerificationDetails();
		
			
		assertEquals(standardCustomerEmailVerificationDetailsNull(), savedEntity);
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
		
		assertEquals(savedEntity, customerEmailVericationDetailsRepository.getByEmail(
				savedEntity.getEmail()));
	}

	

	@Test
	public void updateEmailHashAndEmailHashSentTime()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		
		
		assertEquals(0, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerType(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailType(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getUpdatedBy(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getUpdatedById()).intValue());
		
		EmailVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerEmailVerificationDetails();
		
		String oldEmailHash=oldSaved.getEmailHash();
		
		assertEquals(1, customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(
				oldSaved.getKey().getCustomerId(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getCustomerType(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailType(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHash(), standardUpdateEmailHashAndEmailHashSentTimeDTO().getEmailHashSentTime(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getResendCount(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getUpdatedBy(),
				standardUpdateEmailHashAndEmailHashSentTimeDTO().getUpdatedById()).intValue());
		
		EmailVerificationDetails newFetched=customerEmailVericationDetailsRepository.getByEntityIdTypeAndEmailType(oldSaved.getKey().getCustomerId(),
				standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO().getCustomerType(),
				EMAIL_TYPE_PRIMARY);
		
		String newEmailHash=newFetched.getEmailHash();
		
		assertNull(oldEmailHash);
		assertNotNull((newEmailHash));
		
		
	}
	
	@Test
	public void updateResendCountByCustomerIdAndEmail()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		assertEquals(0, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(standardCustomerIdTypeEmailTypeUpdatedByDTO().getCustomerId(),
				standardCustomerIdTypeEmailTypeUpdatedByDTO().getCustomerType(),EMAIL_TYPE_PRIMARY,
				standardCustomerIdTypeEmailTypeUpdatedByDTO().getRequestedBy(),
				standardCustomerIdTypeEmailTypeUpdatedByDTO().getRequestedById()).intValue());
		
		EmailVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerEmailVerificationDetails();
		
		assertEquals(0, oldSaved.getResendCount().intValue());
		
		assertEquals(1, customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(
				oldSaved.getKey().getCustomerId(),
				standardCustomerIdTypeEmailTypeUpdatedByDTO().getCustomerType(),EMAIL_TYPE_PRIMARY,
				standardCustomerIdTypeEmailTypeUpdatedByDTO().getRequestedBy(),
				standardCustomerIdTypeEmailTypeUpdatedByDTO().getRequestedById()).intValue());
		
		assertEquals(1, customerEmailVericationDetailsRepository.getByEntityIdTypeAndEmailType(oldSaved.getKey().getCustomerId(),oldSaved.getKey().getCustomerType(), EMAIL_TYPE_PRIMARY).getResendCount().intValue());
		
	}
	
	@Test
	public void countTest()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		EmailVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerEmailVerificationDetails();
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
		
		
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		EmailVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerEmailVerificationDetails();
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
	
		assertTrue(customerEmailVericationDetailsRepository.clearTestData());
		
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
	}
	
	@Test
	public void deleteByKey()
	{
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
		EmailVerificationDetails oldSaved= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerEmailVerificationDetails();
		
		assertEquals(1, customerEmailVericationDetailsRepository.count().intValue());
	
		assertTrue(customerEmailVericationDetailsRepository.delete(oldSaved.getKey()));
		
		assertEquals(0, customerEmailVericationDetailsRepository.count().intValue());
		
	}
	
}
