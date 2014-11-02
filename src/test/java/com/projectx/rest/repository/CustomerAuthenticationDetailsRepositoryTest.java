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
import com.projectx.rest.domain.CustomerAuthenticationDetails;

import static com.projectx.rest.fixture.CustomerAuthenticationDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Test")
public class CustomerAuthenticationDetailsRepositoryTest {

	@Autowired
	CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	

	
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
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), savedEntity);
		
	}
	
	
	@Test
	public void updatePasswordAndPasswordType()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(1, customerAuthenticationDetailsRepository.updatePasswordAndPasswordType(standardUpdatePasswordAndPasswordTypeDTO().getCustomerId(),
				standardUpdatePasswordAndPasswordTypeDTO().getPassword(), standardUpdatePasswordAndPasswordTypeDTO().getPasswordType()).intValue());
		
	}
	
	
	@Test
	public void loginVerificationWithEmailMobileCustomer()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertNotNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithEmail().getEmail(),
				standardLoginVerificationWithEmail().getMobile(), standardLoginVerificationWithEmail().getPassword()));
		
		assertNotNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithMobile().getEmail(),
				standardLoginVerificationWithMobile().getMobile(), standardLoginVerificationWithMobile().getPassword()));
		
		assertNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithMobileNewPassword().getEmail(),
				standardLoginVerificationWithMobileNewPassword().getMobile(), standardLoginVerificationWithMobileNewPassword().getPassword()).getCustomerId());
		
		assertNull(customerAuthenticationDetailsRepository.loginVerification(standardLoginVerificationWithEmailNewPassword().getEmail(),
				standardLoginVerificationWithEmailNewPassword().getMobile(), standardLoginVerificationWithEmailNewPassword().getPassword()).getCustomerId());
	}
	
	@Test
	public void getByCustomerId()
	{
		assertEquals(0,customerAuthenticationDetailsRepository.count().intValue());
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(1,customerAuthenticationDetailsRepository.count().intValue());
		
		assertEquals(standardCustomerEmailMobileAuthenticationDetails(), customerAuthenticationDetailsRepository.getByCustomerId(standardCustomerEmailMobileAuthenticationDetails().getCustomerId()));
		
	}

}
