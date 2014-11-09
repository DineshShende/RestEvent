package com.projectx.rest.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@ComponentScan(basePackages="com.projectx")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() throws IOException
    {
       	RestTemplate restTemplate=new RestTemplate();
    
    	return restTemplate;
    }
    
    @Bean
    public JavaMailSenderImpl mailSender()
    {
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
    	
    	return mailSender;
    	
    }
    
}
