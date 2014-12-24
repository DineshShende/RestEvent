package com.projectx.rest.services.completeregister;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*; 

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
//@Transactional
public class CustomerDetailsServiceTest {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	AddressService addressService;
	
	@Before
	@After
	public void setUp()
	{
		customerDetailsService.clearTestData();
		mobileVerificationService.clearTestData();
		emailVerificationService.clearTestData();
		quickRegisterService.clearDataForTesting();
	}
	
	@Test
	public void environmentTest() {
		
		
		
	}
	
	
	@Test
	public void createCustomerDetailsFromQuickRegisterEntity()
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	@Test
	public void mergeCustomerDetails()
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()));
		
		assertEquals(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()), mergeEntity);
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	@Test
	public void mergeCustomerDetailsWithNewSecondaryMobile()
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()));
		
		assertEquals(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()), mergeEntity);
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity));
		
		assertEquals(1, mobileVerificationService.count().intValue());
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	
	@Test
	public void mergeCustomerDetailsWithNewMobile() throws Exception
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetails(savedEntity), mergeEntity);
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewMobile(mergeEntity));
		
		assertEquals(2, mobileVerificationService.count().intValue());
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	
	
	@Test
	public void mergeCustomerDetailsWithEmail() throws Exception
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(1, emailVerificationService.count().intValue());
		
		assertEquals(standardCustomerDetails(savedEntity), mergeEntity);
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewEmail(mergeEntity));
		
		assertEquals(1, emailVerificationService.count().intValue());
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	
	@Test
	public void mergeCustomerDetailsWithHomeAddress() throws Exception
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		Address oldHomeAddress=mergeEntity.getHomeAddressId();
		
		assertNotNull(addressService.findById(oldHomeAddress.getAddressId()));
			
		assertEquals(standardCustomerDetails(savedEntity), mergeEntity);
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewHomeAddress(mergeEntity));
		
		assertNull(addressService.findById(oldHomeAddress.getAddressId()));
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	@Test
	public void saveAndCheckIfExistMobileVerificationDetails() throws Exception
	{
		assertFalse(customerDetailsService.checkIfMobileSaved(CUST_ID, CUST_TYPE_CUSTOMER, CUST_MOBILE));
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertTrue(customerDetailsService.checkIfMobileSaved(savedEntity.getCustomerId(), 1, savedEntity.getMobile()));
		
	}
	
	@Test
	public void saveAndCheckIfExistEmailVerificationDetails() throws Exception
	{
		assertFalse(customerDetailsService.checkIfEmailSaved(CUST_ID, CUST_TYPE_CUSTOMER, CUST_EMAIL));
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertTrue(customerDetailsService.checkIfEmailSaved(savedEntity.getCustomerId(), 1, savedEntity.getEmail()));
		
	}
	
	
	@Test
	public void verifyMobileDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
	
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(savedEntity.getCustomerId(), 1, savedEntity.getMobile());
		
		assertEquals(false, customerDetailsService.findById(savedEntity.getCustomerId()).getIsMobileVerified());
		
		assertEquals(false, customerDetailsService.verifyMobileDetails(savedEntity.getCustomerId(),
				1, savedEntity.getMobile(), 1, 101010));
		
		assertEquals(true, customerDetailsService.verifyMobileDetails(savedEntity.getCustomerId(),
				1, savedEntity.getMobile(), 1, mobileVerificationDetails.getMobilePin()));
		
		assertEquals(true, customerDetailsService.findById(savedEntity.getCustomerId()).getIsMobileVerified());
		
	}
	
	
	
	@Test
	public void verifySeconadryMobileDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(mergeEntity.getCustomerId(), 1, mergeEntity.getSecondaryMobile());
		
		assertEquals(null, customerDetailsService.findById(mergeEntity.getCustomerId()).getIsSecondaryMobileVerified());
		
		
		assertEquals(false, customerDetailsService.verifyMobileDetails(mergeEntity.getCustomerId(),
				1, mergeEntity.getSecondaryMobile(), 2, 101010));
		
		assertEquals(true, customerDetailsService.verifyMobileDetails(mergeEntity.getCustomerId(),
				1, mergeEntity.getSecondaryMobile(), 2, mobileVerificationDetails.getMobilePin()));
		
		
		assertEquals(true, customerDetailsService.findById(savedEntity.getCustomerId()).getIsSecondaryMobileVerified());
		
	}
	
	
	@Test
	public void verifyEmailDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService
				.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(mergeEntity.getCustomerId(), 1, mergeEntity.getEmail());
		
		assertEquals(false, customerDetailsService.findById(mergeEntity.getCustomerId()).getIsEmailVerified());
		
		
		assertEquals(false, customerDetailsService.verifyEmailDetails(mergeEntity.getCustomerId(),
				1, mergeEntity.getEmail(), "101010"));
		
		assertEquals(true, customerDetailsService.verifyEmailDetails(mergeEntity.getCustomerId(),
				1, mergeEntity.getEmail(), emailVerificationDetails.getEmailHash()));
		
		
		assertEquals(true, customerDetailsService.findById(savedEntity.getCustomerId()).getIsEmailVerified());
		
	}
	
	
	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
			
		assertTrue(customerDetailsService
				.sendMobileVerificationDetails(mergeEntity.getCustomerId(), 1, mergeEntity.getMobile()));
		
		assertTrue(customerDetailsService
				.sendMobileVerificationDetails(mergeEntity.getCustomerId(), 1, mergeEntity.getSecondaryMobile()));
		
	}
	
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
			
		assertTrue(customerDetailsService
				.sendEmailVerificationDetails(mergeEntity.getCustomerId(), 1, mergeEntity.getEmail()));
		
			
	}
	
	
	@Test
	public void findById() throws Exception
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(savedEntity, customerDetailsService.findById(savedEntity.getCustomerId()));
	}
	
	@Test
	public void count() throws Exception
	{
		assertEquals(0, customerDetailsService.count().intValue());
		

		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(1, customerDetailsService.count().intValue());
	}

}
