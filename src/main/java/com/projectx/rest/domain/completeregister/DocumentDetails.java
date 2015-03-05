package com.projectx.rest.domain.completeregister;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.*;

public class DocumentDetails {


	private DocumentKey key;
	
	private byte[] document;
	
	@NotNull
	private String contentType;
	
	@NotNull
	private Integer verificationStatus;
	
	private String verificationRemark;
	
	@NotNull
	private Date insertTime;
	
	@NotNull
	private Date updateTime;
	
	@NotNull
	private String updatedBy;

	public DocumentDetails() {

	}

	public DocumentDetails(DocumentKey key, byte[] document, String contentType,
			Integer verificationStatus, String verificationRemark,
			Date insertTime, Date updateTime, String updatedBy) {
		super();
		this.key = key;
		this.document = document;
		this.contentType = contentType;
		this.verificationStatus = verificationStatus;
		this.verificationRemark = verificationRemark;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}



	public DocumentKey getKey() {
		return key;
	}

	public void setKey(DocumentKey key) {
		this.key = key;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(Integer verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getInsertTime() {
		return insertTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	
	public String getVerificationRemark() {
		return verificationRemark;
	}

	public void setVerificationRemark(String verificationRemark) {
		this.verificationRemark = verificationRemark;
	}

	@Override
	public String toString() {
		return "DocumetDetails [key=" + key + ", document="
				+ Arrays.toString(document) + ", contentType=" + contentType
				+ ", verificationStatus=" + verificationStatus
				+ ", verificationRemark=" + verificationRemark
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", updatedBy=" + updatedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + Arrays.hashCode(document);
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		DocumentDetails other = (DocumentDetails) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (!Arrays.equals(document, other.document))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
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

	
	
	
	
	/*
	private static byte[] writtingImage(String fileLocation) {
	      System.out.println("file lication is"+fileLocation);
	     IOManager manager=new IOManager();
	        try {
	           return manager.getBytesFromFile(fileLocation);
	            
	        } catch (IOException e) {
	        }
	        return null;
	    }
	*/
}
