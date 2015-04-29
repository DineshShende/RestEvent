package com.projectx.rest.repository.request;

import static org.junit.Assert.*;
import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;
import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class FreightRequestByVendorRepositotyTest {

	@Autowired
	FreightRequestByVendorRepository  freightRequestByVendorRepository;
	
	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;

	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Value("${FREIGHTSTATUS_BLOCKED}")
	private String FREIGHTSTATUS_BLOCKED;
	
	@Value("${FREIGHTSTATUS_NEW}")
	private String FREIGHTSTATUS_NEW;
			
	@Value("${FREIGHTSTATUS_BOOKED}")
	private String FREIGHTSTATUS_BOOKED;		
	
	@Before
	@After
	public void clearData()
	{
		
		freightRequestByVendorRepository.clearTestData();
		vehicleDetailsRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		//assertEquals(savedEntity, freightRequestByVendorRepository.getById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByVendorRepository.count().intValue());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		savedEntity.setSource(REQ_DESTINATION_UPDATED);
		savedEntity.setAvailableTime(REQ_AVAIL_TIME_UPDATED);
		savedEntity.setPickupRangeInKm(REQ_PICK_UP_RANGE);
		
		FreightRequestByVendor updatedEntity=freightRequestByVendorRepository.save(savedEntity);
		
		assertEquals(savedEntity, updatedEntity);
		
		assertEquals(1, freightRequestByVendorRepository.count().intValue());
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorRepository.count().intValue());
		
		freightRequestByVendorRepository.deleteById(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorRepository.count().intValue());
		
		freightRequestByVendorRepository.clearTestData();
		
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
	}
	
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		List<FreightRequestByVendorDTO> requestList=freightRequestByVendorRepository.findByVendor(savedEntity.getVendorId());
		
		//assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}
	
	
	@Test
	public void getMatchingVendorRequestFullTruckLoad()
	{
		
		FreightRequestByCustomer freightRequestByCustomer=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		vehicleDetailsRepository.save(standardVehicleDetailsOpen307());
		
		freightRequestByVendorRepository.save(standardFreightRequestByVendorOpen307());
		
		vehicleDetailsRepository.save(standardVehicleDetailsClosed());
		
		freightRequestByVendorRepository.save(standardFreightRequestByVendorClosed());
		
		vehicleDetailsRepository.save(standardVehicleDetailsFlexible());
		
		freightRequestByVendorRepository.save(standardFreightRequestByVendorFlexible());
		
		List<FreightRequestByVendorDTO> matchList=freightRequestByVendorRepository.getMatchingVendorReqFromCustomerReq(freightRequestByCustomer);
		
		assertEquals(1, matchList.size());
	}
	
	@Test
	public void updateReservationStatus()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorRepository.count().intValue());
		
		assertEquals(1, freightRequestByVendorRepository.updateReservationStatusWithReservedFor(savedEntity.getRequestId(),
				FREIGHTSTATUS_NEW, FREIGHTSTATUS_BLOCKED, 543L,1,savedEntity.getVendorId()).intValue());
		
		assertEquals(1, freightRequestByVendorRepository.updateReservationStatusWithReservedFor(savedEntity.getRequestId(),
				FREIGHTSTATUS_BLOCKED, FREIGHTSTATUS_BOOKED, 543L,1,savedEntity.getVendorId()).intValue());
		
	}

}
