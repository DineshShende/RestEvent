package com.projectx.rest.handlers.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.projectx.rest.domain.async.RetriggerDTO;
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
import com.projectx.rest.repository.request.FreightRequestByCustomerRepository;
import com.projectx.rest.service.ivr.OutBoundCallService;
import com.projectx.rest.service.ivr.QuestionHandlingService;
import com.projectx.rest.services.async.RetriggerService;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;

@Component
public class FreightRequestByCustomerHandler implements
		FreightRequestByCustomerService {

	@Autowired
	FreightRequestByCustomerRepository freightRequestByCustomerRepository;

	@Autowired
	FreightRequestByCustomerStatusDTO freightRequestByCustomerStatusDTO;
	
	@Autowired
	TrackKookooResponseDTO trackKookooResponseDTO;
	
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
	
	
	@Autowired
	RetriggerService retriggerService; 
	
	@Autowired
	Gson gson;
	
	@Override
	public FreightRequestByCustomer newRequest(
			FreightRequestByCustomer freightRequestByCustomer) throws ResourceAlreadyPresentException,ValidationFailedException{
		
		return freightRequestByCustomerRepository.save(freightRequestByCustomer);
	}

	@Override
	public FreightRequestByCustomer getRequestById(Long requestId) throws ResourceNotFoundException{

		return freightRequestByCustomerRepository.getById(requestId);
	}

	@Override
	public List<FreightRequestByCustomer> getAllRequestForCustomer(
			Long  customerId) {
		
		return freightRequestByCustomerRepository.findByCustomerId(customerId);
	}

	@Override
	public Boolean deleteRequestById(Long requestId) {

		return freightRequestByCustomerRepository.deleteById(requestId);
	}

	@Override
	public Boolean clearTestData() {

		return freightRequestByCustomerRepository.clearTestData();
	}

	@Override
	public Integer count() {

		return freightRequestByCustomerRepository.count();
	}

	@Override
	public List<FreightRequestByCustomer> getMatchingCustReqForVendorReq(
			FreightRequestByVendor freightRequestByVendor,String allocationStatus) {
		
		List<FreightRequestByCustomer> result= freightRequestByCustomerRepository.getMatchingCustReqForVendorReq(freightRequestByVendor,allocationStatus);
		
		
		return result;
	}

	@Override
	public Integer updateAllocationStatus(Long freightRequestByCustomerId,
			String oldStatus, String allocationStatus, Long allocatedFor,Integer updatedBy,Long updatedById) {
		
		return freightRequestByCustomerRepository
				.updateReservationStatusWithReservedFor(freightRequestByCustomerId, oldStatus, allocationStatus, allocatedFor,updatedBy,updatedById);
	}

	@Override
	public void getMatchingCustReqForVendorReqAndProceedWithHandShake(
			FreightRequestByVendor freightRequestByVendor) {
		
		List<FreightRequestByCustomer> list=getMatchingCustReqForVendorReq(freightRequestByVendor,FREIGHTALLOCATIONSTATUS_RESPONDED);
		
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
					List<QuestionPossibleAnswersSelectedAnswer> questionList= questionHandlingService.getAll();
				
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
			
			list=getMatchingCustReqForVendorReq(freightRequestByVendor,FREIGHTALLOCATIONSTATUS_NEW);
			
			if(list.size()!=0)
			{
			
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

		
		
	}

}
