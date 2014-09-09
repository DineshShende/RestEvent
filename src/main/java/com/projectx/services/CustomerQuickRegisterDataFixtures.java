package com.projectx.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.Email;

@Component
@Profile("Test")

public class CustomerQuickRegisterDataFixtures implements
		CustomerQuickRegisterService {

	Map<String,Email> emailMap;
	
	public CustomerQuickRegisterDataFixtures() {
		emailMap=new HashMap<String,Email>();
	}


	@Override
	public Email addEmail(Email email) {
		
		if(checkEmailExisted(email))
		{
			email.setMessage("Email already Existed");
			return email;
		}
		else
		{
			email.setMessage("Email added Sucessfully");
			emailMap.put(email.getEmail(), email);
			return email;
		}
		
	}

	
	@Override
	public Boolean checkEmailExisted(Email email) {
		
		if(emailMap.containsKey(email.getEmail()))
			return true;
		else
			return false;
		
	}

	/*
	@Override
	public Email emailRedirect(Email email) {
		
		System.out.println("In test:"+email.getEmail());
		return new Email("dineshshe@gmail.com");
	}

	@Override
	public List<Email> getAllEmails() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
}
