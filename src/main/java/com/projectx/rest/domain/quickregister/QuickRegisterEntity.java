package com.projectx.rest.domain.quickregister;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.data.domain.quickregister.CustomerQuickEntitySaveDTO;
import com.projectx.rest.util.annotation.Pincode;
import com.projectx.rest.util.annotation.QuickRegisterEntityValid;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;

@QuickRegisterEntityValid
public class QuickRegisterEntity {

	private Long customerId;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	private String email;
	
	private Long mobile;
	
	@Pincode
	private Integer pincode;
	
	private Boolean isEmailVerified;

	private Boolean isMobileVerified;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private Date insertTime;
	
	@NotNull
	private Date updateTime;
	
	@NotNull
	private Integer updatedBy;
	
	@NotNull
	private Integer insertedBy;
	
	@NotNull
	private Long updatedById;
	
	@NotNull
	private Long insertedById;
	
	
	public QuickRegisterEntity() {
		
		
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
	
	public Long getCustomerId() {
		return customerId;
	}

	public QuickRegisterEntity(Long customerId, String firstName,
			String lastName, String email, Long mobile, Integer pincode,
			Boolean isEmailVerified, Boolean isMobileVerified,
			Integer customerType, Date insertTime, Date updateTime,
			Integer updatedBy,Integer insertedBy,Long updatedById,Long insertedById) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pincode = pincode;
		this.isEmailVerified = isEmailVerified;
		this.isMobileVerified = isMobileVerified;
		this.customerType = customerType;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
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
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getInsertTime() {
		return insertTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	
	

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(Integer insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Long getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}

	public Long getInsertedById() {
		return insertedById;
	}

	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}

	@Override
	public String toString() {
		return "QuickRegisterEntity [customerId=" + customerId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + ", pincode=" + pincode
				+ ", isEmailVerified=" + isEmailVerified
				+ ", isMobileVerified=" + isMobileVerified + ", customerType="
				+ customerType + ", insertTime=" + insertTime + ", updateTime="
				+ updateTime + ", updatedBy=" + updatedBy + ", insertedBy="
				+ insertedBy + ", updatedById=" + updatedById
				+ ", insertedById=" + insertedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
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
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		QuickRegisterEntity other = (QuickRegisterEntity) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
			return false;
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
		}
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} else if (!insertedBy.equals(other.insertedBy))
			return false;
		if (insertedById == null) {
			if (other.insertedById != null)
				return false;
		} else if (!insertedById.equals(other.insertedById))
			return false;
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
		}
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} else if (!updatedById.equals(other.updatedById))
			return false;
		return true;
	}

			
	
}
