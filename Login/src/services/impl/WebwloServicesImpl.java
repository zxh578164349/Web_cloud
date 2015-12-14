package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebWloDao;

import entity.Webcost;
import entity.Webwlo;
import entity.WebwloId;
import services.IWebwloServices;
import util.PageBean;

public class WebwloServicesImpl implements IWebwloServices {

	private IWebWloDao wloDao;

	public IWebWloDao getWloDao() {
		return wloDao;
	}

	public void setWloDao(IWebWloDao wloDao) {
		this.wloDao = wloDao;
	}

	public void addWebWloDao(Webwlo wlo) {
		wloDao.addWebWloDao(wlo);
	}

	public List<Webwlo> selectWloDao(String factNo, String yymm, int page,
			int rows) {
		return wloDao.selectWloDao(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return wloDao.totlePage(factNo, yymm);
	}

	public Webwlo findById(WebwloId id) {
		return wloDao.findById(id);
	}

	public List<Webwlo> selByYymm(String yymm, String factNo) {
		return wloDao.selByYymm(yymm, factNo);
	}

	public void delete(WebwloId id) {
		wloDao.delete(id);
	}

	public Webwlo selBycan(String factNo, Date date, String factCode) {
		// TODO Auto-generated method stub
		return wloDao.selBycan(factNo, date, factCode);
	}

	public boolean check(String factNo, String factCode, String yymm) {
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
		Webwlo wlo=this.selBycan(factNo, date, factCode);
		if(wlo!=null){
			flag=true;
		}				
		return flag;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return wloDao.findPageBean(pageSize, page, factNo, yymm,yymm2);
	}

	public List<Webwlo> findByAny(String factNo, String beginDate,String endDate){
		// TODO Auto-generated method stub
		return wloDao.findByAny(factNo, beginDate,endDate);
	}

	public Webwlo findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return wloDao.findById(factNo, factCode, yymm);
	}

}
