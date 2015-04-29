package com.projectx.rest.repository.quickregister;


import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static com.projectx.rest.config.Constants.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotSavedException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class QuickRegisterRepositoryTest {

	@Autowired
	QuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVericationDetailsRepository emailVericationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Before
	public void clearExistingRecords()
	{
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		mobileVerificationDetailsRepository.clearTestData();
		emailVericationDetailsRepository.clearTestData();
		authenticationDetailsRepository.clearLoginDetailsForTesting();
	}

	@Autowired
	Environment env;
	
	@Test
	public void saveWithErrors()
	{
		QuickRegisterEntity quickRegisterEntity=null;
		
		String activeProfile =System.getProperty("spring.profiles.active");
		
		System.out.println("Test Profile:"+activeProfile+":Pro:"+env.getActiveProfiles()[0]);
		
		try{
			quickRegisterEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomerWithError()).getCustomerQuickRegisterEntity();
		}catch(ValidationFailedException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	}
	
	@Test
	public void saveWithAlreadySaved()
	{
		QuickRegisterEntity quickRegisterEntity=null;
		
		transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		try{
			quickRegisterEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		}catch(QuickRegisterDetailsAlreadyPresentException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	}
	
	
	
	@Test
	public void findByCustomerIdWithEmailMobileCustomer()  {
	
		QuickRegisterEntity quickRegisterEntity=null;
	
		try{
			quickRegisterEntity=customerQuickRegisterRepository.findByCustomerId(CUST_ID);
		}catch(ResourceNotFoundException e)
		{		
			assertNull(quickRegisterEntity);
		}
		
		QuickRegisterEntity savedCustomer=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();

		assertEquals(savedCustomer,customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()));
			
		
	}
	
	@Test
	public void findByEmailAndMobileWithEmailMobileCustomer() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=null;
		
		try{
			quickRegisterEntity=customerQuickRegisterRepository.findByEmail(CUST_EMAIL);
		}catch(ResourceNotFoundException e)
		{
			assertNull(quickRegisterEntity);
		}
		
		
		
		try{
			quickRegisterEntity=customerQuickRegisterRepository.findByMobile(CUST_MOBILE);
		}catch(ResourceNotFoundException e)
		{
			assertNull(quickRegisterEntity);
		}
		
				
		QuickRegisterEntity savedEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		assertEquals(savedEntity,customerQuickRegisterRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(savedEntity,customerQuickRegisterRepository.findByMobile(CUST_MOBILE));
		
	}
	
	
	@Test
	public void updateEmailVericationStatusWithEmailMobileCustomer()
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateEmailVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedById()));
		
		QuickRegisterEntity savedCustomer=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		assertEquals(1,customerQuickRegisterRepository.count().intValue());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updateEmailVerificationStatus(savedCustomer.getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedById()));
		
		assertEquals(CUST_IS_EMAIL_VERIFIED_TRUE, customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getIsEmailVerified());
		
	}
	
	@Test
	public void updateMobileVericationStatusWithEmailMobileCustomer()
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateMobileVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),standardUpdateEmailMobileVerificationStatus().getUpdatedById()));
		
		QuickRegisterEntity savedCustomer=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		assertEquals(1,customerQuickRegisterRepository.count().intValue());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updateMobileVerificationStatus(savedCustomer.getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedById()));
		
		assertEquals(CUST_IS_MOBILE_VERIFIED_TRUE, customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getIsMobileVerified());
		
		
	}
	
	
	
	
	@Test
	public void clearData() throws Exception
	{
		assertEquals(0,customerQuickRegisterRepository.count().intValue());
		
		QuickRegisterEntity savedCustomer=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		assertEquals(1,customerQuickRegisterRepository.count().intValue());
		
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		
		assertEquals(0,customerQuickRegisterRepository.count().intValue());
		
	}
	
	
	
		

	
}
