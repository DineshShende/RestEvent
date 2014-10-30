package com.projectx.rest.utils;

import static org.junit.Assert.*;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class HandleCustomerVerificationTest {

	@Autowired
	HandleCustomerVerification handleCustomerVerification;
	
	@Test
	public void test() throws UnirestException {
		//CustomerQuickRegisterEntity customer=standardEmailCustomer();
		//customer.setEmail("dineshshe@gmail.com");
		
		//handleCustomerVerification.generatePassword();
		
		//handleCustomerVerification.sendMobilePin(9960821869L, "Y+O+P+T");
		//handleCustomerVerification.sendEmailHash("dineshshe@gmail.com", "Hi There!!");
	}
	/*
	@Test
	public void sendMobilePin() throws UnirestException
	{
		assertTrue(handleCustomerVerification.sendSMS(CUST_MOBILE, "Hi"));
	}
	*/

}
