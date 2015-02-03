package com.projectx.rest.repository.request;


import static com.projectx.rest.fixture.request.TestRequestDataFixtures.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.TestRequest;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class TestRequestRepositoryTest {

	@Autowired
	TestRequestRepository  testRequestRepository;

	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Before
	public void clearData()
	{
		testRequestRepository.clearTestData();
		vehicleDetailsRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		assertEquals(0, testRequestRepository.count().intValue());
		
		TestRequest savedEntity=testRequestRepository.save(standardTestRequest());
		
		assertEquals(savedEntity, testRequestRepository.getById(savedEntity.getRequestId()));
		
		assertEquals(1, testRequestRepository.count().intValue());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, testRequestRepository.count().intValue());
		
		TestRequest savedEntity=testRequestRepository.save(standardTestRequest());
		
		savedEntity.setSource(REQ_DESTINATION_UPDATED);
		savedEntity.setAvailableTime(REQ_AVAIL_TIME_UPDATED);
		savedEntity.setPickupRangeInKm(REQ_PICK_UP_RANGE);
		
		TestRequest updatedEntity=testRequestRepository.save(savedEntity);
		
		assertEquals(savedEntity, updatedEntity);
		
		assertEquals(1, testRequestRepository.count().intValue());
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, testRequestRepository.count().intValue());
		
		TestRequest savedEntity=testRequestRepository.save(standardTestRequest());
		
		assertEquals(1, testRequestRepository.count().intValue());
		
		testRequestRepository.deleteById(savedEntity.getRequestId());
		
		assertEquals(0, testRequestRepository.count().intValue());
		
	}
	
	@Test
	public void count()
	{
		assertEquals(0, testRequestRepository.count().intValue());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, testRequestRepository.count().intValue());
		
		TestRequest savedEntity=testRequestRepository.save(standardTestRequest());
		
		assertEquals(1, testRequestRepository.count().intValue());
		
		testRequestRepository.clearTestData();
		
		assertEquals(0, testRequestRepository.count().intValue());
	}
	
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, testRequestRepository.count().intValue());
		
		TestRequest savedEntity=testRequestRepository.save(standardTestRequest());
		
		List<TestRequest> requestList=testRequestRepository.findByVendor(savedEntity.getVendorId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}

}
