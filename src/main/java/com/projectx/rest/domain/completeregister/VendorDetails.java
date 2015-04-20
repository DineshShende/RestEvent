package com.projectx.rest.domain.completeregister;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.*;




public class VendorDetails {

	private Long vendorId;
	
	@NotNull
	private String firstName;
	
	private String middleName;
	
	@NotNull
	private String lastName;
	
	private Date dateOfBirth;
	
	private String firmName;
	
	@Valid
	private Address firmAddress;
	
	@Valid
	private Address homeAddress;
	
	
	private Long mobile;
	
	private Long phoneNumber;
	
	
	private Boolean isMobileVerified;
	
	
	private String email;
	
	
	private Boolean isEmailVerified;
	
	private String laguage;
	
	private Long secondaryMobile;
	
	private Boolean isSecondaryMobileVerified;
	
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

	public VendorDetails() {

	}

	
	
	public VendorDetails(Long vendorId, String firstName, String middleName,
			String lastName, Date dateOfBirth, String firmName,
			Address firmAddress, Address homeAddress, Long mobile,
			Long phoneNumber, Boolean isMobileVerified, String email,
			Boolean isEmailVerified, String laguage, Long secondaryMobile,
			Boolean isSecondaryMobileVerified, Date insertTime,
			Date updateTime, 
			Integer updatedBy,Integer insertedBy,Long updatedById,Long insertedById) {
		super();
		this.vendorId = vendorId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.firmName = firmName;
		this.firmAddress = firmAddress;
		this.homeAddress = homeAddress;
		this.mobile = mobile;
		this.phoneNumber = phoneNumber;
		this.isMobileVerified = isMobileVerified;
		this.email = email;
		this.isEmailVerified = isEmailVerified;
		this.laguage = laguage;
		this.secondaryMobile = secondaryMobile;
		this.isSecondaryMobileVerified = isSecondaryMobileVerified;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
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


	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	
	
	
	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	

	public Long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	public Long getSecondaryMobile() {
		return secondaryMobile;
	}



	public void setSecondaryMobile(Long secondaryMobile) {
		this.secondaryMobile = secondaryMobile;
	}



	public Boolean getIsSecondaryMobileVerified() {
		return isSecondaryMobileVerified;
	}



	public void setIsSecondaryMobileVerified(Boolean isSecondaryMobileVerified) {
		this.isSecondaryMobileVerified = isSecondaryMobileVerified;
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
		return "VendorDetails [vendorId=" + vendorId + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", firmName="
				+ firmName + ", firmAddress=" + firmAddress + ", homeAddress="
				+ homeAddress + ", mobile=" + mobile + ", phoneNumber="
				+ phoneNumber + ", isMobileVerified=" + isMobileVerified
				+ ", email=" + email + ", isEmailVerified=" + isEmailVerified
				+ ", laguage=" + laguage + ", secondaryMobile="
				+ secondaryMobile + ", isSecondaryMobileVerified="
				+ isSecondaryMobileVerified + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", updatedBy=" + updatedBy
				+ ", insertedBy=" + insertedBy + ", updatedById=" + updatedById
				+ ", insertedById=" + insertedById + "]";
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
				+ ((firmName == null) ? 0 : firmName.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeAddress == null) ? 0 : homeAddress.hashCode());
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
		result = prime
				* result
				+ ((isSecondaryMobileVerified == null) ? 0
						: isSecondaryMobileVerified.hashCode());
		result = prime * result + ((laguage == null) ? 0 : laguage.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result
				+ ((secondaryMobile == null) ? 0 : secondaryMobile.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		} 
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
		if (firmName == null) {
			if (other.firmName != null)
				return false;
		} else if (!firmName.equals(other.firmName))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
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
		}
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
		if (isSecondaryMobileVerified == null) {
			if (other.isSecondaryMobileVerified != null)
				return false;
		} else if (!isSecondaryMobileVerified
				.equals(other.isSecondaryMobileVerified))
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
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (secondaryMobile == null) {
			if (other.secondaryMobile != null)
				return false;
		} else if (!secondaryMobile.equals(other.secondaryMobile))
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
		}
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		}
		return true;
	}



}
