package com.projectx.rest.services.completeregister;

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
import org.springframework.transaction.annotation.Transactional;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*; 

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
//@Transactional
public class CustomerDetailsServiceTest {

	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
	private Integer ACTOR_ENTITY_SELF_WEB=1;
	
	@Before
	@After
	public void setUp()
	{
		customerDetailsService.clearTestData();
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
		assertEquals(0, customerDetailsService.count().intValue());
		
		assertNull(customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer()));
		
		QuickRegisterEntity savedQuickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntityWithDefaultHomeAdd(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER), savedEntity);
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	
	
	@Test
	public void mergeCustomerDetails()
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity savedQuickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntityWithDefaultHomeAdd(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER), savedEntity);

		MobileVerificationDetails mobileVerificationDetailsNull=null;
		
		
		
		try{
			mobileVerificationDetailsNull=mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
			
			assertNull(mobileVerificationDetailsNull);
			
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		
		
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		try{
			mobileVerificationDetailsNull=mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
			
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY).getMobile());
		
		EmailVerificationDetails emailVerificationDetailsNull=null;
		
		try{
			emailVerificationDetailsNull=emailVerificationService.getByEntityIdTypeAndEmailType(savedEntity.getCustomerId(),
					ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
			
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergeEntity);
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	

	
	@Test
	public void mergeCustomerDetailsWithOutCreatingByQuickRegisterEntity()
	{
		assertEquals(0, customerDetailsService.count().intValue());
	
		MobileVerificationDetails mobileVerificationDetailsNull=null;
		
		try{
			mobileVerificationDetailsNull=mobileVerificationService.getByEntityIdTypeAndMobileType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
			
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		
		try{
			mobileVerificationDetailsNull=mobileVerificationService.getByEntityIdTypeAndMobileType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
			
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		
		EmailVerificationDetails emailVerificationDetailsNull=null;
		
		try{
			emailVerificationDetailsNull=emailVerificationService.getByEntityIdTypeAndEmailType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
					ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
			
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()).getMobile(),mobileVerificationService.getByEntityIdTypeAndMobileType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getMobile());
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()).getSecondaryMobile(),mobileVerificationService.getByEntityIdTypeAndMobileType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY).getMobile());
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()).getEmail(),emailVerificationService.getByEntityIdTypeAndEmailType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getEmail());
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}

	
	
	@Test
	public void mergeCustomerDetailsWithNewSecondaryMobile()
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity savedQuickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntityWithDefaultHomeAdd(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(standardCustomerDetails(savedEntity), mergeEntity.getMobile(), null, mergeEntity.getEmail()), mergeEntity);
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()).getSecondaryMobile(),
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY).getMobile());
		
		assertEquals(null,customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertTrue( mobileVerificationService.reSetMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY,
				CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_SECONDARY, mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,mergeEntity.getCustomerId()));

		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity));
		
		MobileVerificationDetails mobileVerificationDetailsNull=null;
		
		try{
			mobileVerificationDetailsNull=mobileVerificationService.getByMobile(mergeEntity.getMobile());
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		assertNotNull(mobileVerificationService.getByMobile(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity).getSecondaryMobile()).getKey());
		
		assertTrue( mobileVerificationService.reSetMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY,
				CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertNotEquals(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY, mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,mergeEntity.getCustomerId()));

		assertNotEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertEquals(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertEquals(2, mobileVerificationService.count().intValue());
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}

	
	
	@Test
	public void mergeCustomerDetailsWithNewMobile() throws Exception
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntityWithDefaultHomeAdd(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(savedEntity,mergeEntity.getMobile(),mergeEntity.getSecondaryMobile(),mergeEntity.getEmail()), mergeEntity);
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewMobile(mergeEntity));
		
		MobileVerificationDetails mobileVerificationDetailsNull=null;
		
		try{
			mobileVerificationDetailsNull=mobileVerificationService.getByMobile(mergeEntity.getMobile());
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		assertNotNull(mobileVerificationService.getByMobile(standardCustomerDetailsWithNewMobile(mergeEntity).getMobile()).getKey());
		
		assertTrue( mobileVerificationService.reSetMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		assertEquals(standardCustomerDetails(savedEntity).getMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getMobile());
		
		assertNotEquals(standardCustomerDetailsWithNewMobile(mergeEntity).getMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getMobile());
		
		assertTrue(mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY, mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,mergeEntity.getCustomerId()));

		assertNotEquals(standardCustomerDetails(savedEntity).getMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getMobile());
		
		assertEquals(standardCustomerDetailsWithNewMobile(mergeEntity).getMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getMobile());

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
		
		assertEquals(standardCustomerFromQuickEntityWithDefaultHomeAdd(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(1, emailVerificationService.count().intValue());
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(savedEntity,mergeEntity.getMobile(),mergeEntity.getSecondaryMobile(),mergeEntity.getEmail()), mergeEntity);
		
		assertNotNull(emailVerificationService.getByEmail(standardCustomerDetails(savedEntity).getEmail()).getKey());
		
		EmailVerificationDetails emailVerificationDetailsNull=null;
		
		try{
			emailVerificationDetailsNull=emailVerificationService.getByEmail(standardCustomerDetailsWithNewEmail(mergeEntity).getEmail());
		}
		catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewEmail(mergeEntity));
		
		try{
			emailVerificationDetailsNull=emailVerificationService.getByEmail(standardCustomerDetails(savedEntity).getEmail());
		}
		catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
		assertNotNull(emailVerificationService.getByEmail(standardCustomerDetailsWithNewEmail(mergeEntity).getEmail()).getKey());

		assertEquals(standardCustomerDetails(savedEntity).getEmail(),customerDetailsService.findById(mergeEntity.getCustomerId()).getEmail());
		
		assertNotEquals(standardCustomerDetailsWithNewEmail(mergeEntity).getEmail(),customerDetailsService.findById(mergeEntity.getCustomerId()).getEmail());
		
		assertTrue( emailVerificationService.reSetEmailHash(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService.getByEntityIdTypeAndEmailType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		
		assertTrue(emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY, emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY,mergeEntity.getCustomerId()));

		assertNotEquals(standardCustomerDetails(savedEntity).getEmail(),customerDetailsService.findById(mergeEntity.getCustomerId()).getEmail());
		
		assertEquals(standardCustomerDetailsWithNewEmail(mergeEntity).getEmail(),customerDetailsService.findById(mergeEntity.getCustomerId()).getEmail());

		
		
		assertEquals(1, emailVerificationService.count().intValue());
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	
	
	
	@Test
	public void mergeCustomerDetailsWithHomeAddressPartialChanges() throws Exception
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntityWithDefaultHomeAdd(savedEntity.getCustomerId(),ENTITY_TYPE_CUSTOMER), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		Address oldHomeAddress=mergeEntity.getHomeAddressId();
		
		//assertNotNull(addressService.findById(oldHomeAddress.getAddressId()));
			
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(savedEntity,mergeEntity.getMobile(),mergeEntity.getSecondaryMobile(),mergeEntity.getEmail()), mergeEntity);
		
		mergeEntity.getHomeAddressId().setAddressLine("AT-BAYAJI NAGAR");
		mergeEntity.getHomeAddressId().setCity("PUNE");
		
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(mergeEntity);
		
	//	assertNull(addressService.findById(oldHomeAddress.getAddressId()));
		
		assertEquals("AT-BAYAJI NAGAR", customerDetailsService.findById(mergeEntity.getCustomerId()).getHomeAddressId().getAddressLine());
		assertEquals("PUNE", customerDetailsService.findById(mergeEntity.getCustomerId()).getHomeAddressId().getCity());
		
		assertEquals(oldHomeAddress.getAddressId(), mergeEntity.getHomeAddressId().getAddressId());
		
		assertEquals(1, customerDetailsService.count().intValue());
		
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
	
	
	
	
	@Test
	public void verifyMobileDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
	
		assertEquals(false, customerDetailsService.findById(savedEntity.getCustomerId()).getIsMobileVerified());
		
		assertEquals(false, mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(savedEntity.getCustomerId(),
				 MOB_TYPE_PRIMARY, ENTITY_TYPE_CUSTOMER, 101010,CUST_UPDATED_BY,savedEntity.getCustomerId()));
		
		
		
		assertTrue(mobileVerificationService.sendMobilePin(savedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER,
				ENTITY_TYPE_PRIMARY, ACTOR_ENTITY_SELF_WEB, savedEntity.getCustomerId()));
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER,MOB_TYPE_PRIMARY);
		
		assertEquals(true, mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, MOB_TYPE_PRIMARY, mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,savedEntity.getCustomerId()));
		
		assertEquals(true, customerDetailsService.findById(savedEntity.getCustomerId()).getIsMobileVerified());
		
	}
	
	
	@Test
	public void verifyEmailDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
		
		
		
		assertEquals(false, customerDetailsService.findById(mergeEntity.getCustomerId()).getIsEmailVerified());
		
		
		assertEquals(false, emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER,EMAIL_TYPE_PRIMARY, "101010",CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		assertTrue(emailVerificationService.sendEmailHash(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				ACTOR_ENTITY_SELF_WEB, mergeEntity.getCustomerId()));
		
		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService
				.getByEntityIdTypeAndEmailType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, EMAIL_TYPE_PRIMARY);
		
		
		assertEquals(true, emailVerificationService.verifyEmailHashUpdateStatusAndSendPassword(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, EMAIL_TYPE_PRIMARY, emailVerificationDetails.getEmailHash(),CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		
		assertEquals(true, customerDetailsService.findById(savedEntity.getCustomerId()).getIsEmailVerified());
		
	}
	
	
	@Test
	public void verifySeconadryMobileDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
		assertEquals(false, customerDetailsService.findById(mergeEntity.getCustomerId()).getIsSecondaryMobileVerified());
		
		assertTrue( mobileVerificationService.sendMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, 2,CUST_UPDATED_BY,
				mergeEntity.getCustomerId()));
		
		assertEquals(false, mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, MOB_TYPE_SECONDARY, 101010,CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		assertNotEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(), customerDetailsService.findById(savedEntity.getCustomerId()).getSecondaryMobile());
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, MOB_TYPE_SECONDARY);
		
		assertEquals(true, mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, MOB_TYPE_SECONDARY, mobileVerificationDetails.getMobilePin(),CUST_UPDATED_BY,mergeEntity.getCustomerId()));
		
		
		assertEquals(true, customerDetailsService.findById(savedEntity.getCustomerId()).getIsSecondaryMobileVerified());
		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(), customerDetailsService.findById(savedEntity.getCustomerId()).getSecondaryMobile());
	}
	

	
	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
			
		assertTrue(mobileVerificationService
				.sendMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, MOB_TYPE_PRIMARY,CUST_UPDATED_BY,
						mergeEntity.getCustomerId()));
		
		assertTrue(mobileVerificationService
				.sendMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, MOB_TYPE_PRIMARY,CUST_UPDATED_BY,
						mergeEntity.getCustomerId()));
		
	}
	
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
			
		assertTrue(emailVerificationService
				.sendEmailHash(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, EMAIL_TYPE_PRIMARY,CUST_UPDATED_BY,
						mergeEntity.getCustomerId()));
		
			
	}
	
}





