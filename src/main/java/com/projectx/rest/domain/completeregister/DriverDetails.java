package com.projectx.rest.domain.completeregister;

import java.util.Date;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;


public class DriverDetails {
	
	private Long driverId;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private String bloodGroup;
	
	private Address homeAddress;
	
	private Long mobile;
	
	private Boolean isMobileVerified;
	
	private Long homeContactNumber;
	
	private String licenceNumber;
	
	private Date drivingSince;
	
	private Date employedSince;
	
	private Boolean isFreightRequestPermissionGiven;
	
	private Boolean isDealFinalizationPermissionGiven;

	private String language;
	
	private Long vendorId;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private String updatedBy;

	public DriverDetails() {

	}









	public DriverDetails(Long driverId, String firstName, String middleName,
			String lastName, Date dateOfBirth, String bloodGroup,
			Address homeAddress, Long mobile, Boolean isMobileVerified,
			Long homeContactNumber, String licenceNumber, Date drivingSince,
			Date employedSince, Boolean isFreightRequestPermissionGiven,
			Boolean isDealFinalizationPermissionGiven, String language,
			Long vendorId, Date insertTime, Date updateTime, String updatedBy) {
		super();
		this.driverId = driverId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.bloodGroup = bloodGroup;
		this.homeAddress = homeAddress;
		this.mobile = mobile;
		this.isMobileVerified = isMobileVerified;
		this.homeContactNumber = homeContactNumber;
		this.licenceNumber = licenceNumber;
		this.drivingSince = drivingSince;
		this.employedSince = employedSince;
		this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
		this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
		this.language = language;
		this.vendorId = vendorId;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}









	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Address getHomeAddress() {
		return homeAddress;

	}
	
	public Boolean getIsMobileVerified() {
		return isMobileVerified;
	}


	public void setIsMobileVerified(Boolean isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}


	public void setIsFreightRequestPermissionGiven(
			Boolean isFreightRequestPermissionGiven) {
		this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
	}



	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getHomeContactNumber() {
		return homeContactNumber;
	}

	public void setHomeContactNumber(Long homeContactNumber) {
		this.homeContactNumber = homeContactNumber;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDrivingSince() {
		return drivingSince;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setDrivingSince(Date drivingSince) {
		this.drivingSince = drivingSince;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEmployedSince() {
		return employedSince;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setEmployedSince(Date employedSince) {
		this.employedSince = employedSince;
	}

	public Boolean getIsFreightRequestPermissionGiven() {
		return isFreightRequestPermissionGiven;
	}

	public void setIsFrieghtRequestPermissionGiven(
			Boolean isFrieghtRequestPermissionGiven) {
		this.isFreightRequestPermissionGiven = isFrieghtRequestPermissionGiven;
	}

	public Boolean getIsDealFinalizationPermissionGiven() {
		return isDealFinalizationPermissionGiven;
	}

	public void setIsDealFinalizationPermissionGiven(
			Boolean isDealFinalizationPermissionGiven) {
		this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
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
		return "DriverDetails [driverId=" + driverId + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", bloodGroup="
				+ bloodGroup + ", homeAddress=" + homeAddress + ", mobile="
				+ mobile + ", isMobileVerified=" + isMobileVerified
				+ ", homeContactNumber=" + homeContactNumber
				+ ", licenceNumber=" + licenceNumber + ", drivingSince="
				+ drivingSince + ", employedSince=" + employedSince
				+ ", isFreightRequestPermissionGiven="
				+ isFreightRequestPermissionGiven
				+ ", isDealFinalizationPermissionGiven="
				+ isDealFinalizationPermissionGiven + ", language=" + language
				+ ", vendorId=" + vendorId + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", updatedBy=" + updatedBy
				+ "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bloodGroup == null) ? 0 : bloodGroup.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result
				+ ((drivingSince == null) ? 0 : drivingSince.hashCode());
		result = prime * result
				+ ((employedSince == null) ? 0 : employedSince.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime
				* result
				+ ((homeContactNumber == null) ? 0 : homeContactNumber
						.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime
				* result
				+ ((isDealFinalizationPermissionGiven == null) ? 0
						: isDealFinalizationPermissionGiven.hashCode());
		result = prime
				* result
				+ ((isFreightRequestPermissionGiven == null) ? 0
						: isFreightRequestPermissionGiven.hashCode());
		result = prime
				* result
				+ ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((licenceNumber == null) ? 0 : licenceNumber.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
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
		DriverDetails other = (DriverDetails) obj;
		if (bloodGroup == null) {
			if (other.bloodGroup != null)
				return false;
		} else if (!bloodGroup.equals(other.bloodGroup))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (Math.abs(dateOfBirth.getTime()-other.dateOfBirth.getTime())>1000000)
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (drivingSince == null) {
			if (other.drivingSince != null)
				return false;
		} else if (Math.abs(drivingSince.getTime()-other.drivingSince.getTime())>1000000)
			return false;
		if (employedSince == null) {
			if (other.employedSince != null)
				return false;
		} else if (Math.abs(employedSince.getTime()-other.employedSince.getTime())>1000000)
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
		if (homeContactNumber == null) {
			if (other.homeContactNumber != null)
				return false;
		} else if (!homeContactNumber.equals(other.homeContactNumber))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (Math.abs(insertTime.getTime()-other.insertTime.getTime())>1000000)
			return false;
		if (isDealFinalizationPermissionGiven == null) {
			if (other.isDealFinalizationPermissionGiven != null)
				return false;
		} else if (!isDealFinalizationPermissionGiven
				.equals(other.isDealFinalizationPermissionGiven))
			return false;
		if (isFreightRequestPermissionGiven == null) {
			if (other.isFreightRequestPermissionGiven != null)
				return false;
		} else if (!isFreightRequestPermissionGiven
				.equals(other.isFreightRequestPermissionGiven))
			return false;
		if (isMobileVerified == null) {
			if (other.isMobileVerified != null)
				return false;
		} else if (!isMobileVerified.equals(other.isMobileVerified))
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
		if (licenceNumber == null) {
			if (other.licenceNumber != null)
				return false;
		} else if (!licenceNumber.equals(other.licenceNumber))
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
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (Math.abs(updateTime.getTime()-other.updateTime.getTime())>1000000)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}





	
	
}
