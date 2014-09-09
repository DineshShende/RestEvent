package com.projectx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.Email;
import com.projectx.rest.persistence.domain.EmailDTO;



@Component
@PropertySource(value="classpath:/application.properties")
@Profile("Prod")
public class CustomerQuickRegisterHandler implements
		CustomerQuickRegisterService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	@Override
	public Email addEmail(Email email) throws Exception {

		EmailDTO emailDTO = new EmailDTO(email.getName(), email.getEmail());

		Boolean emailAlreadyExisted =checkEmailExisted(email);

		if (emailAlreadyExisted) {
			email.setMessage("Email already Existed");
			return email;
		} else {

			Email createdEmail = restTemplate.postForObject(
					env.getProperty("data.host")+"/email/addemail",
					emailDTO, Email.class);

			email.setEmail(createdEmail.getEmail());
			email.setName(createdEmail.getName());
			email.setMessage("Email added Sucessfully");

			return email;
		}
	}

	@Override
	public Boolean checkEmailExisted(Email email) {

		Boolean emailAlreadyExisted = restTemplate.postForObject(
				env.getProperty("data.host")+"/email/checkemail",
				email.getEmail(), Boolean.class);

		return emailAlreadyExisted;
	}

	/*
	 * @Override public Email emailRedirect(Email email) {
	 * 
	 * Email redirectEmail = restTemplate.postForObject(
	 * "http://localhost:8080/RestEvent/customer/checkemail", email,
	 * Email.class);
	 * 
	 * System.out.println("In dev:" + email.getEmail()); return redirectEmail;
	 * 
	 * }
	 * 
	 * @Override public List<Email> getAllEmails() {
	 * 
	 * return customerQuickRegisterRepository.getAllEmails(); }
	 */

}
