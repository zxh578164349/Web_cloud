package services.impl;

import java.util.Map;

import dao.IWebPhonebookDao;
import entity.WebPhonebook;
import services.IWebPhonebookServices;
import util.PageBean;

public class WebPhonebookServicesImpl implements IWebPhonebookServices{
	private IWebPhonebookDao webphonedao;
	
	

	public void setWebphonedao(IWebPhonebookDao webphonedao) {
		this.webphonedao = webphonedao;
	}



	public void add(WebPhonebook phone,long pbId) {
		// TODO Auto-generated method stub
		webphonedao.add(phone,pbId);
	}



	public PageBean findPageBean(int pageSize, int page, String factNo,
			String department, String post, String userName) {
		// TODO Auto-generated method stub
		return webphonedao.findPageBean(pageSize, page, factNo, department, post, userName);
	}



	public WebPhonebook findById(long pbId) {
		// TODO Auto-generated method stub
		return webphonedao.findById(pbId);
	}



	public void delete(long pbId) {
		// TODO Auto-generated method stub
		webphonedao.delete(pbId);
	}



	public void addLarge(Map<String, Object> map, String username) {
		// TODO Auto-generated method stub
		webphonedao.addLarge(map, username);
	}

}
