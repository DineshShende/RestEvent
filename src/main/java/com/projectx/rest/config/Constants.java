package com.projectx.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;



public class Constants {

	 private Constants() {
	    
	 }

	 	@Autowired
		private static Environment env;
	 
	    public static final String SPRING_PROFILE_ACTIVE ="Dev";
	    public static final String SPRING_PROFILE_DEVELOPMENT ="Dev";
	    public static final String SPRING_PROFILE_PRODUCTION = "Prod";
	    public static final String SPRING_PROFILE_TEST = "Test";
	    

	
}
