package com.projectx.rest.handlers.ivr;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.domain.ivr.IVRCallInfoDTO;
import com.projectx.rest.service.ivr.OutBoundCallService;

@Component
@PropertySource(value="classpath:/application.properties")
@Profile(value="Prod")
public class OutBoundCallHandler implements OutBoundCallService {

	@Autowired
	RestTemplate restTemplate;  
	
	@Autowired
	Environment env;
	
	@Override
	public String makeOutBoundCall(IVRCallInfoDTO ivrCallInfoDTO)  {

		String url=(env.getProperty("KOO_URL")+"?phone_no=0"+ivrCallInfoDTO.getMobile()+"&api_key="+env.getProperty("KOO_API_KEY")+""
				+ "&outbound_version=2&extra_data=<response><playtext>"+ivrCallInfoDTO.getPossibleAnswersSelectedAnswer().getQuestion()+""
				+ "</playtext></response>&url="+env.getProperty("KOO_ACCESSIBLE_URL")+"/koocall");
		
		String result=restTemplate.getForObject(url, String.class);
		
		String status=null;
		String sid=null;
				
		org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
		try {
		    org.jdom.Document doc = saxBuilder.build(new StringReader(result));
		    Element element=doc.getRootElement();
		    
		    
		    status=element.getChild("status").getText();
		    sid=element.getChild("message").getText();
		    
		    String message = doc.getRootElement().getText();
		    System.out.println(message);
		} catch (JDOMException e) {
		    
		} catch (IOException e) {
		    
		}
	    
		
		if(status.equals("queued"))
			return sid;
		else
			throw new RuntimeException();


	}

	
	
}
