package com.projectx.rest.repository.completeregister;




import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.VendorDetailsDataFixture.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;

import static com.projectx.rest.fixture.quickregister. EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister. MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister. AuthenticationDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles={"Dev"})
public class TransactionalUpdatesRepositoryTest {

	@Autowired
	CustomerDetailsRepository customerDetailsCustomRepository; 
	
	@Autowired
	VendorDetailsRepository vendorDetailsCustomRepository;
	
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVericationDetailsRepository emailVerificationDetailsRepository;
	
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Autowired
	QuickRegisterRepository quickRegisterRepository;
		
	@Before
	public void clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		emailVerificationDetailsRepository.clearTestData();
		mobileVerificationDetailsRepository.clearTestData();
		vendorDetailsCustomRepository.clearTestData();
		authenticationDetailsRepository.clearLoginDetailsForTesting();
		quickRegisterRepository.clearCustomerQuickRegister();
	}

	@Test
	public void environmentTest() {
		
	}


	@Test
	public void updateCustomerDetailsWithNewMobile()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(savedEntity.getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_TYPE_CUSTOMER),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
		
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity,updatedEntity);
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE).getKey());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}


	@Test
	public void updateCustomerDetailsWithNewEmail()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(savedEntity.getCustomerId());
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, 1, 1),
						savedEntity.getEmail(), CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setEmail(CUST_EMAIL_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
		
		assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity,updatedEntity);
		
		assertNotNull( emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		assertNull( emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
		
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}
	
	
	@Test
	public void updateCustomerWithNewSecondaryMobile()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(savedEntity.getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 2),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setSecondaryMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
		
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity,updatedEntity);
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE).getKey());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}

	
	
	@Test
	public void updateCustomerWithNewMobileNewSecondaryMobileNewEmail()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(savedEntity.getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsSec=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 2),
						CUST_SEC_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_EMAIL, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsSec);
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setSecondaryMobile(CUST_SEC_MOBILE_NEW);
		savedEntity.setEmail(CUST_EMAIL_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(2, mobileVerificationDetailsRepository.count().intValue());
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
		assertEquals(mobileVerificationDetailsSec, mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE));
		assertNotNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
		
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE_NEW).getKey());
		assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		
		assertEquals(savedEntity,updatedEntity);
		
		assertNull(mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE).getKey());
		assertNull(mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE).getKey());
		assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE_NEW).getKey());
		assertNotNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		assertEquals(2, mobileVerificationDetailsRepository.count().intValue());
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
	}
	
	
	
	
	@Test
	public void updateCustomerWithNewMobileNewSecondaryMobileNewEmailFailWithDuplicateMobile()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		CustomerDetails oldEntity=customerDetailsCustomRepository.findOne(savedEntity.getCustomerId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsSec=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 2),
						CUST_SEC_MOBILE, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						CUST_EMAIL, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(213L, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsSec);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsDuplicate);
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setSecondaryMobile(CUST_SEC_MOBILE_NEW);
		savedEntity.setEmail(CUST_EMAIL_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		assertEquals(3, mobileVerificationDetailsRepository.count().intValue());
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
		assertEquals(mobileVerificationDetailsSec, mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE));
		assertNotNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE_NEW).getKey());
		assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		
		assertEquals(oldEntity, updatedEntity);
		
			assertEquals(3, mobileVerificationDetailsRepository.count().intValue());
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count().intValue());
			
			assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
			assertEquals(mobileVerificationDetailsSec, mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE));
			assertNotNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
			
			assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
			assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE_NEW).getKey());
			assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
			
			
			assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
		
		
	}
	
	
	
	@Test
	public void updateVendorDetailsWithNewMobile()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(VENDOR_ID,ENTITY_TYPE_VENDOR ,1),
						standardVendor().getMobile(), CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(standardVendor().getMobile()));
		
		assertNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());//as we are not changing mobile until verified
		
		assertEquals(savedEntity, updatedEntity);
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW));
		
		assertNull( mobileVerificationDetailsRepository.getByMobile(standardVendor().getMobile()).getKey());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(VENDOR_ID));
	}

	
	@Test
	public void updateVendorDetailsWithNewEmail()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(standardVendor().getVendorId(), ENTITY_TYPE_VENDOR, 1),
						standardVendor().getEmail(), CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setEmail(CUST_EMAIL_NEW);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.getByEmail(standardVendor().getEmail()).getKey());
		
		assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity, updatedEntity);
		
		assertNotNull( emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		assertNull( emailVerificationDetailsRepository.getByEmail(standardVendor().getEmail()).getKey());
		
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(standardVendor().getVendorId()));
	}
	

	@Test
	public void updateVendorWithNewMobileNewEmailFailWithDuplicateMobile()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(standardVendor().getVendorId(), ENTITY_TYPE_VENDOR, 1),
						standardVendor().getMobile(), CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, 1),
						standardVendor().getEmail(), CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(213L, CUST_TYPE_CUSTOMER, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		mobileVerificationDetailsRepository.save(mobileVerificationDetailsDuplicate);
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setFirstName("RRRR");
		savedEntity.setMobile(CUST_MOBILE_NEW);
		savedEntity.setEmail(CUST_EMAIL_NEW);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		assertEquals(2, mobileVerificationDetailsRepository.count().intValue());
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(standardVendor().getMobile()));
		assertNotNull(emailVerificationDetailsRepository.getByEmail(standardVendor().getEmail()).getKey());
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		assertEquals(oldEntity, updatedEntity);
		
		
			assertEquals(2, mobileVerificationDetailsRepository.count().intValue());
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count().intValue());
			
			assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(standardVendor().getMobile()));
			assertNotNull(emailVerificationDetailsRepository.getByEmail(standardVendor().getEmail()).getKey());
			
			assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
			assertNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
			
			
			assertEquals(oldEntity, vendorDetailsCustomRepository.findOne(standardVendor().getVendorId()));
		
		
	}

	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithCustomerMobile()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
	//	assertNotNull(authenticationDetailsRepository.getCustomerAuthenticationDetailsByMobile(savedMobileDetails.getMobile()).getKey());
		
		assertNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_MOBILE_NEW,customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertNull(authenticationDetailsRepository.getByMobile(savedCustomer.getMobile()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		
	}
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithCustomerMobileWithFailure()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(savedMobileDetails.getMobile()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		
			assertFalse(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			
			assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
			
			assertEquals(2, authenticationDetailsRepository.count().intValue());
			
			assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		
		
	}
		
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithVendorMobile()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedVendor.getVendorId(), 2, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(savedAuthenticationDetails.getMobile()).getKey());
		
		assertNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_MOBILE_NEW,vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNull(authenticationDetailsRepository.getByMobile(savedAuthenticationDetails.getMobile()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW));
		
		
	}
	
	
	@Test
	public void updateMobileInDetailsEnityAndAuthenticationDetailsWithVendorMobileWithFailure()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		MobileVerificationDetails newMobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedVendor.getVendorId(), 2, 1),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(savedMobileDetails.getMobile()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		
			assertFalse(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
		
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
			
			assertEquals(2, authenticationDetailsRepository.count().intValue());
			
			assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		
		
	}

	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithCustomerEmail()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		assertNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_EMAIL_NEW,customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW));
		
		
	}
		
	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithCustomerEmailWithFailure()
	{
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(0, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		CustomerDetails savedCustomer=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedCustomer.getCustomerId(), 1, 1),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINe");
		
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), CUST_EMAIL_NEW, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedEmailDetails.getEmail()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		
			assertFalse(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), 1, 1));
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			
			assertEquals(1, emailVerificationDetailsRepository.count().intValue());
			
			assertEquals(2, authenticationDetailsRepository.count().intValue());
			
			assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		
		
	}

	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithVendorEmail()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedVendor.getVendorId(), 2, 1),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINe");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		assertNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_EMAIL_NEW,vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW));
		
		
	}
	
	
	@Test
	public void updateEmailInDetailsEnityAndAuthenticationDetailsWithVendorEmailWithFailure()
	{
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(0, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(0, authenticationDetailsRepository.count().intValue());
		
		VendorDetails savedVendor=vendorDetailsCustomRepository.save(standardVendor());
		
		EmailVerificationDetails newEmailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedVendor.getVendorId(), 2, 1),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), 0, new Date(), new Date(), "CUST_ONLINe");
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), CUST_EMAIL_NEW, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, 0, 0, new Date(), new Date(), "CUST_ONLINE");
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		
			assertFalse(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), 2, 1));
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			
			assertEquals(1, emailVerificationDetailsRepository.count().intValue());
			
			assertEquals(2, authenticationDetailsRepository.count().intValue());
			
			assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		
		
	}

	
	@Test
	public void saveNewQuickRegisterEntity()
	{
		assertEquals(0, quickRegisterRepository.findAll().size());
		
		assertEquals(0,mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0,emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(0,authenticationDetailsRepository.count().intValue());
		
		
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(standardEmailMobileCustomer().getEmail(), savedEntity.getCustomerQuickRegisterEntity().getEmail());
		
		assertEquals(standardCustomerMobileVerificationDetails().getMobile(), savedEntity.getCustomerMobileVerificationDetails().getMobile());
		
		assertEquals(standardCustomerEmailVerificationDetails().getEmail(), savedEntity.getCustomerEmailVerificationDetails().getEmail());
		
		assertEquals(standardCustomerEmailVerificationDetails().getEmail(), authenticationDetailsRepository
				.getByCustomerIdType(savedEntity.getCustomerQuickRegisterEntity().getCustomerId(), savedEntity.getCustomerQuickRegisterEntity().getCustomerType()).getEmail());
		
		assertEquals(1,quickRegisterRepository.findAll().size());
		
		assertEquals(1,mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1,emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1,authenticationDetailsRepository.count().intValue());
		
	}
	
	
	@Test
	public void saveNewQuickRegisterEntityFailedCase()
	{
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(0,mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0,emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(0,authenticationDetailsRepository.count().intValue());
		
		authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=null;
		
		savedEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer());
		
		assertEquals(new QuickRegisterEntity(), savedEntity.getCustomerQuickRegisterEntity());
		
		assertEquals(new MobileVerificationDetails(), savedEntity.getCustomerMobileVerificationDetails());
		
		assertEquals(new EmailVerificationDetails(), savedEntity.getCustomerEmailVerificationDetails());
		
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(0,mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(0,emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1,authenticationDetailsRepository.count().intValue());
		
		
	}
	

	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithCustomerEntity()
	{
		
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(1,quickRegisterRepository.findAll().size());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		
		assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		
		assertNotNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
	}

	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithCustomerEntityWithFailure()
	{
		
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerDetailsCustomRepository.save(new CustomerDetails(215L, "ABX", "ASD", null, null, CUST_MOBILE,null, CUST_EMAIL, null, 
				null, null, null, null, null, null, null, null, null, null));
		
		
		assertEquals(1,quickRegisterRepository.findAll().size());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		
			assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
			assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		
			assertEquals(1,quickRegisterRepository.findAll().size());
		
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
	}
	
	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithVendorEntity()
	{
		
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileVendor());
		
		assertEquals(1,quickRegisterRepository.findAll().size());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		
		assertNotNull(customerOrVendorDetailsDTO.getVendorDetails());
		
		assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
	}

	
	@Test
	public void deleteQuickRegisterEntityCreateDetailsWithVendorEntityWithFailure()
	{
		
		assertEquals(0,quickRegisterRepository.findAll().size());
		
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterRepository.save(standardEmailMobileVendor());
		
		vendorDetailsCustomRepository.save(new VendorDetails(215L, "ASD", "AES",null, null, standardEmailMobileVendor().getMobile(),
				null, standardEmailMobileVendor().getEmail(), null, 
				null, null, null, null));
		
		
		assertEquals(1,quickRegisterRepository.findAll().size());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		
			assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		
			assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
			assertEquals(1,quickRegisterRepository.findAll().size());
		
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
	}


}
