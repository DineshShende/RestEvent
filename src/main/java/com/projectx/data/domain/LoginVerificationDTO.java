package com.projectx.data.domain;

public class LoginVerificationDTO {

	private String email;
	
	private Long mobile;
	
	private String password;

	public LoginVerificationDTO() {

	}

	public LoginVerificationDTO(String email, Long mobile, String password) {
		super();
		this.email = email;
		this.mobile = mobile;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginVerificationDTO [email=" + email + ", mobile=" + mobile
				+ ", password=" + password + "]";
	}
	
	
	
}
