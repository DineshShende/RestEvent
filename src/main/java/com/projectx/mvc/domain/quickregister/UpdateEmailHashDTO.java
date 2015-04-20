package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class UpdateEmailHashDTO {

	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private Integer emailType;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;
	
	public UpdateEmailHashDTO() {
	
	}

	public UpdateEmailHashDTO(Long customerId, Integer customerType,
			Integer emailType, Integer  requestedBy,Long requestedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailType = emailType;
		this.requestedBy = requestedBy;
		this.requestedById=requestedById;
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





	public Integer getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Long getRequestedById() {
		return requestedById;
	}

	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}

	@Override
	public String toString() {
		return "UpdateEmailHashDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", emailType=" + emailType
				+ ", requestedBy=" + requestedBy + ", requestedById="
				+ requestedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
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
		UpdateEmailHashDTO other = (UpdateEmailHashDTO) obj;
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
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		return true;
	}

	
		
}
