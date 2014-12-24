package com.projectx.rest.controller.completeregister;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.controller.quickregister.AuthenticationController;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;

public class CustomerDetailsStandAloneTest {
	@InjectMocks
	CustomerDetailsController customerDetailsController;
	
	@Mock
	CustomerDetailsService customerDetailsService; 
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerDetailsController)
	    		.build();
	    
	}

	
	@Test
	public void environmentTest() {
		
	}
	
	
	
}
