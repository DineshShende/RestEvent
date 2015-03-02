package com.projectx.rest.exception.repository.async;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.async.RetriggerDetails;


@Repository
public interface RetriggerDetailsRepository {
	
	public RetriggerDetails save(RetriggerDetails retriggerDetails );
	
	public List<RetriggerDetails> findAll();
	
	public Boolean deleteById(Long retriggerId);
	
	public void clearTestData();

}
