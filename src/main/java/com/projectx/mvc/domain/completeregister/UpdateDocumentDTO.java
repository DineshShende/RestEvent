package com.projectx.mvc.domain.completeregister;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import com.projectx.rest.domain.completeregister.DocumentKey;

public class UpdateDocumentDTO {

	private DocumentKey key;
	
	@NotNull
	private byte [] document;
	
	@NotNull
	private String contentType;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public UpdateDocumentDTO() {

	}

	public UpdateDocumentDTO(DocumentKey key, byte[] document,
			String contentType, Integer requestedBy,Long requestedById) {
		super();
		this.key = key;
		this.document = document;
		this.contentType = contentType;
		this.requestedBy = requestedBy;
		this.requestedById=requestedById;
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

	public Integer getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Long getRequestedById() {
		return requestedById;
	}

	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}

	@Override
	public String toString() {
		return "UpdateDocumentDTO [key=" + key + ", document="
				+ Arrays.toString(document) + ", contentType=" + contentType
				+ ", requestedBy=" + requestedBy + ", requestedById="
				+ requestedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + Arrays.hashCode(document);
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
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
		UpdateDocumentDTO other = (UpdateDocumentDTO) obj;
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
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		return true;
	}

	
}
