package com.projectx.rest.fixture;

import com.projectx.rest.domain.Email;

public class RestDataFixture {
	
	public static String EMAIL_NAME="dinesh";
	public static String EMAIL_EMAIL="dineshshe@gmail.com";
	public static String EMAIL_MSG="Email added Sucessfully";
			
	
	public static Email standardEmail()
	{
		return new Email(EMAIL_EMAIL,EMAIL_NAME);
		
	}
	
	public static String standardJsonEmail()
	{
		return "{\"name\":\"dinesh\",\"email\":\"dineshshe@gmail.com\"}";
		
	}


}
