package com.projectx.rest.services.request;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
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
import com.projectx.rest.repository.async.RetriggerDetailsRepository;

import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class FreightRequestByVendorServiceTest {

	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	RetriggerDetailsRepository retriggerDetailsRepository;
	
	@Test
	public void environmentTest() {
		
	}
	
	@Before
	public void clearTestData()
	{
		retriggerDetailsRepository.clearTestData();
	}
	
	@Test
	public void getMatchingVendorReqFromCustomerReq()
	{
		assertEquals(0, retriggerDetailsRepository.findAll().size());
		
		List<FreightRequestByVendor> result=freightRequestByVendorService.getMatchingVendorReqFromCustomerReq(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, retriggerDetailsRepository.findAll().size());
	}

}
