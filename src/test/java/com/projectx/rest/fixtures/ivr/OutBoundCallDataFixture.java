package com.projectx.rest.fixtures.ivr;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.projectx.mvc.domain.ivr.KooResponseDTO;
import com.projectx.rest.domain.ivr.PreBookEntity;



public class OutBoundCallDataFixture {

	
	static Gson gson=new Gson();
	
	public static String standardJsonKooResponseDTO(KooResponseDTO kooResponseDTO)
	{
		
		return gson.toJson(kooResponseDTO);
	}
	
}
