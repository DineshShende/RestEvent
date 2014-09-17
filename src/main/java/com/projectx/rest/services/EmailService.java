package com.projectx.rest.services;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.Email;

@Service
public interface EmailService {

	//Email emailRedirect(Email email);
	
	//List<Email> getAllEmails();
	
	Email addEmail(Email email) throws Exception;
	
	Boolean checkEmailExisted(Email email);
	
}
