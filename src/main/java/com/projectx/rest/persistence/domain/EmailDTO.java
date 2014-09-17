package com.projectx.rest.persistence.domain;

public class EmailDTO {
	
	private String email;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EmailDTO(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	
	

}