package com.projectx.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.projectx.rest.domain.Email;
import com.projectx.services.CustomerQuickRegisterService;

@Controller
@RequestMapping(value="/email")
public class CustomerQuickRegister {

	
	@Autowired
	CustomerQuickRegisterService customerQuickRegisterService;
    
    
    
    @RequestMapping(value="/addemail",method=RequestMethod.POST)
    public ResponseEntity<Email> addEmail(@RequestBody Email email,UriComponentsBuilder builder) throws Exception
	{
		Email newEmail=customerQuickRegisterService.addEmail(email);
    	
		System.out.println("In controller:"+email+":newemail"+newEmail);
		
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(builder.path("/customer/email/{id}").buildAndExpand(newEmail.getEmail().toString()).toUri());
		
		return new ResponseEntity<Email>(newEmail,headers,HttpStatus.CREATED);
		
		
	}
    
    /*
    @RequestMapping(value="/checkemail",method=RequestMethod.POST)
    public Email redirectEmail(@RequestBody Email email)
    {
    	System.out.println(email.getEmail());
    	return email;
    	
    }
    */
    
}
