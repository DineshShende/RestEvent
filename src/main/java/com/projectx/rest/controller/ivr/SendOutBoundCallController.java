package com.projectx.rest.controller.ivr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;



import com.projectx.mvc.domain.ivr.KooResponseDTO;
import com.projectx.rest.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.rest.domain.ivr.IVRCallInfoDTO;
import com.projectx.rest.repository.ivr.QuestionPossibleAnswersSelectedAnswerRepositoty;

@RestController
@RequestMapping(value="/outboundcall")
@PropertySource(value="classpath:/application.properties")
public class SendOutBoundCallController {

	@Autowired
	FreightRequestByCustomerStatusDTO freightRequestByCustomerStatusDTO;
	
	@Autowired
	QuestionPossibleAnswersSelectedAnswerRepositoty questionPossibleAnswersSelectedAnswerRepositoty;
	
	@Autowired
	AsyncRestTemplate asyncRestTemplate; 
	
	@Autowired
	Environment env;
	
	@RequestMapping(value="/makecall",method=RequestMethod.POST)
	public String outBoundCall(@RequestBody IVRCallInfoDTO ivrCallInfoDTO)
	{
	
		StringBuilder stringBuilder=new StringBuilder();
		
		String randomNumber=UUID.randomUUID().toString();
		
		stringBuilder.append("<response><status>queued</status><message>");
		stringBuilder.append(randomNumber);
		stringBuilder.append("</message></response>");
		
			
		Thread t1=new Thread(()->
		{
			
			try {
				Thread.sleep((long)(Math.random()*60000));
			} catch (Exception e) {
				
			}
			
			asyncRestTemplate.exchange(env.getProperty("rest.url")+"/outboundcall/receiveResponse", HttpMethod.POST,
					new HttpEntity<KooResponseDTO>(new KooResponseDTO(randomNumber, ivrCallInfoDTO.getMobile(), (int)(Math.random()*2))), Boolean.class);
		}
		);
		
		t1.start();
		
		return stringBuilder.toString();
		
	}
	
	
	
}
