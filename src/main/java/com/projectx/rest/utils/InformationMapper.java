package com.projectx.rest.utils;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
@Component
@Profile(value={"Test","Dev"})
public class InformationMapper {
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;

	private static final Integer ENTITY_TYPE_CUSTOMER=new Integer(1);
	
	private static final Integer ENTITY_TYPE_VENDOR=new Integer(2);


	
	
	public HashMap<String,Object > getBasicInfoByEntityIdType(Long entityId,Integer entityType)
	{
		HashMap<String,Object> infoMap=new HashMap<String,Object>();
		
		QuickRegisterEntity quickRegisterEntity=customerQuickRegisterService.getByEntityId(entityId);
		
		if(entityType.equals(ENTITY_TYPE_CUSTOMER))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				
				infoMap.put("firstName", quickRegisterEntity.getFirstName());
				infoMap.put("lastName", quickRegisterEntity.getLastName());
				infoMap.put("email", quickRegisterEntity.getEmail());
				infoMap.put("isEmailVerified", quickRegisterEntity.getIsEmailVerified());
				infoMap.put("mobile", quickRegisterEntity.getMobile());
				infoMap.put("isMobileVerified", quickRegisterEntity.getIsMobileVerified());
				
		   }
			else
			{	
				CustomerDetails customerDetails=customerDetailsService.findById(entityId);
				
				infoMap.put("firstName", customerDetails.getFirstName());
				infoMap.put("lastName", customerDetails.getLastName());
				infoMap.put("email", customerDetails.getEmail());
				infoMap.put("isEmailVerified", customerDetails.getIsEmailVerified());
				infoMap.put("mobile", customerDetails.getMobile());
				infoMap.put("isMobileVerified", customerDetails.getIsMobileVerified());
				
			}
			
		}
		else if(entityType.equals(ENTITY_TYPE_VENDOR))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				infoMap.put("firstName", quickRegisterEntity.getFirstName());
				infoMap.put("lastName", quickRegisterEntity.getLastName());
				infoMap.put("email", quickRegisterEntity.getEmail());
				infoMap.put("isEmailVerified", quickRegisterEntity.getIsEmailVerified());
				infoMap.put("mobile", quickRegisterEntity.getMobile());
				infoMap.put("isMobileVerified", quickRegisterEntity.getIsMobileVerified());
			}
			else
			{
				VendorDetails vendorDetails=vendorDetailsService.findById(entityId);
				
				infoMap.put("firstName", vendorDetails.getFirstName());
				infoMap.put("lastName", vendorDetails.getLastName());
				infoMap.put("email", vendorDetails.getEmail());
				infoMap.put("isEmailVerified", vendorDetails.getIsEmailVerified());
				infoMap.put("mobile", vendorDetails.getMobile());
				infoMap.put("isMobileVerified", vendorDetails.getIsMobileVerified());
			}
		}		

		return infoMap;
	}
	
	

}
