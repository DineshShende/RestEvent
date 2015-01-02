package com.projectx.mvc.domain.completeregister;

import com.projectx.rest.domain.completeregister.DocumentKey;

public class UpdateDocumentVerificationStatusAndRemarkDTO {

	private DocumentKey key;
	
	private Integer verificationStatus;
	
	private String verificationRemark;

	public UpdateDocumentVerificationStatusAndRemarkDTO() {

	}

	public UpdateDocumentVerificationStatusAndRemarkDTO(DocumentKey key,
			Integer verificationStatus, String verificationRemark) {

		this.key = key;
		this.verificationStatus = verificationStatus;
		this.verificationRemark = verificationRemark;
	}

	
	
	public DocumentKey getKey() {
		return key;
	}

	public void setKey(DocumentKey key) {
		this.key = key;
	}

	public Integer getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(Integer verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getVerificationRemark() {
		return verificationRemark;
	}

	public void setVerificationRemark(String verificationRemark) {
		this.verificationRemark = verificationRemark;
	}

	@Override
	public String toString() {
		return "UpdateDocumentVerificationStatusAndRemarkDTO [key=" + key
				+ ", verificationStatus=" + verificationStatus
				+ ", verificationRemark=" + verificationRemark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime
				* result
				+ ((verificationRemark == null) ? 0 : verificationRemark
						.hashCode());
		result = prime
				* result
				+ ((verificationStatus == null) ? 0 : verificationStatus
						.hashCode());
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
		UpdateDocumentVerificationStatusAndRemarkDTO other = (UpdateDocumentVerificationStatusAndRemarkDTO) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (verificationRemark == null) {
			if (other.verificationRemark != null)
				return false;
		} else if (!verificationRemark.equals(other.verificationRemark))
			return false;
		if (verificationStatus == null) {
			if (other.verificationStatus != null)
				return false;
		} else if (!verificationStatus.equals(other.verificationStatus))
			return false;
		return true;
	}
	
	
	
}
