package com.projectx.rest.domain.ivr;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class QuestionPossibleAnswerSelectedAnswerList {
	
	List<QuestionPossibleAnswersSelectedAnswer> list;

	public QuestionPossibleAnswerSelectedAnswerList() {

	}

	@JsonCreator
	public QuestionPossibleAnswerSelectedAnswerList(
			List<QuestionPossibleAnswersSelectedAnswer> list) {

		this.list = list;
	}

	public List<QuestionPossibleAnswersSelectedAnswer> getList() {
		return list;
	}

	public void setList(List<QuestionPossibleAnswersSelectedAnswer> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "QuestionPossibleAnswerSelectedAnswerList []";
	}
	
	

}
