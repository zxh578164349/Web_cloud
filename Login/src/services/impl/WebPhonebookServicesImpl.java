package services.impl;

import java.util.List;
import java.util.Map;

import dao.IWebPhonebookDao;
import entity.KyzExpectmatmLog;
import entity.WebPhonebook;
import services.IWebPhonebookServices;
import util.PageBean;

public class WebPhonebookServicesImpl implements IWebPhonebookServices{
	private IWebPhonebookDao webphonedao;
	
	

	public void setWebphonedao(IWebPhonebookDao webphonedao) {
		this.webphonedao = webphonedao;
	}



	public void add(WebPhonebook phone) {
		// TODO Auto-generated method stub
		webphonedao.add(phone);
	}



	public PageBean findPageBean(int pageSize, int page, String factNo,
			String department, String post, String userName) {
		// TODO Auto-generated method stub
		return webphonedao.findPageBean(pageSize, page, factNo, department, post, userName);
	}



	



	public void addLarge(Map<String, Object> map, String username) {
		// TODO Auto-generated method stub
		webphonedao.addLarge(map, username);
	}



	public List<WebPhonebook> findToPrint(String factNo, String department,
			String post, String userName) {
		// TODO Auto-generated method stub
		return webphonedao.findToPrint(factNo, department, post, userName);
	}



	public List<String> findDepartments(String factNo) {
		// TODO Auto-generated method stub
		return webphonedao.findDepartments(factNo);
	}



	public List<String> findPosts(String factNo) {
		// TODO Auto-generated method stub
		return webphonedao.findPosts(factNo);
	}



	public WebPhonebook findById(String factNo, String department, String post,
			String userName, String phoneA, String phoneB, String phoneC,
			String email) {
		// TODO Auto-generated method stub
		return webphonedao.findById(factNo, department, post, userName, phoneA, phoneB, phoneC, email);
	}



	public void delete(String factNo, String department, String post,
			String userName, String phoneA, String phoneB, String phoneC,
			String email,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		webphonedao.delete(factNo, department, post, userName, phoneA, phoneB, phoneC, email,delLog);
	}

}
