package com.projectx.rest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendEmailThread implements Runnable {

	

	@Autowired
	private  MailSender mailSender;
	
	private String email;
	
	private String message;
	
	
	
	public SendEmailThread(String email, String message) {
		super();
		this.email = email;
		this.message = message;
	}



	@Override
	public void run() {
		
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		
		mailMessage.setTo(email);
		mailMessage.setSubject("Greetings from transportdeal.in");
		mailMessage.setText(message);
			
		System.out.println("Email sent");
		
		//mailSender.send(mailMessage);
		
		

	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
