
package com.projectx.rest.fixture.completeregister;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.mvc.domain.completeregister.VerifyEmailDTO;
import com.projectx.mvc.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;



public class CustomerDetailsDataFixtures {
	
	
	public static Date CUST_DOB =new Date();
	
	public static String CUST_LANG="Marathi";
	public static String CUST_BUSINESS_DOMAIN="TRANSPORT";
	
	public static String CUST_NAME_OF_FIRM="ABC TRANSPORT";
	public static Long CUST_SEC_MOBILE=8598058044L;
	public static Long CUST_SEC_MOBILE_NEW=8888888888L;
	public static Long CUST_MOBILE_NEW=9999999999L;
	public static String CUST_SEC_EMAIL="dineshshende@hotmail.com";
	
	public static String CUST_EMAIL_NEW="dineshshende@gmail.com";
	
	public static Integer CUST_TYPE_CUSTOMER=1;
	
	public static Date CUST_DATE=new Date();
	
	static Gson gson=new Gson();
	
	
	/*
	public static CustomerDetails standardCustomerDetails()
	{
		return new CustomerDetails(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_DOB, standardAddress(), CUST_MOBILE, CUST_ISMOBILE_VERIFIED,
				CUST_EMAIL, CUST_ISEMAIL_VERIFIED, CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),CUST_SEC_MOBILE , 
				CUST_ISMOBILE_VERIFIED, 
				CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
*/
	
	public static CustomerDetails standardCustomerFromQuickEntity()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerFromQuickEntityDuplicate()
	{
		return new CustomerDetails(435L, standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerFromQuickEntity(Long customerId)
	{
		return new CustomerDetails(customerId, standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), null, null, standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), null, null, null, null, null, false,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static CustomerDetails standardCustomerDetailsFirstPart()
	{
		return new CustomerDetails(standardEmailMobileCustomer().getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), CUST_DATE, standardAddress(), standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, null, null, null, null, null,
				null, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetails(CustomerDetails customerDetails)
	{
		return new CustomerDetails(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddress(), customerDetails.getMobile(), 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, false,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetailsAlreadyPresent()
	{
		return new CustomerDetails(323L, CUST_FIRSTNAME,CUST_LASTNAME, CUST_DATE, standardAddress(),
				CUST_MOBILE,CUST_IS_MOBILE_VERIFIED_FALSE,CUST_EMAIL,CUST_IS_EMAIL_VERIFIED_FALSE, CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, false,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	
	public static CustomerDetails standardCustomerDetailsError(CustomerDetails customerDetails)
	{
		return new CustomerDetails(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddress(), null, 
				customerDetails.getIsEmailVerified(),null,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				CUST_SEC_MOBILE, false,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetailsWithOldMobileSceMobileEmail(CustomerDetails customerDetails,Long mobile,Long secMobolie,String email)
	{
		return new CustomerDetails(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddress(), mobile, 
				customerDetails.getIsEmailVerified(),email,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, standardAddress(),
				secMobolie, false,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetailsWithNewSecondaryMobile(CustomerDetails customerDetails)
	{
		return new CustomerDetails(customerDetails.getCustomerId(), standardEmailMobileCustomer().getFirstName(),
				standardEmailMobileCustomer().getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), standardEmailMobileCustomer().getMobile(), 
				standardEmailMobileCustomer().getIsEmailVerified(),standardEmailMobileCustomer().getEmail(),
				standardEmailMobileCustomer().getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetailsWithNewMobile(CustomerDetails customerDetails)
	{
		return new CustomerDetails(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), CUST_MOBILE_NEW, 
				customerDetails.getIsEmailVerified(),customerDetails.getEmail(),
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	
	public static CustomerDetails standardCustomerDetailsWithNewEmail(CustomerDetails customerDetails)
	{
		return new CustomerDetails(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, customerDetails.getHomeAddressId(), CUST_MOBILE_NEW, 
				customerDetails.getIsEmailVerified(),CUST_EMAIL_NEW,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static CustomerDetails standardCustomerDetailsWithNewHomeAddress(CustomerDetails customerDetails)
	{
		return new CustomerDetails(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), CUST_DATE, standardAddressUpdated(), CUST_MOBILE_NEW, 
				customerDetails.getIsEmailVerified(),CUST_EMAIL_NEW,
				customerDetails.getIsEmailVerified(), CUST_LANG, CUST_BUSINESS_DOMAIN, CUST_NAME_OF_FIRM, customerDetails.getFirmAddressId(),
				CUST_SEC_MOBILE_NEW, null,	CUST_SEC_EMAIL, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
		
	}
	
	public static UpdateAddressDTO standardUpdateAddressDTO()
	{
		return new UpdateAddressDTO(CUST_ID, new Address(CUST_TYPE_CUSTOMER, "Updted", "Updted", "Updted", "Updted", 234567,
				new Date(), new Date(), "CUST_ONLINE"));
	}
	
	public static UpdateMobileVerificationStatusUpdatedByDTO standardMobileVerificationStatusDTO()
	{
		return new UpdateMobileVerificationStatusUpdatedByDTO(CUST_ID,CUST_MOBILE, true,CUST_UPDATED_BY);
	}
	
	
	public static String standardJsonCustomerDetails(CustomerDetails customerDetails)
	{
		
		/*
		JsonObject jsonArray=Json.createObjectBuilder().add("firstName", customerDetails.getFirstName())
							.add("lastName", customerDetails.getLastName())
							.add("dateOfBirth", new Date().getTime())
							.add("homeAddress",Json.createObjectBuilder().add("customerType", standardAddress().getCustomerType())
									.add("addressLine",customerDetails.getHomeAddressId().getAddressLine())
									.add("city",customerDetails.getHomeAddressId().getCity())
									.add("district",customerDetails.getHomeAddressId().getDistrict())
									.add("state",customerDetails.getHomeAddressId().getState())
									.add("pincode",customerDetails.getHomeAddressId().getPincode())
									.add("insertTime",customerDetails.getHomeAddressId().getInsertTime().getTime())
									.add("updateTime",customerDetails.getHomeAddressId().getUpdateTime().getTime())
									.add("updatedBy",customerDetails.getHomeAddressId().getUpdatedBy())
									.build().toString())
							.add("mobile", customerDetails.getMobile())
							.add("isMobileVerified", customerDetails.getIsMobileVerified())
							.add("email", customerDetails.getEmail())
							.add("isEmailVerified", customerDetails.getIsEmailVerified())
							.add("language", customerDetails.getLanguage())
							.add("businessDomain",customerDetails.getBusinessDomain())
							.add("nameOfFirm", customerDetails.getNameOfFirm())
							.add("homeAddress",null)
							.add()
							
				.build();
		
		JsonObject jsonObject=new JsonObject();
		
		System.out.println(jsonArray.toString());
		*/
		
		
		System.out.println(gson.toJson(customerDetails));
		
		return gson.toJson(customerDetails);
	}
/*	
	public static String standardJsonUpdateAddress()
	{
		System.out.println(gson.toJson(standardUpdateAddressDTO()));
		
		return gson.toJson(standardUpdateAddressDTO());
	}
*/	
	public static String standardJsonUpdateMobileVerificationStatus()
	{
		System.out.println(gson.toJson(standardMobileVerificationStatusDTO()));
		
		return gson.toJson(standardMobileVerificationStatusDTO());
	}
	
	
	public static String standardJsonVerifyMobileDetails(VerifyMobileDTO verifyMobileDTO)
	{
		System.out.println(gson.toJson(verifyMobileDTO));
		
		return gson.toJson(verifyMobileDTO);
	}
	
	public static String standardJsonVerifyEmailDetails(VerifyEmailDTO verifyEmailDTO)
	{
		System.out.println(gson.toJson(verifyEmailDTO));
		
		return gson.toJson(verifyEmailDTO);
	}
	
	public static String standardJsonCustomerIdTypeMobileDTO(CustomerIdTypeMobileTypeDTO idTypeMobileDTO)
	{
		System.out.println(gson.toJson(idTypeMobileDTO));
		
		return gson.toJson(idTypeMobileDTO);
	}
	
	public static String standardJsonCustomerIdTypeEmailDTO(CustomerIdTypeEmailTypeDTO idTypeEmailDTO)
	{
		System.out.println(gson.toJson(idTypeEmailDTO));
		
		return gson.toJson(idTypeEmailDTO);
	}
}
