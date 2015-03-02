package com.projectx.rest.repository.completeregister;

import static org.junit.Assert.*;
import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.completeregister.DriverDetailsDataFixtures.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
//@Transactional
public class DriverDetailsRepositoryTest {

	@Autowired
	DriverDetailsRepository driverDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Before
	public void clearTestData()
	{
		driverDetailsRepository.clearTestData();
		mobileVerificationDetailsRepository.clearTestData();
	}
	
	@Test
	public void environmentTest()
	{
		
	}



	@Test
	public void saveAndGetById()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
	}
	

	
	@Test
	public void saveWithFailure()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		mobileVerificationDetailsRepository
		.save(new MobileVerificationDetails(new MobileVerificationDetailsKey(234L, 1, 1), DRIVER_MOBILE, null, 0,0, new Date(), new Date(), "CUST_ONLINE"));
	
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=null;
		
		try
		{
			savedEntity=driverDetailsRepository.save(standardDriverDetails());
		}
		catch(DriverDetailsAlreadyPresentException e)
		{
			assertNull(savedEntity);
		}
		
		
		//assertNull(savedEntity.getDriverId());
			
		//assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
			
		assertEquals(0, driverDetailsRepository.count().intValue());
		
	}

	
		
	@Test
	public void updateAddress()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		Address address=savedEntity.getHomeAddress();
		
		address.setAddressLine("Updated");
		address.setCity("New City");
		
		savedEntity.setHomeAddress(address);
		
		//savedEntity.setFirstName("ABC");
		
		savedEntity=driverDetailsRepository.save(savedEntity);
		
		Address address1=savedEntity.getHomeAddress();
		
		address1.setCity("Pune");
		address1.setPincode(432345);
		
		savedEntity.setHomeAddress(address1);
		
		savedEntity=driverDetailsRepository.save(savedEntity);
		
		//assertEquals(address, savedEntity.getHomeAddress());
		
		assertEquals(1, driverDetailsRepository.count().intValue());
	}
	
	
	
	@Test
	public void deleteByKey()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		driverDetailsRepository.deleteById(savedEntity.getDriverId());
		
		assertEquals(0, driverDetailsRepository.count().intValue());
	}
	
	@Test
	public void updateMobileAndMobileVerificationStatus()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		assertEquals(1, driverDetailsRepository
				.updateMobileAndMobileVerificationStatus(savedEntity.getDriverId(), 988776655443L, true,savedEntity.getUpdatedBy()).intValue());
		
		
	}
	
	
	@Test
	public void updateDriverMobile()
	{
		
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		assertNotNull(mobileVerificationDetailsRepository.getByMobile(standardDriverDetails().getMobile()));
		
		MobileVerificationDetails mobileVerificationDetails=null;
		
		try{
			
			mobileVerificationDetails=
					mobileVerificationDetailsRepository.getByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile());
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}

		savedEntity=driverDetailsRepository.update(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()));
		
		assertNotNull(mobileVerificationDetailsRepository.getByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile()));
		
		
		try{
			
			mobileVerificationDetails=
					mobileVerificationDetailsRepository.getByMobile(standardDriverDetails().getMobile());
		}
		catch(MobileVerificationDetailsNotFoundException e)
		{
			assertNull(mobileVerificationDetails);
		}
		
		assertEquals(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getFirstName(), driverDetailsRepository.findOne(savedEntity.getDriverId()).getFirstName());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
	}

	
	
	@Test
	public void updateDriverMobileFailure()
	{
		
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsRepository.findOne(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsRepository.count().intValue());
		
		assertEquals(1, mobileVerificationDetailsRepository.count().intValue());
		
		
		mobileVerificationDetailsRepository
			.save(new MobileVerificationDetails(new MobileVerificationDetailsKey(234L, 1, 1), DRIVER_MOBILE_UPDATED, null, 0,0, new Date(), new Date(), "CUST_ONLINE"));
		
		assertNotNull(mobileVerificationDetailsRepository.getByMobile(standardDriverDetails().getMobile()));
		
		assertNotNull(mobileVerificationDetailsRepository.getByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile()));
		
		DriverDetails updatedEntity=null;
		
		try{
			updatedEntity=driverDetailsRepository.update(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()));
		}
		catch(DriverDetailsUpdateFailedException e)
		{
			assertNull(updatedEntity);
		}
		
			assertNotNull(mobileVerificationDetailsRepository.getByMobile(standardDriverDetails().getMobile()));
			
			assertNotNull(mobileVerificationDetailsRepository.getByMobile(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile()));
			
			assertEquals(standardDriverDetails().getFirstName(), driverDetailsRepository.findOne(savedEntity.getDriverId()).getFirstName());
			
			assertEquals(2, mobileVerificationDetailsRepository.count().intValue());
		
	}
	

	@Test
	public void getDriversByVendorId()
	{
		assertEquals(0, driverDetailsRepository.count().intValue());
		
		DriverDetails savedEntity=driverDetailsRepository.save(standardDriverDetails());
		
		DriverDetails savedEntityOther=driverDetailsRepository.save(standardDriverDetailsOther());
		
		List<DriverDetails> driverList=driverDetailsRepository.getDriversByVendorId(standardDriverDetails().getVendorId());
		
		assertEquals(savedEntity, driverList.get(0));
		
		assertEquals(savedEntityOther, driverList.get(1));
		
		assertEquals(2, driverList.size());
	}
	
	
}
