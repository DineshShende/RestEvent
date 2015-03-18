package com.projectx.rest.services.ivr;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
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
import com.projectx.rest.domain.ivr.IVRCallInfoDTO;
import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.service.ivr.OutBoundCallService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class OutBoundCallResponseServiceTest {

	@Autowired
	OutBoundCallService outBoundCallService;
	
	@Test
	public void environmentTest() {
		
	}

	@Test
	public void responseParse()
	{
		List<String> possibleAns=new ArrayList<String>();
		
		possibleAns.add("A");
		possibleAns.add("B");
		
		String sid=outBoundCallService.makeOutBoundCall(new IVRCallInfoDTO(9960821869L, 
				
				new QuestionPossibleAnswersSelectedAnswer("How R U", possibleAns, 0)));
		
		System.out.println(sid);
		
		assertNotNull(sid);
	}
	
}
