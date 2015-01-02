package com.projectx.mvc.domain.completeregister;

import java.util.Arrays;

import com.projectx.rest.domain.completeregister.DocumentKey;

public class UpdateDocumentDTO {

	private DocumentKey key;
	
	private byte [] document;
	
	private String contentType;

	public UpdateDocumentDTO() {

	}

	public UpdateDocumentDTO(DocumentKey key, byte[] document,
			String contentType) {
		this.key = key;
		this.document = document;
		this.contentType = contentType;
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

	@Override
	public String toString() {
		return "UpdateDocumentDTO [key=" + key + ", document="
				+ Arrays.toString(document) + ", contentType=" + contentType
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + Arrays.hashCode(document);
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		return true;
	}
	

	
	
}
