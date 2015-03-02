package com.projectx.rest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureAdapter;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.gson.Gson;
import com.projectx.mvc.domain.completeregister.EmailMessageDTO;
import com.projectx.mvc.domain.completeregister.MobileMessageDTO;
import com.projectx.rest.domain.async.RetriggerDTO;
import com.projectx.rest.domain.async.RetriggerDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.exception.repository.async.RetriggerDetailsRepository;
import com.projectx.rest.services.async.RetriggerService;

@Component
@Profile(value={"Dev","Test"})

@PropertySource(value="classpath:/application.properties")
public class HandleCustomerVerification {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AsyncRestTemplate asyncRestTemplate;
	
	@Autowired
	Environment env;

	@Autowired
	private RetriggerService retriggerService;
	
	@Autowired
	private  MailSender mailSender;
	
	@Autowired
	private SecureRandom secureRandom;
	
	@Autowired
	Gson gson;
	
	@Autowired
	private RetriggerDetailsRepository retriggerDetailsRepository;
	

	final Logger log1 = LoggerFactory.getLogger(this.getClass());
	
	
	private final int PASSWORD_LENGTH=6;
	
	public  String generateEmailHash() {

		String allPossibleChar="01234567789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		StringBuilder sb=new StringBuilder();

		
		for(int i=0;i<10;i++)
		{
			sb.append(allPossibleChar.charAt(secureRandom.nextInt(allPossibleChar.length())));
		}
		
		String password=sb.toString();
		
		MessageDigest md = null;
		try {
				md = MessageDigest.getInstance("SHA-256");
		}
		catch (NoSuchAlgorithmException e)
		{
			
			e.printStackTrace();
		}
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        
        StringBuffer sbNew = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sbNew.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		
        String emailHash=sbNew.toString();
        
        
		return emailHash;
		
	}

	public  Integer genarateMobilePin() {

		int number=secureRandom.nextInt(9)+1;
		
		for(int i=0;i<5;i++)
		{
			number=(number*10)+secureRandom.nextInt(10);
		}	
		
		return number;
	}

	public String generatePassword()
	{
		StringBuilder passwordBuilder=new StringBuilder();
		
		String allPossibleChar="01234567789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for(int i=0;i<PASSWORD_LENGTH;i++)
		{
			passwordBuilder.append(allPossibleChar.charAt(secureRandom.nextInt(allPossibleChar.length())));
		}
			
		return passwordBuilder.toString();
	}
	
	
	
	//@Async
	public Boolean sendEmailAsynchronous(String email,String message) 
	{
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		
		mailMessage.setTo(email);
		mailMessage.setSubject("Greetings from transportdeal.in");
		mailMessage.setText(message);
		
		Boolean status=false;
		
		try{
			//mailSender.send(mailMessage);
			Thread.sleep(800);
			status=true;
		}catch(MailException | InterruptedException e)
		{
			status=false;
		}
		
		return status;
	}
	
	public Boolean sendEmail(String email,String message) 
	{
		
		EmailMessageDTO emailMessageDTO=new EmailMessageDTO(email, UUID.randomUUID(),message);
		
		HttpEntity<EmailMessageDTO> emailMessage=new HttpEntity<EmailMessageDTO>(emailMessageDTO);
		
		ListenableFuture<ResponseEntity<Integer>>		
		 status=asyncRestTemplate.exchange(env.getProperty("async.url")+"/sendVerificationDetails/sendEmailAsync", HttpMethod.POST,
				 emailMessage, Integer.class);
		 
		 																																																																																																																																																																																																													
		BooleanStatus booleanStatus=new BooleanStatus(true);
	
		 
		status.addCallback(new ListenableFutureCallback<ResponseEntity<Integer>>() {

			@Override
			public void onSuccess(ResponseEntity<Integer> result) {
				
				if(result.getBody().equals(new Integer(2)))
				{
	
					booleanStatus.setStatus(true);
					log1.debug("Sucess in sendMail method="+emailMessageDTO);
					
				}
				else if(result.getBody().equals(new Integer(1)))
				{
	
					booleanStatus.setStatus(false);
					log1.debug("Unsucessful in sendMail method="+emailMessageDTO);
					RetriggerDTO retriggerDTO=new RetriggerDTO("/sendVerificationDetails/sendEmailAsync", emailMessageDTO);
					
					retriggerService.requestRetry(retriggerDTO);
				}
				
			}

			@Override
			public void onFailure(Throwable t) {

				
				booleanStatus.setStatus(false);
			
				log1.debug("exception in sendMail method="+emailMessageDTO);
				RetriggerDTO retriggerDTO=new RetriggerDTO("/sendVerificationDetails/sendEmailAsync", emailMessageDTO);
				
				retriggerService.requestRetry(retriggerDTO);
				
				log1.error("Error"+t.toString());
				
			}


		});
		
		
		return booleanStatus.getStatus();
		
	}
	
	
	
	//@Async
	public Boolean sendSMSAsynchronous(Long mobile,String message)  
	{
		StringBuilder requestBuilder=new StringBuilder();
		
		requestBuilder.append("http://login.bulksmsindia.biz/messageapi.asp?username=karle7&password=58483712&sender=karlee&mobile=");
		requestBuilder.append(mobile);
		requestBuilder.append("&message=");
		requestBuilder.append(message);
		
		Boolean status=false;
		
		try{
		
			//String result=restTemplate.getForObject(requestBuilder.toString(), String.class);	
			
			//System.out.println(result);
			
			Thread.sleep(800);
			
			status=true;
		}catch(Exception e)
		{
			status=false;
		}
		
		return status;
		
	}
	
	public Boolean sendSMS(Long mobile,String message)  
	{
		MobileMessageDTO mobileMessageDTO=new MobileMessageDTO(mobile, UUID.randomUUID(),message);
		
		HttpEntity<MobileMessageDTO> mobileMessage=new HttpEntity<MobileMessageDTO>(mobileMessageDTO);
		
		ListenableFuture<ResponseEntity<Integer>>		
		 status=asyncRestTemplate.exchange(env.getProperty("async.url")+"/sendVerificationDetails/sendSMSAsync", HttpMethod.POST,
				 mobileMessage, Integer.class);
		 
		 																																																																																																																																																																																																													
		BooleanStatus booleanStatus=new BooleanStatus(true);
	
		 
		status.addCallback(new ListenableFutureCallback<ResponseEntity<Integer>>() {

			@Override
			public void onSuccess(ResponseEntity<Integer> result) {
				
				if(result.getBody().equals(new Integer(2)))
				{
	
					booleanStatus.setStatus(true);
					log1.debug("Sucess in sendSMS method="+mobileMessageDTO);
					
				}
				else if(result.getBody().equals(new Integer(1)))
				{
	
					booleanStatus.setStatus(false);
					log1.debug("Unsucessful in sendSMS method="+mobileMessageDTO);
					RetriggerDTO retriggerDTO=new RetriggerDTO("/sendVerificationDetails/sendSMSAsync", mobileMessageDTO);
					
					retriggerService.requestRetry(retriggerDTO);
				}
				
			}

			@Override
			public void onFailure(Throwable t) {

				
				booleanStatus.setStatus(false);
			
				log1.debug("exception in sendSMS method="+mobileMessageDTO);
				RetriggerDTO retriggerDTO=new RetriggerDTO("/sendVerificationDetails/sendSMSAsync", mobileMessageDTO);
				
				retriggerService.requestRetry(retriggerDTO);
				
				log1.error("Error"+t.toString());
				
			}


		});
		
		
		return booleanStatus.getStatus();

		
	}
	
		
}

