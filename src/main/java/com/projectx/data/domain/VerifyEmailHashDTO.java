package com.projectx.data.domain;

public class VerifyEmailHashDTO {

	private Long customerId;
	private String emailHash;
	
	public VerifyEmailHashDTO() {
	
	}
	
	public VerifyEmailHashDTO(Long customerId, String emailHash) {
		super();
		this.customerId = customerId;
		this.emailHash = emailHash;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}
	
	
	
}
