package com.projectx.rest.fixtures.async;

import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;

import java.util.UUID;

import com.google.gson.Gson;
import com.projectx.mvc.domain.completeregister.EmailMessageDTO;
import com.projectx.mvc.domain.completeregister.MobileMessageDTO;
import com.projectx.rest.domain.async.RetriggerDetails;


public class RetriggerDetailsDataFixtures {

	private static Gson gson=new Gson();
	
	
	public static RetriggerDetails standardRetriggerDetails()
	{
		return new RetriggerDetails("vendorRequest", gson.toJson(standardFreightRequestByVendor()));
	}
	
	public static String standardJsonRetrigger(RetriggerDetails retriggerDetails)
	{
		return gson.toJson(standardRetriggerDetails());
	}
	
	public static EmailMessageDTO standardEmailMessageDTO()
	{
		return new EmailMessageDTO("dineshshe@gmail.com", UUID.randomUUID(), "Hi");
	}
	
	public static MobileMessageDTO standardMobileMessageDTO()
	{
		return new MobileMessageDTO(9960821869L, UUID.randomUUID(), "Hi");
	}
	
	public static String standardJsonEmailMessageDTO(EmailMessageDTO emailMessageDTO)
	{
		return gson.toJson(emailMessageDTO);
	}
	
	public static String standardJsonMobileMessageDTO(MobileMessageDTO emailMessageDTO)
	{
		return gson.toJson(emailMessageDTO);
	}
	
}
