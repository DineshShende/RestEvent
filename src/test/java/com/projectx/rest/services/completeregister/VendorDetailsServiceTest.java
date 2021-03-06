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
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.config.Constants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class VendorDetailsServiceTest {

	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	
	private Integer ACTOR_ENTITY_SELF_WEB=1;
	
	
	@Before
	@After
	public void setUp()
	{
		vendorDetailsService.clearTestData();
		mobileVerificationService.clearTestData();
		emailVerificationService.clearTestData();
		quickRegisterService.clearDataForTesting();
		authenticationService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
		
	}

	
	@Test
	public void createCustomerDetailsFromQuickRegisterEntity()
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		assertNull(vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileVendor()));
		
		QuickRegisterEntity quickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
				
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAdd(savedEntity.getVendorId()), savedEntity);
		
		assertEquals(1, vendorDetailsService.count().intValue());
		
	}
	
	
	@Test
	public void update()
	{
		
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAdd(savedEntity.getVendorId()), savedEntity);
		
		assertEquals(1, vendorDetailsService.count().intValue());
		
		savedEntity.setDateOfBirth(new Date());
		savedEntity.setFirstName("Ted");
		savedEntity.setLastName("Mosby");
		savedEntity.setIsSecondaryMobileVerified(false);
		
		assertEquals(savedEntity, vendorDetailsService.updateVendorDetails(savedEntity));
		
		assertEquals(1, vendorDetailsService.count().intValue());
	}
	
	
	
	@Test
	public void findById()
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAdd(savedEntity.getVendorId()), savedEntity);
		
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
		
		assertEquals(standardVendorCreatedFromQuickRegisterWithDefaultHomeAdd(savedEntity.getVendorId()), savedEntity);
		
		assertTrue(mobileVerificationService.sendMobilePin(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR,
				ENTITY_TYPE_PRIMARY, ACTOR_ENTITY_SELF_WEB, savedEntity.getVendorId()));
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getVendorId(),
						ENTITY_TYPE_VENDOR, MOB_TYPE_PRIMARY);
		
		assertFalse(savedEntity.getIsMobileVerified());
		
		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR, MOB_TYPE_PRIMARY, 
				mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,savedEntity.getVendorId()));
		
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
		
		assertTrue(emailVerificationService.sendEmailHash(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
				ACTOR_ENTITY_SELF_WEB, savedEntity.getVendorId()));
		
		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService.getByEntityIdTypeAndEmailType(savedEntity.getVendorId(),
						ENTITY_TYPE_VENDOR, EMAIL_TYPE_PRIMARY);
		
		assertFalse(savedEntity.getIsEmailVerified());
		
		
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR, EMAIL_TYPE_PRIMARY, 
				emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY,savedEntity.getVendorId()));
		
		assertTrue(vendorDetailsService.findById(savedEntity.getVendorId()).getIsEmailVerified());
	}


	
	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertTrue(mobileVerificationService.sendMobilePin(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR,
				MOB_TYPE_PRIMARY,CUST_UPDATED_BY,savedEntity.getVendorId()));
	}
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		assertEquals(0, vendorDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		VendorDetails savedEntity=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertTrue(emailVerificationService.sendEmailHash(savedEntity.getVendorId(), ENTITY_TYPE_VENDOR,
				EMAIL_TYPE_PRIMARY,CUST_UPDATED_BY,savedEntity.getVendorId()));
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
	
	
	
	
}
