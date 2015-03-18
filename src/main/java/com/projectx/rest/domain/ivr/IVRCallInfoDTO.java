package com.projectx.rest.domain.ivr;

public class IVRCallInfoDTO {
	
	private Long mobile;
	
	private QuestionPossibleAnswersSelectedAnswer possibleAnswersSelectedAnswer;

	public IVRCallInfoDTO() {

	}

	public IVRCallInfoDTO(Long mobile,
			QuestionPossibleAnswersSelectedAnswer possibleAnswersSelectedAnswer) {
		super();
		this.mobile = mobile;
		this.possibleAnswersSelectedAnswer = possibleAnswersSelectedAnswer;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public QuestionPossibleAnswersSelectedAnswer getPossibleAnswersSelectedAnswer() {
		return possibleAnswersSelectedAnswer;
	}

	public void setPossibleAnswersSelectedAnswer(
			QuestionPossibleAnswersSelectedAnswer possibleAnswersSelectedAnswer) {
		this.possibleAnswersSelectedAnswer = possibleAnswersSelectedAnswer;
	}

	@Override
	public String toString() {
		return "IVRCallInfoDTO [mobile=" + mobile
				+ ", possibleAnswersSelectedAnswer="
				+ possibleAnswersSelectedAnswer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime
				* result
				+ ((possibleAnswersSelectedAnswer == null) ? 0
						: possibleAnswersSelectedAnswer.hashCode());
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
		IVRCallInfoDTO other = (IVRCallInfoDTO) obj;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (possibleAnswersSelectedAnswer == null) {
			if (other.possibleAnswersSelectedAnswer != null)
				return false;
		} else if (!possibleAnswersSelectedAnswer
				.equals(other.possibleAnswersSelectedAnswer))
			return false;
		return true;
	}

		

}
