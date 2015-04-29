package com.projectx.rest.controller.registercommon;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.quickregister.ResetPasswordRedirectDTO;
import com.projectx.rest.domain.comndto.ResponseDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.registercommon.ForgetPasswordEntity;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.registercommon.RegisterCommonService;

@RestController
public class RegisterCommonController {
	
	
	@Autowired
	RegisterCommonService registerCommonService;
	
		
	@RequestMapping(value="/customer/quickregister/resetPasswordRedirect",method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<ForgetPasswordEntity>> resetPasswordRedirect(@Valid @RequestBody ResetPasswordRedirectDTO passwordRedirectDTO,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<ResponseDTO<ForgetPasswordEntity>> result=null;
		
		try{
			ForgetPasswordEntity quickRegisterEntity=registerCommonService.forgetPassword(passwordRedirectDTO.getEntity(),
					passwordRedirectDTO.getRequestedBy(),passwordRedirectDTO.getRequestedById());
			result=new ResponseEntity<ResponseDTO<ForgetPasswordEntity>>(new ResponseDTO<ForgetPasswordEntity>("",quickRegisterEntity), HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			result=new ResponseEntity<ResponseDTO<ForgetPasswordEntity>>(new ResponseDTO<ForgetPasswordEntity>(e.getMessage(),null), HttpStatus.OK);
		}
		
		return result;
	}


}
