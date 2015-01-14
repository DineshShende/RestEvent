package com.projectx.mvc.domain.quickregister;

public class GetByMobileDTO {

	Long mobile;

	public GetByMobileDTO() {
	
	}
	
	public GetByMobileDTO(Long mobile) {
		super();
		this.mobile = mobile;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	
	
}
