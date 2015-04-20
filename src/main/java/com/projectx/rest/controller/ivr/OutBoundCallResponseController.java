package com.projectx.rest.controller.ivr;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.ivr.FreightRequestByCustomerStatusDTO;
import com.projectx.rest.domain.ivr.IVRCallInfoDTO;
import com.projectx.rest.domain.ivr.KooKooRequestEntity;
import com.projectx.rest.domain.ivr.PreBookEntity;
import com.projectx.rest.domain.ivr.QuestionListWithCounter;
import com.projectx.rest.domain.ivr.TrackKookooResponseDTO;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.service.ivr.OutBoundCallService;
import com.projectx.rest.service.ivr.PreBookService;
import com.projectx.rest.services.handshake.DealService;
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
	OutBoundCallService outBoundCallService;
	
	@Autowired
	DealService dealService;
	
	@Value("${ACTOR_ENTITY_SELF_FREIGHT_RES_SYS}")
	private Integer ACTOR_ENTITY_SELF_FREIGHT_RES_SYS;
	
	@Value("${ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID}")
	private Long ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID;
	
	@Value("${POSITIVE_RESPONSE}")
	private Integer POSITIVE_RESPONSE;
	
	@Value("${NEGATIVE_RESPONSE}")
	private Integer NEGATIVE_RESPONSE;
	
	@Value("${ZERO_COUNT}")
	private Integer ZERO_COUNT;
	
	@Value("${ONE_COUNT}")
	private Integer ONE_COUNT;
	
	@Value("${FREIGHTSTATUS_BLOCKED}")
	private String FREIGHTSTATUS_BLOCKED;
	
	@Value("${FREIGHTSTATUS_NEW}")
	private String FREIGHTSTATUS_NEW;
	
	@Value("${FREIGHTSTATUS_BOOKED}")
	private String FREIGHTSTATUS_BOOKED;
	
	@Value("${FREIGHTALLOCATIONSTATUS_RESPONDED}")
	private String FREIGHTALLOCATIONSTATUS_RESPONDED;
	
	@Value("${FREIGHTALLOCATIONSTATUS_NEW}")
	private String FREIGHTALLOCATIONSTATUS_NEW;
	
	@Value("${FREIGHTALLOCATIONSTATUS_BOOKED}")
	private String FREIGHTALLOCATIONSTATUS_BOOKED;
	
	@Value("${FREIGHTALLOCATIONSTATUS_BLOCKED}")
	private String FREIGHTALLOCATIONSTATUS_BLOCKED;
	
	@Value("${FREIGHTALLOCATIONSTATUS_NEGFIRSTSTAGE}")
	private String FREIGHTALLOCATIONSTATUS_NEGFIRSTSTAGE;
	
	@Value("${FREIGHTALLOCATIONSTATUS_NEGSECONDSTAGE}")
	private String FREIGHTALLOCATIONSTATUS_NEGSECONDSTAGE;
	
	
	@RequestMapping(value="/receiveResponse/{sid}/{mobile}/{option}")
	public void receiveResponse(@PathVariable("sid") String sid,@PathVariable("mobile") Long mobile,@PathVariable("option") Integer option)
	{
		
		KooKooRequestEntity kooKooRequestEntity=trackKookooResponseDTO.get(sid);
		
		QuestionListWithCounter questionListWithCounter=
				freightRequestByCustomerStatusDTO.getQuestionList(kooKooRequestEntity.getFreightRequestByCustomerId());
		
		if(option.equals(POSITIVE_RESPONSE))
		{
			if(questionListWithCounter.getCounter().equals(ZERO_COUNT))
			{

				Integer updateStatus=freightRequestByVendorService
						.updateReservationStatusWithReservedFor(kooKooRequestEntity.getFreightRequestByVendorId(),FREIGHTSTATUS_NEW, FREIGHTSTATUS_BLOCKED,
								kooKooRequestEntity.getFreightRequestByCustomerId(),ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);
				
				if(updateStatus.equals(POSITIVE_RESPONSE))
				{
					Integer customerRequestUpdateStatus=freightRequestByCustomerService
							.updateAllocationStatus(kooKooRequestEntity.getFreightRequestByCustomerId(), FREIGHTALLOCATIONSTATUS_NEW,
									FREIGHTALLOCATIONSTATUS_BLOCKED,kooKooRequestEntity.getFreightRequestByVendorId(),
									ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);
							
					if(customerRequestUpdateStatus.equals(POSITIVE_RESPONSE))
					{	
						questionListWithCounter.setCounter(questionListWithCounter.getCounter()+1);
						
						freightRequestByCustomerStatusDTO.update(kooKooRequestEntity.getFreightRequestByCustomerId(), questionListWithCounter);
						
						IVRCallInfoDTO ivrCallInfoDTO=new IVRCallInfoDTO(questionListWithCounter.getMobile(), 
								questionListWithCounter.getQuestionList().get(questionListWithCounter.getCounter()));	 
						 
						 String sidNew=outBoundCallService.makeOutBoundCall(ivrCallInfoDTO);
						 
						 trackKookooResponseDTO.add(sidNew, new KooKooRequestEntity(kooKooRequestEntity.getFreightRequestByCustomerId(),
								 kooKooRequestEntity.getFreightRequestByVendorId()));
						
					}
					
				}
				else
				{
					Integer customerRequestUpdateStatus=freightRequestByCustomerService
								.updateAllocationStatus(kooKooRequestEntity.getFreightRequestByCustomerId(), FREIGHTALLOCATIONSTATUS_NEW,
										FREIGHTALLOCATIONSTATUS_RESPONDED, kooKooRequestEntity.getFreightRequestByVendorId(),
										ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);
				}
			}
			else if(questionListWithCounter.getCounter().equals(ONE_COUNT))
			{
				Integer updateVendorRequest=freightRequestByVendorService
						.updateReservationStatusWithReservedFor(kooKooRequestEntity.getFreightRequestByVendorId(), FREIGHTSTATUS_BLOCKED,
								FREIGHTSTATUS_BOOKED, kooKooRequestEntity.getFreightRequestByCustomerId(),
								ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);
						
						
				Integer updateCustomerRequestStatus=freightRequestByCustomerService.
						updateAllocationStatus(kooKooRequestEntity.getFreightRequestByCustomerId(), FREIGHTALLOCATIONSTATUS_BLOCKED,
								FREIGHTALLOCATIONSTATUS_BOOKED, kooKooRequestEntity.getFreightRequestByVendorId(),
								ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);		
				
				
				if(updateCustomerRequestStatus.equals(POSITIVE_RESPONSE) && updateVendorRequest.equals(POSITIVE_RESPONSE))
				{
					
					dealService.triggerDealAndExchangeContact(kooKooRequestEntity.getFreightRequestByCustomerId(),
							kooKooRequestEntity.getFreightRequestByVendorId(), "ONLINE", 100, "triggeredBy");
					
				}	
				
			}
		}
		else if(option.equals(NEGATIVE_RESPONSE))
		{
			
			if(questionListWithCounter.getCounter().equals(ZERO_COUNT))
			{
				freightRequestByCustomerService
				.updateAllocationStatus(kooKooRequestEntity.getFreightRequestByCustomerId(), FREIGHTALLOCATIONSTATUS_NEW,
						FREIGHTALLOCATIONSTATUS_NEGFIRSTSTAGE, kooKooRequestEntity.getFreightRequestByVendorId(),
						ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);
			}
			else if(questionListWithCounter.getCounter().equals(ONE_COUNT))
			{
				freightRequestByVendorService
				.updateReservationStatusWithReservedFor(kooKooRequestEntity.getFreightRequestByVendorId(),
						FREIGHTSTATUS_BLOCKED, FREIGHTSTATUS_NEW, null,
						ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);
				
				freightRequestByCustomerService
				.updateAllocationStatus(kooKooRequestEntity.getFreightRequestByCustomerId(), FREIGHTALLOCATIONSTATUS_BLOCKED,
						FREIGHTALLOCATIONSTATUS_NEGSECONDSTAGE, kooKooRequestEntity.getFreightRequestByVendorId(),
						ACTOR_ENTITY_SELF_FREIGHT_RES_SYS,ACTOR_ENTITY_SELF_FREIGHT_RES_SYS_ID);
				
			}

			
		}
				
		trackKookooResponseDTO.delete(sid);
		
		
	}

	/*
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
	*/
	
}
