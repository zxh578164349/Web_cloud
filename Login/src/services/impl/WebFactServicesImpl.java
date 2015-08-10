package services.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IWebFactDao;

import entity.WebFact;
import entity.WebFactId;
import services.IWebFactServices;

public class WebFactServicesImpl implements IWebFactServices {
	private IWebFactDao webFactDao;

	public void setWebFactDao(IWebFactDao webFactDao) {
		this.webFactDao = webFactDao;
	}

	public List<WebFact> findAllFact() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFact();
	}
	
	public List<WebFact> findAllFact2(){
		// TODO Auto-generated method stub
		List list=this.findAllFact();
		List<WebFact>list2=new ArrayList();
		try {
			for(int i=0;i<list.size();i++){
				WebFact fact=new WebFact();
				Object[]obj=(Object[])list.get(i);
				String factno=obj[0].toString();
				String factsname=obj[1].toString();
				StringBuffer temp=new StringBuffer();//組合(factSname+factNo)
				temp.append(factsname+"(");
				temp.append(factno+")");
				fact.setFactCode(factno);//以WebFactt的factCode充當factNo,因為dwr到頁面時獲取不到id.factNo的值
				fact.setFactSname(temp.toString());//以組合(factSname+factNo)充當factSname,便於在頁面上顯示(factSname+factNo)
				list2.add(fact);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list2;
	}
	

	public List<WebFact> findFactById(String factNo) {
		// TODO Auto-generated method stub
		return webFactDao.findFactById(factNo);
	}
	public List<WebFact> findFactById_showA(String factNo) {
		// TODO Auto-generated method stub
		return webFactDao.findFactById_showA(factNo);
	}
	public List<WebFact> findFactById_show(String factNo) {
		// TODO Auto-generated method stub
		return webFactDao.findFactById_show(factNo);
	}

	/**
	 * 返回factNo的factSname
	 */
	public String selByid(String id) {
		// TODO Auto-generated method stub
		return webFactDao.selByid(id);
	}

	/**
	 * tw的所有factArea
	 */
	public List findAllFactCode() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFactCode();
	}
	public List findAllFactCode_show() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFactCode_show();
	}

	/**
	 * 不同factNo(除了tw)的factArea
	 */
	public List findFactCodeByFactNo(String factNo) {
		// TODO Auto-generated method stub
		List list = webFactDao.findFactCodeByFactNo(factNo);
		return list;
	}
	public List findFactCodeByFactNo_show(String factNo){
		List list = webFactDao.findFactCodeByFactNo_show(factNo);
		return list;
	}
	public List findFactCodeByFactNo_show_dw(String factNo){
		List list = webFactDao.findFactCodeByFactNo_show_dw(factNo);
		return list;
	}

	public String findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return webFactDao.findByFactNo(factNo);
	}

	public List<String> findFactNoByFactCode(String factCode) {
		// TODO Auto-generated method stub
		return webFactDao.findFactNoByFactCode(factCode);
	}

	public List<String> findFactNameByFactCode(String factCode) {
		// TODO Auto-generated method stub
		return webFactDao.findFactNameByFactCode(factCode);
	}

	public List<WebFact> findFactByFactCode(String factCode) {
		// TODO Auto-generated method stub
		return webFactDao.findFactByFactCode(factCode);
	}
	public List<WebFact> findFactByFactCode_showA(String factCode) {
		// TODO Auto-generated method stub
		return webFactDao.findFactByFactCode_showA(factCode);
	}

	public List<Object[]> findAllWebFact() {
		// TODO Auto-generated method stub
		return webFactDao.findAllWebFact();
	}
	public List<Object[]> findAllWebFact_showA() {
		// TODO Auto-generated method stub
		return webFactDao.findAllWebFact_showA();
	}

	public List<Object[]> findAllFactCode2() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFactCode2();
	}
	public List<Object[]> findAllFactCode2_showA() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFactCode2_showA();
	}

	public List<String> findAllFactNo() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFactNo();
	}

	public List<WebFact> findAllFact_2() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFact_2();
	}

}
