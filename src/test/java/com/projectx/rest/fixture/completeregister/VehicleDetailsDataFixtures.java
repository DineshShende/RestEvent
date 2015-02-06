package com.projectx.rest.fixture.completeregister;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.rest.domain.completeregister.VehicleBrandDetails;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.completeregister.VehicleTypeDetails;


public class VehicleDetailsDataFixtures {
	
public static String VEHICLE_TYPE_NAME="VehcileTypeName";
	
	public static Long VEHICLE_TYPE_ID=235L;
	
	public static Long VEHICLE_BRAND_ID=267L;
	
	public static String VEHICLE_BRAND_NAME="Tata tempo";
	
	public static String VEHICLE_MODEL_NUMBER="407";
	
	public static Long VEHICLE_ID=269L;
	
	public static String VEHICLE_OWNER_FIRST_NAME="Abraham";
	
	public static String VEHICLE_OWNER_MIDDLE_NAME="Benjamin";
	
	public static String VEHICLE_OWNER_LAST_NAME="Deviliers";
	
	public static String VEHICLE_OWNER_FIRST_NAME_OTHER="John";
	
	public static String VEHICLE_OWNER_MIDDLE_NAME_OTHER="Hamish";
	
	public static String VEHICLE_OWNER_LAST_NAME_OTHER="Watson";
	
	public static String VEHICLE_BODY_TYPE_OPEN="OPEN";
	
	public static String VEHICLE_BODY_TYPE_CLOSED="CLOSED";
	
	public static Boolean VEHICLE_BOOLEAN_TRUE=true;
	
	public static String VEHICLE_REGISTRATION_NUMBER="MH12HG4563";
	
	public static String VEHICLE_CHASIS_NUMBER="MH12HG4563JDFR634";
	
	public static String VEHICLE_REGISTRATION_NUMBER_OTHER="MH14HG4588";
	
	public static String VEHICLE_CHASIS_NUMBER_OTHER="MH12HG456DFKJDFR634";
	
	public static Integer VEHICLE_LOAD_CAPACITY=100;
	
	public static Integer VEHICLE_NOOF_WHEELS=8;
	
	public static String VEHICLE_PERMIT_TYPE_NATIONAL="NATIONAL";
	
	public static String VEHICLE_PERMIT_TYPE_STATE="STATE";
	
	public static String VEHICLE_INSURANCE_NUMBER="ADRF3442537JD";
	
	public static String VEHICLE_INSURANCE_COMPANY="LIC";
	
	public static Integer VEHICLE_LENGTH=100;
	
	public static Integer VEHICLE_HEIGHT=10;
	
	public static Integer VEHICLE_WIDTH=40;
	
	public static Long VEHICLE_VENDOR_ID=213L;
	
	public static Date VEHCLE_DATE=new Date();
	
	public static String VEHICLE_UPDATED_BY="CUST_ONLINE";
	
	private static Gson gson=new Gson();
	
	
	public static VehicleTypeDetails standVehicleTypeDetails()
	{
		return new VehicleTypeDetails(VEHICLE_TYPE_ID, VEHICLE_TYPE_NAME);
	}
	
	public static VehicleBrandDetails standardVehicleBrandDetails()
	{
		return new VehicleBrandDetails(VEHICLE_BRAND_ID, standVehicleTypeDetails(), VEHICLE_BRAND_NAME, VEHICLE_MODEL_NUMBER);
		
	}
	
	public static VehicleBrandDetails standardVehicleBrandDetails307()
	{
		return new VehicleBrandDetails(VEHICLE_BRAND_ID, standVehicleTypeDetails(), VEHICLE_BRAND_NAME, "307");
		
	}
	
	public static VehicleBrandDetails standardVehicleBrandDetailsAcer()
	{
		return new VehicleBrandDetails(VEHICLE_BRAND_ID, standVehicleTypeDetails(), "ACER", "507");
		
	}
	
	public static VehicleDetailsDTO standardVehicleDetails()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				standVehicleTypeDetails(), standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				VEHICLE_REGISTRATION_NUMBER, VEHICLE_CHASIS_NUMBER,VEHICLE_LOAD_CAPACITY,VEHICLE_LENGTH,VEHICLE_WIDTH,VEHICLE_HEIGHT, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,
				VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsOpen()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				standVehicleTypeDetails(), standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_OPEN, false,
				"VEHICLE_REGISTRATION_NUMBERO", "VEHICLE_CHASIS_NUMBERO",40,100,40,10, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,
				VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsOpen307()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				standVehicleTypeDetails(), standardVehicleBrandDetails307(), VEHICLE_BODY_TYPE_OPEN, false,
				"VEHICLE_REGISTRATION_NUMBERO", "VEHICLE_CHASIS_NUMBERO",40,100,40,10, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,
				VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsClosed()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				standVehicleTypeDetails(), standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_CLOSED, false,
				"VEHICLE_REGISTRATION_NUMBERC", "VEHICLE_CHASIS_NUMBERC",60,100,60,10, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,
				VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY);
	}
	
	
	public static VehicleDetailsDTO standardVehicleDetailsFlexible()
	{
		
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME, VEHICLE_OWNER_MIDDLE_NAME, VEHICLE_OWNER_LAST_NAME,
				standVehicleTypeDetails(), standardVehicleBrandDetailsAcer(), VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				"VEHICLE_REGISTRATION_NUMBERF", "VEHICLE_CHASIS_NUMBERF",100,200,50,20, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,
				VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY);
	}
	
	public static VehicleDetailsDTO standardVehicleDetailsOther()
	{
		return new VehicleDetailsDTO(VEHICLE_ID, VEHICLE_OWNER_FIRST_NAME_OTHER, VEHICLE_OWNER_MIDDLE_NAME_OTHER, VEHICLE_OWNER_LAST_NAME_OTHER,
				standVehicleTypeDetails(), standardVehicleBrandDetails(), VEHICLE_BODY_TYPE_OPEN, VEHICLE_BOOLEAN_TRUE,
				VEHICLE_REGISTRATION_NUMBER_OTHER, VEHICLE_CHASIS_NUMBER_OTHER,VEHICLE_LOAD_CAPACITY,VEHICLE_LENGTH,VEHICLE_WIDTH,VEHICLE_HEIGHT, VEHICLE_NOOF_WHEELS,
				VEHICLE_PERMIT_TYPE_NATIONAL, VEHICLE_BOOLEAN_TRUE, VEHICLE_INSURANCE_NUMBER, VEHICLE_INSURANCE_COMPANY, VEHICLE_VENDOR_ID,
				VEHCLE_DATE, VEHCLE_DATE, VEHICLE_UPDATED_BY);
	}
	
	public static String standardVehicleJson(VehicleDetailsDTO vehicleDetails)
	{
		return gson.toJson(vehicleDetails);
	}

}
