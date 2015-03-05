package com.projectx.rest.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.projectx.mvc.domain.completeregister.EmailMessageDTO;
import com.projectx.mvc.domain.completeregister.MobileMessageDTO;
import com.projectx.rest.domain.async.RetriggerDTO;
@Component
@Profile("Dev")
public class HandleVerificationServiceFixture implements
		HandleVerificationService {

	@Autowired
	private SecureRandom secureRandom;
	
	private final int PASSWORD_LENGTH=6;
	
	@Override
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

	/* (non-Javadoc)
	 * @see com.projectx.rest.utils.HandleVerificationService#genarateMobilePin()
	 */
	@Override
	public  Integer genarateMobilePin() {

		int number=secureRandom.nextInt(9)+1;
		
		for(int i=0;i<5;i++)
		{
			number=(number*10)+secureRandom.nextInt(10);
		}	
		
		return number;
	}

	/* (non-Javadoc)
	 * @see com.projectx.rest.utils.HandleVerificationService#generatePassword()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see com.projectx.rest.utils.HandleVerificationService#sendEmailAsynchronous(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean sendEmailAsynchronous(String email,String message) 
	{
		
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.projectx.rest.utils.HandleVerificationService#sendEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean sendEmail(String email,String message) 
	{
		
		
		return true;	
	}
	
	
	
	//@Async
	/* (non-Javadoc)
	 * @see com.projectx.rest.utils.HandleVerificationService#sendSMSAsynchronous(java.lang.Long, java.lang.String)
	 */
	@Override
	public Boolean sendSMSAsynchronous(Long mobile,String message)  
	{
	
		return true;
		
	}
	
	/* (non-Javadoc)
	 * @see com.projectx.rest.utils.HandleVerificationService#sendSMS(java.lang.Long, java.lang.String)
	 */
	@Override
	public Boolean sendSMS(Long mobile,String message)  
	{
		
		return true;
	}

}
