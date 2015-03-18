package com.projectx.rest.repository.ivr;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;





@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")
public class QuestionPossibleAnswersSelectedAnswerRepositoryTest {

	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepositoty questionPossibleAnswersSelectedAnswerRepository; 
	
	@Test
	public void save()
	{
		List<String> possibileAnswer=new ArrayList<String>();
		
		possibileAnswer.add("Sleeping");
		possibileAnswer.add("Working");
		possibileAnswer.add("Driving");
		possibileAnswer.add("Playing");
		
		QuestionPossibleAnswersSelectedAnswer questionPossibleAnswersSelectedAnswer=
				new QuestionPossibleAnswersSelectedAnswer("Hi What R U?", possibileAnswer, 1);
		
		
		QuestionPossibleAnswersSelectedAnswer savedEntity=questionPossibleAnswersSelectedAnswerRepository
				.save(questionPossibleAnswersSelectedAnswer);
		
		
		QuestionPossibleAnswersSelectedAnswer fetchedEntity=questionPossibleAnswersSelectedAnswerRepository
				.getQuestionById(savedEntity.getQuestionId());
		
		System.out.println(fetchedEntity);
		
		assertEquals(fetchedEntity, questionPossibleAnswersSelectedAnswerRepository.getAll().get(0));
		
	}


}
