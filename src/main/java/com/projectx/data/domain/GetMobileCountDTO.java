package com.projectx.data.domain;

public class GetMobileCountDTO {
	
	private Long mobile;

	public GetMobileCountDTO() {
	
	}
	
	
	public GetMobileCountDTO(Long mobile) {
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
