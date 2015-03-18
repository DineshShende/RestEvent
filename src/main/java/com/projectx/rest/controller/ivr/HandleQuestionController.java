package com.projectx.rest.controller.ivr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.rest.domain.ivr.QuestionListWithCounter;
import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepositoty;



@RestController
@RequestMapping(value="/ivr")
public class HandleQuestionController {

	@Autowired
	FreightRequestByCustomerStatusDTO mobileQuestionSessionDTO;
	
	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepositoty questionPossibleAnswersSelectedAnswerRepositoty;
	
	@RequestMapping(value="/initiateForMobile/{mobile}",method=RequestMethod.GET)
	public void initiateIVRCall(@PathVariable Long mobile)
	{
		List<QuestionPossibleAnswersSelectedAnswer> questionList=questionPossibleAnswersSelectedAnswerRepositoty.getAll();
		
		//QuestionListWithCounter questionListWithCounter=new QuestionListWithCounter(0, questionList);
		
		//mobileQuestionSessionDTO.add(mobile, questionListWithCounter);
		
		askQuestionInSequence(mobile);
		
		System.out.println("After Complete Process"+mobileQuestionSessionDTO.getQuestionList(mobile));
			
	}
	
	public void askQuestionInSequence(Long mobile)
	{
		QuestionListWithCounter listWithCounter=mobileQuestionSessionDTO.getQuestionList(mobile);
		
		if(listWithCounter.getQuestionList().size()>listWithCounter.getCounter())
		{
			String response=askQuestionWithReplyResponse(listWithCounter.getQuestionList().get(listWithCounter.getCounter()));
			
			//listWithCounter.getQuestionList().get(listWithCounter.getCounter()).setSelectedAnswer(response);
			
			Integer counterIncrement=listWithCounter.getCounter()+1;
			
			listWithCounter.setCounter(counterIncrement);
			
			mobileQuestionSessionDTO.update(mobile, listWithCounter);
			
			askQuestionInSequence(mobile);
			
		}
		
	}
	
	public String askQuestionWithReplyResponse(QuestionPossibleAnswersSelectedAnswer question)
	{
		System.out.println("Asked Question"+question);
		
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return question.getPossibleAnswer().get((int)(Math.random()*question.getPossibleAnswer().size()));
	}
}
