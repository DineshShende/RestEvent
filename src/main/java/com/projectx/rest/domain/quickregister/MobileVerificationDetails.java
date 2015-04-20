package com.projectx.rest.domain.quickregister;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;

public class MobileVerificationDetails {

	private MobileVerificationDetailsKey key;
	
	private Long  mobile;
		
	private Integer mobilePin;
	
	private Integer mobileVerificationAttempts;
	
	private Integer resendCount;
	
	private Date insertTime;
	
	private Date UpdateTime;
	
	private Integer updatedBy;
	
	private Integer insertedBy;
	
	private Long updatedById;
	
	private Long insertedById;
	

	public MobileVerificationDetails() {

	}

	
	
	public MobileVerificationDetails(MobileVerificationDetailsKey key,
			Long mobile, Integer mobilePin,
			Integer mobileVerificationAttempts, Integer resendCount,
			Date insertTime, Date updateTime, 
			Integer updatedBy,Integer insertedBy,Long updatedById,Long insertedById) {
		super();
		this.key = key;
		this.mobile = mobile;
		this.mobilePin = mobilePin;
		this.mobileVerificationAttempts = mobileVerificationAttempts;
		this.resendCount = resendCount;
		this.insertTime = insertTime;
		this.UpdateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
	}



	public MobileVerificationDetailsKey getKey() {
		return key;
	}

	public void setKey(MobileVerificationDetailsKey key) {
		this.key = key;
	}

	public Long getMobile() {
		return mobile;
	}



	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}



	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}

	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}

	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
	}

	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
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
		return UpdateTime;
	}


	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}


	
	
	public Integer getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}



	public Integer getInsertedBy() {
		return insertedBy;
	}



	public void setInsertedBy(Integer insertedBy) {
		this.insertedBy = insertedBy;
	}



	public Long getUpdatedById() {
		return updatedById;
	}



	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}



	public Long getInsertedById() {
		return insertedById;
	}



	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}



	@Override
	public String toString() {
		return "MobileVerificationDetails [key=" + key + ", mobile=" + mobile
				+ ", mobilePin=" + mobilePin + ", mobileVerificationAttempts="
				+ mobileVerificationAttempts + ", resendCount=" + resendCount
				+ ", insertTime=" + insertTime + ", UpdateTime=" + UpdateTime
				+ ", updatedBy=" + updatedBy + ", insertedBy=" + insertedBy
				+ ", updatedById=" + updatedById + ", insertedById="
				+ insertedById + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((UpdateTime == null) ? 0 : UpdateTime.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		MobileVerificationDetails other = (MobileVerificationDetails) obj;
		if (UpdateTime == null) {
			if (other.UpdateTime != null)
				return false;
		}
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		}
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} else if (!insertedBy.equals(other.insertedBy))
			return false;
		if (insertedById == null) {
			if (other.insertedById != null)
				return false;
		} else if (!insertedById.equals(other.insertedById))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (mobileVerificationAttempts == null) {
			if (other.mobileVerificationAttempts != null)
				return false;
		} else if (!mobileVerificationAttempts
				.equals(other.mobileVerificationAttempts))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} else if (!updatedById.equals(other.updatedById))
			return false;
		return true;
	}



		
}
