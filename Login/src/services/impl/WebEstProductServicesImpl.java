package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebEstProductDao;

import entity.KyzExpectmatmLog;
import entity.Webestproduct;
import entity.WebestproductId;
import services.IWebEstProductServices;
import util.PageBean;

public class WebEstProductServicesImpl implements IWebEstProductServices {
	private IWebEstProductDao estProDao;

	public void setEstProDao(IWebEstProductDao estProDao) {
		this.estProDao = estProDao;
	}

	public void add(Webestproduct product) {
		// TODO Auto-generated method stub
		estProDao.add(product);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return estProDao.findPageBean(pageSize, page, factNo, yymm,yymm2);
	}

	public List<Webestproduct> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return estProDao.findByFactNo(factNo);
	}

	public Webestproduct findById(WebestproductId id) {
		// TODO Auto-generated method stub
		return estProDao.findById(id);
	}

	public void delete(WebestproductId id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		estProDao.delete(id,delLog);
	}

	public List<Webestproduct> selByFactNoAndYymm(String factNo, String yymm,
			String type) {
		// TODO Auto-generated method stub
		return estProDao.selByFactNoAndYymm(factNo, yymm, type);
	}

	public boolean check(String factNo, String factCode, String yymm,String type) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		DateFormat format=new SimpleDateFormat("yyyyMM");
		Date date=null;
		try {
			date=format.parse(yymm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Webestproduct>list=this.findByFactNo(factNo);
		if (list.size() > 0) {//start if
			for (int i = 0; i < list.size(); i++){
				if (factCode.equals(list.get(i).getId().getFactCode())
						&& factNo.equals(list.get(i).getId().getFactNo())
						&& date.equals(list.get(i).getId().getYymm())
						&&type.equals(list.get(i).getId().getType())) {																					
                    flag=true;
					break;
				}		    
	       }
      }//end if				
		return flag;
	}

	public Webestproduct findById(String factNo, String factCode, String yymm,
			String type) {
		// TODO Auto-generated method stub
		return estProDao.findById(factNo, factCode, yymm, type);
	}

	public Object[] findSum(String factCode, String yymm, String type) {
		// TODO Auto-generated method stub
		return estProDao.findSum(factCode, yymm, type);
	}

	public List<Webestproduct> findByFactcode(String factNo, String factCode,
			String yymm) {
		// TODO Auto-generated method stub
		return estProDao.findByFactcode(factNo, factCode, yymm);
	}

	public List<Webestproduct> findNullYpre(String factNo, String factCode,
			String yymm) {
		// TODO Auto-generated method stub
		return estProDao.findNullYpre(factNo, factCode, yymm);
	}

	public List<Webestproduct> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		return estProDao.findByAny(factNo, beginDate, endDate);
	}

	public List<String[]> getFactPrint_show(String date) {
		// TODO Auto-generated method stub
		return estProDao.getFactPrint_show(date);
	}

	public Object[] reportWebCashout(String factNo,String factCode,String yymm,String type) {
		// TODO Auto-generated method stub
		return estProDao.reportWebCashout(factNo,factCode,yymm,type);
	}

	public List<String> findtypeById(String factNo, String factCode,
			String yymm) {
		// TODO Auto-generated method stub
		return estProDao.findtypeById(factNo, factCode, yymm);
	}

	/**
	 * 日期:2016/4/1
	 * 描述:
	 */
	
	
	public List<Webestproduct> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		return estProDao.findByYymm(yymm);
	}


}
