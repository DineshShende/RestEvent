package com.projectx.rest.domain.quickregister;


public class AuthenticationDetailsAng {

	private AuthenticationDetailsKey key; 
	
	private String completeName;

	private String passwordType;
	
	private Boolean isCompleteRegisterCompleted;

	
	
	public AuthenticationDetailsAng() {

	}



	public AuthenticationDetailsAng(AuthenticationDetailsKey key,
			String completeName, String passwordType,
			Boolean isCompleteRegisterCompleted) {
		super();
		this.key = key;
		this.completeName = completeName;
		this.passwordType = passwordType;
		this.isCompleteRegisterCompleted = isCompleteRegisterCompleted;
	}



	public AuthenticationDetailsKey getKey() {
		return key;
	}



	public void setKey(AuthenticationDetailsKey key) {
		this.key = key;
	}



	public String getCompleteName() {
		return completeName;
	}



	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}



	public String getPasswordType() {
		return passwordType;
	}



	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}



	public Boolean getIsCompleteRegisterCompleted() {
		return isCompleteRegisterCompleted;
	}



	public void setIsCompleteRegisterCompleted(Boolean isCompleteRegisterCompleted) {
		this.isCompleteRegisterCompleted = isCompleteRegisterCompleted;
	}



	@Override
	public String toString() {
		return "AuthenticationDetailsAng [key=" + key + ", completeName="
				+ completeName + ", passwordType=" + passwordType
				+ ", isCompleteRegisterCompleted="
				+ isCompleteRegisterCompleted + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((completeName == null) ? 0 : completeName.hashCode());
		result = prime
				* result
				+ ((isCompleteRegisterCompleted == null) ? 0
						: isCompleteRegisterCompleted.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((passwordType == null) ? 0 : passwordType.hashCode());
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
		AuthenticationDetailsAng other = (AuthenticationDetailsAng) obj;
		if (completeName == null) {
			if (other.completeName != null)
				return false;
		} else if (!completeName.equals(other.completeName))
			return false;
		if (isCompleteRegisterCompleted == null) {
			if (other.isCompleteRegisterCompleted != null)
				return false;
		} else if (!isCompleteRegisterCompleted
				.equals(other.isCompleteRegisterCompleted))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (passwordType == null) {
			if (other.passwordType != null)
				return false;
		} else if (!passwordType.equals(other.passwordType))
			return false;
		return true;
	}



		
	
	
}
