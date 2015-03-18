package com.projectx.rest.repository.ivr;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.ivr.PreBookEntity;

@Repository
public interface PreBookEntityRepository {
	
	PreBookEntity save(PreBookEntity preBookEntity);
	
	PreBookEntity getByFreightRequestByCustomerId(Long freightRequestByCustomerId);
	
	Integer deleteByFreightRequestByCustomerId(Long freightRequestByCustomerId);
	
	Integer count();
	
	Boolean clearTestData();

}
