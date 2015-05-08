package com.projectx.rest.controller.handshake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.handshake.DealInfoAndVendorContactDetailsDTO;
import com.projectx.mvc.domain.handshake.TriggerDealDTO;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.handshake.DealDetails;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.handlers.handshake.DealHandler;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.handshake.DealService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@RestController
@RequestMapping(value="/deal")
public class DealController {
	
	@Autowired
	DealService dealService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Value("${FREIGHTSTATUS_NEW}")
	private String FREIGHTSTATUS_NEW;
	
	@Value("${FREIGHTSTATUS_BOOKED}")
	private String FREIGHTSTATUS_BOOKED;
	
	@Value("${POSITIVE_RESPONSE}")
	private Integer POSITIVE_RESPONSE;
	
	@RequestMapping(value="/triggerdeal",method=RequestMethod.POST)
	public ResponseEntity<DealInfoAndVendorContactDetailsDTO> triggerDeal(@RequestBody TriggerDealDTO triggerDealDTO)
	{
		//TODO Need to do in one transaction
		
		Integer customerReqUpdate=freightRequestByCustomerService.updateAllocationStatus(triggerDealDTO.getFreightRequestByCustomerId(), FREIGHTSTATUS_NEW, FREIGHTSTATUS_BOOKED,
				triggerDealDTO.getFreightRequestByVendorId(), triggerDealDTO.getRequestedBy(),triggerDealDTO.getRequestedById());
		
		Integer vendorReqUpdate=freightRequestByVendorService.updateReservationStatusWithReservedFor(triggerDealDTO.getFreightRequestByVendorId(), FREIGHTSTATUS_NEW,
				FREIGHTSTATUS_BOOKED, triggerDealDTO.getFreightRequestByCustomerId(), triggerDealDTO.getRequestedBy(),triggerDealDTO.getRequestedById());
		
		
		if(customerReqUpdate.equals(POSITIVE_RESPONSE) && vendorReqUpdate.equals(POSITIVE_RESPONSE))
		{
			
			DealDetails dealDetails=dealService.triggerDealAndExchangeContact(triggerDealDTO.getFreightRequestByCustomerId(),
					triggerDealDTO.getFreightRequestByVendorId(),"", 100, triggerDealDTO.getRequestedBy(),triggerDealDTO.getRequestedById());
			
			FreightRequestByVendorDTO freightRequestByVendor=freightRequestByVendorService
					.getRequestById(triggerDealDTO.getFreightRequestByVendorId());
			
			
			if(freightRequestByVendor.getRequestId()!=null)
			{
			
				VendorDetails vendorDetails=vendorDetailsService.findById(freightRequestByVendor.getVendorId());
				
					
				DealInfoAndVendorContactDetailsDTO contactDetailsDTO=
						new DealInfoAndVendorContactDetailsDTO(dealDetails.getDealId(),
								vendorDetails.getFirstName(), vendorDetails.getLastName(), vendorDetails.getMobile(),
								vendorDetails.getFirmAddress().getCity(), vendorDetails.getFirmAddress().getState(),
								vendorDetails.getFirmAddress().getPincode());
				return new ResponseEntity<DealInfoAndVendorContactDetailsDTO>(contactDetailsDTO, HttpStatus.OK);
			
			}
			else
				return new ResponseEntity<DealInfoAndVendorContactDetailsDTO>(new DealInfoAndVendorContactDetailsDTO(), HttpStatus.OK);
				
		}
		else
			return new ResponseEntity<DealInfoAndVendorContactDetailsDTO>(new DealInfoAndVendorContactDetailsDTO(), HttpStatus.OK);
			
			
	}
	

}
