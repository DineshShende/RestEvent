package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_CHANGED;
import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_DEFAULT;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.InformationMapper;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;


@Component
@Profile(value={"Dev","Test"})
public class AuthenticationHandler implements AuthenticationService {

	@Autowired
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;

	@Autowired
	HandleCustomerVerification handleCustomerVerification; 
	
	@Autowired
	QuickRegisterService customerQuickRegisterService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;

	@Autowired
	InformationMapper informationMapper; 
	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static final Integer UPDATE_SUCESS=new Integer(1);
	


	@Override
	public AuthenticationDetails createCustomerAuthenticationDetails(
			QuickRegisterEntity customerQuickRegisterEntity)
	{
	
		AuthenticationDetails customerAuthenticationDetails=new AuthenticationDetails();
		
		AuthenticationDetailsKey key=new AuthenticationDetailsKey(customerQuickRegisterEntity.getCustomerId(),
				customerQuickRegisterEntity.getCustomerType());
		customerAuthenticationDetails.setKey(key);
		customerAuthenticationDetails.setEmail(customerQuickRegisterEntity.getEmail());
		customerAuthenticationDetails.setMobile(customerQuickRegisterEntity.getMobile());
		customerAuthenticationDetails.setLastUnsucessfullAttempts(0);;
		customerAuthenticationDetails.setResendCount(0);
		customerAuthenticationDetails.setInsertTime(new Date());
		customerAuthenticationDetails.setUpdateTime(new Date());
		customerAuthenticationDetails.setUpdatedBy("CUST_ONLINE");
		
		return customerAuthenticationDetails;
	}

	
	@Override
	public AuthenticationDetails verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		
		AuthenticationDetails customerAuthenticationDetails=new AuthenticationDetails();
		
		if(isMobileNumber(loginVerificationDTO.getLoginEntity()))
		{
			customerAuthenticationDetails=customerAuthenticationDetailsRepository.getByMobile(Long.parseLong(loginVerificationDTO.getLoginEntity()));
		}
		else
		{
			customerAuthenticationDetails=customerAuthenticationDetailsRepository.getByEmail(loginVerificationDTO.getLoginEntity());
		}
		
		if(customerAuthenticationDetails.getKey()!=null && !loginVerificationDTO.getPassword().equals(customerAuthenticationDetails.getPassword()))
		{
			customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(customerAuthenticationDetails.getKey().getCustomerId(),
					customerAuthenticationDetails.getKey().getCustomerType());
			
			customerAuthenticationDetails=new AuthenticationDetails();	
			
		}
		
		return customerAuthenticationDetails;
	}

	
	
	@Override
	public AuthenticationDetails verifyDefaultEmailLoginDetails(
			LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO) {

		AuthenticationDetails customerAuthenticationDetails=new AuthenticationDetails(); 
		
		customerAuthenticationDetails=customerAuthenticationDetailsRepository.getByCustomerIdType(
				emailPasswordDTO.getCustomerId(),emailPasswordDTO.getCustomerType());
		
		if(customerAuthenticationDetails.getKey()!=null &&
				customerAuthenticationDetails.getEmailPassword().equals(emailPasswordDTO.getEmailPassword()))
		{
			return customerAuthenticationDetails;
		}
		else
		{
			customerAuthenticationDetails=new AuthenticationDetails();
			return customerAuthenticationDetails;
		}
		
		
	}

	
	@Override
	public Boolean sendOrResendOrResetDefaultPassword(Long entityId,
			Integer entityType, Boolean resetFlag, Boolean resendFlag) {
		
		
		HashMap<String,Object> infoMap=informationMapper.getBasicInfoByEntityIdType(entityId, entityType);

		String firstName=(String)infoMap.get("firstName");
		String lastName = (String)infoMap.get("lastName");
		String email=(String)infoMap.get("email");
		Boolean isEmailVerified=(Boolean)infoMap.get("isEmailVerified");
		Long mobile=(Long)infoMap.get("mobile");
		Boolean isMobileVerified=(Boolean)infoMap.get("isMobileVerified");
		
		
		Boolean emailSendStatus=true;
		Boolean smsSendStatus=true;
		Integer passwordUpdateStatus=new Integer(1);
		Integer resendStatus=new Integer(1);
		
		AuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.getByCustomerIdType(entityId,entityType);
		
		if(!resetFlag && (customerAuthenticationDetails.getPasswordType()!=null && 
				customerAuthenticationDetails.getPasswordType().equals(CUST_PASSWORD_TYPE_CHANGED)))
			return true;
		
		if(customerAuthenticationDetails.getPassword()==null || resetFlag)
		{	
			String password=handleCustomerVerification.generatePassword();
			customerAuthenticationDetails.setPassword(password);
			customerAuthenticationDetails.setPasswordType(CUST_PASSWORD_TYPE_DEFAULT);
			customerAuthenticationDetails.setUpdateTime(new Date());
			customerAuthenticationDetails.setUpdatedBy("CUST_ONLINE");


			 if(email!=null)
			 {	 
				 String emailPassword=handleCustomerVerification.generateEmailHash();
				 customerAuthenticationDetails.setEmailPassword(emailPassword);
				 
				 passwordUpdateStatus=customerAuthenticationDetailsRepository
						 .updatePasswordEmailPasswordAndPasswordTypeAndCounts(customerAuthenticationDetails.getKey().getCustomerId(),
								 customerAuthenticationDetails.getKey().getCustomerType(), customerAuthenticationDetails.getPassword(),
								 customerAuthenticationDetails.getEmailPassword(), customerAuthenticationDetails.getPasswordType());
						 
			 }
			 else
			 {
				 passwordUpdateStatus=customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts 
						 (customerAuthenticationDetails.getKey().getCustomerId(),customerAuthenticationDetails.getKey().getCustomerType(),
								 customerAuthenticationDetails.getPassword(),null, customerAuthenticationDetails.getPasswordType());
			 }
			 
		}
									
		if(email!=null && isEmailVerified)
		{
			messagerSender.sendPasswordEmail(entityId,entityType, firstName, lastName,
					email, customerAuthenticationDetails.getEmailPassword());
		}	
		
		if(mobile!=null && isMobileVerified)
		{
			
			messagerSender.sendPasswordSMS(firstName, lastName, mobile,
					customerAuthenticationDetails.getPassword());
		}	
		
		if(resendFlag)
			resendStatus=customerAuthenticationDetailsRepository.incrementResendCount(entityId,entityType);
		
		if(passwordUpdateStatus.equals(UPDATE_SUCESS) && emailSendStatus && smsSendStatus&&resendStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;

		
	}

	

	@Override
	public Boolean sendDefaultPassword(QuickRegisterEntity customer,Boolean resetFlag) 
	{
		Boolean status=sendOrResendOrResetDefaultPassword(customer.getCustomerId(), customer.getCustomerType(), resetFlag, false);
		
		return status;		
		
	}
	
	
	@Override
	public Boolean resendDefaultPassword(
			QuickRegisterEntity customer) {

		Boolean status=sendOrResendOrResetDefaultPassword(customer.getCustomerId(), customer.getCustomerType(), false, true);
		
		return status;
	}

	@Override
	public Boolean resetPassword(CustomerIdTypeDTO customerIdDTO) 
	{
		QuickRegisterEntity customer=customerQuickRegisterService
				.getByEntityId(customerIdDTO.getCustomerId());
		
		return sendDefaultPassword(customer,true);
	}
	
	@Override
	public Boolean resendPassword(CustomerIdTypeDTO customerIdDTO) {

		QuickRegisterEntity customer=customerQuickRegisterService
				.getByEntityId(customerIdDTO.getCustomerId());
		
		return resendDefaultPassword(customer);
	}


	@Override
	public Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts(updatePasswordDTO.getCustomerId(),
				updatePasswordDTO.getCustomerType(),updatePasswordDTO.getPassword(),null, CUST_PASSWORD_TYPE_CHANGED);
		
		if(updateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
	}

	@Override
	public AuthenticationDetails getByEntityIdType(Long customerId,Integer customerType)
	{
		return customerAuthenticationDetailsRepository.getByCustomerIdType(customerId,customerType);
	}
	
	@Override
	public AuthenticationDetails saveCustomerAuthenticationDetails(
			AuthenticationDetails entity) {
		
		AuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.save(entity);
		
		return customerAuthenticationDetails;
	}


	
	
	@Override
	public QuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity) {
		
		//TODO
		
		QuickRegisterEntity quickRegisterEntity=new QuickRegisterEntity();
		
		if(isMobileNumber(entity))
		{
			quickRegisterEntity=customerQuickRegisterService.getByMobile(Long.parseLong(entity));
		}
		else
		{
			quickRegisterEntity=customerQuickRegisterService.getByEmail(entity);
		}
				
		return quickRegisterEntity;
	}

	
	
	private Boolean isMobileNumber(String entity)
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(entity, pos);
		return (entity.length() == pos.getIndex()&&entity.length()==10);
		
	}



	@Override
	public Boolean clearTestData() {
		
		return customerAuthenticationDetailsRepository.clearLoginDetailsForTesting();
		 
	}


}
