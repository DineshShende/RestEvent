package com.projectx.rest.repository.async;


import static org.junit.Assert.*;
import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixtures.async.RetriggerDetailsDataFixtures.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.async.RetriggerDetails;
import com.projectx.rest.exception.repository.async.RetriggerDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
public class RetriggerDetailsRepositoryTest {

	@Autowired
	RetriggerDetailsRepository retriggerDetailsRepository;
	
	

	
	@Before
	public void setUp()
	{
		retriggerDetailsRepository.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}
	

	@Test
	public void saveAndGet()
	{
		
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(standardRetriggerDetails());
		
		assertEquals(1, retriggerDetailsRepository.findAll().size());
		
		retriggerDetailsRepository.deleteById(savedEntity.getRetriggerId());
		
	}
	
	@Test
	public void findAll()
	{
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(standardRetriggerDetails());
		
		List<RetriggerDetails> list=(List<RetriggerDetails>) retriggerDetailsRepository.findAll();
	
		assertEquals(1, list.size());
		
		retriggerDetailsRepository.deleteById(savedEntity.getRetriggerId());
		
	}
	
	@Test
	public void deleteById()
	{
		RetriggerDetails savedEntity=retriggerDetailsRepository.save(standardRetriggerDetails());
		
		assertEquals(savedEntity, retriggerDetailsRepository.findAll().get(0));
		
		retriggerDetailsRepository.deleteById(savedEntity.getRetriggerId());
		
		assertEquals(0,retriggerDetailsRepository.findAll().size());
	}


}
