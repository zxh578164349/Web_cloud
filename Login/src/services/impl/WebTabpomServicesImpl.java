package services.impl;

import java.util.List;

import dao.IWebTabpomDao;
import entity.KyzExpectmatmLog;
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

	public void delete(WebTabpom tabpom,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		tabpomDao.delete(tabpom,delLog);
	}

	public void delete(String pomNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		tabpomDao.delete(pomNo,delLog);
	}

	public PageBean findPageBean(int pageSize, int page, String pomNo,
			String brank,String yymm,String yymm2,String factNo) {
		// TODO Auto-generated method stub
		return tabpomDao.findPageBean(pageSize, page, pomNo, brank,yymm,yymm2,factNo);
	}

	public String findPomNoById(String pomNo) {
		// TODO Auto-generated method stub
		return tabpomDao.findPomNoById(pomNo);
	}

	public String makePomNo(String brank, String tabpomDate) {
		// TODO Auto-generated method stub
		StringBuffer pomNo=new StringBuffer();
		pomNo.append("WX"+brank+tabpomDate.substring(2));
		List<String>list=tabpomDao.findPomNos(brank, tabpomDate);
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

	/**
	 * 日期:2016/11/2
	 * 描述:
	 */
	
	
	public List<String> findPomNos(String brank,String tabpomDate){
		// TODO Auto-generated method stub
		return tabpomDao.findPomNos(brank,tabpomDate);
	}

}
