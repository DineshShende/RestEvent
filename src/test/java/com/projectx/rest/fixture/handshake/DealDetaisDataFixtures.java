package com.projectx.rest.fixture.handshake;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.rest.domain.handshake.DealDetails;


public class DealDetaisDataFixtures {

	public static Long DEAL_ID=123L;
	
	public static Long DEAL_FREIGHTREQUESTBYCUSTOMER_ID=234L;
	
	public static Long DEAL_FREIGHTREQUESTBYVENDOR_ID=234L;

	public static Integer DEAL_AMOUNT=100;
	
	public static String DEAL_DEDUCTIONMODE="ONLINE";
	
	public static Date DEAL_DATE=new Date();
	
	public static String DEAL_UPDATE_BY="CUST_ONLINE";
	
	
	private static Gson gson=new Gson(); 
	
	public static DealDetails standardDealDetails()
	{
		return new DealDetails( DEAL_FREIGHTREQUESTBYCUSTOMER_ID, DEAL_FREIGHTREQUESTBYVENDOR_ID, DEAL_DEDUCTIONMODE,
				DEAL_AMOUNT, DEAL_UPDATE_BY, DEAL_DATE, DEAL_UPDATE_BY, DEAL_DATE);
	}
	
	
	public static String standardJsonDealDetails(DealDetails dealDetails)
	{
		System.out.println(gson.toJson(dealDetails));
		
		return gson.toJson(dealDetails);
	}
	
}

