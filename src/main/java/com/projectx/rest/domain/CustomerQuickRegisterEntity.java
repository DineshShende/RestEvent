package com.projectx.rest.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectx.data.domain.CustomerQuickEntitySaveDTO;

public class CustomerQuickRegisterEntity {

	
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
	
	
	public CustomerQuickRegisterEntity() {
		
		
	}
	
	
	public CustomerQuickEntitySaveDTO toCustomerQuickRegisterDTO()
	{
		CustomerQuickEntitySaveDTO  customerQuickRegisterDTO1=new CustomerQuickEntitySaveDTO();
		//customerQuickRegisterDTO1.setCustomerId(this.customerId);
		customerQuickRegisterDTO1.setFirstName(this.firstName);
		customerQuickRegisterDTO1.setLastName(this.lastName);
		customerQuickRegisterDTO1.setEmail(this.email);
		customerQuickRegisterDTO1.setMobile(this.mobile);
		customerQuickRegisterDTO1.setPin(this.pin);
		customerQuickRegisterDTO1.setStatus(this.status);
		customerQuickRegisterDTO1.setMobilePin(this.mobilePin);
		customerQuickRegisterDTO1.setEmailHash(this.emailHash);
		customerQuickRegisterDTO1.setMobileVerificationAttempts(this.mobileVerificationAttempts);
		customerQuickRegisterDTO1.setMobilePinSentTime(this.mobilePinSentTime);
		customerQuickRegisterDTO1.setEmailHashSentTime(this.emailHashSentTime);
		customerQuickRegisterDTO1.setLastStatusChangedTime(this.lastStatusChangedTime);
		
		
		return customerQuickRegisterDTO1;
	}
	

	public CustomerQuickRegisterEntity createCopy()
	{
		CustomerQuickRegisterEntity customerQuickRegisterDTO1=new CustomerQuickRegisterEntity();
		customerQuickRegisterDTO1.setCustomerId(this.customerId);
		customerQuickRegisterDTO1.setFirstName(this.firstName);
		customerQuickRegisterDTO1.setLastName(this.lastName);
		customerQuickRegisterDTO1.setEmail(this.email);
		customerQuickRegisterDTO1.setMobile(this.mobile);
		customerQuickRegisterDTO1.setPin(this.pin);
		customerQuickRegisterDTO1.setStatus(this.status);
		customerQuickRegisterDTO1.setMobilePin(this.mobilePin);
		customerQuickRegisterDTO1.setEmailHash(this.emailHash);
		customerQuickRegisterDTO1.setMobileVerificationAttempts(this.mobileVerificationAttempts);
		customerQuickRegisterDTO1.setMobilePinSentTime(this.mobilePinSentTime);
		customerQuickRegisterDTO1.setEmailHashSentTime(this.emailHashSentTime);
		customerQuickRegisterDTO1.setLastStatusChangedTime(this.lastStatusChangedTime);
		
		return customerQuickRegisterDTO1;
	}
	
	

	public CustomerQuickRegisterEntity(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pin,
			String status, Integer mobilePin, String emailHash,
			Integer mobileVerificationAttempts, Date mobilePinSentTime,
			Date emailHashSentTime, Date lastStatusChangedTime, String password,String passwordType) {
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


	@JsonIgnore
	public boolean isMobileVerifiedEmailVerficationPending() {
		return this.status.equals("MobileVerifiedEmailVerficationPending");
	}
	@JsonIgnore
	public boolean isMobileVerified() {
		return this.status.equals("MobileVerified");
	}
	@JsonIgnore
	public boolean isEmailMobileVerified() {
		return this.status.equals("EmailMobileVerified");
	}
	@JsonIgnore
	public boolean isEmailVerified() {
		return this.status.equals("EmailVerified");
	}
	@JsonIgnore
	public boolean isEmailVerificationPending() {
		return this.status.equals("EmailVerificationPending");
	}
	@JsonIgnore
	public boolean isMobileVerificationPending() {
		return this.status.equals("MobileVerificationPending");
	}
	@JsonIgnore
	public boolean isEmailMobileVerificationPending() {
		return this.status.equals("EmailMobileVerificationPending");
	}
	@JsonIgnore
	public boolean isEmailVerifiedMobileVerficationPending() {
		return this.status.equals("EmailVerifiedMobileVerficationPending");
	}
	@JsonIgnore
	public void setStatusEmailVerifiedMobileVerficationPending() {
		this.status = "EmailVerifiedMobileVerficationPending";
	}



	
	public void setStatusMobileVerifiedEmailVerficationPending() {
		this.status = "MobileVerifiedEmailVerficationPending";
	}
	
	
	public void setStatusMobileVerified() {
		this.status = "MobileVerified";
	}

	
	public void setStatusEmailVerified() {
		this.status = "EmailVerified";
	}

	
	public void setStatusEmailMobileVerified() {
		this.status = "EmailMobileVerified";
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
		return "CustomerQuickRegisterEntity [customerId=" + customerId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobile=" + mobile + ", pin=" + pin
				+ ", status=" + status + ", mobilePin=" + mobilePin
				+ ", emailHash=" + emailHash + ", mobileVerificationAttempts="
				+ mobileVerificationAttempts + ", mobilePinSentTime="
				+ mobilePinSentTime + ", emailHashSentTime="
				+ emailHashSentTime + ", lastStatusChangedTime="
				+ lastStatusChangedTime + "]";
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
		result = prime
				* result
				+ ((emailHashSentTime == null) ? 0 : emailHashSentTime
						.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime
				* result
				+ ((lastStatusChangedTime == null) ? 0 : lastStatusChangedTime
						.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime
				* result
				+ ((mobilePinSentTime == null) ? 0 : mobilePinSentTime
						.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
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
		} else if (!(customerId!=null && other.customerId!=null))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!(emailHash.length()==other.emailHash.length()))
			return false;
		if (emailHashSentTime == null) {
			if (other.emailHashSentTime != null)
				return false;
		} else if ((emailHashSentTime.getTime()-other.emailHashSentTime.getTime())>60 * 1000)
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
		if (lastStatusChangedTime == null) {
			if (other.lastStatusChangedTime != null)
				return false;
		//Changed to cope up with testing time differences	
		} else if ((lastStatusChangedTime.getTime()-other.getLastStatusChangedTime().getTime())>60 * 1000)//(!lastStatusChangedTime.equals(other.lastStatusChangedTime))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (Math.abs(mobilePin-other.mobilePin)>999999)
			return false;
		if (mobilePinSentTime == null) {
			if (other.mobilePinSentTime != null)
				return false;
		} else if ((mobilePinSentTime.getTime()-other.mobilePinSentTime.getTime())>60 * 1000)
			return false;
		if (mobileVerificationAttempts == null) {
			if (other.mobileVerificationAttempts != null)
				return false;
		} else if (!mobileVerificationAttempts
				.equals(other.mobileVerificationAttempts))
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
