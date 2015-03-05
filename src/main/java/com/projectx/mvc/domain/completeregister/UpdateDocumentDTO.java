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
	private String requestedBy;

	public UpdateDocumentDTO() {

	}

	public UpdateDocumentDTO(DocumentKey key, byte[] document,
			String contentType, String requestedBy) {
		super();
		this.key = key;
		this.document = document;
		this.contentType = contentType;
		this.requestedBy = requestedBy;
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

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	@Override
	public String toString() {
		return "UpdateDocumentDTO [key=" + key + ", document="
				+ Arrays.toString(document) + ", contentType=" + contentType
				+ ", requestedBy=" + requestedBy + "]";
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
		return true;
	}

		
	
}
