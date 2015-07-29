package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebMixPersonDao;

import entity.Webmixperson;
import entity.WebmixpersonId;
import services.IWebMixPersonServices;
import util.PageBean;

public class WebMixPersonServicesImpl implements IWebMixPersonServices {

	private IWebMixPersonDao mixPersonDao;

	public IWebMixPersonDao getMixPersonDao() {
		return mixPersonDao;
	}

	public void setMixPersonDao(IWebMixPersonDao mixPersonDao) {
		this.mixPersonDao = mixPersonDao;
	}

	public void addWebMixPerson(Webmixperson mixperson) {
		mixPersonDao.addWebMixPerson(mixperson);
	}

	public List<Webmixperson> selectMixperson(String factNo, String yymm,
			int page, int rows) {
		return mixPersonDao.selectMixperson(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return mixPersonDao.totlePage(factNo, yymm);
	}

	public Webmixperson findById(WebmixpersonId id) {
		return mixPersonDao.findById(id);
	}

	public List<Webmixperson> selByYymm(String yymm, String factNo) {
		return mixPersonDao.selByYymm(yymm, factNo);
	}

	public void delete(WebmixpersonId id) {
		mixPersonDao.delete(id);
	}

	public Webmixperson selBycan(String factNo, Date date, String factCode) {
		return mixPersonDao.selBycan(factNo, date, factCode);
	}

	public boolean check(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		DateFormat format=new SimpleDateFormat("yyyyMM");
		Date date=null;
		try {
			date = format.parse(yymm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Webmixperson person = this.selBycan(factNo,date,factCode);
		if (person != null) {
			flag=true;
		}
		return flag;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return mixPersonDao.findPageBean(pageSize, page, factNo, yymm);
	}

	public List<Webmixperson> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		return mixPersonDao.findByAny(factNo, beginDate, endDate);
	}

	public Webmixperson findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return mixPersonDao.findById(factNo, factCode, yymm);
	}

}
