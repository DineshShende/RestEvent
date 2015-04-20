package com.projectx.rest.fixture.request;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.rest.domain.request.FreightRequestByCustomer;


public class FreightRequestByCustomerDataFixture {

public static Long CREQ_REQ_ID=414L;
	
	public static Integer CREQ_SOURCE=411045;
	
	public static Integer CREQ_DEST=413102;
	
	public static Date CREQ_PICK_UP_TIME=new Date(new Date().getTime()+100000000000L);
	
	public static Integer CREQ_NOOFVEHICLE=1;
	
	public static Boolean CREQ_BOOL_FALSE=false;
	
	public static Boolean CREQ_BOOL_TRUE=true;
	
	public static Integer CREQ_CAPACITY=40;

	public static String CREQ_BODYTYPE_OPEN="Open";
	
	public static String CREQ_BODYTYPE_CLOSED="Closed";
	
	public static Integer CREQ_GROSS_WEIGHT=40;
	
	public static Integer CREQ_LENGTH=100;
	
	public static Integer CREQ_WIDTH=40;
	
	public static Integer CREQ_HEIGHT=10;
	
	public static String CREQ_VEHICLE_BRAND="Tata Tempo";
	
	public static String CREQ_VEHICLE_MODEL="407";
	
	public static String CREQ_COMMITITY="Fertiliser";
	
	public static String CREQ_PICKUP_TIME="1:00PM";
	
	public static Date CREQ_DATE=new Date();
	
	public static Long CREQ_CUST_ID=212L;
	
	public static String CREQ_STATUS="NEW";
	
	public static Integer CREQ_UPDATED_BY=1;
	
	private static Gson gson=new Gson();
	
	
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoad()
	{
	
		
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				100,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoad(Long customerId)
	{
	
		
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				100,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoad110()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				110,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoad110(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				110,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoadOpenTataReq()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, "307", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoadOpenTataReq(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_OPEN,null , null, null,null, CREQ_VEHICLE_BRAND, "307", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoadClosedAcerReq()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_CLOSED,null , null, null,null, "Acer", "507", CREQ_COMMITITY,CREQ_PICKUP_TIME,
				CREQ_CUST_ID,CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoadClosedAcerReq(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_CLOSED,null , null, null,null, "Acer", "507", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoadUpdated()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_CLOSED, null, null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerFullTruckLoadUpdated(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_TRUE, CREQ_BOOL_FALSE,
				CREQ_CAPACITY,CREQ_BODYTYPE_CLOSED, null, null, null,null, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null, CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoad()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_CLOSED, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	10, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoad(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_CLOSED, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	10, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoad15()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_CLOSED, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	15, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE, CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoad15(long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_CLOSED, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	15, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL,
				CREQ_COMMITITY, CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "", "", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "", "", CREQ_COMMITITY,CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "", "307", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "", "307", CREQ_COMMITITY, 
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, CREQ_VEHICLE_BRAND, "", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, CREQ_VEHICLE_BRAND, "",
				CREQ_COMMITITY, CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenTata()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY, 
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenAcer()
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "Acer", "507", CREQ_COMMITITY, 
				CREQ_PICKUP_TIME,CREQ_CUST_ID, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenTata(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, CREQ_VEHICLE_BRAND, CREQ_VEHICLE_MODEL, CREQ_COMMITITY,CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,
				CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static FreightRequestByCustomer standardFreightRequestByCustomerLessThanTruckLoadOpenAcer(Long customerId)
	{
		return new FreightRequestByCustomer(CREQ_SOURCE, CREQ_DEST, CREQ_PICK_UP_TIME, CREQ_NOOFVEHICLE, CREQ_BOOL_FALSE, CREQ_BOOL_TRUE,
				null, CREQ_BODYTYPE_OPEN, CREQ_GROSS_WEIGHT, CREQ_LENGTH, CREQ_WIDTH,	CREQ_HEIGHT, "Acer", "507", CREQ_COMMITITY,
				CREQ_PICKUP_TIME,customerId, CREQ_STATUS,null,CREQ_DATE, CREQ_DATE,CREQ_UPDATED_BY,CREQ_UPDATED_BY,CREQ_CUST_ID,CREQ_CUST_ID);
	}
	
	public static String standardJsonFreightRequestByCustomer(FreightRequestByCustomer freightRequestByCustomer)
	{
		System.out.println(gson.toJson(freightRequestByCustomer));
		
		return gson.toJson(freightRequestByCustomer);
	}
	
}
