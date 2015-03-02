package com.projectx.rest.controller.async;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardJsonQuickRegister;
import static com.projectx.rest.fixtures.async.RetriggerDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
public class AsyncControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void sendSMS() throws Exception
	{
		this.mockMvc.perform(
				post("/asycn/sendSMS")
					.content(standardJsonMobileMessageDTO(standardMobileMessageDTO()))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	

}
