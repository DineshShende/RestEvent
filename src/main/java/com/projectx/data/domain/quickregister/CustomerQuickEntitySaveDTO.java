package com.projectx.data.domain.quickregister;

import java.util.Date;

public class CustomerQuickEntitySaveDTO {

	private Long customerId;
	
	private String firstName;
		
	private String lastName;
		
	private String email;
		
	private Long mobile;
		
	private Integer pin;
		
	private String status;

	private Integer mobilePin;
		
	private String emailHash;
		
	private Integer mobileVerificationAttempts;
		
	private Date mobilePinSentTime;
		
	private Date emailHashSentTime;
		
	private Date lastStatusChangedTime;
		

	public CustomerQuickEntitySaveDTO() {
		super();
	}

	public CustomerQuickEntitySaveDTO(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pin,
			String status, Integer mobilePin, String emailHash,
			Integer mobileVerificationAttempts, Date mobilePinSentTime,
			Date emailHashSentTime, Date lastStatusChangedTime,
			String password, String passwordType) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
		this.status = status;
		this.mobilePin = mobilePin;
		this.emailHash = emailHash;
		this.mobileVerificationAttempts = mobileVerificationAttempts;
		this.mobilePinSentTime = mobilePinSentTime;
		this.emailHashSentTime = emailHashSentTime;
		this.lastStatusChangedTime = lastStatusChangedTime;
		
	}

	
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}

	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
	}

	public Date getMobilePinSentTime() {
		return mobilePinSentTime;
	}

	public void setMobilePinSentTime(Date mobilePinSentTime) {
		this.mobilePinSentTime = mobilePinSentTime;
	}

	public Date getEmailHashSentTime() {
		return emailHashSentTime;
	}

	public void setEmailHashSentTime(Date emailHashSentTime) {
		this.emailHashSentTime = emailHashSentTime;
	}

	public Date getLastStatusChangedTime() {
		return lastStatusChangedTime;
	}

	public void setLastStatusChangedTime(Date lastStatusChangedTime) {
		this.lastStatusChangedTime = lastStatusChangedTime;
	}


	@Override
	public String toString() {
		return "CustomerQuickEntitySaveDTO [customerId=" + customerId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobile=" + mobile + ", pin=" + pin
				+ ", status=" + status + ", mobilePin=" + mobilePin
				+ ", emailHash=" + emailHash + ", mobileVerificationAttempts="
				+ mobileVerificationAttempts + ", mobilePinSentTime="
				+ mobilePinSentTime + ", emailHashSentTime="
				+ emailHashSentTime + ", lastStatusChangedTime="
				+ lastStatusChangedTime + "]";
	}

	
	
	
}
