package com.projectx.rest.service.ivr;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;

@Service
public interface QuestionHandlingService {
	
	QuestionPossibleAnswersSelectedAnswer save(QuestionPossibleAnswersSelectedAnswer entity);
	
	List<QuestionPossibleAnswersSelectedAnswer> getAll();
	
	QuestionPossibleAnswersSelectedAnswer getQuestionById(Long questionId);


}
