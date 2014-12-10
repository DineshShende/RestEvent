package com.projectx.web.domain.quickregister;

public class LoginVerificationDTO {

	private String loginEntity;
	
	private String password;

	public LoginVerificationDTO() {

	}

	public LoginVerificationDTO(String loginEntity, String password) {
		super();
		this.loginEntity = loginEntity;
		this.password = password;
	}

	public String getLoginEntity() {
		return loginEntity;
	}

	public void setLoginEntity(String loginEntity) {
		this.loginEntity = loginEntity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((loginEntity == null) ? 0 : loginEntity.hashCode());
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
		LoginVerificationDTO other = (LoginVerificationDTO) obj;
		if (loginEntity == null) {
			if (other.loginEntity != null)
				return false;
		} else if (!loginEntity.equals(other.loginEntity))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginVerificationDTO [loginEntity=" + loginEntity
				+ ", password=" + password + "]";
	}

	
	
	
	
}
