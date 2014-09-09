package com.projectx.rest.domain;

public class Email {

	private String email;
	private String name;
	private String message;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emaill) {
		this.email = emaill;
	}

	public Email(String email) {
		this.email = email;
	}

	public Email() {
	
	}

	public Email(String email, String name) {
		this.email=email;
		this.name=name;

	}

	@Override
	public String toString() {
		return "Email [email=" + email + ", name=" + name + ", message="
				+ message + "]";
	}

	
	
	
}