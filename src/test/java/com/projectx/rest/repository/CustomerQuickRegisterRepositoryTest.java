package com.projectx.rest.repository;


import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")
public class CustomerQuickRegisterRepositoryTest {

	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Before
	public void clearExistingRecords()
	{
		customerQuickRegisterRepository.clearCustomerQuickRegister();
	}
	
	
	@Test
	public void findAllWithEmailMobileCustomer() {
		
		List<CustomerQuickRegisterEntity> customerList=customerQuickRegisterRepository.findAll();
		
		assertEquals(0,customerList.size());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerList=customerQuickRegisterRepository.findAll();
		
		assertEquals(1,customerList.size());
		
		CustomerQuickRegisterEntity customer=customerList.get(0);
		
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
		
	
		assertNull(customerQuickRegisterRepository.findByCustomerId(CUST_ID).getCustomerId());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());

		assertEquals(savedCustomer,customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()));
			
		
	}
	
	@Test
	public void findByEmailAndMobileWithEmailMobileCustomer() throws Exception
	{
		assertNull(customerQuickRegisterRepository.findByEmail(CUST_EMAIL).getCustomerId());
		
		assertNull(customerQuickRegisterRepository.findByMobile(CUST_MOBILE).getCustomerId());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(standardEmailMobileCustomer(),customerQuickRegisterRepository.findByEmail(CUST_EMAIL));
		
		assertEquals(standardEmailMobileCustomer(),customerQuickRegisterRepository.findByMobile(CUST_MOBILE));
		
	}
	
	
	@Test
	public void updateEmailVericationStatusWithEmailMobileCustomer()
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateEmailVerificationStatus(standardUpdateEmailMobileVerificationStatus().getCustomerId(),
				standardUpdateEmailMobileVerificationStatus().getStatus(),standardUpdateEmailMobileVerificationStatus().getUpdateTime(),
				standardUpdateEmailMobileVerificationStatus().getUpdatedBy()));
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
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
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
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
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
	}
	
	
	
		

	
}
