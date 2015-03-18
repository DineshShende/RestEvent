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
			quickRegisterEntity=customerQuickRegisterRepository.save(standardEmailMobileCustomerWithError());
		}catch(ValidationFailedException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	}
	
	@Test
	public void saveWithAlreadySaved()
	{
		QuickRegisterEntity quickRegisterEntity=null;
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		try{
			quickRegisterEntity=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		}catch(QuickRegisterDetailsAlreadyPresentException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	}
	
	@Test
	public void findAllWithEmailMobileCustomer() {
		
		List<QuickRegisterEntity> customerList=customerQuickRegisterRepository.findAll();
		
		assertEquals(0,customerList.size());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerList=customerQuickRegisterRepository.findAll();
		
		assertEquals(1,customerList.size());
		
		QuickRegisterEntity customer=customerList.get(0);
		
		assertEquals(CUST_FIRSTNAME, customer.getFirstName());
		assertEquals(CUST_LASTNAME, customerList.get(0).getLastName());
		assertEquals(CUST_EMAIL, customerList.get(0).getEmail());
		assertEquals(CUST_MOBILE, customerList.get(0).getMobile());
		assertEquals(CUST_PIN_CODE, customerList.get(0).getPincode());
		assertEquals(CUST_IS_EMAIL_VERIFIED_FALSE, customerList.get(0).getIsEmailVerified());
		assertEquals(CUST_IS_MOBILE_VERIFIED_FALSE, customerList.get(0).getIsMobileVerified());
		assertNotNull(customerList.get(0).getInsertTime());
		assertNotNull(customerList.get(0).getUpdateTime());
		assertEquals(CUST_UPDATED_BY, customerList.get(0).getUpdatedBy());
		
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
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());

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
		
				
		QuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(savedEntity,customerQuickRegisterRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(savedEntity,customerQuickRegisterRepository.findByMobile(CUST_MOBILE));
		
	}
	
	
	@Test
	public void updateEmailVericationStatusWithEmailMobileCustomer()
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateEmailVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()));
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updateEmailVerificationStatus(savedCustomer.getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()));
		
		assertEquals(CUST_IS_EMAIL_VERIFIED_TRUE, customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getIsEmailVerified());
		
	}
	
	@Test
	public void updateMobileVericationStatusWithEmailMobileCustomer()
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateMobileVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()));
		
		QuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updateMobileVerificationStatus(savedCustomer.getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()));
		
		assertEquals(CUST_IS_MOBILE_VERIFIED_TRUE, customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()).getIsMobileVerified());
		
		
	}
	
	
	
	
	@Test
	public void clearData() throws Exception
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		QuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
	}
	
	
	
		

	
}
