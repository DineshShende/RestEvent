package com.projectx.data.domain.quickregister;

public class MobilePinPasswordDTO {

	private Long mobile;
	
	private Integer mobilePin;
	
	private String password;

	
	
	public MobilePinPasswordDTO() {
	}



	public MobilePinPasswordDTO(Long mobile, Integer mobilePin, String password) {
		super();
		this.mobile = mobile;
		this.mobilePin = mobilePin;
		this.password = password;
	}



	public Long getMobile() {
		return mobile;
	}



	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}



	public Integer getMobilePin() {
		return mobilePin;
	}



	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "MobilePinPasswordDTO [mobile=" + mobile + ", mobilePin="
				+ mobilePin + ", password=" + password + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		MobilePinPasswordDTO other = (MobilePinPasswordDTO) obj;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


	
	
}
