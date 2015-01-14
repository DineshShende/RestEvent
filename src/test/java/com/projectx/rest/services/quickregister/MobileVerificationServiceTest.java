package com.projectx.rest.services.quickregister;


import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
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
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class MobileVerificationServiceTest {

	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	QuickRegisterService customerQuickRegisterHandler;
	
	
	@Before
	public void clearTestData()
	{
		mobileVerificationService.clearTestData();
		customerQuickRegisterHandler.clearDataForTesting();
	}
	
	/*
	@Test
	public void environmentTest() {
		
		
	}
	
	
	@Test
	public void createCustomerMobileVerificationEntity()
	{
		MobileVerificationDetails emailVerificationDetails=mobileVerificationService
				.createCustomerMobileVerificationEntity(standardEmailMobileCustomer().getCustomerId(),standardEmailMobileCustomer().getCustomerType(),
						standardEmailMobileCustomer().getMobile(),CUST_MOBILE_TYPE_PRIMARY,standardEmailMobileCustomer().getUpdatedBy());
		
		assertEquals(standardCustomerMobileVerificationDetails().getKey(), emailVerificationDetails.getKey());
	}
	

	@Test
	public void saveCustomerMobileVerificationEntityAndGetByCustomerIdAndEmail()
	{
		MobileVerificationDetails savedEntity=mobileVerificationService.saveCustomerMobileVerificationDetails(standardCustomerMobileVerificationDetails());
		
		MobileVerificationDetails fetchedEntity=mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getKey().getCustomerId(),
						savedEntity.getKey().getCustomerType(),savedEntity.getKey().getMobile());
		
		assertEquals(savedEntity, fetchedEntity);
		
	}
	

	@Test
	public void checkIfEmailExist() throws Exception
	{
	
		assertEquals("NOTEXIST", mobileVerificationService.checkIfMobileAlreadyExist(CUST_ID, QUICK_TYPE_CUSTOMER, standardEmailCustomerDTO().getMobile()));
		
				
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardMobileCustomerDTO()).getCustomer();
		
		assertEquals("EXIST", mobileVerificationService.checkIfMobileAlreadyExist(handledEntity.getCustomerId(), QUICK_TYPE_CUSTOMER, handledEntity.getMobile()));
		
		assertEquals("EXISTWITHOTHERENTITY", mobileVerificationService.checkIfMobileAlreadyExist(CUST_ID, QUICK_TYPE_VENDOR, handledEntity.getMobile()));
	}
*/
	
	@Test
	public void deleteKey()
	{
		MobileVerificationDetails savedEntity=mobileVerificationService.saveDetails(standardCustomerMobileVerificationDetails());
		
		assertTrue(mobileVerificationService
				.deleteByKey(savedEntity.getKey()));
		
	}
}
