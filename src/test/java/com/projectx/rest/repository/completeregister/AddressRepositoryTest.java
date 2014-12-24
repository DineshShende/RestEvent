package com.projectx.rest.repository.completeregister;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.Address;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles={"Dev"})

public class AddressRepositoryTest {

	@Autowired
	AddressRepository addressRepository; 
	
	@Before
	public void clearTestData()
	{
		//addressRepository.deleteAll();
	}
	
	@Test
	public void environmentTest() {
		
	}

	
	@Test
	public void saveAndGetById()
	{
		Address address=addressRepository.save(standardAddressWithId());
		
		assertEquals(standardAddress(), address);
		
		assertEquals(standardAddress(), addressRepository.findOne(address.getAddressId()));
	}
	@Test
	public void deleteById()
	{
	//	assertEquals(0,addressRepository.count());
		
		Address savedEntity=addressRepository.save(standardAddress());
		
		assertEquals(standardAddress(), savedEntity);
		
		addressRepository.deleteById(savedEntity.getAddressId());
		
		assertNull(addressRepository.findOne(savedEntity.getAddressId()));
		
	//	assertEquals(1,addressRepository.count());
		
	}
	
	
}
