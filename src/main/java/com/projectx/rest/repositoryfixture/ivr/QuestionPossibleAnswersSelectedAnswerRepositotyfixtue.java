package com.projectx.rest.repositoryfixture.ivr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepositoty;

@Component
@Profile("Test")
public class QuestionPossibleAnswersSelectedAnswerRepositotyfixtue implements
		QuestionPossibleAnswersSelectedAnswerRepositoty {

	Map<Long,QuestionPossibleAnswersSelectedAnswer> list;
	
	
	
	public QuestionPossibleAnswersSelectedAnswerRepositotyfixtue() {
		list=new HashMap<Long,QuestionPossibleAnswersSelectedAnswer>();
		
		List<String> possibleAnsList=new ArrayList<String>();
		
		possibleAnsList.add("Yes");
		possibleAnsList.add("No");
		
		list.put(1L, new QuestionPossibleAnswersSelectedAnswer("What to Block?",possibleAnsList,0));
		list.put(2L, new QuestionPossibleAnswersSelectedAnswer("What to Book?",possibleAnsList,0));
	}

	public QuestionPossibleAnswersSelectedAnswerRepositotyfixtue(
			Map<Long, QuestionPossibleAnswersSelectedAnswer> list) {
		super();
		
	}

	@Override
	public QuestionPossibleAnswersSelectedAnswer save(
			QuestionPossibleAnswersSelectedAnswer entity) {

		
		return entity;
	}

	@Override
	public List<QuestionPossibleAnswersSelectedAnswer> getAll() {

		List<QuestionPossibleAnswersSelectedAnswer> returnList=new ArrayList<QuestionPossibleAnswersSelectedAnswer>();
		
		for(Long key:list.keySet())
		{
			returnList.add(list.get(key));
		}
		
		return returnList;
	}

	@Override
	public QuestionPossibleAnswersSelectedAnswer getQuestionById(Long questionId) {

		return list.get(questionId);
	}

}
