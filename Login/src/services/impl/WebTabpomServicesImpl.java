package services.impl;

import java.util.List;

import dao.IWebTabpomDao;
import entity.WebTabpom;
import services.IWebTabpomServices;
import util.PageBean;

public class WebTabpomServicesImpl implements IWebTabpomServices{
	private IWebTabpomDao tabpomDao;
	

	public void setTabpomDao(IWebTabpomDao tabpomDao) {
		this.tabpomDao = tabpomDao;
	}

	public void add(WebTabpom tabpom) {
		// TODO Auto-generated method stub
		tabpomDao.add(tabpom);
	}

	public WebTabpom findById(String pomNo) {
		// TODO Auto-generated method stub
		return tabpomDao.findById(pomNo);
	}

	public void delete(WebTabpom tabpom) {
		// TODO Auto-generated method stub
		tabpomDao.delete(tabpom);
	}

	public void delete(String pomNo) {
		// TODO Auto-generated method stub
		tabpomDao.delete(pomNo);
	}

	public PageBean findPageBean(int pageSize, int page, String pomName,
			String brank,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return tabpomDao.findPageBean(pageSize, page, pomName, brank,yymm,yymm2);
	}

	public String findPomNoById(String pomNo) {
		// TODO Auto-generated method stub
		return tabpomDao.findPomNoById(pomNo);
	}

	public String makePomNo(String component, String tabpomDate) {
		// TODO Auto-generated method stub
		StringBuffer pomNo=new StringBuffer();
		pomNo.append("WX"+component+tabpomDate.substring(2));
		List<String>list=tabpomDao.findPomNos(component, tabpomDate);
		if(list.size()>0){
			String indexStr=list.get(0).substring(list.get(0).length()-3);
			Integer temp=Integer.parseInt(indexStr)+1;
			String temp2=temp.toString();
			if(temp2.length()==1){
				temp2="00"+temp2;
			}
			if(temp2.length()==2){
				temp2="0"+temp2;
			}
			pomNo.append(temp2);
		}else{
			pomNo.append("001");
		}		
		return pomNo.toString();
	}

	public List<WebTabpom> findByAny(String pomName, String brank,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return tabpomDao.findByAny(pomName, brank,yymm,yymm2);
	}

}
