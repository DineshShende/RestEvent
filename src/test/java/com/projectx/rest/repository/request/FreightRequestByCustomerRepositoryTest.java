package com.projectx.rest.repository.request;

import static org.junit.Assert.*;

import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.request.FreightRequestByCustomer;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles("Prod")

public class FreightRequestByCustomerRepositoryTest {
 
	@Autowired
	FreightRequestByCustomerRepository  freightRequestByCustomerRepository;

	@Before
	public void clearData()
	{
		freightRequestByCustomerRepository.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.getById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
	}
	
	
	@Test
	public void update()
	{
				
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		savedEntity.setBodyType(CREQ_BODYTYPE_CLOSED);
		
		freightRequestByCustomerRepository.save(savedEntity);
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.getById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
		
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
		
		freightRequestByCustomerRepository.deleteById(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
	}

	
	@Test
	public void clearTestData()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
		
		freightRequestByCustomerRepository.clearTestData();
		
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
	}
	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerRepository.findByCustomerId(savedEntity.getCustomerId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}
}
