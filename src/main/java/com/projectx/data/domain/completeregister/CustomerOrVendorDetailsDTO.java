package com.projectx.data.domain.completeregister;
 
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;


public class CustomerOrVendorDetailsDTO {
	
	private CustomerDetails  customerDetails;
	
	private VendorDetails vendorDetails;

	
	public CustomerOrVendorDetailsDTO() {

	}


	public CustomerOrVendorDetailsDTO(CustomerDetails customerDetails,
			VendorDetails vendorDetails) {

		this.customerDetails = customerDetails;
		this.vendorDetails = vendorDetails;
	}


	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}


	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}


	public VendorDetails getVendorDetails() {
		return vendorDetails;
	}


	public void setVendorDetails(VendorDetails vendorDetails) {
		this.vendorDetails = vendorDetails;
	}


	@Override
	public String toString() {
		return "CustomerOrVendorDetailsDTO [customerDetails=" + customerDetails
				+ ", vendorDetails=" + vendorDetails + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerDetails == null) ? 0 : customerDetails.hashCode());
		result = prime * result
				+ ((vendorDetails == null) ? 0 : vendorDetails.hashCode());
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
		CustomerOrVendorDetailsDTO other = (CustomerOrVendorDetailsDTO) obj;
		if (customerDetails == null) {
			if (other.customerDetails != null)
				return false;
		} else if (!customerDetails.equals(other.customerDetails))
			return false;
		if (vendorDetails == null) {
			if (other.vendorDetails != null)
				return false;
		} else if (!vendorDetails.equals(other.vendorDetails))
			return false;
		return true;
	}
	
	
	

}
