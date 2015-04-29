package com.projectx.rest.fixture.handshake;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.mvc.domain.handshake.TriggerDealDTO;
import com.projectx.rest.domain.handshake.DealDetails;


public class DealDetaisDataFixtures {

	public static Long DEAL_ID=123L;
	
	public static Long DEAL_FREIGHTREQUESTBYCUSTOMER_ID=234L;
	
	public static Long DEAL_FREIGHTREQUESTBYVENDOR_ID=234L;

	public static Integer DEAL_AMOUNT=100;
	
	public static String DEAL_DEDUCTIONMODE="ONLINE";
	
	public static Date DEAL_DATE=new Date();
	
	public static Integer DEAL_UPDATE_BY=1;
	
	public static Long DEAL_UPDATE_BY_ID=123L;
	
	
	private static Gson gson=new Gson(); 
	
	public static DealDetails standardDealDetails()
	{
		return new DealDetails(DEAL_FREIGHTREQUESTBYCUSTOMER_ID, DEAL_FREIGHTREQUESTBYVENDOR_ID, DEAL_DEDUCTIONMODE,
				DEAL_AMOUNT, DEAL_DATE, DEAL_DATE, DEAL_UPDATE_BY, DEAL_UPDATE_BY_ID, DEAL_UPDATE_BY, DEAL_UPDATE_BY_ID);
				
	}
	
	
	public static String standardJsonDealDetails(DealDetails dealDetails)
	{
		System.out.println(gson.toJson(dealDetails));
		
		return gson.toJson(dealDetails);
	}
	
	public static TriggerDealDTO standardTriggerDealDTO(Long customerRquestId,Long vendorRequestId)
	{
		return new TriggerDealDTO(customerRquestId, vendorRequestId, DEAL_UPDATE_BY, DEAL_UPDATE_BY_ID);
	}
	
	public static String standardJsonTriggerDealDTO(TriggerDealDTO triggerDealDTO)
	{
		return gson.toJson(triggerDealDTO);
	}
	
}

