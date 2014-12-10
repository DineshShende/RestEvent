package com.projectx.web.domain.quickregister;

public class GetByEmailDTO {

	String email;

	public GetByEmailDTO() {
	
	}
	
	public GetByEmailDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
