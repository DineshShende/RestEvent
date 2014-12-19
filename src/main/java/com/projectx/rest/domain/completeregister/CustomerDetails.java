package com.projectx.rest.domain.completeregister;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;


public class CustomerDetails {
	
	private Long customerId;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private Address homeAddressId;
	
	private Long mobile;
	
	private Boolean isMobileVerified ;
	
	private String  email;
	
	private Boolean isEmailVerified;
	
	private String language;
	
	private String  businessDomain;
	
	private String nameOfFirm;
	
	private Address firmAddressId;
	
	private Long secondaryMobile;
	
	private Boolean isSecondaryMobileVerified;
	
	private String secondaryEmail;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private String updatedBy;

	
	public CustomerDetails() {

	}


	public CustomerDetails(Long customerId, String firstName, String lastName,
			Date dateOfBirth, Address homeAddressId, Long mobile,
			Boolean isMobileVerified, String email, Boolean isEmailVerified,
			String language, String businessDomain, String nameOfFirm,
			Address firmAddressId, Long secondaryMobile,
			Boolean isSecondaryMobileVerified, String secondaryEmail,
			Date insertTime, Date updateTime, String updatedBy) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddressId = homeAddressId;
		this.mobile = mobile;
		this.isMobileVerified = isMobileVerified;
		this.email = email;
		this.isEmailVerified = isEmailVerified;
		this.language = language;
		this.businessDomain = businessDomain;
		this.nameOfFirm = nameOfFirm;
		this.firmAddressId = firmAddressId;
		this.secondaryMobile = secondaryMobile;
		this.isSecondaryMobileVerified = isSecondaryMobileVerified;
		this.secondaryEmail = secondaryEmail;
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Address getHomeAddressId() {
		return homeAddressId;
	}


	public void setHomeAddressId(Address homeAddressId) {
		this.homeAddressId = homeAddressId;
	}


	public Long getMobile() {
		return mobile;
	}


	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}


	public Boolean getIsMobileVerified() {
		return isMobileVerified;
	}


	public void setIsMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}


	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getBusinessDomain() {
		return businessDomain;
	}


	public void setBusinessDomain(String businessDomain) {
		this.businessDomain = businessDomain;
	}


	public String getNameOfFirm() {
		return nameOfFirm;
	}


	public void setNameOfFirm(String nameOfFirm) {
		this.nameOfFirm = nameOfFirm;
	}


	public Address getFirmAddressId() {
		return firmAddressId;
	}


	public void setFirmAddressId(Address firmAddressId) {
		this.firmAddressId = firmAddressId;
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


	public String getSecondaryEmail() {
		return secondaryEmail;
	}


	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
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
		return "CustomerDetails [customerId=" + customerId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", homeAddressId=" + homeAddressId
				+ ", mobile=" + mobile + ", isMobileVerified="
				+ isMobileVerified + ", email=" + email + ", isEmailVerified="
				+ isEmailVerified + ", language=" + language
				+ ", businessDomain=" + businessDomain + ", nameOfFirm="
				+ nameOfFirm + ", firmAddressId=" + firmAddressId
				+ ", secondaryMobile=" + secondaryMobile
				+ ", isSecondaryMobileVerified=" + isSecondaryMobileVerified
				+ ", secondaryEmail=" + secondaryEmail + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", updatedBy="
				+ updatedBy + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessDomain == null) ? 0 : businessDomain.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firmAddressId == null) ? 0 : firmAddressId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeAddressId == null) ? 0 : homeAddressId.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((isEmailVerified == null) ? 0 : isEmailVerified.hashCode());
		result = prime
				* result
				+ ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
		result = prime
				* result
				+ ((isSecondaryMobileVerified == null) ? 0
						: isSecondaryMobileVerified.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((nameOfFirm == null) ? 0 : nameOfFirm.hashCode());
		result = prime * result
				+ ((secondaryEmail == null) ? 0 : secondaryEmail.hashCode());
		result = prime * result
				+ ((secondaryMobile == null) ? 0 : secondaryMobile.hashCode());
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
		CustomerDetails other = (CustomerDetails) obj;
		if (businessDomain == null) {
			if (other.businessDomain != null)
				return false;
		} else if (!businessDomain.equals(other.businessDomain))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (Math.abs(dateOfBirth.getTime()-other.dateOfBirth.getTime())>1000)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firmAddressId == null) {
			if (other.firmAddressId != null)
				return false;
		} else if (!firmAddressId.equals(other.firmAddressId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeAddressId == null) {
			if (other.homeAddressId != null)
				return false;
		} else if (!homeAddressId.equals(other.homeAddressId))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (Math.abs(insertTime.getTime()-other.insertTime.getTime())>1000)
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
		if (isSecondaryMobileVerified == null) {
			if (other.isSecondaryMobileVerified != null)
				return false;
		} else if (!isSecondaryMobileVerified
				.equals(other.isSecondaryMobileVerified))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
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
		if (nameOfFirm == null) {
			if (other.nameOfFirm != null)
				return false;
		} else if (!nameOfFirm.equals(other.nameOfFirm))
			return false;
		if (secondaryEmail == null) {
			if (other.secondaryEmail != null)
				return false;
		} else if (!secondaryEmail.equals(other.secondaryEmail))
			return false;
		if (secondaryMobile == null) {
			if (other.secondaryMobile != null)
				return false;
		} else if (!secondaryMobile.equals(other.secondaryMobile))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (Math.abs(updateTime.getTime()-other.updateTime.getTime())>1000)//!updateTime.equals(other.updateTime)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}


	
	
}
