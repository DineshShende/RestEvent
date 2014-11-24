package com.projectx.rest.utils;

import java.util.Properties;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.util.Log4jConfigListener;



public class SendEmailThread implements Runnable {

		
	private String email;
	
	private String message;
	
	
	public SendEmailThread() {

	}

	public SendEmailThread(String email, String message) {

		
		this.email = email;
		this.message = message;
	}

	@Override
	public void run() {

		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername("transportdeal@gmail.com");
    	mailSender.setPassword("projectx");
    	
    	
    	Properties prop=new Properties();
    	prop.put("mail.smtp.auth", true);
    	prop.put("mail.smtp.starttls.enable", true);
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	mailSender.setJavaMailProperties(prop);
    
		
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		
		mailMessage.setTo(email);
		mailMessage.setSubject("Greetings from transportdeal.in");
		mailMessage.setText(message);
			
		
		System.out.println(mailSender);
		
		mailSender.send(mailMessage);
		
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
				
		System.out.println("Simulated Email sent");
		

		
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
