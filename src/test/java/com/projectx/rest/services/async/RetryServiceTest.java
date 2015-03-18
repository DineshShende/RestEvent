package com.projectx.rest.services.async;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.domain.completeregister.EmailMessageDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.async.RetriggerDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class RetryServiceTest {


	@Autowired
	RetriggerService retriggerService; 
	
	
	@Test
	public void environmentTest() {
		
		//retriggerService.requestRetry(new RetriggerDTO("/sendVerificationDetails/sendEmailAsync", new EmailMessageDTO("dineshshe@gmail.com", "Ho")));
		
		
	}

}
