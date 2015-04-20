package com.projectx.rest.repository.completeregister;




import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
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
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.completeregister.UpdateEmailInDetailsAndAuthenticationDetailsFailedException;
import com.projectx.rest.exception.repository.completeregister.UpdateMobileInDetailsAndAuthentionDetailsFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;

import static com.projectx.rest.fixture.quickregister. EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister. MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister. AuthenticationDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
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
		
	private static Integer ENTITY_TYPE_PRIMARY=1;
	
	private static Integer ENTITY_TYPE_SECONDARY=2;
	
	private static Integer ENTITY_TYPE_CUSTOMER=1;
	
	private static Integer ENTITY_TYPE_VENDOR=2;
	
	private static Integer ZERO_COUNT=0;
	
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
		
		mobileVerificationDetails=null;
		try
		{
		mobileVerificationDetails=mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW);
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity,updatedEntity);
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		try
		{
		mobileVerificationDetails=mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE);
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
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
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						savedEntity.getEmail(), CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(), CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setEmail(CUST_EMAIL_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
		
		emailVerificationDetails=null;
		
		try{
			emailVerificationDetails=emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
		}
		catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetails);
		}
		
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity,updatedEntity);
		
		assertNotNull( emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		
		try{
			emailVerificationDetails=emailVerificationDetailsRepository.getByEmail(CUST_EMAIL);
		}
		catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetails);
		}
		
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY),
						CUST_MOBILE, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),  CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setSecondaryMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
		
		mobileVerificationDetails=null;
		
		try{
			mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW);
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity,updatedEntity);
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		try{
			mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE);
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),  CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails mobileVerificationDetailsSec=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY),
						CUST_SEC_MOBILE, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(), CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
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
		
		mobileVerificationDetails=null;
		
		try{
			mobileVerificationDetails=mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW);
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		
		mobileVerificationDetailsSec=null;
		
		try{
			mobileVerificationDetailsSec=mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE_NEW);
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsSec);
		}
		
		emailVerificationDetails=null;
		
		try{
			emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetails);
		}
		CustomerDetails updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());
		savedEntity.setSecondaryMobile(oldEntity.getSecondaryMobile());
		savedEntity.setEmail(oldEntity.getEmail());
		
		
		assertEquals(savedEntity,updatedEntity);
		
		mobileVerificationDetails=null;
		
		try{
			mobileVerificationDetails=mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE);
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		
		mobileVerificationDetailsSec=null;
		
		try{
			mobileVerificationDetailsSec=mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE);
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsSec);
		}
		
		emailVerificationDetails=null;
		
		try{
			emailVerificationDetailsRepository.getByEmail(CUST_EMAIL);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetails);
		}
		
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),  CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails mobileVerificationDetailsSec=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_SECONDARY),
						CUST_SEC_MOBILE, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),  CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),  CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(213L, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),  CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		
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
		
		MobileVerificationDetails mobileVerificationDetailsNull=null;
		
		try
		{
			mobileVerificationDetailsNull=mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE_NEW);
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		EmailVerificationDetails emailVerificationDetailsNull=null;
		
		try
		{
			emailVerificationDetailsNull=emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
		CustomerDetails updatedEntity=null;
		
		try{		
			updatedEntity=transactionalUpdatesRepository.updateCustomerDetails(savedEntity);
		}catch(CustomerDetailsTransactionalUpdateFailedException e)
		{
			assertNull(updatedEntity);
		}
		
		
		//assertEquals(oldEntity, updatedEntity);
		
			assertEquals(3, mobileVerificationDetailsRepository.count().intValue());
			assertEquals(1, customerDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count().intValue());
			
			assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE));
			assertEquals(mobileVerificationDetailsSec, mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE));
			assertNotNull(emailVerificationDetailsRepository.getByEmail(CUST_EMAIL).getKey());
			
			assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
					
			mobileVerificationDetailsNull=null;
			
			try
			{
				mobileVerificationDetailsNull=mobileVerificationDetailsRepository.getByMobile(CUST_SEC_MOBILE_NEW);
			}catch(MobileVerificationDetailsNotFoundException e)
			{
				assertNull(mobileVerificationDetailsNull);
			}
			
			emailVerificationDetailsNull=null;
			
			try
			{
				emailVerificationDetailsNull=emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
			}catch(EmailVerificationDetailNotFoundException e)
			{
				assertNull(emailVerificationDetailsNull);
			}
			
			assertEquals(oldEntity, customerDetailsCustomRepository.findOne(CUST_ID));
		
		
	}
	
	
	
	@Test
	public void updateVendorDetailsWithNewMobile()
	{
		assertEquals(0, vendorDetailsCustomRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsCustomRepository.save(standardVendor());
		
		VendorDetails oldEntity=vendorDetailsCustomRepository.findOne(standardVendor().getVendorId());
		
		MobileVerificationDetails mobileVerificationDetails=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(VENDOR_ID,ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						standardVendor().getMobile(), CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		mobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		savedEntity.setMobile(CUST_MOBILE_NEW);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(standardVendor().getMobile()));
		
		MobileVerificationDetails mobileVerificationDetailsNull=null;
		
		try{
			mobileVerificationDetailsNull=mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW);
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		savedEntity.setMobile(oldEntity.getMobile());//as we are not changing mobile until verified
		
		assertEquals(savedEntity, updatedEntity);
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW));
		
		mobileVerificationDetailsNull=null;
		
		try{
			mobileVerificationDetailsNull=mobileVerificationDetailsRepository.getByMobile(standardVendor().getMobile());
		}catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetailsNull);
		}
		
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
				new EmailVerificationDetails(new EmailVerificationDetailsKey(standardVendor().getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						standardVendor().getEmail(), CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		emailVerificationDetailsRepository.save(emailVerificationDetails);
		
		savedEntity.setEmail(CUST_EMAIL_NEW);
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertNotNull(emailVerificationDetailsRepository.getByEmail(standardVendor().getEmail()).getKey());
		
		EmailVerificationDetails emailVerificationDetailsNull=null;
		
		try{
			emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
		
		VendorDetails updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		
		savedEntity.setEmail(oldEntity.getEmail());
		
		assertEquals(savedEntity, updatedEntity);
		
		assertNotNull( emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		try{
			emailVerificationDetailsRepository.getByEmail(standardVendor().getEmail());
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(standardVendor().getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						standardVendor().getMobile(), CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		EmailVerificationDetails emailVerificationDetails=
				new EmailVerificationDetails(new EmailVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						standardVendor().getEmail(), CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails mobileVerificationDetailsDuplicate=
				new MobileVerificationDetails(new MobileVerificationDetailsKey(213L, ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		
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
		
		EmailVerificationDetails emailVerificationDetailsNull=null;
		
		try{
			emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
		}catch(EmailVerificationDetailNotFoundException e)
		{
			assertNull(emailVerificationDetailsNull);
		}
		
		
		
		VendorDetails updatedEntity=null;
		
		try{
			updatedEntity=transactionalUpdatesRepository.updateVendorDetails(savedEntity);
		}catch(VendorDetailsTransactionalUpdateFailedException e)
		{
			assertNull(updatedEntity);
		}
		
		//assertEquals(oldEntity, updatedEntity);
		
		
			assertEquals(2, mobileVerificationDetailsRepository.count().intValue());
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
			assertEquals(1, emailVerificationDetailsRepository.count().intValue());
			
			assertEquals(mobileVerificationDetails, mobileVerificationDetailsRepository.getByMobile(standardVendor().getMobile()));
			assertNotNull(emailVerificationDetailsRepository.getByEmail(standardVendor().getEmail()).getKey());
			
			assertNotNull( mobileVerificationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
					
			emailVerificationDetailsNull=null;
			
			try{
				emailVerificationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
			}catch(EmailVerificationDetailNotFoundException e)
			{
				assertNull(emailVerificationDetailsNull);
			}
			
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
	//	assertNotNull(authenticationDetailsRepository.getCustomerAuthenticationDetailsByMobile(savedMobileDetails.getMobile()).getKey());
		
		AuthenticationDetails authenticationDetails=null;
		
		try{
			authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				savedCustomer.getUpdatedBy(),savedCustomer.getCustomerId()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_MOBILE_NEW,customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		authenticationDetails=null;
		
		try{
			authenticationDetailsRepository.getByMobile(savedCustomer.getMobile());
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
				
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, 1), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getMobile(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(savedMobileDetails.getMobile()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
			Boolean status=null;
		
			try{
				status=transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
						savedCustomer.getUpdatedBy(),savedCustomer.getCustomerId());
			}catch(UpdateMobileInDetailsAndAuthentionDetailsFailedException e)
			{
				assertNull(status);
			}
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(savedAuthenticationDetails.getMobile()).getKey());
		
		AuthenticationDetails authenticationDetails=null;
		
		try{
			authenticationDetails=authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		assertTrue(transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
				savedVendor.getUpdatedBy(),savedVendor.getVendorId()));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_MOBILE_NEW,vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		authenticationDetails=null;
		
		try{
			authenticationDetails=authenticationDetailsRepository.getByMobile(savedAuthenticationDetails.getMobile());
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
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
				new MobileVerificationDetails(new MobileVerificationDetailsKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_MOBILE_NEW, CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, ENTITY_TYPE_CUSTOMER), null, CUST_MOBILE_NEW, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		MobileVerificationDetails savedMobileDetails=mobileVerificationDetailsRepository.save(newMobileVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getMobile(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getMobile());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(savedMobileDetails.getMobile()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByMobile(CUST_MOBILE_NEW).getKey());
		
		Boolean status=null;
		
		try{
			status=transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
					savedVendor.getUpdatedBy(),savedVendor.getVendorId());
		}catch(UpdateMobileInDetailsAndAuthentionDetailsFailedException e)
		{
			assertNull(status);
		}
		
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
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		
		AuthenticationDetails authenticationDetails=null;
		
		try{
				authenticationDetails=authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
				savedCustomer.getUpdatedBy(),savedCustomer.getCustomerId()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_EMAIL_NEW,customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		authenticationDetails=null;
		
		try{
				authenticationDetails=authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail());
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
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
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, ENTITY_TYPE_CUSTOMER), CUST_EMAIL_NEW, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), 
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetails());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedCustomer.getEmail(),customerDetailsCustomRepository.findOne(savedCustomer.getCustomerId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedEmailDetails.getEmail()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		Boolean status=null;
		
		try{
			status=transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedCustomer.getCustomerId(), ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_PRIMARY,
					savedCustomer.getUpdatedBy(),savedCustomer.getCustomerId());
		}
		catch (UpdateEmailInDetailsAndAuthenticationDetailsFailedException e) {
			assertNull(status);
		}	
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
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		AuthenticationDetails authenticationDetails=null;
		
		try{
			authenticationDetails=authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
		assertTrue(transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
				savedVendor.getUpdatedBy(),savedVendor.getVendorId()));
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		assertEquals(1, emailVerificationDetailsRepository.count().intValue());
		
		assertEquals(1, authenticationDetailsRepository.count().intValue());
		
		assertEquals(CUST_EMAIL_NEW,vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		authenticationDetails=null;
		
		try{
			authenticationDetails=authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail());
		}catch(AuthenticationDetailsNotFoundException e)
		{
			assertNull(authenticationDetails);
		}
		
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
				new EmailVerificationDetails(new EmailVerificationDetailsKey(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY),
						CUST_EMAIL_NEW, CUST_EMAILHASH, new Date(), ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		AuthenticationDetails duplicateAuthenticationDetails=
				new AuthenticationDetails(new AuthenticationDetailsKey(214L, ENTITY_TYPE_CUSTOMER), CUST_EMAIL_NEW, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,
						CUST_EMAILHASH, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(),
						 CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
		
		EmailVerificationDetails savedEmailDetails=emailVerificationDetailsRepository.save(newEmailVerificationDetails);
		
		AuthenticationDetails savedAuthenticationDetails=authenticationDetailsRepository.save(standardCustomerEmailMobileAuthenticationDetailsVendor());
		
		authenticationDetailsRepository.save(duplicateAuthenticationDetails);
		
		assertEquals(savedVendor.getEmail(),vendorDetailsCustomRepository.findOne(savedVendor.getVendorId()).getEmail());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(savedAuthenticationDetails.getEmail()).getKey());
		
		assertNotNull(authenticationDetailsRepository.getByEmail(CUST_EMAIL_NEW).getKey());
		
		Boolean status=null;
		
		try{
			status=transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(savedVendor.getVendorId(), ENTITY_TYPE_VENDOR, ENTITY_TYPE_PRIMARY,
					savedVendor.getUpdatedBy(),savedVendor.getVendorId());
		}catch(UpdateEmailInDetailsAndAuthenticationDetailsFailedException e)
		{
			assertNull(status);
		}
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
		
		try{
			savedEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(standardEmailMobileCustomer());
		}catch(QuickRegisterDetailsAlreadyPresentException e)
		{
			assertNull(savedEntity);
		}
		/*
		assertEquals(new QuickRegisterEntity(), savedEntity.getCustomerQuickRegisterEntity());
		
		assertEquals(new MobileVerificationDetails(), savedEntity.getCustomerMobileVerificationDetails());
		
		assertEquals(new EmailVerificationDetails(), savedEntity.getCustomerEmailVerificationDetails());
		*/
		
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
		
		customerDetailsCustomRepository.save(new CustomerDetails(215L, "ABX","A.", "ASD", null, null, CUST_MOBILE,null,false, CUST_EMAIL, false, 
				null, null, null, null, null, null, null, new Date(), new Date(), CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID));
		
		
		assertEquals(1,quickRegisterRepository.findAll().size());
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		try{
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			assertNull(customerOrVendorDetailsDTO);
		}
		/*	
		assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		
		assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		*/
		
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
		
		vendorDetailsCustomRepository.save(new VendorDetails(215L, "ASD","A.", "AES",null, null,null,null, standardEmailMobileVendor().getMobile(),
				null,false, standardEmailMobileVendor().getEmail(), false, 
				null, null, null, new Date(),new Date(), CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID));
		
		
		assertEquals(1,quickRegisterRepository.findAll().size());
		
		assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
		CustomerOrVendorDetailsDTO customerOrVendorDetailsDTO = null;
		
		try{
			customerOrVendorDetailsDTO=
				transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
		}catch(DeleteQuickCreateDetailsEntityFailedException e)
		{
			assertNull(customerOrVendorDetailsDTO);
		}
		/*
			assertNull(customerOrVendorDetailsDTO.getVendorDetails());
		
			assertNull(customerOrVendorDetailsDTO.getCustomerDetails());
		*/	
		
			assertEquals(1,quickRegisterRepository.findAll().size());
		
			assertEquals(1, vendorDetailsCustomRepository.count().intValue());
		
	}


}
