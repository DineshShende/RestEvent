package com.projectx.rest.repositoryImpl.ivr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.ivr.QuestionPossibleAnswerSelectedAnswerList;
import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepositoty;



@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class QuestionPossibleAnswersSelectedAnswerRepositotyImpl implements
		QuestionPossibleAnswersSelectedAnswerRepositoty {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public QuestionPossibleAnswersSelectedAnswer save(
			QuestionPossibleAnswersSelectedAnswer entity) {
		
		HttpEntity<QuestionPossibleAnswersSelectedAnswer> httpEntity=new HttpEntity<QuestionPossibleAnswersSelectedAnswer>(entity);
		
		ResponseEntity<QuestionPossibleAnswersSelectedAnswer> result=restTemplate.exchange(env.getProperty("data.url")+"/ivr/saveQuestion", 
				HttpMethod.POST, httpEntity, QuestionPossibleAnswersSelectedAnswer.class);
		
		
		return result.getBody();
	}

	@Override
	public List<QuestionPossibleAnswersSelectedAnswer> getAll() {
		
		QuestionPossibleAnswerSelectedAnswerList list=restTemplate.getForObject(env.getProperty("data.url")+"/ivr/getAllQuestions", QuestionPossibleAnswerSelectedAnswerList.class);
		
		
		return list.getList();
	}

	@Override
	public QuestionPossibleAnswersSelectedAnswer getQuestionById(Long questionId) {

		ResponseEntity<QuestionPossibleAnswersSelectedAnswer> result=restTemplate.exchange(env.getProperty("data.url")+"/ivr/getQuestionById/"+questionId, 
				HttpMethod.GET, null, QuestionPossibleAnswersSelectedAnswer.class);
		
		
		return result.getBody();
	}

}
