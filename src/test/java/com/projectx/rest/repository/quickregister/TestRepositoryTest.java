package com.projectx.rest.repository.quickregister;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.services.quickregister.TestRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")
public class TestRepositoryTest {

	@Autowired
	TestRepository testRepository;
	
	@Test
	public void test() {
	
		testRepository.test();
	}

	
	
}
