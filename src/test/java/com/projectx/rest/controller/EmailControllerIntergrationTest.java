package com.projectx.rest.controller;



import static com.projectx.rest.fixture.RestDataFixture.EMAIL_EMAIL;
import static com.projectx.rest.fixture.RestDataFixture.EMAIL_MSG;
import static com.projectx.rest.fixture.RestDataFixture.EMAIL_NAME;
import static com.projectx.rest.fixture.RestDataFixture.standardJsonEmail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.rest.config.Application;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration


@ActiveProfiles("Test")
public class EmailControllerIntergrationTest {

	
	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	
	@Test
	public void thatEmailAddedSucessfully() throws Exception {
		
		this.mockMvc.perform(
	            post("/email/checkemail")
	                    .content("{\"name\":\"dinesh\"}")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print());
	
	}
	/*
	@Test
	public void thatEmailAddedSucessfully() throws Exception {
		
		this.mockMvc.perform(
	            post("/email/addemail")
	                    .content(standardJsonEmail())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.email").value(EMAIL_EMAIL))
	            .andExpect(jsonPath("$.name").value(EMAIL_NAME));
	           // .andExpect(jsonPath("$.message").value(EMAIL_MSG));
				
		
		
		
	}

	
	@Test
	  public void thatAddEmailPassesLocationHeader() throws Exception {

	    this.mockMvc.perform(
	    		post("/email/addemail")
                .content(standardJsonEmail())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
	            .andExpect(header().string("Location", Matchers.endsWith("/customer/email/"+EMAIL_EMAIL)));
	  }
	  */
}
