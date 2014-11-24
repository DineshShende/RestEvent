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
	
	private Integer pincode;
	
	private Boolean isEmailVerified;

	private Boolean isMobileVerified;
		
	private Date insertTime;
	
	private Date updateTime;
	
	private String updatedBy;
	
	
	public CustomerQuickRegisterEntity() {
		
		
	}
	
	/*
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
	*/
	
	public CustomerQuickRegisterEntity(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pincode,
			Boolean isEmailVerified, Boolean isMobileVerified, Date insertTime,
			Date updateTime, String updatedBy) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pincode = pincode;
		this.isEmailVerified = isEmailVerified;
		this.isMobileVerified = isMobileVerified;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
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

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Boolean getIsMobileVerified() {
		return isMobileVerified;
	}

	public void setIsMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "CustomerQuickRegisterEntity [customerId=" + customerId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobile=" + mobile + ", pincode="
				+ pincode + ", isEmailVerified=" + isEmailVerified
				+ ", isMobileVerified=" + isMobileVerified + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", updatedBy="
				+ updatedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((isEmailVerified == null) ? 0 : isEmailVerified.hashCode());
		result = prime
				* result
				+ ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		} else if (true)
			return true;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (Math.abs(insertTime.getTime()-other.insertTime.getTime())<1000)
			return true;
		if (isEmailVerified == null) {
			if (other.isEmailVerified != null)
				return false;
		} else if (!isEmailVerified.equals(other.isEmailVerified))
			return false;
		if (isMobileVerified == null) {
			if (other.isMobileVerified != null)
				return false;
		} else if (!isMobileVerified.equals(other.isMobileVerified))
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
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (Math.abs(updateTime.getTime()-other.updateTime.getTime())<1000)
			return true;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}


	
	
}
