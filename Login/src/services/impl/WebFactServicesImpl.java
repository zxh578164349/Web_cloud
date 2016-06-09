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
				StringBuffer temp=new StringBuffer();//�զX(factSname+factNo)
				temp.append(factsname+"(");
				temp.append(factno+")");
				fact.setFactCode(factno);//�HWebFactt��factCode�R��factNo,�]��dwr�쭶��������id.factNo����
				fact.setFactSname(temp.toString());//�H�զX(factSname+factNo)�R��factSname,�K��b�����W���(factSname+factNo)
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
	 * ��^factNo��factSname
	 */
	public String selByid(String id) {
		// TODO Auto-generated method stub
		return webFactDao.selByid(id);
	}

	/**
	 * tw���Ҧ�factArea
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
	 * ���PfactNo(���Ftw)��factArea
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

	
	
	public List<Object[]> findAllFact_obj() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFact_obj();
	}

	public List<Object[]> findAllFactarea_obj() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFactarea_obj();
	}

	public List<Object[]> findFactByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return webFactDao.findFactByFactNo(factNo);
	}

	/**
	 * 日期:2016/2/29
	 * 描述:
	 */
	
	
	public List<WebFact> findFactAble() {
		// TODO Auto-generated method stub
		return webFactDao.findFactAble();
	}

	/**
	 * 日期:2016/2/29
	 * 描述:
	 */
	
	
	public List<Object[]> findFactAreaAbled() {
		// TODO Auto-generated method stub
		return webFactDao.findFactAreaAbled();
	}

	/**
	 * 日期:2016/3/28
	 * 描述:
	 */
	
	
	public List<String> findfactAreaByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return webFactDao.findfactAreaByFactNo(factNo);
	}

	/**
	 * 日期:2016/4/1
	 * 描述:
	 */
	
	
	public List<WebFact> findAll() {
		// TODO Auto-generated method stub
		return webFactDao.findAll();
	}

	/**
	 * 日期:2016/4/6
	 * 描述:
	 */
	
	
	public List<Object[]> findFactAble2() {
		// TODO Auto-generated method stub
		return webFactDao.findFactAble2();
	}

	/**
	 * 日期:2016/4/19
	 * 描述:
	 */
	
	
	public List<WebFact> findAllFact_showA() {
		// TODO Auto-generated method stub
		return webFactDao.findAllFact_showA();
	}

	/**
	 * 日期:2016/5/5
	 * 描述:
	 */
	
	
	public List<String> findByFactNo_showA(String factNo) {
		// TODO Auto-generated method stub
		return webFactDao.findByFactNo_showA(factNo);
	}

	/**
	 * 日期:2016/6/7
	 * 描述:
	 */
	
	
	public List<WebFact> findByList(List<String> list) {
		// TODO Auto-generated method stub
		return webFactDao.findByList(list);
	}

	public List<String> findFactNoshow() {
		// TODO Auto-generated method stub
		return webFactDao.findFactNoshow();
	}

	public List<String> findFactCodeshow() {
		// TODO Auto-generated method stub
		return webFactDao.findFactCodeshow();
	}

}
