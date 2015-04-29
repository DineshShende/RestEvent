package com.projectx.rest.repository.completeregister;

import static org.junit.Assert.*;
import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class VehicleDetailsRepositoryTest {

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@After
	@Before
	public  void setUp()
	{
		vehicleDetailsRepository.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, vehicleDetailsRepository.count().intValue());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(savedEntity, vehicleDetailsRepository.findOne(savedEntity.getVehicleId()));
		
		assertEquals(1, vehicleDetailsRepository.count().intValue());
	}
	
	@Test
	public void getByIdAndGetByRegistrationNumberFailure()
	{
		VehicleDetails savedEntity=null;
		
		try{
			savedEntity=vehicleDetailsRepository.findOne(VEHICLE_ID);
		}catch(VehicleDetailsNotFoundException e)
		{
			assertNull(savedEntity);
		}
		
		try{
			savedEntity=vehicleDetailsRepository.findByRegistrationNumber(VEHICLE_REGISTRATION_NUMBER);
		}catch(VehicleDetailsNotFoundException e)
		{
			assertNull(savedEntity);
		}
		
	}
	
	@Test
	public void findByRegistrationNumber()
	{
		assertEquals(0, vehicleDetailsRepository.count().intValue());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(1, vehicleDetailsRepository.count().intValue());
		
		assertEquals(savedEntity, vehicleDetailsRepository.findByRegistrationNumber(savedEntity.getRegistrationNumber()));
		
	}
	
	@Test
	public void update()
	{
		assertEquals(0, vehicleDetailsRepository.count().intValue());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(savedEntity, vehicleDetailsRepository.findOne(savedEntity.getVehicleId()));
		
		savedEntity.setPermitType(VEHICLE_PERMIT_TYPE_STATE);
		savedEntity.setVehicleBodyType(VEHICLE_BODY_TYPE_CLOSED);
		
		vehicleDetailsRepository.save(savedEntity);
		
		assertEquals(savedEntity, vehicleDetailsRepository.findOne(savedEntity.getVehicleId()));
		
		assertEquals(1, vehicleDetailsRepository.count().intValue());
	}

	@Test
	public void delete()
	{
		assertEquals(0, vehicleDetailsRepository.count().intValue());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		assertEquals(1, vehicleDetailsRepository.count().intValue());
		
		vehicleDetailsRepository.clearTestData();
		
		assertEquals(0, vehicleDetailsRepository.count().intValue());
	}
	
	@Test
	public void getVehicleByVendorId()
	{
		assertEquals(0, vehicleDetailsRepository.count().intValue());
		
		VehicleDetails savedEntity=vehicleDetailsRepository.save(standardVehicleDetails());
		
		VehicleDetails savedEntityOther=vehicleDetailsRepository.save(standardVehicleDetailsOther());
		
		List<VehicleDetails> vehicleList=vehicleDetailsRepository.getVehiclesByVendorId(standardVehicleDetailsOther().getVendorId());
		
		assertEquals(2, vehicleList.size());
		
		assertEquals(savedEntity, vehicleList.get(0));
		
		assertEquals(savedEntityOther, vehicleList.get(1));
		
	}
	
}
