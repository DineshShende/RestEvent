package com.projectx.rest.services.request;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class FreightRequestByCustomerServiceTest {

	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Test
	public void environmentTest() {
		
	}
	/*
	@Test
	public void getMatchingCustReqForVendorReq()
	{
		
	}
	*/

}
