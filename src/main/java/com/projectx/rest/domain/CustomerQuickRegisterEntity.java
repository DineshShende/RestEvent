package com.projectx.rest.domain;

public class CustomerQuickRegisterEntity {

	//private CustomerQuickRegisterKey key;

	private Long customerId;
	
	private String firstName;
	private String lastName;

	private String email;
	
	private Long mobile;
	
	private Integer pin;

	private String status;

	private Integer mobilePin;

	private Long emailHash;


	
	
	
	
	public CustomerQuickRegisterEntity(Long customerId,String firstName, String lastName,
			String email, Long mobile, Integer pin, String status,
			Integer mobilePin, Long emailHash) {
		super();
		this.customerId=customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
		this.status = status;
		this.mobilePin = mobilePin;
		this.emailHash = emailHash;
	}

	public CustomerQuickRegisterEntity() {
		// TODO Auto-generated constructor stub
	}
	
	

	public boolean isEmailVerificationPending() {
		return status == "EmailVerificationPending";
	}

	public boolean isMobileVerificationPending() {
		return status == "MobileVerificationPending";
	}

	public boolean isEmailMobileVerificationPending() {
		return status == "EmailMobileVerificationPending";
	}
	
	public boolean isEmailVerifiedMobileVerficationPending() {
		return status == "EmailVerifiedMobileVerficationPending";
	}
	
	public void setStatusEmailVerifiedMobileVerficationPending() {
		this.status = "EmailVerifiedMobileVerficationPending";
	}

	public boolean isMobileVerifiedEmailVerficationPending() {
		return status == "MobileVerifiedEmailVerficationPending";
	}

	public void setStatusMobileVerifiedEmailVerficationPending() {
		this.status = "MobileVerifiedEmailVerficationPending";
	}
	
	public boolean isMobileVerified() {
		return status == "MobileVerified";
	}
	
	public void setStatusMobileVerified() {
		this.status = "MobileVerified";
	}

	public boolean isEmailVerified() {
		return status == "EmailVerified";
	}
	
	public void setStatusEmailVerified() {
		this.status = "EmailVerified";
	}

	public boolean isEmailMobileVerified() {
		return status == "EmailMobileVerified";
	}
	
	public void setStatusEmailMobileVerified() {
		this.status = "EmailMobileVerified";
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}

	public Long getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(Long emailHash) {
		this.emailHash = emailHash;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerQuickRegisterEntity other = (CustomerQuickRegisterEntity) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!emailHash.equals(other.emailHash))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
	
}
