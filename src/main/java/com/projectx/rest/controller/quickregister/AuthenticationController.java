package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_PASSWORD_TYPE_CHANGED;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeEmailOrMobileOptionUpdatedBy;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.rest.domain.comndto.ResponseDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsAng;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.AuthenticationService.LoginVerificationFailedException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.utils.InformationMapper;
import com.rabbitmq.client.AMQP.Exchange.Bind;

@RestController
@RequestMapping(value="/customer/quickregister")
public class AuthenticationController {


	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	InformationMapper informationMapper; 
	
	@Value("${PASSWORD_TYPE_CHANGED}")
	private String PASSWORD_TYPE_CHANGED;
	
	@Value("${AUTHENTICATION_DETAILS_OLD_NEW_PASSWORD_DOES_NOT_MATCH}")
	private String AUTHENTICATION_DETAILS_OLD_NEW_PASSWORD_DOES_NOT_MATCH;//="AUTHENTICATION_DETAILS_OLD_NEW_PASSWORD_DOES_NOT_MATCH";
	
	@Value("${Hi}")
	private String Hi;
	
	@RequestMapping(value="/test")
	public String test()
	{
		return AUTHENTICATION_DETAILS_OLD_NEW_PASSWORD_DOES_NOT_MATCH;
	}
	
	
	@RequestMapping(value="/verifyLoginDetails",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<AuthenticationDetailsAng>> verifyLoginDetails(@Valid @RequestBody LoginVerificationDTO loginVerificationDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<AuthenticationDetailsAng>> result=null;
		
		try{
			AuthenticationDetails verifiedEntity= authenticationService.verifyLoginDetails(loginVerificationDTO);
			
			Map<String,Object> resultData=informationMapper.getBasicInfoByEntityIdType(verifiedEntity.getKey().getCustomerId(),
					verifiedEntity.getKey().getCustomerType());
			
			if(!(resultData.get("isCompleteRegisterCompleted")=="true") && verifiedEntity.getPasswordType().equals(PASSWORD_TYPE_CHANGED))
			{
				QuickRegisterEntity quickRegisterEntity=quickRegisterService.getByEntityId((Long)resultData.get("entityId"));
				try{
					customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
				}catch(DeleteQuickCreateDetailsEntityFailedException e)
				{
					//Need to handle this situation
				}
				
			}
			
			
			AuthenticationDetailsAng authenticationDetailsAng=new AuthenticationDetailsAng(verifiedEntity.getKey(), 
					resultData.get("firstName")+" "+((resultData.get("middleName")!=null)?resultData.get("middleName"):"")+" "+resultData.get("lastName"),
					verifiedEntity.getPasswordType(), resultData.get("isCompleteRegisterCompleted")=="true");
			
			result=new ResponseEntity<ResponseDTO<AuthenticationDetailsAng>>(
					new ResponseDTO<AuthenticationDetailsAng>("", authenticationDetailsAng), HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException | LoginVerificationFailedException e )
		{
			result=new ResponseEntity<ResponseDTO<AuthenticationDetailsAng>>(
					new ResponseDTO<AuthenticationDetailsAng>(e.getMessage(),null), HttpStatus.OK);
		}
				
		return result;
	}
	
	@RequestMapping(value="/verifyLoginDefaultEmailPassword")
			public ResponseEntity<ResponseDTO<AuthenticationDetailsAng>> verifyLoginDefaultEmailPassword(@Valid @RequestBody LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<AuthenticationDetailsAng>> result=null;
		
		try{
			AuthenticationDetails verifiedEntity= authenticationService.verifyDefaultEmailLoginDetails(emailPasswordDTO);
			
			Map<String,Object> resultData=informationMapper.getBasicInfoByEntityIdType(verifiedEntity.getKey().getCustomerId(),
					verifiedEntity.getKey().getCustomerType());
			
			
			AuthenticationDetailsAng authenticationDetailsAng=new AuthenticationDetailsAng(verifiedEntity.getKey(), 
					resultData.get("firstName")+" "+((resultData.get("middleName")!=null)?resultData.get("middleName"):"")+" "+resultData.get("lastName"),
					verifiedEntity.getPasswordType(), resultData.get("isCompleteRegisterCompleted")=="true");
			
			result=new ResponseEntity<ResponseDTO<AuthenticationDetailsAng>>(new ResponseDTO<AuthenticationDetailsAng>(
					"",authenticationDetailsAng), HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException |LoginVerificationFailedException e)
		{
			result=new ResponseEntity<ResponseDTO<AuthenticationDetailsAng>>(new ResponseDTO<AuthenticationDetailsAng>(
					e.getMessage(),null), HttpStatus.OK);
		}
		
		return result;
		
	}

	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> resetPassword(@Valid @RequestBody CustomerIdTypeEmailOrMobileOptionUpdatedBy customerIdDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<Boolean>> result=null;
		
		try{
			Boolean status=authenticationService.resetPassword(new CustomerIdTypeUpdatedByDTO(customerIdDTO.getCustomerId(),
					customerIdDTO.getCustomerType(), customerIdDTO.getUpdatedBy(),customerIdDTO.getUpdatedById()),customerIdDTO.getEmailOrMobile());
			result=new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",status), HttpStatus.OK);
			
		}catch(AuthenticationDetailsNotFoundException  | QuickRegisterEntityNotFoundException | CustomerDetailsNotFoundException 
				|VendorDetailsNotFoundException e)
		{
			result=new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(e.getMessage(),null),HttpStatus.NO_CONTENT);
		}
			
		return result;
	}
	
	@RequestMapping(value="/resendPassword",method=RequestMethod.POST)
	public ResponseEntity<Boolean> resendPassword(@Valid @RequestBody CustomerIdTypeEmailOrMobileOptionUpdatedBy customerIdDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<Boolean> result=null;
		
		try{
			Boolean status=authenticationService.resendPassword(new CustomerIdTypeUpdatedByDTO(customerIdDTO.getCustomerId(),
					customerIdDTO.getCustomerType(), customerIdDTO.getUpdatedBy(),customerIdDTO.getUpdatedById()),customerIdDTO.getEmailOrMobile());
			result=new ResponseEntity<Boolean>(status, HttpStatus.OK);
		}catch(AuthenticationDetailsNotFoundException  | QuickRegisterEntityNotFoundException | CustomerDetailsNotFoundException 
				|VendorDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			
		return result;
	}
	
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<Boolean>> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		
		
		AuthenticationDetails authenticationDetails=
				authenticationService.getByEntityIdType(updatePasswordDTO.getCustomerId(), updatePasswordDTO.getCustomerType());
		
		if(!authenticationDetails.getPassword().equals(updatePasswordDTO.getOldPassword()))
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>(AUTHENTICATION_DETAILS_OLD_NEW_PASSWORD_DOES_NOT_MATCH,null), HttpStatus.OK);
		
		if(authenticationDetails.getPasswordType().equals(PASSWORD_TYPE_CHANGED) && updatePasswordDTO.getIsForcefulChangePassword())
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		
		UpdatePasswordAndPasswordTypeDTO updatePassword=new UpdatePasswordAndPasswordTypeDTO(
				updatePasswordDTO.getCustomerId(),updatePasswordDTO.getCustomerType(),updatePasswordDTO.getPassword(), 
				CUST_PASSWORD_TYPE_CHANGED,updatePasswordDTO.getIsForcefulChangePassword(),updatePasswordDTO.getRequestedBy(),
				updatePasswordDTO.getRequestedById());	
		
		try{
			Boolean result=authenticationService.updatePassword(updatePassword);
			
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",result), HttpStatus.OK);
		}catch(ValidationFailedException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		
	}

	@RequestMapping(value="/getAuthenticationDetailsById",method=RequestMethod.POST)
	public ResponseEntity<AuthenticationDetails> getAuthenticationDetailsByCustomerId(@Valid @RequestBody CustomerIdTypeDTO customerId,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<AuthenticationDetails> result=null;
		 
		try{
			AuthenticationDetails verifiedEntity= authenticationService
					.getByEntityIdType(customerId.getCustomerId(),customerId.getCustomerType());
			
			
			result=new ResponseEntity<AuthenticationDetails>(verifiedEntity, HttpStatus.FOUND);
		}catch(AuthenticationDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return result;
	}

	
	@RequestMapping(value="/clearAuthTestData")
	public Boolean clearTestData()
	{
		authenticationService.clearTestData();
		
		return true;
	}
	
}
