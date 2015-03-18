package com.projectx.rest.repository.ivr;

import java.util.List;

import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;






public interface QuestionPossibleAnswersSelectedAnswerRepositoty {

	QuestionPossibleAnswersSelectedAnswer save(QuestionPossibleAnswersSelectedAnswer entity);
	
	List<QuestionPossibleAnswersSelectedAnswer> getAll();
	
	QuestionPossibleAnswersSelectedAnswer getQuestionById(Long questionId);
	
}
