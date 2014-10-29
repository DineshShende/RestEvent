package com.projectx.rest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
@Profile(value={"Dev","Test"})
public class HandleCustomerVerification {
	
	
	@Autowired
	private  MailSender mailSender;
	
	public  String generateEmailHash() {

		String allPossibleChar="01234567789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		StringBuilder sb=new StringBuilder();
		SecureRandom secureRandom=new SecureRandom();
		
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

		SecureRandom secureRandom=new SecureRandom();
		int number=secureRandom.nextInt(9)+1;
		
		for(int i=0;i<5;i++)
		{
			number=(number*10)+secureRandom.nextInt(10);
		}	
		
		return number;
	}

	
	public Boolean sendEmailHash(String email,String message)
	{
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		
		mailMessage.setTo(email);
		mailMessage.setSubject("Greetings from transportdeal.in");
		mailMessage.setText(message);
			
		mailSender.send(mailMessage);
		
		return true;
	}
	
	public Boolean sendMobilePin(Long mobile,String message) throws UnirestException
	{
		String processedMessage=message.replace(" ", "+");
		
		StringBuilder requestBuilder=new StringBuilder();
		
		requestBuilder.append("https://site2sms.p.mashape.com/index.php?msg=");
		requestBuilder.append(processedMessage);
		requestBuilder.append("&phone=");
		requestBuilder.append(mobile);
		requestBuilder.append("&pwd=projectx&uid=9960821869");
		
		System.out.println(requestBuilder.toString());
		
		HttpResponse<JsonNode> response = Unirest.get(requestBuilder.toString())
				.header("X-Mashape-Key", "sTnPJWur9ZmshvwqGPSriebc0XbKp1l7AWBjsn0ID2ca6pEmZD")
				.asJson();
		
		/*
		HttpResponse<JsonNode> response = Unirest.get("https://site2sms.p.mashape.com/index.php?msg=Hi+how+r+u&phone=9960821869&pwd=projectx&uid=9960821869")
				.header("X-Mashape-Key", "sTnPJWur9ZmshvwqGPSriebc0XbKp1l7AWBjsn0ID2ca6pEmZD")
				.asJson();
		*/
		
		
		System.out.println(response.getBody());
		
		return true;
	}
	
}
