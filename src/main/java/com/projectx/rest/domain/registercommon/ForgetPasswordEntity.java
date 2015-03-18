package com.projectx.rest.domain.registercommon;

public class ForgetPasswordEntity {
	
	
	private Long customerId;
	
	private Integer customerType;
	
	private Long mobile;
	
	private String email;
	
	private Boolean isMobileVerified;
	
	private Boolean isEmailVerified;
	
	private Boolean isPasswordSent;

	public ForgetPasswordEntity() {

	}



	public ForgetPasswordEntity(Long customerId, Integer customerType,
			Long mobile, String email, Boolean isMobileVerified,
			Boolean isEmailVerified, Boolean isPasswordSent) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.mobile = mobile;
		this.email = email;
		this.isMobileVerified = isMobileVerified;
		this.isEmailVerified = isEmailVerified;
		this.isPasswordSent = isPasswordSent;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
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

	
	
	public Boolean getIsPasswordSent() {
		return isPasswordSent;
	}



	public void setIsPasswordSent(Boolean isPasswordSent) {
		this.isPasswordSent = isPasswordSent;
	}



	@Override
	public String toString() {
		return "ForgetPasswordEntity [customerId=" + customerId
				+ ", customerType=" + customerType + ", mobile=" + mobile
				+ ", email=" + email + ", isMobileVerified=" + isMobileVerified
				+ ", isEmailVerified=" + isEmailVerified + ", isPasswordSent="
				+ isPasswordSent + "]";
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
				+ ((isEmailVerified == null) ? 0 : isEmailVerified.hashCode());
		result = prime
				* result
				+ ((isMobileVerified == null) ? 0 : isMobileVerified.hashCode());
		result = prime * result
				+ ((isPasswordSent == null) ? 0 : isPasswordSent.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		ForgetPasswordEntity other = (ForgetPasswordEntity) obj;
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
		if (isPasswordSent == null) {
			if (other.isPasswordSent != null)
				return false;
		} else if (!isPasswordSent.equals(other.isPasswordSent))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}



		

}
