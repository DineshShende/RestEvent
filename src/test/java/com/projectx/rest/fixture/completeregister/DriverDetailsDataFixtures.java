package com.projectx.rest.fixture.completeregister;


import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.DriverDetails;

public class DriverDetailsDataFixtures {
	
	
	public static String DRIVER_FIRST_NAME="ARUN";
	
	public static String DRIVER_MIDDLE_NAME="M.";
	
	public static String DRIVER_LAST_NAME="GUPTA";

public static String DRIVER_FIRST_NAME_OTHER="John";
	
	public static String DRIVER_MIDDLE_NAME_OTHER="M.";
	
	public static String DRIVER_LAST_NAME_OTHER="Snow";

	
	
	public static Date DRIVER_DATE=new Date();
	
	public static String DRIVER_BLOOD_GROUP="A+";
	
	public static Long DRIVER_MOBILE=9876542312L;
	
	public static Long DRIVER_MOBILE_OTHER=9876000000L;
	
	public static Long DRIVER_MOBILE_UPDATED=9876502312L;
	
	public static Long DRIVER_HOME_NUMBER=9896592312L;
	
	public static String DRIVER_LICENCE_NUMBER="MH42A6543";
	
	public static String DRIVER_LICENCE_NUMBER_OTHER="MH12A6543";
	
	public static Boolean DRIVER_BOOLEAN_TRUE=true;
	
	public static Boolean DRIVER_BOOLEAN_FALSE=false;
	
	public static String DRIVER_LANGUAGE="TELGU";
	
	public static Long DRIVER_VENDOR_ID=213L;
	
	public static Integer DRIVER_UPDATED_BY=1;
	
	private static Gson gson=new Gson();
	
	public static DriverDetails standardDriverDetails()
	{
		return new DriverDetails(1L, DRIVER_FIRST_NAME, DRIVER_MIDDLE_NAME, DRIVER_LAST_NAME, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(),
				DRIVER_MOBILE,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE, DRIVER_VENDOR_ID, DRIVER_DATE, DRIVER_DATE,
				DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,1L,1L);
		
		
	}
	
	public static DriverDetails standardDriverDetailsOther()
	{
		return new DriverDetails(2L, DRIVER_FIRST_NAME_OTHER, DRIVER_MIDDLE_NAME_OTHER, DRIVER_LAST_NAME_OTHER, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(),
				DRIVER_MOBILE_OTHER,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER_OTHER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE, DRIVER_VENDOR_ID, DRIVER_DATE, DRIVER_DATE, 
				DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,2L,2L);
		
		
	}
	
	public static DriverDetails standardDriverDetailsNewMobileAndFirstName(Long driverId)
	{
		return new DriverDetails(driverId, "FIRST_UPDATE", DRIVER_MIDDLE_NAME, DRIVER_LAST_NAME, DRIVER_DATE, DRIVER_BLOOD_GROUP, standardAddress(),
				DRIVER_MOBILE_UPDATED,DRIVER_BOOLEAN_FALSE, DRIVER_HOME_NUMBER, DRIVER_LICENCE_NUMBER, DRIVER_DATE, DRIVER_DATE, DRIVER_BOOLEAN_FALSE,
				DRIVER_BOOLEAN_FALSE, DRIVER_LANGUAGE, DRIVER_VENDOR_ID, DRIVER_DATE, DRIVER_DATE, 
				DRIVER_UPDATED_BY,DRIVER_UPDATED_BY,driverId,driverId);
		
		
	}
	
	public static String standardDriverJson(DriverDetails driverDetails)
	{
		System.out.println(gson.toJson(driverDetails));
		
		return gson.toJson(driverDetails);
	}
	
	
	
	public static String standardUpdateMobileVerificationStatusDTOJson(UpdateMobileVerificationStatusUpdatedByDTO dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
	}

}
