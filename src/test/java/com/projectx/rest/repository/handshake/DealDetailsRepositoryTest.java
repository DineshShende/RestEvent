package com.projectx.rest.repository.handshake;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.handshake.DealDetaisDataFixtures.standardDealDetails;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.handshake.DealDetails;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class DealDetailsRepositoryTest {

	@Autowired
	DealDetailsRepository dealDetailsRepository;
	
	@Before
	public void clearTestData()
	{
		dealDetailsRepository.clearTestData();
		
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGetByDealId()
	{
		assertEquals(0, dealDetailsRepository.count().intValue());
		
		DealDetails savedEntity=dealDetailsRepository.save(standardDealDetails());
		
		DealDetails fetchedEntity=dealDetailsRepository.getByDealId(savedEntity.getDealId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, dealDetailsRepository.count().intValue());
		
	}
	
	@Test
	public void saveAndGetByCustomerRequestId()
	{
		dealDetailsRepository.clearTestData();
		
		assertEquals(0, dealDetailsRepository.count().intValue());
		
		DealDetails savedEntity=dealDetailsRepository.save(standardDealDetails());
		
		DealDetails fetchedEntity=dealDetailsRepository.getByCustomerRequestId(savedEntity.getFreightRequestByCustomerId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, dealDetailsRepository.count().intValue());
		
	}
	
	@Test
	public void saveAndGetByVendorRequestId()
	{
		assertEquals(0, dealDetailsRepository.count().intValue());
		
		DealDetails savedEntity=dealDetailsRepository.save(standardDealDetails());
		
		DealDetails fetchedEntity=dealDetailsRepository.getByVendorRequestId(savedEntity.getFreightRequestByVendorId());
		
		assertEquals(savedEntity,fetchedEntity );
		
		assertEquals(1, dealDetailsRepository.count().intValue());
		
	}

}
