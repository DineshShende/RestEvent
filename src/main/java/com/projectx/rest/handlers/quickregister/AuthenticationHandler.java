package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Date;
import java.util.HashMap;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.AuthenticationService.LoginVerificationFailedException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.utils.HandleVerificationService;
import com.projectx.rest.utils.InformationMapper;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;


@Component
public class AuthenticationHandler implements AuthenticationService {

	@Autowired
	AuthenticationDetailsRepository customerAuthenticationDetailsRepository;

	@Autowired
	HandleVerificationService handleCustomerVerification; 
	
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
	
	private Integer EMAIL_REQ=1;
	
	private Integer ACTOR_ENTITY_SELF_WEB=1;
	
	private Integer MOBILE_REQ=2;


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
		customerAuthenticationDetails.setUpdatedBy(customerQuickRegisterEntity.getUpdatedBy());
		customerAuthenticationDetails.setInsertedBy(customerQuickRegisterEntity.getInsertedBy());
		customerAuthenticationDetails.setUpdatedById(customerQuickRegisterEntity.getUpdatedById());
		customerAuthenticationDetails.setInsertedById(customerQuickRegisterEntity.getInsertedById());
		
		return customerAuthenticationDetails;
	}

	
	@Override
	public AuthenticationDetails verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO)throws AuthenticationDetailsNotFoundException,LoginVerificationFailedException {
		
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
					customerAuthenticationDetails.getKey().getCustomerType(),ACTOR_ENTITY_SELF_WEB,customerAuthenticationDetails.getKey().getCustomerId());
			
			throw new LoginVerificationFailedException(); 	
			
		}
		
		return customerAuthenticationDetails;
	}

	
	
	@Override
	public AuthenticationDetails verifyDefaultEmailLoginDetails(LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO)
					throws AuthenticationDetailsNotFoundException,LoginVerificationFailedException {

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
			throw new LoginVerificationFailedException();
		}
		
		
	}

	
	@Override
	public Boolean sendOrResendOrResetDefaultPassword(Long entityId,
			Integer entityType, Boolean resetFlag, Boolean resendFlag,Integer emailOrMobile,Integer requestedBy,Long requestedById)throws AuthenticationDetailsNotFoundException,
			QuickRegisterEntityNotFoundException,CustomerDetailsNotFoundException,VendorDetailsNotFoundException {
		
		
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
			customerAuthenticationDetails.setUpdatedBy(requestedBy);


			 if(email!=null)
			 {	 
				 String emailPassword=handleCustomerVerification.generateEmailHash();
				 customerAuthenticationDetails.setEmailPassword(emailPassword);
				 
				 passwordUpdateStatus=customerAuthenticationDetailsRepository
						 .updatePasswordEmailPasswordAndPasswordTypeAndCounts(customerAuthenticationDetails.getKey().getCustomerId(),
								 customerAuthenticationDetails.getKey().getCustomerType(), customerAuthenticationDetails.getPassword(),
								 customerAuthenticationDetails.getEmailPassword(), customerAuthenticationDetails.getPasswordType(),requestedBy,requestedById);
						 
			 }
			 else
			 {
				 passwordUpdateStatus=customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts 
						 (customerAuthenticationDetails.getKey().getCustomerId(),customerAuthenticationDetails.getKey().getCustomerType(),
								 customerAuthenticationDetails.getPassword(),null, customerAuthenticationDetails.getPasswordType(),requestedBy,requestedById);
			 }
			 
		}
									
		if(email!=null && isEmailVerified && emailOrMobile.equals(EMAIL_REQ))
		{
			messagerSender.sendPasswordEmail(entityId,entityType, firstName, lastName,
					email, customerAuthenticationDetails.getEmailPassword());
		}	
		
		if(mobile!=null && isMobileVerified && emailOrMobile.equals(MOBILE_REQ))
		{
			
			messagerSender.sendPasswordSMS(firstName, lastName, mobile,
					customerAuthenticationDetails.getPassword());
		}	
		
		if(resendFlag)
			resendStatus=customerAuthenticationDetailsRepository.incrementResendCount(entityId,entityType,requestedBy,requestedById);
		
		if(passwordUpdateStatus.equals(UPDATE_SUCESS) && emailSendStatus && smsSendStatus&&resendStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;

		
	}

	

	@Override
	public Boolean sendDefaultPassword(Long customerId,Integer customerType, Boolean resetFlag,Integer emailOrMobile,Integer requestedBy,Long requestedById) throws AuthenticationDetailsNotFoundException,
		QuickRegisterEntityNotFoundException,CustomerDetailsNotFoundException,VendorDetailsNotFoundException
	{
		Boolean status=sendOrResendOrResetDefaultPassword(customerId, customerType, resetFlag, false,emailOrMobile,requestedBy,requestedById);
		
		return status;		
		
	}
	
	
	@Override
	public Boolean resendDefaultPassword(
			Long customerId,Integer customerType,Integer emailOrMobile,Integer requestedBy,Long requestedById) throws AuthenticationDetailsNotFoundException,
				QuickRegisterEntityNotFoundException,CustomerDetailsNotFoundException,VendorDetailsNotFoundException{

		Boolean status=sendOrResendOrResetDefaultPassword(customerId, customerType, false, true,emailOrMobile,requestedBy,requestedById);
		
		return status;
	}

	@Override
	public Boolean resetPassword(CustomerIdTypeUpdatedByDTO customerIdDTO,Integer emailOrMobile) throws AuthenticationDetailsNotFoundException,
	QuickRegisterEntityNotFoundException,CustomerDetailsNotFoundException,VendorDetailsNotFoundException 
	{
		//QuickRegisterEntity customer=customerQuickRegisterService
		//		.getByEntityId(customerIdDTO.getCustomerId());
		
		return sendDefaultPassword(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType(),true,emailOrMobile,
				customerIdDTO.getUpdatedBy(),customerIdDTO.getUpdatedById());
	}
	
	@Override
	public Boolean resendPassword(CustomerIdTypeUpdatedByDTO customerIdDTO,Integer emailOrMobile) throws AuthenticationDetailsNotFoundException,
		QuickRegisterEntityNotFoundException,CustomerDetailsNotFoundException,VendorDetailsNotFoundException{

		QuickRegisterEntity customer=customerQuickRegisterService
				.getByEntityId(customerIdDTO.getCustomerId());
		
		return resendDefaultPassword(customerIdDTO.getCustomerId(),customerIdDTO.getCustomerType(),emailOrMobile,
				customerIdDTO.getUpdatedBy(),customerIdDTO.getUpdatedById());
	}


	@Override
	public Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO) throws ValidationFailedException
	{
		Integer updateStatus=customerAuthenticationDetailsRepository.updatePasswordEmailPasswordAndPasswordTypeAndCounts(updatePasswordDTO.getCustomerId(),
				updatePasswordDTO.getCustomerType(),updatePasswordDTO.getPassword(),null, CUST_PASSWORD_TYPE_CHANGED,
				updatePasswordDTO.getUpdatedBy(),updatePasswordDTO.getUpdatedById());
		
		if(updateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
	}

	@Override
	public AuthenticationDetails getByEntityIdType(Long customerId,Integer customerType) throws AuthenticationDetailsNotFoundException
	{
		return customerAuthenticationDetailsRepository.getByCustomerIdType(customerId,customerType);
	}
	
	@Override
	public AuthenticationDetails saveCustomerAuthenticationDetails(
			AuthenticationDetails entity) throws ResourceAlreadyPresentException,ValidationFailedException{
		
		AuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.save(entity);
		
		return customerAuthenticationDetails;
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
