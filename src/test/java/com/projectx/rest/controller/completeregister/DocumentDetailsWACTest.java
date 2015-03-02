package com.projectx.rest.controller.completeregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.completeregister.DocumentDetailsDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE)

public class DocumentDetailsWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	
	@Test
	public void saveCustomerDocument() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocument().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocument().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocument().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocument().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
		
	}
	
	
	@Test
	public void getCustomerDetailsByKey() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/document/getCustomerDocumentById")
	                    .content(standardJsonDocumentKey())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocument().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocument().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocument().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocument().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
	    
	}

	
	@Test
	public void updateDoucment() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
	    
	}

	
	@Test
	public void updateDoucmentVerificationStatusAndRemark() throws Exception
	{
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocument()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/document/saveCustomerDocument")
	                    .content(standardJsonDocumentDetails(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
	            .andExpect(jsonPath("$.key.customerType").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getKey().getCustomerType()))
	            .andExpect(jsonPath("$.key.documentName").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getKey().getDocumentName()))
	            //.andExpect(jsonPath("$.document").value(standardDocumentDetailsWithDummyDocument().getDocument()))
	            .andExpect(jsonPath("$.contentType").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getContentType()))
	            .andExpect(jsonPath("$.verificationStatus").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getVerificationStatus()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());		
	
	    
	}


}
