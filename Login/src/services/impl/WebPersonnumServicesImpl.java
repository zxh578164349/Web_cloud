package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebPersonNumDao;

import entity.KyzExpectmatmLog;
import entity.Webpersonnum;
import entity.WebpersonnumId;
import services.IWebPersonnumServices;
import util.PageBean;

public class WebPersonnumServicesImpl implements IWebPersonnumServices {
	private IWebPersonNumDao personNumDao;

	public void setPersonNumDao(IWebPersonNumDao personNumDao) {
		this.personNumDao = personNumDao;
	}

	public void add(Webpersonnum person) {
		// TODO Auto-generated method stub
		personNumDao.add(person);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String beginDay,String endDay) {
		// TODO Auto-generated method stub
		return personNumDao.findPageBean(pageSize, page, factNo, yymm,beginDay,endDay);
	}

	public List<Webpersonnum> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return personNumDao.findByFactNo(factNo);
	}

	public Webpersonnum findById(WebpersonnumId id) {
		// TODO Auto-generated method stub
		return personNumDao.findById(id);
	}

	public void delete(WebpersonnumId id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		personNumDao.delete(id,delLog);
	}

	public List<Webpersonnum> findByYymmdd(String yymmdd) {
		// TODO Auto-generated method stub
		return personNumDao.findByYymmdd(yymmdd);
	}

	public List<Object[]> getTotalByYymmdd(String yymmdd) {
		// TODO Auto-generated method stub
		return personNumDao.getTotalByYymmdd(yymmdd);
	}

	public Webpersonnum findById2(String factNo, String factCode, String yymmdd) {
		// TODO Auto-generated method stub
		return personNumDao.findById2(factNo, factCode, yymmdd);
	}

	public List<Object[]> findByYnmmddAndFactcode( String yymmdd) {
		// TODO Auto-generated method stub
		return personNumDao.findByYnmmddAndFactcode( yymmdd);
	}

	public boolean check(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		DateFormat format=new SimpleDateFormat("yyyyMMdd");
		Date date=null;
		try {
			date=format.parse(yymm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Webpersonnum>list=this.findByFactNo(factNo);
		if (list.size() > 0) {//start if
			for (int i = 0; i < list.size(); i++){
				if (factCode.equals(list.get(i).getId().getFactCode())
						&& factNo.equals(list.get(i).getId().getFactNo())
						&& date.equals(list.get(i).getId().getYymmdd())) {																					
                    flag=true;
					break;
				}		    
	       }
      }//end if	
		return flag;
	}

}
