package com.projectx.web.domain;

public class UpdateEmailHashDTO {

	private Long customerId;
	private Long emailHash;
	
	public UpdateEmailHashDTO() {
	
	}
	
	public UpdateEmailHashDTO(Long customerId, Long emailHash) {
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

	public Long getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(Long emailHash) {
		this.emailHash = emailHash;
	}
	
	
	
}