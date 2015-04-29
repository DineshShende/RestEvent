package com.projectx.rest.servicefixture.ivr;

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
@Profile(value={"Dev","Test"})
public class OutBoundCallServiceFixture implements OutBoundCallService {

	@Autowired
	RestTemplate restTemplate;  
	
	@Autowired
	Environment env;
	
	@Override
	public String makeOutBoundCall(IVRCallInfoDTO ivrCallInfoDTO)  {

		String result=restTemplate.postForObject(env.getProperty("rest.url")+"/outboundcall/makecall",ivrCallInfoDTO ,String.class);
		
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