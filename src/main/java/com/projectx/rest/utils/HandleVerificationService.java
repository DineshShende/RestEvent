package com.projectx.rest.utils;

import org.springframework.stereotype.Service;

@Service
public interface HandleVerificationService {

	public abstract String generateEmailHash();

	public abstract Integer genarateMobilePin();

	public abstract String generatePassword();

	//@Async
	public abstract Boolean sendEmailAsynchronous(String email, String message);

	public abstract Boolean sendEmail(String email, String message);

	//@Async
	public abstract Boolean sendSMSAsynchronous(Long mobile, String message);

	public abstract Boolean sendSMS(Long mobile, String message);

}