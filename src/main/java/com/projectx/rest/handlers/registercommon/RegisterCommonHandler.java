package com.projectx.rest.handlers.registercommon;

import java.text.NumberFormat;
import java.text.ParsePosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.mvc.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.registercommon.ForgetPasswordEntity;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.services.registercommon.RegisterCommonService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.HandleVerificationService;

@Component
public class RegisterCommonHandler implements RegisterCommonService {

	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	private Integer ENTITY_TYPE_CUSTOMER=1;
	
	private Integer ENTITY_TYPE_VENDOR=2;
	
	
	private Integer EMAIL_REQ=1;
	
	private Integer MOBILE_REQ=2;
	
	@Override
	public ForgetPasswordEntity forgetPassword(String entity,Integer requestedBy,Long requestedById) {

		
		QuickRegisterEntity quickRegisterEntity=null;
		
		Boolean emailFlag=false;
		
		Boolean mobileFlag=false;
		
		Boolean sentPasswordStatus=false;
		
		ForgetPasswordEntity forgetPasswordEntity=new ForgetPasswordEntity();
		
		if(isMobileNumber(entity))
		{
			MobileVerificationDetails mobileVerificationDetails= mobileVerificationService.getByMobile(Long.parseLong(entity));
			forgetPasswordEntity.setCustomerId(mobileVerificationDetails.getKey().getCustomerId());
			forgetPasswordEntity.setCustomerType(mobileVerificationDetails.getKey().getCustomerType());
			
			mobileFlag=true;
			
		}
		else
		{
			EmailVerificationDetails emailVerificationDetails=emailVerificationService.getByEmail(entity);
			forgetPasswordEntity.setCustomerId(emailVerificationDetails.getKey().getCustomerId());
			forgetPasswordEntity.setCustomerType(emailVerificationDetails.getKey().getCustomerType());
			
			emailFlag=true;
			
		}
	
		
		try{
			quickRegisterEntity =quickRegisterService.getByEntityId(forgetPasswordEntity.getCustomerId());
		
			
			forgetPasswordEntity.setMobile(quickRegisterEntity.getMobile());
			forgetPasswordEntity.setEmail(quickRegisterEntity.getEmail());
			forgetPasswordEntity.setIsEmailVerified(quickRegisterEntity.getIsEmailVerified());
			forgetPasswordEntity.setIsMobileVerified(quickRegisterEntity.getIsMobileVerified());
			
			
		}catch(ResourceNotFoundException e)
		{
			if(forgetPasswordEntity.getCustomerType().equals(ENTITY_TYPE_CUSTOMER))
			{
				try{
					
					CustomerDetails customerDetails=customerDetailsService.findById(forgetPasswordEntity.getCustomerId());
					
					forgetPasswordEntity.setMobile(customerDetails.getMobile());
					forgetPasswordEntity.setEmail(customerDetails.getEmail());
					forgetPasswordEntity.setIsEmailVerified(customerDetails.getIsEmailVerified());
					forgetPasswordEntity.setIsMobileVerified(customerDetails.getIsMobileVerified());
					
					
				}catch(ResourceNotFoundException ex)
				{
					throw new ResourceNotFoundException();
				}
				
			}
			else
			{
				try{
				
					VendorDetails vendorDetails=vendorDetailsService.findById(forgetPasswordEntity.getCustomerId());
					
					forgetPasswordEntity.setMobile(vendorDetails.getMobile());
					forgetPasswordEntity.setEmail(vendorDetails.getEmail());
					forgetPasswordEntity.setIsEmailVerified(vendorDetails.getIsEmailVerified());
					forgetPasswordEntity.setIsMobileVerified(vendorDetails.getIsMobileVerified());
					
					
					
				}catch(ResourceNotFoundException ex)
				{
					throw new ResourceNotFoundException();
				}
				
			}
		}
		
		
		if(emailFlag && forgetPasswordEntity.getIsEmailVerified())
		{
			Boolean status=authenticationService.resetPassword(new CustomerIdTypeUpdatedByDTO(forgetPasswordEntity.getCustomerId(),
					forgetPasswordEntity.getCustomerType(), requestedBy,requestedById),EMAIL_REQ);
			
			if(status)
				sentPasswordStatus=true;
		}
		
		if(mobileFlag && forgetPasswordEntity.getIsMobileVerified())
		{
			Boolean status=authenticationService.resetPassword(new CustomerIdTypeUpdatedByDTO(forgetPasswordEntity.getCustomerId(),
					forgetPasswordEntity.getCustomerType(), requestedBy,requestedById),MOBILE_REQ);
			
			if(status)
				sentPasswordStatus=true;
		}
		
		forgetPasswordEntity.setIsPasswordSent(sentPasswordStatus);
		
		return forgetPasswordEntity;
	}
	
	
	private Boolean isMobileNumber(String entity)
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(entity, pos);
		return (entity.length() == pos.getIndex()&&entity.length()==10);
		
	}


}
