package com.projectx.web.domain.quickregister;

public class VerifyEmailHashDTO {

	private Long customerId;
	private Integer customerType;
	private String email;
	private String emailHash;
	
	public VerifyEmailHashDTO() {
	
	}

	

	public VerifyEmailHashDTO(Long customerId, Integer customerType,
			String email, String emailHash) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.email = email;
		this.emailHash = emailHash;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	
	
	public Integer getCustomerType() {
		return customerType;
	}



	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}



	@Override
	public String toString() {
		return "VerifyEmailHashDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", email=" + email
				+ ", emailHash=" + emailHash + "]";
	}


	
}
