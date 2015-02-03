package com.projectx.rest.repository.request;

import static org.junit.Assert.*;

import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;

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


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class FreightRequestByVendorRepositotyTest {

	@Autowired
	FreightRequestByVendorRepository  freightRequestByVendorRepository;

	@Before
	public void clearData()
	{
	//	freightRequestByVendorRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveAndGetById()
	{
		//assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(savedEntity, freightRequestByVendorRepository.getById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByVendorRepository.count().intValue());
	}
	
	@Test
	public void update()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
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
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		assertEquals(1, freightRequestByVendorRepository.count().intValue());
		
		freightRequestByVendorRepository.clearTestData();
		
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
	}
	
	
	@Test
	public void findByVendorId()
	{
		assertEquals(0, freightRequestByVendorRepository.count().intValue());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		List<FreightRequestByVendor> requestList=freightRequestByVendorRepository.findByVendor(savedEntity.getVendorId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}
	
}
