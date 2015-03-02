package com.projectx.rest.repository.completeregister;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
public class VendorDetailsRepositoryTest {

	@Autowired
	VendorDetailsRepository vendorDetailsRepository; 
	
	@Autowired
	MobileVerificationService 	mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Before
	public void setUp()
	{
		vendorDetailsRepository.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	
	@Test
	public void saveAndFindOne()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(savedEntity, vendorDetailsRepository.findOne(savedEntity.getVendorId()));
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
	}
	
	@Test
	public void findOneFailure()
	{
		VendorDetails savedEntity=null;
		
		try{
			savedEntity=vendorDetailsRepository.findOne(VENDOR_ID);
		}catch(VendorDetailsNotFoundException e)
		{
			assertNull(savedEntity);
		}
	}
	
	@Test
	public void count()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
	}
	
	
	@Test
	public void deleteAll()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertTrue(vendorDetailsRepository.clearTestData());
		
		assertEquals(0,vendorDetailsRepository.count().intValue());
	}
	
	
	@Test
	public void updateEmailVerificationStatus()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
			
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertEquals(1,vendorDetailsRepository
				.updateEmailVerificationStatus(new UpdateEmailVerificationStatusUpdatedByDTO(savedEntity.getVendorId(),standardVendor().getEmail(), true,savedEntity.getUpdatedBy())).intValue());
	
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertTrue(vendorDetailsRepository.findOne(savedEntity.getVendorId()).getIsEmailVerified());
	}
	
	@Test
	public void updateMobileVerificationStatus()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertEquals(1,vendorDetailsRepository
				.updateMobileVerificationStatus(new UpdateMobileVerificationStatusUpdatedByDTO(savedEntity.getVendorId(),savedEntity.getMobile() ,true,savedEntity.getUpdatedBy())).intValue());
	
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertTrue(vendorDetailsRepository.findOne(savedEntity.getVendorId()).getIsMobileVerified());
	}
	
	
	@Test
	public void updateAddress()
	{
		assertEquals(0,vendorDetailsRepository.count().intValue());
		
		VendorDetails savedEntity=vendorDetailsRepository.save(standardVendor());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		savedEntity.getFirmAddress().setAddressLine(standardAddressUpdated().getAddressLine());
		savedEntity.getFirmAddress().setCity(standardAddressUpdated().getCity());
		savedEntity.getFirmAddress().setDistrict(standardAddressUpdated().getDistrict());
		savedEntity.getFirmAddress().setState(standardAddressUpdated().getState());
		savedEntity.getFirmAddress().setPincode(standardAddressUpdated().getPincode());
		
		VendorDetails updatedEntity=vendorDetailsRepository.update(savedEntity);
		
		assertEquals(savedEntity.getFirmAddress().getAddressId(), updatedEntity.getFirmAddress().getAddressId());
		
		assertEquals(1,vendorDetailsRepository.count().intValue());
		
		assertEquals(standardAddressUpdated(), updatedEntity.getFirmAddress());
	}
	

}
