package com.projectx.rest.repository.completeregister;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDocument;
import com.projectx.rest.repository.completeregister.CustomerDocumentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")
public class CustomerDocumentRepositoryTest {

	
	@Autowired
	CustomerDocumentRepository customerDocumentRepository;
	
	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void saveNewCustomerDocument()
	{
		/*
		CustomerDocument customerDocument=new CustomerDocument(3L, "ahakjdgskjagdkjgsjg".getBytes());
		
		customerDocumentRepository.saveCustomerDocument(customerDocument);
		
		customerDocument=customerDocumentRepository.getCustomerDocumentByCustomerId(3L);
		
		System.out.println(customerDocument);
		*/
	}

}
