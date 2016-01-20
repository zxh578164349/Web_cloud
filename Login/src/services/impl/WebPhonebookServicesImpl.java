package services.impl;

import dao.IWebPhonebookDao;
import entity.WebPhonebook;
import services.IWebPhonebookServices;

public class WebPhonebookServicesImpl implements IWebPhonebookServices{
	private IWebPhonebookDao webphonedao;
	
	

	public void setWebphonedao(IWebPhonebookDao webphonedao) {
		this.webphonedao = webphonedao;
	}



	public void add(WebPhonebook phone) {
		// TODO Auto-generated method stub
		webphonedao.add(phone);
	}

}
