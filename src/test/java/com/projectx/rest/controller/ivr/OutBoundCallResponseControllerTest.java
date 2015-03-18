package com.projectx.rest.controller.ivr;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.standardCustomerDetailsAlreadyPresent;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.standardVehicleDetails;
import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.standardFreightRequestByVendor;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.rest.domain.ivr.KooKooRequestEntity;
import com.projectx.rest.domain.ivr.QuestionListWithCounter;
import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.domain.ivr.TrackKookooResponseDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.service.ivr.PreBookService;
import com.projectx.rest.service.ivr.QuestionHandlingService;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VehicleDetailsService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class OutBoundCallResponseControllerTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	TrackKookooResponseDTO trackKookooResponseDTO;
	
	@Autowired
	FreightRequestByCustomerStatusDTO freightRequestByCustomerStatusDTO; 
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuestionHandlingService questionHandlingService;
	
	@Autowired
	PreBookService preBookService;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		freightRequestByCustomerService.clearTestData();
		freightRequestByVendorService.clearTestData();
		vehicleDetailsService.clearTestData();
		customerDetailsService.clearTestData();
		preBookService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	
	@Test
	public void receiveResponseNextQuestion() throws Exception
	{
		
		CustomerDetails customerDetails=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsAlreadyPresent());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService
				.newRequest(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel(customerDetails.getCustomerId()));
		
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor freightRequestByVendor=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());

		List<QuestionPossibleAnswersSelectedAnswer> questionList=questionHandlingService.getAll();
		
		QuestionListWithCounter questionListWithCounter=new QuestionListWithCounter(customerDetails.getMobile(), 0, questionList);
		 
		 freightRequestByCustomerStatusDTO.add(savedEntity.getRequestId(), questionListWithCounter);
		 
		 
		 String sid=UUID.randomUUID().toString();
		 
		 trackKookooResponseDTO.add(sid, new KooKooRequestEntity(savedEntity.getRequestId(), freightRequestByVendor.getRequestId()));
		 
		
		assertEquals(0, freightRequestByCustomerStatusDTO.getQuestionList(savedEntity.getRequestId()).getCounter().intValue()); 
		
		this.mockMvc.perform(
	            get("/outboundcall/receiveResponse/"+sid+"/"+customerDetails.getMobile()+"/1")
	                    )
	            .andDo(print())
	            .andExpect(status().isOk());
	 
		
		assertEquals(1, freightRequestByCustomerStatusDTO.getQuestionList(savedEntity.getRequestId()).getCounter().intValue());
		
	}

	@Test
	public void receiveResponsePrebook() throws Exception
	{
		
		CustomerDetails customerDetails=customerDetailsService.mergeCustomerDetails(standardCustomerDetailsAlreadyPresent());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService
				.newRequest(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel(customerDetails.getCustomerId()));
		
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor freightRequestByVendor=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());

		freightRequestByVendorService.updateReservationStatusWithReservedFor(freightRequestByVendor.getRequestId(),
				freightRequestByVendor.getStatus(), "BLOCKED", 234L);
		
		List<QuestionPossibleAnswersSelectedAnswer> questionList=questionHandlingService.getAll();
		
		QuestionListWithCounter questionListWithCounter=new QuestionListWithCounter(customerDetails.getMobile(), 0, questionList);
		 
		 freightRequestByCustomerStatusDTO.add(savedEntity.getRequestId(), questionListWithCounter);
		 
		 
		 String sid=UUID.randomUUID().toString();
		 
			 
		 trackKookooResponseDTO.add(sid, new KooKooRequestEntity(savedEntity.getRequestId(), freightRequestByVendor.getRequestId()));
		 
		
		assertEquals(0, freightRequestByCustomerStatusDTO.getQuestionList(savedEntity.getRequestId()).getCounter().intValue()); 
		
		assertEquals(0, preBookService.count().intValue());
		
		this.mockMvc.perform(
	            get("/outboundcall/receiveResponse/"+sid+"/"+customerDetails.getMobile()+"/1")
	                    )
	            .andDo(print())
	            .andExpect(status().isOk());
	 
		
		assertEquals(0, freightRequestByCustomerStatusDTO.getQuestionList(savedEntity.getRequestId()).getCounter().intValue());
		
		assertEquals(1, preBookService.count().intValue());
		
	}

	
}
