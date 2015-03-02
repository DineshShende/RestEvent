package com.projectx.rest.domain.completeregister;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.*;




public class VendorDetails {

	private Long vendorId;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private Address firmAddress;
	
	private Long mobile;
	
	private Boolean isMobileVerified;
	
	private String email;
	
	private Boolean isEmailVerified;
	
	private String laguage;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private String updatedBy;

	public VendorDetails() {

	}

	public VendorDetails(Long vendorId, String firstName, String lastName,
			Date dateOfBirth, Address firmAddress, Long mobile,
			Boolean isMobileVerified, String email, Boolean isEmailVerified,
			String laguage, Date insertTime, Date updateTime, String updatedBy) {

		this.vendorId = vendorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.firmAddress = firmAddress;
		this.mobile = mobile;
		this.isMobileVerified = isMobileVerified;
		this.email = email;
		this.isEmailVerified = isEmailVerified;
		this.laguage = laguage;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}



	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getFirmAddress() {
		return firmAddress;
	}

	public void setFirmAddress(Address firmAddress) {
		this.firmAddress = firmAddress;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLaguage() {
		return laguage;
	}

	public void setLaguage(String laguage) {
		this.laguage = laguage;
	}

	public Boolean getIsMobileVerified() {
		return isMobileVerified;
	}

	public void setIsMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "VendorDetails [vendorId=" + vendorId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", firmAddress=" + firmAddress + ", mobile="
				+ mobile + ", isMobileVerified=" + isMobileVerified
				+ ", email=" + email + ", isEmailVerified=" + isEmailVerified
				+ ", laguage=" + laguage + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", updatedBy=" + updatedBy
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firmAddress == null) ? 0 : firmAddress.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((isEmailVerified == null) ? 0 : isEmailVerified.hashCode());
		result = prime
				* result
				+ ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
		result = prime * result + ((laguage == null) ? 0 : laguage.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
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
		VendorDetails other = (VendorDetails) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (Math.abs(dateOfBirth.getTime()-other.dateOfBirth.getTime())>100000)//
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firmAddress == null) {
			if (other.firmAddress != null)
				return false;
		} else if (!firmAddress.equals(other.firmAddress))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
/*		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (Math.abs(insertTime.getTime()-other.insertTime.getTime())>100000)
			return false;*/
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
		if (laguage == null) {
			if (other.laguage != null)
				return false;
		} else if (!laguage.equals(other.laguage))
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
		/*
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (Math.abs(updateTime.getTime()-other.updateTime.getTime())>100000)
			return false;
			*/
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		/*
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
			
		*/	
		return true;
	}


	
}
