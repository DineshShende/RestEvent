package com.projectx.rest.controller.handshake;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.standardVehicleDetails;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.completeregister.VendorDetailsDataFixture.*;
import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.rest.fixture.handshake.DealDetaisDataFixtures.*;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.completeregister.CustomerDetailsRepository;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.repository.completeregister.VendorDetailsRepository;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.repository.request.FreightRequestByCustomerRepository;
import com.projectx.rest.repository.request.FreightRequestByVendorRepository;
import com.projectx.rest.services.handshake.DealService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class DealControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Value("${FREIGHTSTATUS_NEW}")
	private String FREIGHTSTATUS_NEW;
	
	@Value("${FREIGHTSTATUS_BOOKED}")
	private String FREIGHTSTATUS_BOOKED;
	
	@Value("${POSITIVE_RESPONSE}")
	private Integer POSITIVE_RESPONSE;
	
	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;
	
	@Autowired
	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	VendorDetailsRepository vendorDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	@Autowired
	EmailVericationDetailsRepository emailVericationDetailsRepository;
	
	@Autowired
	AuthenticationDetailsRepository authenticationDetailsRepository;
	
	@Autowired
	DealService dealService;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Before
	@After
	public void clearTestData()
	{
		freightRequestByCustomerRepository.clearTestData();
		freightRequestByVendorRepository.clearTestData();
		vehicleDetailsRepository.clearTestData();
		customerDetailsRepository.deleteAll();
		vendorDetailsRepository.clearTestData();
		mobileVerificationDetailsRepository.clearTestData();
		emailVericationDetailsRepository.clearTestData();
		authenticationDetailsRepository.clearLoginDetailsForTesting();
		dealService.clearTestData();
		
	}
	
	
	@Test
	public void triggerDeal() throws Exception
	{
		freightRequestByCustomerRepository.clearTestData();
		
		CustomerDetails customerDetails=customerDetailsRepository.save(standardCustomerFromQuickEntity());
		
		VendorDetails vendorDetails=vendorDetailsRepository.save(standardVendor());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad110(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadClosedAcerReq(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadOpenTataReq(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoad15(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenAcer(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenTata(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel(customerDetails.getCustomerId()));
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel(customerDetails.getCustomerId()));
		
		//FreightRequestByVendor vendorRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		
		vehicleDetailsRepository.save(standardVehicleDetails(vendorDetails.getVendorId()));
		
		FreightRequestByVendor testRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor(vendorDetails.getVendorId()));
		
		
		
		List<FreightRequestByCustomer> list=freightRequestByCustomerRepository.getMatchingCustReqForVendorReq(testRequest,REQ_STATUS);

		FreightRequestByCustomer fetchedCustomerReq=list.get(0);
		
		assertEquals(0, dealService.count().intValue());
		
		this.mockMvc.perform(
				post("/deal/triggerdeal")
				.content(standardJsonTriggerDealDTO(standardTriggerDealDTO(fetchedCustomerReq.getRequestId(),
						testRequest.getRequestId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());


		
		assertEquals(FREIGHTSTATUS_BOOKED, freightRequestByCustomerRepository.getById(fetchedCustomerReq.getRequestId()).getAllocationStatus());
		
		assertEquals(testRequest.getRequestId(), freightRequestByCustomerRepository.getById(fetchedCustomerReq.getRequestId()).getAllocatedFor());
		
		assertEquals(FREIGHTSTATUS_BOOKED, freightRequestByVendorRepository.getById(testRequest.getRequestId()).getStatus());
		
		assertEquals(fetchedCustomerReq.getRequestId(), freightRequestByVendorRepository.getById(testRequest.getRequestId()).getReservedBy());
		
		assertEquals(1, dealService.count().intValue());
		
	}
	
	
}
