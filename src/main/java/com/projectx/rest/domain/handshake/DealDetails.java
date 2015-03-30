package com.projectx.rest.domain.handshake;

import java.util.Date;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;


public class DealDetails {

	private Long dealId;
	
	@NotNull
	private Long freightRequestByCustomerId;
	
	@NotNull
	private Long freightRequestByVendorId;
	
	private String deductionMode;
	
	private Integer amount;
	
	@NotNull
	private String insertedBy;
	
	@NotNull
	private Date insertTime;
	
	@NotNull
	private String updatedBy;
	
	@NotNull
	private Date updateTime;

	public DealDetails() {

	}

	public DealDetails(Long freightRequestByCustomerId,
			Long freightRequestByVendorId, String deductionMode,
			Integer amount, String insertedBy, Date insertTime,
			String updatedBy, Date updateTime) {
		super();
		
		this.freightRequestByCustomerId = freightRequestByCustomerId;
		this.freightRequestByVendorId = freightRequestByVendorId;
		this.deductionMode = deductionMode;
		this.amount = amount;
		this.insertedBy = insertedBy;
		this.insertTime = insertTime;
		this.updatedBy = updatedBy;
		this.updateTime = updateTime;
	}

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public Long getFreightRequestByCustomerId() {
		return freightRequestByCustomerId;
	}

	public void setFreightRequestByCustomerId(Long freightRequestByCustomerId) {
		this.freightRequestByCustomerId = freightRequestByCustomerId;
	}

	public Long getFreightRequestByVendorId() {
		return freightRequestByVendorId;
	}

	public void setFreightRequestByVendorId(Long freightRequestByVendorId) {
		this.freightRequestByVendorId = freightRequestByVendorId;
	}

	public String getDeductionMode() {
		return deductionMode;
	}

	public void setDeductionMode(String deductionMode) {
		this.deductionMode = deductionMode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getInsertTime() {
		return insertTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "DealDetails [dealId=" + dealId
				+ ", freightRequestByCustomerId=" + freightRequestByCustomerId
				+ ", freightRequestByVendorId=" + freightRequestByVendorId
				+ ", deductionMode=" + deductionMode + ", amount=" + amount
				+ ", insertedBy=" + insertedBy + ", insertTime=" + insertTime
				+ ", updatedBy=" + updatedBy + ", updateTime=" + updateTime
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((dealId == null) ? 0 : dealId.hashCode());
		result = prime * result
				+ ((deductionMode == null) ? 0 : deductionMode.hashCode());
		result = prime
				* result
				+ ((freightRequestByCustomerId == null) ? 0
						: freightRequestByCustomerId.hashCode());
		result = prime
				* result
				+ ((freightRequestByVendorId == null) ? 0
						: freightRequestByVendorId.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
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
		DealDetails other = (DealDetails) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (dealId == null) {
			if (other.dealId != null)
				return false;
		} else if (!dealId.equals(other.dealId))
			return false;
		if (deductionMode == null) {
			if (other.deductionMode != null)
				return false;
		} else if (!deductionMode.equals(other.deductionMode))
			return false;
		if (freightRequestByCustomerId == null) {
			if (other.freightRequestByCustomerId != null)
				return false;
		} else if (!freightRequestByCustomerId
				.equals(other.freightRequestByCustomerId))
			return false;
		if (freightRequestByVendorId == null) {
			if (other.freightRequestByVendorId != null)
				return false;
		} else if (!freightRequestByVendorId
				.equals(other.freightRequestByVendorId))
			return false;
		/*if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;*/
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} else if (!insertedBy.equals(other.insertedBy))
			return false;
		/*if (updateTime == null) {
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
