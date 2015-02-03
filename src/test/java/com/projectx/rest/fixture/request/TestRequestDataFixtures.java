package com.projectx.rest.fixture.request;

import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.standardVehicleDetails;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.request.TestRequest;


public class TestRequestDataFixtures {
	
	
public static VehicleDetailsDTO REQ_VEHICLE=standardVehicleDetails();
	
	public static Integer REQ_SOURCE=411045;
	
	public static Integer REQ_DESTINATION=413102;
	
	public static Integer REQ_DESTINATION_UPDATED=413133;
	
	public static Long REQ_DRIVER=213L;
	
	public  static Date REQ_AVAIL_DATE=new Date();
	
	public static String REQ_AVAIL_TIME="6:00PM";
	
	public static String REQ_AVAIL_TIME_UPDATED="8:00PM";
	
	public static Integer REQ_PICK_UP_RANGE=10;
	
	public static Integer REQ_PICK_UP_RANGE_UPDATED=0;
	
	public static Date REQ_DATE=new Date();
	
	public static String REQ_UPDATED_BY="CUST_ONLINE";
	
	public static Long REQ_VENDOR_ID=213L;
	
	public static String REQ_STATUS="NEW";
	
	private static Gson gson=new Gson();
	
	public static TestRequest standardTestRequest()
	{
		
		
		return new TestRequest(1L,standardVehicleDetails(), REQ_SOURCE, REQ_DESTINATION, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME,
				REQ_PICK_UP_RANGE,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static TestRequest standardTestRequestUpdated()
	{
		return new TestRequest(1L,standardVehicleDetails(), REQ_SOURCE, REQ_DESTINATION_UPDATED, REQ_DRIVER, REQ_AVAIL_DATE, REQ_AVAIL_TIME_UPDATED,
				REQ_PICK_UP_RANGE_UPDATED,REQ_VENDOR_ID,REQ_STATUS, REQ_DATE, REQ_DATE, REQ_UPDATED_BY);
		
	}
	
	public static String stanardJsonTestRequest(TestRequest freightRequestByVendor)
	{
		System.out.println(gson.toJson(freightRequestByVendor));
		
		return gson.toJson(freightRequestByVendor);
	}


}
