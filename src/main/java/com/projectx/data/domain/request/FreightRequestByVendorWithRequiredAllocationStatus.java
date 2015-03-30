package com.projectx.data.domain.request;

import com.projectx.rest.domain.request.FreightRequestByVendor;

public class FreightRequestByVendorWithRequiredAllocationStatus {
	
	private String status;
	
	private FreightRequestByVendor freightRequestByVendor;

	
	
	
	public FreightRequestByVendorWithRequiredAllocationStatus() {

	}




	public FreightRequestByVendorWithRequiredAllocationStatus(String status,
			FreightRequestByVendor freightRequestByVendor) {

		this.status = status;
		this.freightRequestByVendor = freightRequestByVendor;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public FreightRequestByVendor getFreightRequestByVendor() {
		return freightRequestByVendor;
	}




	public void setFreightRequestByVendor(
			FreightRequestByVendor freightRequestByVendor) {
		this.freightRequestByVendor = freightRequestByVendor;
	}




	@Override
	public String toString() {
		return "FreightRequestByVendorWithRequiredAllocationStatus [status="
				+ status + ", freightRequestByVendor=" + freightRequestByVendor
				+ "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((freightRequestByVendor == null) ? 0
						: freightRequestByVendor.hashCode());
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
		FreightRequestByVendorWithRequiredAllocationStatus other = (FreightRequestByVendorWithRequiredAllocationStatus) obj;
		if (freightRequestByVendor == null) {
			if (other.freightRequestByVendor != null)
				return false;
		} else if (!freightRequestByVendor.equals(other.freightRequestByVendor))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
	

}
