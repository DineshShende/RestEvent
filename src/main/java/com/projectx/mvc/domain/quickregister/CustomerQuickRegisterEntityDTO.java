package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.util.annotation.QuickRegisterDTOValid;
import com.projectx.rest.util.annotation.QuickRegisterEntityValid;

@QuickRegisterDTOValid
public class CustomerQuickRegisterEntityDTO {
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;

	private String email;
	
	private Long mobile;

	@NotNull
	private Integer pin;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private String  requestBy;
	
	
	public CustomerQuickRegisterEntityDTO()
	{
		
	}

	public CustomerQuickRegisterEntityDTO(String firstName, String lastName,
			String email, Long mobile, Integer pin, Integer customerType,
			String requestBy) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.pin = pin;
		this.customerType = customerType;
		this.requestBy = requestBy;
	}





	public QuickRegisterEntity toCustomerQuickRegisterEntity()
	{
		QuickRegisterEntity newCustomer=new QuickRegisterEntity();
		newCustomer.setFirstName(this.firstName);
		newCustomer.setLastName(this.lastName);
		newCustomer.setEmail(this.email);
		newCustomer.setMobile(this.mobile);
		newCustomer.setPincode(this.pin);
		newCustomer.setCustomerType(this.customerType);
		//newCustomer.setIsEmailVerified(false);
		//newCustomer.setIsMobileVerified(false);
		newCustomer.setUpdatedBy(this.requestBy);
		
		return newCustomer;
		
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	
	

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	@Override
	public String toString() {
		return "CustomerQuickRegisterEntityDTO [firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", mobile="
				+ mobile + ", pin=" + pin + ", customerType=" + customerType
				+ ", requestBy=" + requestBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result
				+ ((requestBy == null) ? 0 : requestBy.hashCode());
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
		CustomerQuickRegisterEntityDTO other = (CustomerQuickRegisterEntityDTO) obj;
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
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
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (requestBy == null) {
			if (other.requestBy != null)
				return false;
		} else if (!requestBy.equals(other.requestBy))
			return false;
		return true;
	}

			

}
