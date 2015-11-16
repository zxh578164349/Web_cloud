package services.impl;

import java.util.List;

import dao.IWebTypeDao;
import entity.WebType;
import services.IWebTypeServices;
import util.PageBean;

public class WebTypeServicesImpl implements IWebTypeServices{
	private IWebTypeDao webtypeDao;
	
	

	public void setWebtypeDao(IWebTypeDao webtypeDao) {
		this.webtypeDao = webtypeDao;
	}



	public void add(WebType type) {
		// TODO Auto-generated method stub
		webtypeDao.add(type);
	}



	public PageBean findPageBean(int page, int pageSize, String factNo) {
		// TODO Auto-generated method stub
		return webtypeDao.findPageBean(page, pageSize, factNo);
	}



	public WebType findById(String factNo, String typeNo) {
		// TODO Auto-generated method stub
		return webtypeDao.findById(factNo, typeNo);
	}



	public void delete(String factNo, String typeNo) {
		// TODO Auto-generated method stub
		webtypeDao.delete(factNo, typeNo);
	}



	public List<WebType> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		List<WebType>list=webtypeDao.findByFactNo(factNo);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).setWebtypeMk(list.get(i).getId().getTypeNo());				
			}
		}	
		return list;
	}



	public String findTypeNameById(String factNo, String typeNo) {
		// TODO Auto-generated method stub
		return webtypeDao.findTypeNameById(factNo, typeNo);
	}



	public List<WebType> findByFactNo2(String factNo) {
		// TODO Auto-generated method stub
		return webtypeDao.findByFactNo2(factNo);
	}





}
