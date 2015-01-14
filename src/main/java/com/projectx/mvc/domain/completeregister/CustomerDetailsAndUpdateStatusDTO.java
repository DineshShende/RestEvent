package com.projectx.mvc.domain.completeregister;

import com.projectx.rest.domain.completeregister.CustomerDetails;

public class CustomerDetailsAndUpdateStatusDTO {

	private String status;
	
	private CustomerDetails customerDetails;

	public CustomerDetailsAndUpdateStatusDTO() {

	}

	public CustomerDetailsAndUpdateStatusDTO(String status,
			CustomerDetails customerDetails) {
		this.status = status;
		this.customerDetails = customerDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	@Override
	public String toString() {
		return "CustomerDetailsAndUpdateStatusDTO [status=" + status
				+ ", customerDetails=" + customerDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerDetails == null) ? 0 : customerDetails.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		CustomerDetailsAndUpdateStatusDTO other = (CustomerDetailsAndUpdateStatusDTO) obj;
		if (customerDetails == null) {
			if (other.customerDetails != null)
				return false;
		} else if (!customerDetails.equals(other.customerDetails))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
	
	
}
