package com.projectx.rest.repository;

import java.util.ArrayList;
import java.util.List;

import com.projectx.rest.domain.Email;

public class CustomerQuickRegisterMemoryRepository implements
		CustomerQuickRegisterRepository {

	private List<Email> emailList;

	public CustomerQuickRegisterMemoryRepository() {

		this.emailList = new ArrayList<Email>();
	}

	@Override
	public List<Email> getAllEmails() {
		
		List<Email> tempList = new ArrayList<Email>();
		
		for(int i=0;i<emailList.size();i++)
		{
			tempList.add(emailList.get(i));
		}
		
		return tempList;
	}

	@Override
	public void addEmail(Email email) {
		emailList.add(email);

	}

	@Override
	public Boolean checkEmailExisted(Email email) {
		int flag=0;
		
		for(int i=0;i<emailList.size();i++)
		{
			if(emailList.get(i).getEmail()==email.getEmail())
			{
				flag=1;
				break;
			}
		}
		
		if(flag==0)
			return false;
		else
			return true;
	}

}
