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
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;

import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")
public class AuthenticationDetailsRepositoryTest {

	@Autowired
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	

	
	@Before
	public void clearTestData()
	{
		customerAuthenticationDetailsRepository.clearLoginDetailsForTesting();
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	
	@Test
	public void addNewCustomerDetailsWithEmailMobileCustomer()
	{
		
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), savedEntity);
		
	}
	
	
	@Test
	public void updatePasswordAndPasswordType()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(CUST_PASSWORD_DEFAULT, savedEntity.getPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_DEFAULT, savedEntity.getPasswordType());
		
		assertEquals(1, customerAuthenticationDetailsRepository.updatePasswordAndPasswordTypeAndCounts(standardUpdatePasswordAndPasswordTypeDTO().getCustomerId(),
				standardUpdatePasswordAndPasswordTypeDTO().getCustomerType(),
				standardUpdatePasswordAndPasswordTypeDTO().getPassword(), standardUpdatePasswordAndPasswordTypeDTO().getPasswordType()).intValue());
		
		assertEquals(CUST_PASSWORD_CHANGED, customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(standardAuthenticationDetailsKey().getCustomerId(),
				standardUpdatePasswordAndPasswordTypeDTO().getCustomerType()).getPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_CHANGED, customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(savedEntity.getKey().getCustomerId(),
				savedEntity.getKey().getCustomerType()).getPasswordType());
		
	}
	
	
	@Test
	public void updateEmailPasswordAndPasswordTypeAndCounts()
	{

		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(CUST_EMAILHASH, savedEntity.getEmailPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_DEFAULT, savedEntity.getPasswordType());
		
		assertEquals(1, customerAuthenticationDetailsRepository.updateEmailPasswordAndPasswordTypeAndCounts(standardUpdateEmailPassword().getCustomerId(),
				standardUpdateEmailPassword().getCustomerType(),standardUpdateEmailPassword().getEmailPassword()).intValue());
		
		assertEquals(CUST_EMAILHASH_UPDATED, customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(savedEntity.getKey().getCustomerId(),
				savedEntity.getKey().getCustomerType()).getEmailPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_DEFAULT, customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(savedEntity.getKey().getCustomerId(),
				savedEntity.getKey().getCustomerType()).getPasswordType());
		
		
	}
	
	
	@Test
	public void updateResendCount()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());

		assertEquals(0, savedEntity.getResendCount().intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementResendCount(standardCustomerIdDTO().getCustomerId(),standardCustomerIdDTO().getCustomerType()).intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(savedEntity.getKey().getCustomerId(),standardCustomerIdDTO().getCustomerType()).getResendCount().intValue());
	}
	
	
	
	@Test
	public void updateLoginVerificationCount()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());

		assertEquals(0, savedEntity.getLastUnsucessfullAttempts().intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(standardCustomerIdDTO().getCustomerId(),
				standardCustomerIdDTO().getCustomerType()).intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType
				(savedEntity.getKey().getCustomerId(),savedEntity.getKey().getCustomerType()).getLastUnsucessfullAttempts().intValue());

		
		
	}
	
	
	@Test
	public void getByCustomerId()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType
				(savedEntity.getKey().getCustomerId(),savedEntity.getKey().getCustomerType()));
		
	}
	
	
	@Test
	public void getCustomerAuthenticationDetailsByEmail()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByEmail(savedEntity.getEmail()));
		
	}
	
	@Test
	public void getCustomerAuthenticationDetailsByMobile()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		AuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), customerAuthenticationDetailsRepository.
				getCustomerAuthenticationDetailsByMobile(savedEntity.getMobile()));
		
	}

	/*
	@Test
	public void loginVerificationWithEmailMobileCustomer()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertNotNull(customerAuthenticationDetailsRepository.loginVerification(standardVerifyLoginDetailsDataWithEmail().getEmail(),
				standardVerifyLoginDetailsDataWithEmail().getMobile(), standardVerifyLoginDetailsDataWithEmail().getPassword()));
		
		assertNotNull(customerAuthenticationDetailsRepository.loginVerification(standardVerifyLoginDetailsDataWithMobile().getEmail(),
				standardVerifyLoginDetailsDataWithMobile().getMobile(), standardVerifyLoginDetailsDataWithMobile().getPassword()));
		
		assertNull(customerAuthenticationDetailsRepository.loginVerification(standardVerifyLoginDetailsDataWithMobileNewPassword().getEmail(),
				standardVerifyLoginDetailsDataWithMobileNewPassword().getMobile(), standardVerifyLoginDetailsDataWithMobileNewPassword().getPassword()).getCustomerId());
		
		assertNull(customerAuthenticationDetailsRepository.loginVerification(standardVerifyLoginDetailsDataWithEmailNewPassword().getEmail(),
				standardVerifyLoginDetailsDataWithEmailNewPassword().getMobile(), standardVerifyLoginDetailsDataWithEmailNewPassword().getPassword()).getCustomerId());
	}
	*/
}