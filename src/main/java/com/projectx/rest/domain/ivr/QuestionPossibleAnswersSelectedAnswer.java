package com.projectx.rest.domain.ivr;

import java.util.List;




public class QuestionPossibleAnswersSelectedAnswer {
	
	private Long questionId;
	
	private String question;
	
	private List<String> possibleAnswer;
	
	private Integer selectedAnswer;
	
	public QuestionPossibleAnswersSelectedAnswer() {

	}

	public QuestionPossibleAnswersSelectedAnswer(
			String question, List<String> possibleAnswer, Integer selectedAnswer) {
		super();
		
		this.question = question;
		this.possibleAnswer = possibleAnswer;
		this.selectedAnswer = selectedAnswer;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getPossibleAnswer() {
		return possibleAnswer;
	}

	public void setPossibleAnswer(List<String> possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}

	public Integer getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(Integer selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

	@Override
	public String toString() {
		return "QuestionPossibleAnswersSelectedAnswer [questionId="
				+ questionId + ", question=" + question + ", possibleAnswer="
				+ possibleAnswer + ", selectedAnswer=" + selectedAnswer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((possibleAnswer == null) ? 0 : possibleAnswer.hashCode());
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result
				+ ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result
				+ ((selectedAnswer == null) ? 0 : selectedAnswer.hashCode());
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
		QuestionPossibleAnswersSelectedAnswer other = (QuestionPossibleAnswersSelectedAnswer) obj;
		if (possibleAnswer == null) {
			if (other.possibleAnswer != null)
				return false;
		} else if (!possibleAnswer.equals(other.possibleAnswer))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		/*if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;*/
		if (selectedAnswer == null) {
			if (other.selectedAnswer != null)
				return false;
		} else if (!selectedAnswer.equals(other.selectedAnswer))
			return false;
		return true;
	}

			

}
