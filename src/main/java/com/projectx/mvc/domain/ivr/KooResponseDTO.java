package com.projectx.mvc.domain.ivr;

import javax.validation.constraints.NotNull;

public class KooResponseDTO {
	
	@NotNull
	private String sid;
	
	@NotNull
	private Long mobile;
	
	@NotNull
	private Integer data;

	public KooResponseDTO() {
		super();
	}

	public KooResponseDTO(String sid, Long mobile, Integer data) {
		super();
		this.sid = sid;
		this.mobile = mobile;
		this.data = data;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "KooResponseDTO [sid=" + sid + ", mobile=" + mobile + ", data="
				+ data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
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
		KooResponseDTO other = (KooResponseDTO) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}

		
	

}
