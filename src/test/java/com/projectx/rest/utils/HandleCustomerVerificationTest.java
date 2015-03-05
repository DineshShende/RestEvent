package com.projectx.rest.utils;


import static org.junit.Assert.*;
import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
public class HandleCustomerVerificationTest {

	@Autowired
	HandleVerificationService handleCustomerVerification;
	
	@Test
	public void sendEmail() throws InterruptedException, ExecutionException  {
		
		
	    handleCustomerVerification.sendEmail("dineshshe@gmail.com", "Hi How r u?");
	    
	
	}
	
	@Test
	public void sendSMSAsynch()
	{
		Boolean status=handleCustomerVerification.sendSMSAsynchronous(9960821869L, "Hi");
		
		System.out.println(status);

	}

	@Test
	public void sendSMS()
	{
		Boolean status=handleCustomerVerification.sendSMS(9960821869L, "Hi");
		
		System.out.println(status);

	}

}
