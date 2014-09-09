package com.projectx.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.controller.CustomerQuickRegister;
import com.projectx.rest.domain.Email;
import com.projectx.services.CustomerQuickRegisterService;



/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
*/
//@ActiveProfiles("Test")
public class CustomerQuickRegisterControllerTest {

	@InjectMocks
	CustomerQuickRegister customerQuickRegisterController;
	
	@Mock
	CustomerQuickRegisterService  customerQuickRegisterService;
	
	private MockMvc mockMvc;;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
		
	    when(customerQuickRegisterService.addEmail(new Email("dineshshe@gmail.com","dinesh"))).thenReturn(new Email("dineshshe@gmail.com","dinesh"));
	}
	
	@Test
	public void thatEmailAddedSucessfully() throws Exception {
		
		this.mockMvc.perform(
	            post("/email/addemail")
	                    .content("{\"name\":\"dinesh\",\"email\":\"dineshshe@gmail.com\"}")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
	            //.andDo();
		
		
		
	}

}
