package com.projectx.rest.controller.ivr;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.rest.domain.ivr.KooKooRequestEntity;
import com.projectx.rest.domain.ivr.PreBookEntity;
import com.projectx.rest.domain.ivr.QuestionListWithCounter;
import com.projectx.rest.domain.ivr.TrackKookooResponseDTO;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.service.ivr.PreBookService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@RestController
@RequestMapping(value="/outboundcall")
public class OutBoundCallResponseController {

	@Autowired
	FreightRequestByCustomerStatusDTO freightRequestByCustomerStatusDTO;
	
	@Autowired
	TrackKookooResponseDTO trackKookooResponseDTO;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	PreBookService preBookService;
	
	private Integer ONE_COUNT=new Integer(1);
	
	private Integer ZERO_COUNT=new Integer(0);
	
	@RequestMapping(value="/receiveResponse/{sid}/{mobile}/{option}")
	public void receiveResponse(@PathVariable("sid") String sid,@PathVariable("mobile") Long mobile,@PathVariable("option") Integer option)
	{
		
		KooKooRequestEntity kooKooRequestEntity=trackKookooResponseDTO.get(sid);
		
		QuestionListWithCounter questionListWithCounter=
				freightRequestByCustomerStatusDTO.getQuestionList(kooKooRequestEntity.getFreightRequestByCustomerId());
		
		if(option.equals(ONE_COUNT))
		{
			if(questionListWithCounter.getCounter().equals(ZERO_COUNT))
			{
				Integer updateStatus=freightRequestByVendorService
						.updateReservationStatusWithReservedFor(kooKooRequestEntity.getFreightRequestByVendorId(),"NEW", "BLOCK",
								kooKooRequestEntity.getFreightRequestByCustomerId());
				
				if(updateStatus.equals(ONE_COUNT))
				{
					questionListWithCounter.setCounter(questionListWithCounter.getCounter()+1);
					freightRequestByCustomerStatusDTO.update(kooKooRequestEntity.getFreightRequestByCustomerId(), questionListWithCounter);
				}
				else
				{
					//Not feasible but we never know
					try
					{
						preBookService.save(new PreBookEntity( kooKooRequestEntity.getFreightRequestByCustomerId(),kooKooRequestEntity.getFreightRequestByVendorId(), 
									new Date(), new Date(), "CUST_ONLINE"));
						
					}catch(ResourceAlreadyPresentException e)
					{
						
					}
				}
			}
			else
			{
				//Customer positively responded for second question---->exchange contacts
				
				
			}
		}
		else
		{
			//Customer Not said Yes
			
		}
		
		
	//	freightRequestByCustomerStatusDTO.delete(kooKooRequestEntity.getFreightRequestByCustomerId());
		trackKookooResponseDTO.delete(sid);
		
		
	}
	
	@RequestMapping(value="/trackresponseentity",method=RequestMethod.GET)
	public Map<String, KooKooRequestEntity> trackresponseentity()
	{
		return trackKookooResponseDTO.getRequestStatus();
	}
	
	@RequestMapping(value="/freightRequestByCustomerStatusDTO",method=RequestMethod.GET)
	public Map<Long, QuestionListWithCounter> freightRequestByCustomerStatusDTO()
	{
		return freightRequestByCustomerStatusDTO.getQuestionStatus();
	}
	
}
