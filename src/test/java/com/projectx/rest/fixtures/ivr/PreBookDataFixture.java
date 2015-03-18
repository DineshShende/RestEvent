package com.projectx.rest.fixtures.ivr;

import java.util.Date;

import com.projectx.rest.domain.ivr.PreBookEntity;



public class PreBookDataFixture {

	public static PreBookEntity standardPreBookEntity()
	{
		return new PreBookEntity(212L, 232L, new Date(), new Date(), "CUST_ONLINE");
	}
	
}
