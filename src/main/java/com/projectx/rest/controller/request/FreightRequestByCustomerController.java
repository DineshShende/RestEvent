package com.projectx.rest.controller.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.rest.domain.ivr.IVRCallInfoDTO;
import com.projectx.rest.domain.ivr.KooKooRequestEntity;
import com.projectx.rest.domain.ivr.QuestionListWithCounter;
import com.projectx.rest.domain.ivr.QuestionPossibleAnswersSelectedAnswer;
import com.projectx.rest.domain.ivr.TrackKookooResponseDTO;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.service.ivr.OutBoundCallService;
import com.projectx.rest.service.ivr.QuestionHandlingService;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;

@RestController
@RequestMapping(value="/request/freightRequestByCustomer")
public class FreightRequestByCustomerController {

	@Autowired
	FreightRequestByCustomerStatusDTO freightRequestByCustomerStatusDTO;
	
	@Autowired
	TrackKookooResponseDTO trackKookooResponseDTO;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	QuestionHandlingService questionHandlingService;
	
	@Autowired
	OutBoundCallService outBoundCallService;
	
	@Value("${FREIGHTALLOCATIONSTATUS_RESPONDED}")
	private String FREIGHTALLOCATIONSTATUS_RESPONDED;
	
	@Value("${FREIGHTALLOCATIONSTATUS_NEW}")
	private String FREIGHTALLOCATIONSTATUS_NEW;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<FreightRequestByCustomer> newRequest(@Valid @RequestBody FreightRequestByCustomer freightRequestByCustomer,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<FreightRequestByCustomer> result=null;
		
		try{
			FreightRequestByCustomer savedEntity=freightRequestByCustomerService.newRequest(freightRequestByCustomer);
			result=new ResponseEntity<FreightRequestByCustomer>(savedEntity, HttpStatus.CREATED);
		}catch(ResourceAlreadyPresentException e)
		{
			result=new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}catch(ValidationFailedException e)
		{
			result=new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		return result;
	}
	
	@RequestMapping(value="/getById/{requestId}")
	public ResponseEntity<FreightRequestByCustomer> getRequestById(@PathVariable Long requestId){
		
		try{
			FreightRequestByCustomer savedEntity=freightRequestByCustomerService.getRequestById(requestId);
			
			return new ResponseEntity<FreightRequestByCustomer>(savedEntity, HttpStatus.FOUND);
		}catch(ResourceNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/getByCustomerId/{customerId}")
	public ResponseEntity<List<FreightRequestByCustomer>> getAllRequestForCustomer(@PathVariable Long customerId)
	{
		List<FreightRequestByCustomer> savedEntity=freightRequestByCustomerService.getAllRequestForCustomer(customerId);
		
		return new ResponseEntity<List<FreightRequestByCustomer>>(savedEntity, HttpStatus.OK);
		
	}

	
	@RequestMapping(value="/getMatchingCustomerReqForVendorReq",method=RequestMethod.POST)
	public void getMatchingCustomerReqForVendorReq(@Valid @RequestBody FreightRequestByVendor freightRequestByVendor,
			BindingResult bindingResult)
	{
		
		List<FreightRequestByCustomer> list=freightRequestByCustomerService.getMatchingCustReqForVendorReq(freightRequestByVendor,FREIGHTALLOCATIONSTATUS_RESPONDED);
		
		if(list.size()!=0)
		{
		
			list.forEach(e->{
				
				QuestionListWithCounter questionListWithCounter=null;
							
				if(freightRequestByCustomerStatusDTO.contains(e.getCustomerId()))
				{
					questionListWithCounter=freightRequestByCustomerStatusDTO.getQuestionList(e.getCustomerId());
					
				}
				else
				{
					List<QuestionPossibleAnswersSelectedAnswer> questionList=questionHandlingService.getAll();
				
					if(questionList==null || questionList.size()==0)
						throw new RuntimeException();
					
				
					CustomerDetails customerDetails=customerDetailsService.findById(e.getCustomerId());
					 
					questionListWithCounter=new QuestionListWithCounter(customerDetails.getMobile(), 0, questionList);
					 
					freightRequestByCustomerStatusDTO.add(e.getRequestId(), questionListWithCounter);
					 
					
				}
					
				IVRCallInfoDTO ivrCallInfoDTO=new IVRCallInfoDTO(questionListWithCounter.getMobile(), questionListWithCounter.getQuestionList().get(questionListWithCounter.getCounter()));	 
				 
				String sid=outBoundCallService.makeOutBoundCall(ivrCallInfoDTO);
					 
				trackKookooResponseDTO.add(sid, new KooKooRequestEntity(e.getRequestId(), freightRequestByVendor.getRequestId()));
				
						
			});
		
		}else
		{
			
			list=freightRequestByCustomerService.getMatchingCustReqForVendorReq(freightRequestByVendor,FREIGHTALLOCATIONSTATUS_NEW);
			
			List<QuestionPossibleAnswersSelectedAnswer> questionList=questionHandlingService.getAll();
			
			if(questionList==null || questionList.size()==0)
				throw new RuntimeException();
			
			list.parallelStream().forEach(e->{
				
				 CustomerDetails customerDetails=customerDetailsService.findById(e.getCustomerId());
				 
				 QuestionListWithCounter questionListWithCounter=new QuestionListWithCounter(customerDetails.getMobile(), 0, questionList);
				 
				 freightRequestByCustomerStatusDTO.add(e.getRequestId(), questionListWithCounter);
				 
				 IVRCallInfoDTO ivrCallInfoDTO=new IVRCallInfoDTO(customerDetails.getMobile(), questionListWithCounter.getQuestionList().get(questionListWithCounter.getCounter()));	 
				 
				 String sid=outBoundCallService.makeOutBoundCall(ivrCallInfoDTO);
				 
				 trackKookooResponseDTO.add(sid, new KooKooRequestEntity(e.getRequestId(), freightRequestByVendor.getRequestId()));
				 
				 
			});
		}
	}

	
	@RequestMapping(value="/deleteById/{requestId}")
	public ResponseEntity<Boolean> deleteRequestById(@PathVariable Long requestId)
	{
		Boolean result=freightRequestByCustomerService.deleteRequestById(requestId);
		
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/clearTestData")
	public Boolean clearTestData(){
		
		Boolean result=freightRequestByCustomerService.clearTestData();
		
		return result;
	}
	
	@RequestMapping(value="/count")
	public Integer count(){
		
		Integer result=freightRequestByCustomerService.count();
		
		return result;
	}

	/*
	@RequestMapping(value="/getMatchingCustomerReqForVendorReq",method=RequestMethod.POST)
	public ResponseEntity<List<FreightRequestByCustomer>> getMatchingCustomerReqForVendorReq(@Valid @RequestBody FreightRequestByVendor freightRequestByVendor,
			BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		List<FreightRequestByCustomer> savedEntity=freightRequestByCustomerService.getMatchingCustReqForVendorReq(freightRequestByVendor);
		
		return new ResponseEntity<List<FreightRequestByCustomer>>(savedEntity, HttpStatus.OK);
		
	}
	*/

	
	/*
	@RequestMapping(value="/addtrackresponseentity")
	public void addtrackresponseentity()
	{
		 trackKookooResponseDTO.add(UUID.randomUUID().toString(), new KooKooRequestEntity(123L, 123L));
	}
	
	@RequestMapping(value="/addfreightRequestByCustomerStatusDTO")
	public void addfreightRequestByCustomerStatusDTO()
	{
		List<QuestionPossibleAnswersSelectedAnswer> questionList=questionHandlingService.getAll();
		
		 QuestionListWithCounter questionListWithCounter=new QuestionListWithCounter(9960821869L, 0, questionList);
		 
		 freightRequestByCustomerStatusDTO.add((long)(Math.random()*123456), questionListWithCounter);
	
	}
	*/
}
