package com.projectx.rest.domain;

public class CustomerQuickRegisterEmailMobileVerificationEntity {
	
	private CustomerQuickRegisterEntity customerQuickRegisterEntity;
	
	private CustomerEmailVerificationDetails customerEmailVerificationDetails;
	
	private CustomerMobileVerificationDetails customerMobileVerificationDetails;

	public CustomerQuickRegisterEmailMobileVerificationEntity() {
		super();
	}

	public CustomerQuickRegisterEmailMobileVerificationEntity(
			CustomerQuickRegisterEntity customerQuickRegisterEntity,
			CustomerEmailVerificationDetails customerEmailVerificationDetails,
			CustomerMobileVerificationDetails customerMobileVerificationDetails) {
		super();
		this.customerQuickRegisterEntity = customerQuickRegisterEntity;
		this.customerEmailVerificationDetails = customerEmailVerificationDetails;
		this.customerMobileVerificationDetails = customerMobileVerificationDetails;
	}

	
	
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntity() {
		return customerQuickRegisterEntity;
	}

	public void setCustomerQuickRegisterEntity(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		this.customerQuickRegisterEntity = customerQuickRegisterEntity;
	}

	public CustomerEmailVerificationDetails getCustomerEmailVerificationDetails() {
		return customerEmailVerificationDetails;
	}

	public void setCustomerEmailVerificationDetails(
			CustomerEmailVerificationDetails customerEmailVerificationDetails) {
		this.customerEmailVerificationDetails = customerEmailVerificationDetails;
	}

	public CustomerMobileVerificationDetails getCustomerMobileVerificationDetails() {
		return customerMobileVerificationDetails;
	}

	public void setCustomerMobileVerificationDetails(
			CustomerMobileVerificationDetails customerMobileVerificationDetails) {
		this.customerMobileVerificationDetails = customerMobileVerificationDetails;
	}

	@Override
	public String toString() {
		return "CustomerQuickRegisterEmailMobileVerificationEntity [customerQuickRegisterEntity="
				+ customerQuickRegisterEntity
				+ ", customerEmailVerificationDetails="
				+ customerEmailVerificationDetails
				+ ", customerMobileVerificationDetails="
				+ customerMobileVerificationDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((customerEmailVerificationDetails == null) ? 0
						: customerEmailVerificationDetails.hashCode());
		result = prime
				* result
				+ ((customerMobileVerificationDetails == null) ? 0
						: customerMobileVerificationDetails.hashCode());
		result = prime
				* result
				+ ((customerQuickRegisterEntity == null) ? 0
						: customerQuickRegisterEntity.hashCode());
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
		CustomerQuickRegisterEmailMobileVerificationEntity other = (CustomerQuickRegisterEmailMobileVerificationEntity) obj;
		if (customerEmailVerificationDetails == null) {
			if (other.customerEmailVerificationDetails != null)
				return false;
		} else if (!customerEmailVerificationDetails
				.equals(other.customerEmailVerificationDetails))
			return false;
		if (customerMobileVerificationDetails == null) {
			if (other.customerMobileVerificationDetails != null)
				return false;
		} else if (!customerMobileVerificationDetails
				.equals(other.customerMobileVerificationDetails))
			return false;
		if (customerQuickRegisterEntity == null) {
			if (other.customerQuickRegisterEntity != null)
				return false;
		} else if (!customerQuickRegisterEntity
				.equals(other.customerQuickRegisterEntity))
			return false;
		return true;
	}
	
	
	

}
