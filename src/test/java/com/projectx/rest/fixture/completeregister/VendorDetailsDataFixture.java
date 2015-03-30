package com.projectx.rest.fixture.completeregister;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;

public class VendorDetailsDataFixture {

	public static Long VENDOR_ID=213L;
	
	public static String VENDER_FIRSTNAME="Ted";
	
	public static String VENDER_MIDDLENAME="A.";
	
	public static String VENDER_LASTNAME="Mosby";
	
	public static String VENDER_FIRMNAME="Mosby Architect";
	
	
	
	public static Date VENDOR_DATE=new Date();
	
	public static Address VENDOR_ADDRESS=standardAddress();
	
	public static Long VENDOR_MOBILE=8625867370L;
	
	public static Long VENDOR_SEC_MOBILE=8625867000L;
	
	public static String VENDOR_EMAIL="tedmosby@gmail.com";
	
	public static Boolean VENDOR_STATUS_TRUE=true;
	
	public static Boolean VENDOR_STATUS_FALSE=false;
	
	public static String VENDOR_LANGUAGE="English";
	
	public static String VENDOR_UPDATEDBY="CUST_ONLINE";
	
	private static Gson gson=new Gson();
	
	public static QuickRegisterEntity standardEmailMobileVendorQuick()
	{
		return new QuickRegisterEntity(VENDOR_ID, VENDER_FIRSTNAME, VENDER_LASTNAME, VENDOR_EMAIL, VENDOR_MOBILE, ADDRESS_PINCODE, VENDOR_STATUS_FALSE, VENDOR_STATUS_FALSE, ENTITY_TYPE_VENDOR, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetails standardVendor()
	{
		return new VendorDetails(VENDOR_ID, VENDER_FIRSTNAME,VENDER_MIDDLENAME, VENDER_LASTNAME, VENDOR_DATE,VENDER_FIRMNAME,VENDOR_ADDRESS, VENDOR_ADDRESS, VENDOR_MOBILE, null,VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,VENDOR_SEC_MOBILE,VENDOR_STATUS_FALSE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetails standardVendor(Long vendorId)
	{
		return new VendorDetails(vendorId, VENDER_FIRSTNAME,VENDER_MIDDLENAME, VENDER_LASTNAME, VENDOR_DATE, VENDER_FIRMNAME,VENDOR_ADDRESS,VENDOR_ADDRESS, VENDOR_MOBILE,null, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,VENDOR_SEC_MOBILE,VENDOR_STATUS_FALSE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static VendorDetails standardVendorCreatedFromQuickRegister()
	{
		return new VendorDetails(standardEmailMobileVendor().getCustomerId(), standardEmailMobileVendor().getFirstName(),
				VENDER_MIDDLENAME,standardEmailMobileVendor().getLastName(),null,null, null, null, standardEmailMobileVendor().getMobile(),null, 
				standardEmailMobileVendor().getIsMobileVerified(),standardEmailMobileVendor().getEmail(),standardEmailMobileVendor().getIsEmailVerified(),
				null,null,null, standardEmailMobileVendor().getInsertTime(), new Date(), standardEmailMobileVendor().getUpdatedBy());
	}
	
	public static VendorDetails standardVendorCreatedFromQuickRegister(Long vendorId)
	{
		return new VendorDetails(vendorId, standardEmailMobileVendor().getFirstName(),null,
				standardEmailMobileVendor().getLastName(), null,null,null, null, standardEmailMobileVendor().getMobile(),null, 
				standardEmailMobileVendor().getIsMobileVerified(),standardEmailMobileVendor().getEmail(),standardEmailMobileVendor().getIsEmailVerified(),
				null,null,null, standardEmailMobileVendor().getInsertTime(), new Date(), standardEmailMobileVendor().getUpdatedBy());
	}
	
	
	public static VendorDetails standardVendor(VendorDetails vendorDetails)
	{
		return new VendorDetails(vendorDetails.getVendorId(), vendorDetails.getFirstName(),vendorDetails.getMiddleName(),vendorDetails.getLastName(),
				new Date(),vendorDetails.getFirmName(),vendorDetails.getFirmAddress(),vendorDetails.getHomeAddress(), vendorDetails.getMobile(),null,
				vendorDetails.getIsMobileVerified(),vendorDetails.getEmail(),vendorDetails.getIsEmailVerified(),VENDOR_LANGUAGE,
				vendorDetails.getSecondaryMobile(),false,vendorDetails.getInsertTime(), new Date(), vendorDetails.getUpdatedBy());
	}
	
	
	public static VendorDetails standardVendorUpdatedFirstLastName()
	{
		return new VendorDetails(VENDOR_ID, "Updated",VENDER_MIDDLENAME, "Updated", VENDOR_DATE,VENDER_FIRMNAME,VENDOR_ADDRESS, VENDOR_ADDRESS, VENDOR_MOBILE,null, VENDOR_STATUS_FALSE,
				VENDOR_EMAIL,VENDOR_STATUS_FALSE, VENDOR_LANGUAGE,VENDOR_SEC_MOBILE,VENDOR_STATUS_FALSE, VENDOR_DATE, VENDOR_DATE, VENDOR_UPDATEDBY);
	}
	
	public static AuthenticationDetailsKey standardAuthenticationDetailsKeyVendor()
	{
		return new AuthenticationDetailsKey(VENDOR_ID, ENTITY_TYPE_VENDOR);
	}

	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsVendor()
	{
		
		return new AuthenticationDetails(standardAuthenticationDetailsKeyVendor(), VENDOR_EMAIL, VENDOR_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, 0, 0,new Date(),new Date(),CUST_UPDATED_BY);
	}
	
	public static UpdateMobileVerificationStatusUpdatedByDTO standardMobileUpdateVerificationStatusDTO()
	{
		return new UpdateMobileVerificationStatusUpdatedByDTO(VENDOR_ID,VENDOR_MOBILE, VENDOR_STATUS_TRUE,VENDOR_UPDATEDBY);
	}
	
	public static UpdateEmailVerificationStatusUpdatedByDTO standardUpdateEmailVerificationStatusDTO()
	{
		return new UpdateEmailVerificationStatusUpdatedByDTO(VENDOR_ID,VENDOR_EMAIL, VENDOR_STATUS_TRUE,VENDOR_UPDATEDBY);
	}
	
	public static String standardJsonVendor(VendorDetails vendorDetails)
	{
		System.out.println(gson.toJson(vendorDetails));
		
		return gson.toJson(vendorDetails);
	}
	
	public static String standardJsonUpdateVerificationStatus(UpdateMobileVerificationStatusUpdatedByDTO vendorDetails)
	{
		System.out.println(gson.toJson(vendorDetails));
		
		return gson.toJson(vendorDetails);
	}
	
	
}
