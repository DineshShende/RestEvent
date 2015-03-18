package com.projectx.rest.domain.ivr;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;

public class PreBookEntity {
	
	private Long preBookId;
	
	private Long freightRequestByCustomerId;
	
	private Long freightRequestByvendorId;
	
	private Date insertTime;
	
	private Date updateTime;
	
	private String updatedBy;

	
	
	
	public PreBookEntity() {

	}



	public PreBookEntity(Long freightRequestByCustomerId,
			Long freightRequestByvendorId, Date insertTime, Date updateTime,
			String updatedBy) {
		super();
		
		this.freightRequestByCustomerId = freightRequestByCustomerId;
		this.freightRequestByvendorId = freightRequestByvendorId;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}



	public Long getFreightRequestByCustomerId() {
		return freightRequestByCustomerId;
	}


	public void setFreightRequestByCustomerId(Long freightRequestByCustomerId) {
		this.freightRequestByCustomerId = freightRequestByCustomerId;
	}


	public Long getFreightRequestByvendorId() {
		return freightRequestByvendorId;
	}


	public void setFreightRequestByvendorId(Long freightRequestByvendorId) {
		this.freightRequestByvendorId = freightRequestByvendorId;
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

	
	

	public Long getPreBookId() {
		return preBookId;
	}



	public void setPreBookId(Long preBookId) {
		this.preBookId = preBookId;
	}



	


	@Override
	public String toString() {
		return "PreBookEntity [preBookId=" + preBookId
				+ ", freightRequestByCustomerId=" + freightRequestByCustomerId
				+ ", freightRequestByvendorId=" + freightRequestByvendorId
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", updatedBy=" + updatedBy + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((freightRequestByCustomerId == null) ? 0
						: freightRequestByCustomerId.hashCode());
		result = prime
				* result
				+ ((freightRequestByvendorId == null) ? 0
						: freightRequestByvendorId.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		PreBookEntity other = (PreBookEntity) obj;
		if (freightRequestByCustomerId == null) {
			if (other.freightRequestByCustomerId != null)
				return false;
		} else if (!freightRequestByCustomerId
				.equals(other.freightRequestByCustomerId))
			return false;
		if (freightRequestByvendorId == null) {
			if (other.freightRequestByvendorId != null)
				return false;
		} else if (!freightRequestByvendorId
				.equals(other.freightRequestByvendorId))
			return false;/*
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;*/
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}
	
	
	
	
	

}
