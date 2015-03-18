package com.projectx.rest.service.ivr;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.ivr.PreBookEntity;

@Service
public interface PreBookService {
	
PreBookEntity save(PreBookEntity preBookEntity);
	
	PreBookEntity getByFreightRequestByCustomerId(Long freightRequestByCustomerId);
	
	Integer deleteByFreightRequestByCustomerId(Long freightRequestByCustomerId);
	
	Integer count();
	
	Boolean clearTestData();

}
