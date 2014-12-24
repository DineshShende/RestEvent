package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_CHANGED;
import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_DEFAULT;

import java.text.NumberFormat;
import java.text.ParsePosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;
import com.projectx.web.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.web.domain.quickregister.LoginVerificationDTO;
import com.projectx.web.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;


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
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static final Integer UPDATE_SUCESS=new Integer(1);
	
	@Override
	public AuthenticationDetails verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		
		AuthenticationDetails customerAuthenticationDetails=new AuthenticationDetails();
		
		if(isMobileNumber(loginVerificationDTO.getLoginEntity()))
		{
			customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByMobile(Long.parseLong(loginVerificationDTO.getLoginEntity()));
		}
		else
		{
			customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByEmail(loginVerificationDTO.getLoginEntity());
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
		
		customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(
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
	public Boolean sendDefaultPassword(QuickRegisterEntity customer,Boolean resetFlag) 
	{
		Boolean emailSendStatus=true;
		Boolean smsSendStatus=true;
		Integer passwordUpdateStatus=new Integer(1);
		Integer emailPasswordUpdateStatus=new Integer(1);
		
		AuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(customer.getCustomerId(),customer.getCustomerType());
		
		if(!resetFlag && (customerAuthenticationDetails.getPasswordType()!=null && 
				customerAuthenticationDetails.getPasswordType().equals(CUST_PASSWORD_TYPE_CHANGED)))
			return true;
		
		if(customerAuthenticationDetails.getPassword()==null || resetFlag)
		{	
			String password=handleCustomerVerification.generatePassword();
			customerAuthenticationDetails.setPassword(password);
			customerAuthenticationDetails.setPasswordType(CUST_PASSWORD_TYPE_DEFAULT);

			 passwordUpdateStatus=customerAuthenticationDetailsRepository.updatePasswordAndPasswordTypeAndCounts 
					 (customerAuthenticationDetails.getKey().getCustomerId(),customerAuthenticationDetails.getKey().getCustomerType(),
							 customerAuthenticationDetails.getPassword(), customerAuthenticationDetails.getPasswordType());
			 if(customer.getEmail()!=null)
			 {	 
				 String emailPassword=handleCustomerVerification.generateEmailHash();
				 customerAuthenticationDetails.setEmailPassword(emailPassword);
				 emailPasswordUpdateStatus=customerAuthenticationDetailsRepository
						 .updateEmailPasswordAndPasswordTypeAndCounts(customerAuthenticationDetails.getKey().getCustomerId(),
								 customerAuthenticationDetails.getKey().getCustomerType(),customerAuthenticationDetails.getEmailPassword());
						 
			 }			 
			 
		}
		
		//CustomerAuthenticationDetails customerAuthenticationDetailsUpdated=customerAuthenticationDetailsRepository
		//		.getCustomerAuthenticationDetailsByCustomerId(customer.getCustomerId());
		
					
		if(customer.getEmail()!=null && customer.getIsEmailVerified())
		{
			messagerSender.sendPasswordEmail(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
					customer.getEmail(), customerAuthenticationDetails.getEmailPassword());
		}	
		
		if(customer.getMobile()!=null && customer.getIsMobileVerified())
		{
			
			messagerSender.sendPasswordSMS(customer.getFirstName(), customer.getLastName(), customer.getMobile(),
					customerAuthenticationDetails.getPassword());
		}	
		
		if(passwordUpdateStatus.equals(UPDATE_SUCESS) && emailSendStatus && smsSendStatus && emailPasswordUpdateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
		
		
	}
	
	
	@Override
	public Boolean resendDefaultPassword(
			QuickRegisterEntity customer) {

		AuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository
				.getCustomerAuthenticationDetailsByCustomerIdType(customer.getCustomerId(),customer.getCustomerType());
		
			
		if(customer.getEmail()!=null && customer.getIsEmailVerified())
		{
			messagerSender.sendPasswordEmail(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
					customer.getEmail(), customerAuthenticationDetails.getEmailPassword());
		}	
		
		if(customer.getMobile()!=null && customer.getIsMobileVerified())
		{
			
			messagerSender.sendPasswordSMS(customer.getFirstName(), customer.getLastName(), customer.getMobile(),
					customerAuthenticationDetails.getPassword());
		}	
		
		Integer updateStatus=customerAuthenticationDetailsRepository.incrementResendCount(customer.getCustomerId(),customer.getCustomerType());
		
		if(updateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
	}

	@Override
	public Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository.updatePasswordAndPasswordTypeAndCounts(updatePasswordDTO.getCustomerId(),
				updatePasswordDTO.getCustomerType(),updatePasswordDTO.getPassword(), CUST_PASSWORD_TYPE_CHANGED);
		
		if(updateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
	}

	@Override
	public AuthenticationDetails getLoginDetailsByCustomerIdType(Long customerId,Integer customerType)
	{
		return customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerIdType(customerId,customerType);
	}
	
	@Override
	public AuthenticationDetails saveCustomerAuthenticationDetails(
			AuthenticationDetails entity) {
		
		AuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.save(entity);
		
		return customerAuthenticationDetails;
	}

	@Override
	public Boolean resetPassword(CustomerIdTypeDTO customerIdDTO) 
	{
		QuickRegisterEntity customer=customerQuickRegisterService
				.getCustomerQuickRegisterEntityByCustomerId(customerIdDTO.getCustomerId());
		
		return sendDefaultPassword(customer,true);
	}
	
	


	@Override
	public Boolean resendPassword(CustomerIdTypeDTO customerIdDTO) {

		QuickRegisterEntity customer=customerQuickRegisterService
				.getCustomerQuickRegisterEntityByCustomerId(customerIdDTO.getCustomerId());
		
		return resendDefaultPassword(customer);
	}

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
		
		return customerAuthenticationDetails;
	}
	
	
	@Override
	public QuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity) {
		
		//TODO
		
		QuickRegisterEntity quickRegisterEntity=new QuickRegisterEntity();
		
		if(isMobileNumber(entity))
		{
			quickRegisterEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByMobile(Long.parseLong(entity));
		}
		else
		{
			quickRegisterEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByEmail(entity);
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
