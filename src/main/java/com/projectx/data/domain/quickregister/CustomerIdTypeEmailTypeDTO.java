package com.projectx.data.domain.quickregister;

public class CustomerIdTypeEmailTypeDTO {

	private Long customerId;
	
	private Integer customerType;
	
	private Integer emailType;
	
	
	public CustomerIdTypeEmailTypeDTO() {

	}

	public CustomerIdTypeEmailTypeDTO(Long customerId, Integer customerType,
			Integer emailType) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailType = emailType;
	}




	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "CustomerIdTypeEmailDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", email=" + emailType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((emailType == null) ? 0 : emailType.hashCode());
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
		CustomerIdTypeEmailTypeDTO other = (CustomerIdTypeEmailTypeDTO) obj;
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
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		return true;
	}

			
}
