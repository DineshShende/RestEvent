package com.projectx.rest.repository;

import java.util.List;

import com.projectx.rest.domain.Email;

public interface CustomerQuickRegisterRepository {

	List<Email> getAllEmails();
	
	void addEmail(Email email);
	
	Boolean checkEmailExisted(Email email);
}
