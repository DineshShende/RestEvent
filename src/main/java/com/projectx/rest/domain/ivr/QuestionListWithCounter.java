package com.projectx.rest.domain.ivr;

import java.util.List;

public class QuestionListWithCounter {

	
	private Long mobile;
	
	private Integer counter;
	
	private List<QuestionPossibleAnswersSelectedAnswer> questionList;

	public QuestionListWithCounter() {

	}

	public QuestionListWithCounter(Long mobile, Integer counter,
			List<QuestionPossibleAnswersSelectedAnswer> questionList) {

		this.mobile = mobile;
		this.counter = counter;
		this.questionList = questionList;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public List<QuestionPossibleAnswersSelectedAnswer> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(
			List<QuestionPossibleAnswersSelectedAnswer> questionList) {
		this.questionList = questionList;
	}

	@Override
	public String toString() {
		return "QuestionListWithCounter [mobile=" + mobile + ", counter="
				+ counter + ", questionList=" + questionList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((counter == null) ? 0 : counter.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((questionList == null) ? 0 : questionList.hashCode());
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
		QuestionListWithCounter other = (QuestionListWithCounter) obj;
		if (counter == null) {
			if (other.counter != null)
				return false;
		} else if (!counter.equals(other.counter))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (questionList == null) {
			if (other.questionList != null)
				return false;
		} else if (!questionList.equals(other.questionList))
			return false;
		return true;
	}

		
	
}
