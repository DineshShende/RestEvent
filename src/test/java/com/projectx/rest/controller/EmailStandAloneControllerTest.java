package com.projectx.rest.controller;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.controller.EmailController;
import com.projectx.rest.domain.Email;
import com.projectx.rest.services.EmailService;


public class EmailStandAloneControllerTest {

	@InjectMocks
	EmailController customerQuickRegisterController;
	
	@Mock
	EmailService  customerQuickRegisterService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
		
	    when(customerQuickRegisterService.addEmail(any(Email.class))).thenReturn(new Email("dineshshe@gmail.com","dinesh"));
	}
	
	
	@Test
	public void thatEmailAddedSucessfully() throws Exception {
		
		this.mockMvc.perform(
	            post("/email/addemail")
	                    .content("{\"name\":\"dinesh\",\"email\":\"dineshshe@gmail.com\"}")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
	            //.andDo();
		
		
		
	}
	
	
	
}
