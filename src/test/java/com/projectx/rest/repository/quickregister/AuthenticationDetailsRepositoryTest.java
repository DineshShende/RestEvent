package com.projectx.rest.repository.quickregister;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class AuthenticationDetailsRepositoryTest {

	@Autowired
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
	
	@Autowired
	EmailVericationDetailsRepository emailVericationDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Before
	public void clearTestData()
	{
		customerAuthenticationDetailsRepository.clearLoginDetailsForTesting();
		quickRegisterRepository.clearCustomerQuickRegister();
		emailVericationDetailsRepository.clearTestData();
		customerMobileVerificationDetailsRepository.clearTestData();

	}
	
	@Test
	public void environmentTest()
	{
	}
	
	
	@Test
	public void findWithFailure()
	{
		
		AuthenticationDetails authenticationDetails=null;
		
		try{
			authenticationDetails=customerAuthenticationDetailsRepository.getByCustomerIdType(CUST_ID, ENTITY_TYPE_CUSTOMER);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		authenticationDetails=null;
		
		try{
			authenticationDetails=customerAuthenticationDetailsRepository.getByEmail(CUST_EMAIL);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		authenticationDetails=null;
		
		try{
			authenticationDetails=customerAuthenticationDetailsRepository.getByMobile(CUST_MOBILE);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
	}
	
	@Test
	public void updatePasswordEmailPasswordAndPasswordTypeWithPass()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		QuickRegisterEntity entity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.getByCustomerIdType(entity.getCustomerId(),entity.getCustomerType());
		
		assertNull(savedEntity.getPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_DEFAULT, savedEntity.getPasswordType());
		
		assertEquals(1, customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts(
				savedEntity.getKey().getCustomerId(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getCustomerType(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getPassword(),null,
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getPasswordType(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getUpdatedBy(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getUpdatedById()).intValue());
		
		assertEquals(CUST_PASSWORD_CHANGED, customerAuthenticationDetailsRepository.getByCustomerIdType(
				savedEntity.getKey().getCustomerId(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getCustomerType()).getPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_CHANGED, customerAuthenticationDetailsRepository.getByCustomerIdType(savedEntity.getKey().getCustomerId(),
				savedEntity.getKey().getCustomerType()).getPasswordType());
		
	}
	
	
	@Test
	public void updateEmailPasswordAndPasswordTypeAndCounts()
	{

		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		QuickRegisterEntity entity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.getByCustomerIdType(entity.getCustomerId(),entity.getCustomerType());
		
		assertNull(savedEntity.getEmailPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_DEFAULT, savedEntity.getPasswordType());
		

		assertEquals(1, customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts(
				savedEntity.getKey().getCustomerId(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getCustomerType(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getPassword(),standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getEmailPassword(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getPasswordType(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getUpdatedBy(),
				standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getUpdatedById()).intValue());
		
		assertEquals(CUST_EMAILHASH_UPDATED, customerAuthenticationDetailsRepository.getByCustomerIdType(savedEntity.getKey().getCustomerId(),
				savedEntity.getKey().getCustomerType()).getEmailPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_CHANGED, customerAuthenticationDetailsRepository.getByCustomerIdType(savedEntity.getKey().getCustomerId(),
				savedEntity.getKey().getCustomerType()).getPasswordType());
		
		
	}
	
	
	@Test
	public void updateResendCount()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
QuickRegisterEntity entity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.getByCustomerIdType(entity.getCustomerId(),entity.getCustomerType());

		assertEquals(0, savedEntity.getResendCount().intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementResendCount(savedEntity.getKey().getCustomerId(),
				standardCustomerIdTypeUpdatedByDTO().getCustomerType(),standardCustomerIdTypeUpdatedByDTO().getUpdatedBy(),
				standardCustomerIdTypeUpdatedByDTO().getUpdatedById()).intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.getByCustomerIdType(savedEntity.getKey().getCustomerId(),standardCustomerIdDTO().getCustomerType()).getResendCount().intValue());
	}
	
	
	
	@Test
	public void updateLoginVerificationCount()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		QuickRegisterEntity entity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.getByCustomerIdType(entity.getCustomerId(),entity.getCustomerType());

		assertEquals(0, savedEntity.getLastUnsucessfullAttempts().intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(
				savedEntity.getKey().getCustomerId(),
				standardCustomerIdTypeUpdatedByDTO().getCustomerType(),standardCustomerIdTypeUpdatedByDTO().getUpdatedBy(),
				standardCustomerIdTypeUpdatedByDTO().getUpdatedById()).intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.getByCustomerIdType
				(savedEntity.getKey().getCustomerId(),savedEntity.getKey().getCustomerType()).getLastUnsucessfullAttempts().intValue());

		
		
	}
	
	
	@Test
	public void getByCustomerId()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		QuickRegisterEntity entity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.getByCustomerIdType(entity.getCustomerId(),entity.getCustomerType());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetailsNull(), customerAuthenticationDetailsRepository.getByCustomerIdType
				(savedEntity.getKey().getCustomerId(),savedEntity.getKey().getCustomerType()));
		
	}
	
	
	@Test
	public void getCustomerAuthenticationDetailsByEmail()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		QuickRegisterEntity entity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.getByCustomerIdType(entity.getCustomerId(),entity.getCustomerType());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetailsNull(), customerAuthenticationDetailsRepository.getByEmail(savedEntity.getEmail()));
		
	}
	
	@Test
	public void getCustomerAuthenticationDetailsByMobile()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		QuickRegisterEntity entity= transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.getByCustomerIdType(entity.getCustomerId(),entity.getCustomerType());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetailsNull(), customerAuthenticationDetailsRepository.
				getByMobile(savedEntity.getMobile()));
		
	}

	
}
