package com.projectx.rest.services.completeregister;

import static com.projectx.rest.fixture.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")

public class VendorDetailsServiceTest {

	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	AddressService addressService;
	
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	
	
	@Before
	@After
	public void setUp()
	{
		vendorDetailsService.clearTestData();
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
		assertEquals(0, vendorDetailsService.count().intValue());
		
		assertNull(vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileVendor());
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendorCreatedFromQuickRegister(savedEntity.getVendorId()), savedEntity);
		
		assertEquals(1, vendorDetailsService.count().intValue());
		
	}
	
	
	@Test
	public void update()
	{
		
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileVendor());
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendorCreatedFromQuickRegister(savedEntity.getVendorId()), savedEntity);
		
		assertEquals(1, vendorDetailsService.count().intValue());
		
		savedEntity.setDateOfBirth(new Date());
		savedEntity.setFirstName("Ted");
		savedEntity.setLastName("Mosby");
		
		assertEquals(savedEntity, vendorDetailsService.updateVendorDetails(savedEntity));
		
		assertEquals(1, vendorDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void findById()
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileVendor());
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendorCreatedFromQuickRegister(savedEntity.getVendorId()), savedEntity);
		
		assertEquals(1, vendorDetailsService.count().intValue());
		
		assertEquals(savedEntity, vendorDetailsService.findById(savedEntity.getVendorId()));
	}
	
	
	
	@Test
	public void verifyMobileDetails() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendorCreatedFromQuickRegister(savedEntity.getVendorId()), savedEntity);
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getVendorId(),
						ENTITY_TYPE_VENDOR, MOB_TYPE_PRIMARY);
		
		assertFalse(savedEntity.getIsMobileVerified());
		
		assertTrue(vendorDetailsService.verifyMobileDetails(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR, MOB_TYPE_PRIMARY, mobileVerificationDetails.getMobilePin()));
		
		assertTrue(vendorDetailsService.findById(savedEntity.getVendorId()).getIsMobileVerified());
	}
	
	
	
	@Test
	public void verifyEmailDetails() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendor(savedEntity), vendorDetailsService.updateVendorDetails(standardVendor(savedEntity)));
		
		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService.getByEntityIdTypeAndEmailType(savedEntity.getVendorId(),
						ENTITY_TYPE_VENDOR, EMAIL_TYPE_PRIMARY);
		
		assertFalse(savedEntity.getIsEmailVerified());
		
		assertTrue(vendorDetailsService.verifyEmailDetails(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR, EMAIL_TYPE_PRIMARY, emailVerificationDetails.getEmailHash()));
		
		assertTrue(vendorDetailsService.findById(savedEntity.getVendorId()).getIsEmailVerified());
	}


	
	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertTrue(vendorDetailsService.sendMobileVerificationDetails(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR, MOB_TYPE_PRIMARY));
	}
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertTrue(vendorDetailsService.sendEmailVerificationDetails(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR, EMAIL_TYPE_PRIMARY));
	}
	
	@Test
	public void count()
	{
		assertEquals(0, vendorDetailsService.count().intValue());
	}
	
	@Test
	public void clearTestData() throws Exception
	{
	
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		vendorDetailsService.clearTestData();
		
		assertEquals(0, vendorDetailsService.count().intValue());
	}
	
	
	/*
	@Test
	public void checkIfMobileSaved() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals("EXIST", vendorDetailsService.checkIfMobileSaved(savedEntity.getVendorId(),2,MOB_TYPE_PRIMARY, savedEntity.getMobile()));
		
		assertEquals("NOTEXIST", vendorDetailsService.checkIfMobileSaved(savedEntity.getVendorId(),2,MOB_TYPE_PRIMARY, CUST_MOBILE));
		
		mobileVerificationService.saveDetails(standardCustomerMobileVerificationDetails());
		
		assertEquals("EXISTWITHOTHERENTITY", vendorDetailsService
				.checkIfMobileSaved(savedEntity.getVendorId(),2,MOB_TYPE_PRIMARY, standardCustomerMobileVerificationDetails().getMobile()));
	}


	@Test
	public void checkIfEmailSaved() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals("EXIST", vendorDetailsService.checkIfEmailSaved(savedEntity.getVendorId(),2, EMAIL_TYPE_PRIMARY,savedEntity.getEmail()));
		
		assertEquals("NOTEXIST", vendorDetailsService.checkIfEmailSaved(savedEntity.getVendorId(),2,EMAIL_TYPE_PRIMARY, CUST_EMAIL));
		
		emailVerificationService.saveDetails(standardCustomerEmailVerificationDetails());
		
		assertEquals("EXISTWITHOTHERENTITY", vendorDetailsService
				.checkIfEmailSaved(savedEntity.getVendorId(),2,EMAIL_TYPE_PRIMARY, standardCustomerEmailVerificationDetails().getEmail()));
	}

	@Test
	public void saveMobileVerificationDetails() 
	{
		assertEquals(0, mobileVerificationService.count().intValue());
		
		assertEquals(standardCustomerMobileVerificationDetails(), vendorDetailsService.saveMobileVerificationDetails(standardCustomerMobileVerificationDetails()));
		
		assertEquals(1, mobileVerificationService.count().intValue());
	}
	
	@Test
	public void saveEmailVerificationDetails() 
	{
		assertEquals(0, emailVerificationService.count().intValue());
		
		assertEquals(standardCustomerEmailVerificationDetails(), vendorDetailsService.saveEmailVerificationDetails(standardCustomerEmailVerificationDetails()));
		
		assertEquals(1, emailVerificationService.count().intValue());
	}
	*/

	
}
