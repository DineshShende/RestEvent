package com.projectx.rest.handlers.ivr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepositoty;
import com.projectx.rest.service.ivr.QuestionHandlingService;

@Component
public class QuestionHandler implements QuestionHandlingService {

	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepositoty questionPossibleAnswersSelectedAnswerRepositoty;
	
	@Override
	public QuestionPossibleAnswersSelectedAnswer save(
			QuestionPossibleAnswersSelectedAnswer entity) {

		return questionPossibleAnswersSelectedAnswerRepositoty.save(entity);
	}

	@Override
	public List<QuestionPossibleAnswersSelectedAnswer> getAll() {

		return questionPossibleAnswersSelectedAnswerRepositoty.getAll();
	}

	@Override
	public QuestionPossibleAnswersSelectedAnswer getQuestionById(Long questionId) {

		return questionPossibleAnswersSelectedAnswerRepositoty.getQuestionById(questionId);
	}

}
