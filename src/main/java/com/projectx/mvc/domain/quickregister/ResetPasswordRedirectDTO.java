package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class ResetPasswordRedirectDTO {
	
	@NotNull
	private String entity;

	public ResetPasswordRedirectDTO() {
	
	}
	
	
	public ResetPasswordRedirectDTO(String entity) {

		this.entity = entity;
	}


	public String getEntity() {
		return entity;
	}


	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	
	

}
