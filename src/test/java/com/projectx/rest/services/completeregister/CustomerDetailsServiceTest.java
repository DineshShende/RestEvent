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
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	private Integer ENTITY_TYPE_VENDOR=2;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ENTITY_TYPE_SECONDARY=2;
	
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
		
		assertNull(customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(standardEmailMobileCustomer()));
		
		QuickRegisterEntity savedQuickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileCustomer());
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntity(savedEntity.getCustomerId()), savedEntity);
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	
	
	
	@Test
	public void mergeCustomerDetails()
	{
		assertEquals(0, customerDetailsService.count().intValue());
		
		QuickRegisterEntity savedQuickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileCustomer());
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntity(savedEntity.getCustomerId()), savedEntity);

		assertNull(mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getKey());
		
		assertNull(mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY).getKey());
		
		assertNull(emailVerificationService.getByEntityIdTypeAndEmailType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getKey());
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertNull(mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getKey());
		
		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY).getMobile());
		
		assertNull(savedEntity.getEmail(),emailVerificationService.getByEntityIdTypeAndEmailType(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getKey());
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(savedEntity,savedEntity.getMobile(),savedEntity.getSecondaryMobile(),savedEntity.getEmail()), mergeEntity);
		
		assertEquals(1, customerDetailsService.count().intValue());
		
	}
	

	
	@Test
	public void mergeCustomerDetailsWithOutCreatingByQuickRegisterEntity()
	{
		assertEquals(0, customerDetailsService.count().intValue());
	
		assertNull(mobileVerificationService.getByEntityIdTypeAndMobileType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getKey());
		
		assertNull(mobileVerificationService.getByEntityIdTypeAndMobileType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY).getKey());
		
		assertNull(emailVerificationService.getByEntityIdTypeAndEmailType(standardCustomerDetails(standardCustomerFromQuickEntity()).getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY).getKey());
	
		
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
		
		QuickRegisterEntity savedQuickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileCustomer());
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);
		
		assertEquals(standardCustomerFromQuickEntity(savedEntity.getCustomerId()), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(standardCustomerDetails(savedEntity), mergeEntity.getMobile(), null, mergeEntity.getEmail()), mergeEntity);
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()).getSecondaryMobile(),
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY).getMobile());
		
		assertEquals(null,customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertTrue( mobileVerificationService.reSetMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY));
		
		
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		assertTrue(customerDetailsService.verifyMobileDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY, mobileVerificationDetails.getMobilePin()));

		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity));
		
		assertNull(mobileVerificationService.getByMobile(mergeEntity.getMobile()).getKey());
		
		assertNotNull(mobileVerificationService.getByMobile(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity).getSecondaryMobile()).getKey());
		
		assertTrue( mobileVerificationService.reSetMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY));
		
		mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY);
		
		assertEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertNotEquals(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertTrue(customerDetailsService.verifyMobileDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY, mobileVerificationDetails.getMobilePin()));

		assertNotEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
		assertEquals(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity).getSecondaryMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getSecondaryMobile());
		
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
		
		assertEquals(standardCustomerFromQuickEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(savedEntity,mergeEntity.getMobile(),mergeEntity.getSecondaryMobile(),mergeEntity.getEmail()), mergeEntity);
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewMobile(mergeEntity));
		
		assertNull(mobileVerificationService.getByMobile(mergeEntity.getMobile()).getKey());
		
		assertNotNull(mobileVerificationService.getByMobile(standardCustomerDetailsWithNewMobile(mergeEntity).getMobile()).getKey());
		
		assertTrue( mobileVerificationService.reSetMobilePin(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY));
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		assertEquals(standardCustomerDetails(savedEntity).getMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getMobile());
		
		assertNotEquals(standardCustomerDetailsWithNewMobile(mergeEntity).getMobile(),customerDetailsService.findById(mergeEntity.getCustomerId()).getMobile());
		
		assertTrue(customerDetailsService.verifyMobileDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY, mobileVerificationDetails.getMobilePin()));

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
		
		assertEquals(standardCustomerFromQuickEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		assertEquals(1, emailVerificationService.count().intValue());
		
		assertEquals(standardCustomerDetailsWithOldMobileSceMobileEmail(savedEntity,mergeEntity.getMobile(),mergeEntity.getSecondaryMobile(),mergeEntity.getEmail()), mergeEntity);
		
		assertNotNull(emailVerificationService.getByEmail(standardCustomerDetails(savedEntity).getEmail()).getKey());
		
		assertNull(emailVerificationService.getByEmail(standardCustomerDetailsWithNewEmail(mergeEntity).getEmail()).getKey());
		
		mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewEmail(mergeEntity));
		
		assertNull(emailVerificationService.getByEmail(standardCustomerDetails(savedEntity).getEmail()).getKey());
		
		assertNotNull(emailVerificationService.getByEmail(standardCustomerDetailsWithNewEmail(mergeEntity).getEmail()).getKey());

		assertEquals(standardCustomerDetails(savedEntity).getEmail(),customerDetailsService.findById(mergeEntity.getCustomerId()).getEmail());
		
		assertNotEquals(standardCustomerDetailsWithNewEmail(mergeEntity).getEmail(),customerDetailsService.findById(mergeEntity.getCustomerId()).getEmail());
		
		assertTrue( emailVerificationService.reSetEmailHash(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY));
		
		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService.getByEntityIdTypeAndEmailType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY);
		
		
		assertTrue(customerDetailsService.verifyEmailDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY, emailVerificationDetails.getEmailHash()));

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
		
		assertEquals(standardCustomerFromQuickEntity(), savedEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
		
		Address oldHomeAddress=mergeEntity.getHomeAddressId();
		
		assertNotNull(addressService.findById(oldHomeAddress.getAddressId()));
			
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
	
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getByEntityIdTypeAndMobileType(savedEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER,MOB_TYPE_PRIMARY);
		
		assertEquals(false, customerDetailsService.findById(savedEntity.getCustomerId()).getIsMobileVerified());
		
		assertEquals(false, customerDetailsService.verifyMobileDetails(savedEntity.getCustomerId(),
				 MOB_TYPE_PRIMARY, ENTITY_TYPE_CUSTOMER, 101010));
		
		assertEquals(true, customerDetailsService.verifyMobileDetails(savedEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, MOB_TYPE_PRIMARY, mobileVerificationDetails.getMobilePin()));
		
		assertEquals(true, customerDetailsService.findById(savedEntity.getCustomerId()).getIsMobileVerified());
		
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
				.getByEntityIdTypeAndEmailType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, EMAIL_TYPE_PRIMARY);
		
		assertEquals(false, customerDetailsService.findById(mergeEntity.getCustomerId()).getIsEmailVerified());
		
		
		assertEquals(false, customerDetailsService.verifyEmailDetails(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER,EMAIL_TYPE_PRIMARY, "101010"));
		
		assertEquals(true, customerDetailsService.verifyEmailDetails(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, EMAIL_TYPE_PRIMARY, emailVerificationDetails.getEmailHash()));
		
		
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
		
		assertTrue( customerDetailsService.sendMobileVerificationDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, 2));
		
		assertEquals(false, customerDetailsService.verifyMobileDetails(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, MOB_TYPE_SECONDARY, 101010));
		
		assertNotEquals(standardCustomerDetails(savedEntity).getSecondaryMobile(), customerDetailsService.findById(savedEntity.getCustomerId()).getSecondaryMobile());
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getByEntityIdTypeAndMobileType(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, MOB_TYPE_SECONDARY);
		
		assertEquals(true, customerDetailsService.verifyMobileDetails(mergeEntity.getCustomerId(),
				ENTITY_TYPE_CUSTOMER, MOB_TYPE_SECONDARY, mobileVerificationDetails.getMobilePin()));
		
		
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
	
			
		assertTrue(customerDetailsService
				.sendMobileVerificationDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, MOB_TYPE_PRIMARY));
		
		assertTrue(customerDetailsService
				.sendMobileVerificationDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, MOB_TYPE_PRIMARY));
		
	}
	
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));
	
			
		assertTrue(customerDetailsService
				.sendEmailVerificationDetails(mergeEntity.getCustomerId(), ENTITY_TYPE_CUSTOMER, EMAIL_TYPE_PRIMARY));
		
			
	}
	
}



/*
@Test
public void handleChangeOfMobileWithSecMobileSucessBeforeMerge() throws Exception
{
	assertEquals(0, customerDetailsService.count().intValue());
	
	QuickRegisterEntity quickRegisterEntity=quickRegisterService
			.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
	
	CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
	
	assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
	
	
	assertEquals("SUCESS", customerDetailsService.handleChangeOfMobile(standardCustomerDetails(savedEntity),savedEntity , "SUCESS", 2));

	
	//assertEquals(standardCustomerDetails(savedEntity), mergeEntity);
	
	//mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsWithNewMobile(mergeEntity));
	
	assertEquals(2, mobileVerificationService.count().intValue());
	
	assertEquals(1, customerDetailsService.count().intValue());
	
}



@Test
public void handleChangeOfMobileWithSecMobileSucess() throws Exception
{
	assertEquals(0, customerDetailsService.count().intValue());
	
	QuickRegisterEntity quickRegisterEntity=quickRegisterService
			.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
	
	CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
	
	assertEquals(standardCustomerDetailsCopiedFromQuickRegisterEntity(), savedEntity);
	
	CustomerDetails mergeEntity=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(savedEntity));

	mobileVerificationService.saveCustomerMobileVerificationDetails(new MobileVerificationDetails(new MobileVerificationDetailsKey(231L, 1, 1), 8888888888L, 1234, 0, 0, new Date(), new Date(), "CUST_ONLINE"));
	
	assertEquals("SUCESS", customerDetailsService.handleChangeOfMobile(standardCustomerDetailsWithNewSecondaryMobile(mergeEntity),mergeEntity , "SUCESS", 2));

	assertEquals(2, mobileVerificationService.count().intValue());
	
	assertEquals(1, customerDetailsService.count().intValue());
	
	@Test
	public void saveAndCheckIfExistMobileVerificationDetails() throws Exception
	{
		assertEquals("NOTEXIST",customerDetailsService.checkIfMobileSaved(CUST_ID, CUST_TYPE_CUSTOMER,MOB_TYPE_PRIMARY, CUST_MOBILE));
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals("EXIST",customerDetailsService.checkIfMobileSaved(savedEntity.getCustomerId(), 1,MOB_TYPE_PRIMARY, savedEntity.getMobile()));
		
	}
	
	@Test
	public void saveAndCheckIfExistEmailVerificationDetails() throws Exception
	{
		assertEquals("NOTEXIST",customerDetailsService.checkIfMobileSaved(CUST_ID, CUST_TYPE_CUSTOMER,MOB_TYPE_PRIMARY, CUST_MOBILE));
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		CustomerDetails savedEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		assertEquals("EXIST",customerDetailsService.checkIfMobileSaved(savedEntity.getCustomerId(), 1,MOB_TYPE_PRIMARY, savedEntity.getMobile()));
		
	}
	*/

