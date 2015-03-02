package com.projectx.mvc.domain.completeregister;

import java.util.UUID;

public class MobileMessageDTO {

	private Long mobile;

	private UUID uuid;
	
	private String message;
	
	public MobileMessageDTO() {

	}

	public MobileMessageDTO(Long mobile, UUID uuid, String message) {
		super();
		this.mobile = mobile;
		this.uuid = uuid;
		this.message = message;
	}



	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "MobileMessageDTO [mobile=" + mobile + ", uuid=" + uuid
				+ ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		MobileMessageDTO other = (MobileMessageDTO) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

		
	
	
}
