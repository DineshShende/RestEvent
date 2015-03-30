package com.projectx.rest.domain.ivr;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="singleton", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class FreightRequestByCustomerStatusDTO {

	private ConcurrentMap<Long , QuestionListWithCounter> questionStatus;

	
	public FreightRequestByCustomerStatusDTO() {
		this.questionStatus = new ConcurrentHashMap<Long, QuestionListWithCounter>();
	}

	public FreightRequestByCustomerStatusDTO(
			ConcurrentMap<Long, QuestionListWithCounter> questionStatus) {
		super();
		this.questionStatus = new ConcurrentHashMap<Long, QuestionListWithCounter>();
	}

	public ConcurrentMap<Long, QuestionListWithCounter> getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(
			ConcurrentMap<Long, QuestionListWithCounter> questionStatus) {
		this.questionStatus = questionStatus;
	}
	
	
	public void add(Long freightRequestByCustomerId,QuestionListWithCounter questionListWithCounter)
	{
		this.questionStatus.put(freightRequestByCustomerId, questionListWithCounter);
	}
	
	public void delete(Long freightRequestByCustomerId)
	{
		this.questionStatus.remove(freightRequestByCustomerId);
		
	}
	
	public Boolean contains(Long freightRequestByCustomerId)
	{
		return this.questionStatus.containsKey(freightRequestByCustomerId);
	}
	
	public QuestionListWithCounter getQuestionList(Long freightRequestByCustomerId)
	{
		return this.questionStatus.get(freightRequestByCustomerId);
		
	}
	
	public void update(Long freightRequestByCustomerId,QuestionListWithCounter questionListWithCounter)
	{
		this.questionStatus.replace(freightRequestByCustomerId, questionListWithCounter);
	}

	@Override
	public String toString() {
		return "MobileQuestionSessionDTO [questionStatus=" + questionStatus
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((questionStatus == null) ? 0 : questionStatus.hashCode());
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
		FreightRequestByCustomerStatusDTO other = (FreightRequestByCustomerStatusDTO) obj;
		if (questionStatus == null) {
			if (other.questionStatus != null)
				return false;
		} else if (!questionStatus.equals(other.questionStatus))
			return false;
		return true;
	} 
	
	
}
