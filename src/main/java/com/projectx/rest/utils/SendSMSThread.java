package com.projectx.rest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class SendSMSThread implements Runnable {


	
	private RestTemplate restTemplate=new RestTemplate();
	
	private Long mobile;
	
	private String message;
	
	
	
	public SendSMSThread(Long mobile, String message) {
		super();
		this.mobile = mobile;
		this.message = message;
	}



	@Override
	public void run() {

		String processedMessage=message.replace(" ", "+");
		
		StringBuilder requestBuilder=new StringBuilder();
		
		requestBuilder.append("http://login.bulksmsindia.biz/messageapi.asp?username=karle7&password=58483712&sender=karlee&mobile=");
		requestBuilder.append(mobile);
		requestBuilder.append("&message=");
		requestBuilder.append(message);
		
		System.out.println("SMS sent");
		
		//System.out.println(requestBuilder.toString());
						
		String result=restTemplate.getForObject(requestBuilder.toString(), String.class);	
			
		System.out.println("Hi");
		
		System.out.println(result);
		
		
		

	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
