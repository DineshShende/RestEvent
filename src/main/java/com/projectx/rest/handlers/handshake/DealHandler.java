package com.projectx.rest.handlers.handshake;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.handshake.DealDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.handshake.DealDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.handshake.DealService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;
import com.projectx.rest.utils.MessagerSender;

@Component
public class DealHandler implements DealService {

	
	@Autowired
	DealDetailsRepository dealDetailsRepository;

	@Autowired
	MessagerSender messagerSender;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Override
	public DealDetails triggerDeal(Long freightRequestByCustomer,
			Long freightRequestByVendor,String deductionMode,Integer amount,String triggeredBy) {

		
		DealDetails dealDetails=new DealDetails(freightRequestByCustomer, freightRequestByVendor, deductionMode, amount, 
				triggeredBy, new Date(), triggeredBy, new Date());

		DealDetails savedDealDetails=save(dealDetails);
		
		return savedDealDetails;
	}

	@Override
	public Boolean exchangeContact(Long dealId, Long freightRequestByCustomer,
			Long freightRequestByVendor) {
		
		
		FreightRequestByCustomer requestByCustomer=freightRequestByCustomerService.getRequestById(freightRequestByCustomer);
		
		FreightRequestByVendor requestByVendor=freightRequestByVendorService.getRequestById(freightRequestByVendor);
		
		
		CustomerDetails customerDetails=customerDetailsService.findById(requestByCustomer.getCustomerId());
				
		VendorDetails vendorDetails=vendorDetailsService.findById(requestByVendor.getVendorId());		
		
		
		Boolean customerSide=messagerSender.exchangeContactAferDeal(customerDetails.getMobile(),freightRequestByCustomer, 
				dealId, vendorDetails.getMobile(), customerDetails.getFirstName()+" "+((customerDetails.getMiddleName()!=null)?customerDetails.getMiddleName():"")+ " "+customerDetails.getLastName());
		

		Boolean vendorSide=messagerSender.exchangeContactAferDeal(vendorDetails.getMobile(), freightRequestByVendor,
				dealId, customerDetails.getMobile(), vendorDetails.getFirstName()+" "+((vendorDetails.getMiddleName()!=null)?vendorDetails.getMiddleName():"")+ " "+vendorDetails.getLastName());
		
		if(customerSide && vendorSide)
			return true;
		else
			return false;
	}

	@Override
	public DealDetails triggerDealAndExchangeContact(
			Long freightRequestByCustomer, Long freightRequestByVendor,String deductionMode,Integer amount,String triggeredBy) {

		DealDetails dealDetails=triggerDeal(freightRequestByCustomer, freightRequestByVendor, deductionMode, amount, triggeredBy);
		
		Boolean result=exchangeContact(dealDetails.getDealId(), freightRequestByCustomer, freightRequestByVendor);

		if(result)
			return dealDetails;
		else
			throw new RuntimeException();
		
	}

	@Override
	public DealDetails save(DealDetails dealDetails) {

		return dealDetailsRepository.save(dealDetails);
	}

	@Override
	public DealDetails getByDealId(Long dealId) {

		return dealDetailsRepository.getByDealId(dealId);
	}

	@Override
	public DealDetails getByCustomerRequestId(Long freightRequestByCustomerId) {

		return dealDetailsRepository.getByCustomerRequestId(freightRequestByCustomerId);
	}

	@Override
	public DealDetails getByVendorRequestId(Long freightRequestByVendorId) {

		return dealDetailsRepository.getByVendorRequestId(freightRequestByVendorId);
	}

	@Override
	public Integer count() {

		return dealDetailsRepository.count();
	}

	@Override
	public Boolean clearTestData() {

		return dealDetailsRepository.clearTestData();
	}

}
