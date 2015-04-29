package com.projectx.mvc.domain.handshake;

import com.projectx.rest.domain.completeregister.Address;

public class DealInfoAndVendorContactDetailsDTO {
	
	private Long dealId;
	
	//private Integer amount;
	
	private String firstName;
	
	//private String middleName;
	
	private String lastName;
	
	private Long mobile;
	
	//private String email;
	
	private String city;
	
	private String state;
	
	private Integer pincode;
	
	
	
	//private Address homeAddress;
	
	//private Address firmAddress;

	public DealInfoAndVendorContactDetailsDTO() {
		super();
	}



	public DealInfoAndVendorContactDetailsDTO(Long dealId, String firstName,
			String lastName, Long mobile, String city, String state,
			Integer pincode) {
		super();
		this.dealId = dealId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}



	public Long getDealId() {
		return dealId;
	}



	public void setDealId(Long dealId) {
		this.dealId = dealId;
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



	public Long getMobile() {
		return mobile;
	}



	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public Integer getPincode() {
		return pincode;
	}



	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	@Override
	public String toString() {
		return "DealInfoAndVendorContactDetailsDTO [dealId=" + dealId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobile=" + mobile + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((dealId == null) ? 0 : dealId.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		DealInfoAndVendorContactDetailsDTO other = (DealInfoAndVendorContactDetailsDTO) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (dealId == null) {
			if (other.dealId != null)
				return false;
		} else if (!dealId.equals(other.dealId))
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
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	
	
}
