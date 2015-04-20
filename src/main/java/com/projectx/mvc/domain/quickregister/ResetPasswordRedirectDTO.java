package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class ResetPasswordRedirectDTO {
	
	@NotNull
	private String entity;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public ResetPasswordRedirectDTO() {
	
	}

	public ResetPasswordRedirectDTO(String entity, Integer requestedBy,Long requestedById) {
		super();
		this.entity = entity;
		this.requestedBy = requestedBy;
		this.requestedById=requestedById;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
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
		return "ResetPasswordRedirectDTO [entity=" + entity + ", requestedBy="
				+ requestedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entity == null) ? 0 : entity.hashCode());
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
		ResetPasswordRedirectDTO other = (ResetPasswordRedirectDTO) obj;
		if (entity == null) {
			if (other.entity != null)
				return false;
		} else if (!entity.equals(other.entity))
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
