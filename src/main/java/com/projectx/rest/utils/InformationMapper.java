package com.projectx.rest.utils;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
@Component
@Profile(value={"Dev","Test","Prod"})
public class InformationMapper {
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;

	private static final Integer ENTITY_TYPE_CUSTOMER=new Integer(1);
	
	private static final Integer ENTITY_TYPE_VENDOR=new Integer(2);


	
	
	public HashMap<String,Object > getBasicInfoByEntityIdType(Long entityId,Integer entityType) throws QuickRegisterEntityNotFoundException,
					CustomerDetailsNotFoundException,VendorDetailsNotFoundException
	{
		HashMap<String,Object> infoMap=new HashMap<String,Object>();
		
		QuickRegisterEntity quickRegisterEntity=null;
				
		try{
			quickRegisterEntity=customerQuickRegisterService.getByEntityId(entityId);
		}catch(ResourceNotFoundException e)
		{
			quickRegisterEntity=null;
		}
		
		
		if(entityType.equals(ENTITY_TYPE_CUSTOMER))
		{
			if(quickRegisterEntity!=null)
			{
				infoMap.put("entityId", quickRegisterEntity.getCustomerId());
				infoMap.put("firstName", quickRegisterEntity.getFirstName());
				infoMap.put("middleName", "");
				infoMap.put("lastName", quickRegisterEntity.getLastName());
				infoMap.put("email", quickRegisterEntity.getEmail());
				infoMap.put("isEmailVerified", quickRegisterEntity.getIsEmailVerified());
				infoMap.put("mobile", quickRegisterEntity.getMobile());
				infoMap.put("isMobileVerified", quickRegisterEntity.getIsMobileVerified());
				infoMap.put("isCompleteRegisterCompleted", "false");
				
		   }
			else
			{	
				CustomerDetails customerDetails=customerDetailsService.findById(entityId);
				
				infoMap.put("entityId", customerDetails.getCustomerId());
				infoMap.put("firstName", customerDetails.getFirstName());
				infoMap.put("middleName", customerDetails.getMiddleName());
				infoMap.put("lastName", customerDetails.getLastName());
				infoMap.put("email", customerDetails.getEmail());
				infoMap.put("isEmailVerified", customerDetails.getIsEmailVerified());
				infoMap.put("mobile", customerDetails.getMobile());
				infoMap.put("isMobileVerified", customerDetails.getIsMobileVerified());
				infoMap.put("isCompleteRegisterCompleted", "true");
				
			}
			
		}
		else if(entityType.equals(ENTITY_TYPE_VENDOR))
		{
			if(quickRegisterEntity!=null)
			{
				infoMap.put("entityId", quickRegisterEntity.getCustomerId());
				infoMap.put("firstName", quickRegisterEntity.getFirstName());
				infoMap.put("middleName", "");
				infoMap.put("lastName", quickRegisterEntity.getLastName());
				infoMap.put("email", quickRegisterEntity.getEmail());
				infoMap.put("isEmailVerified", quickRegisterEntity.getIsEmailVerified());
				infoMap.put("mobile", quickRegisterEntity.getMobile());
				infoMap.put("isMobileVerified", quickRegisterEntity.getIsMobileVerified());
				infoMap.put("isCompleteRegisterCompleted", "false");
			}
			else
			{
				VendorDetails vendorDetails=vendorDetailsService.findById(entityId);
				
				infoMap.put("entityId", vendorDetails.getVendorId());
				infoMap.put("firstName", vendorDetails.getFirstName());
				infoMap.put("middleName",vendorDetails.getMiddleName());
				infoMap.put("lastName", vendorDetails.getLastName());
				infoMap.put("email", vendorDetails.getEmail());
				infoMap.put("isEmailVerified", vendorDetails.getIsEmailVerified());
				infoMap.put("mobile", vendorDetails.getMobile());
				infoMap.put("isMobileVerified", vendorDetails.getIsMobileVerified());
				//TODO how to decide the state of complete register 
				infoMap.put("isCompleteRegisterCompleted", "true");
			}
		}		

		return infoMap;
	}
	
	

}
