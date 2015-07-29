package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import services.IWebMix2Services;
import util.PageBean;
import dao.IWebMix2Dao;
import entity.Webmix2;
import entity.Webmix2Id;

public class WebMix2ServicesImpl implements IWebMix2Services {
	private IWebMix2Dao mix2Dao;

	public IWebMix2Dao getMix2Dao() {
		return mix2Dao;
	}

	public void setMix2Dao(IWebMix2Dao mix2Dao) {
		this.mix2Dao = mix2Dao;
	}

	public void addWebMix2(Webmix2 mix2) {
		mix2Dao.addWebMix2(mix2);
	}

	public List<Webmix2> selectMix2(String factNo, String yymm, int page,
			int rows) {
		return mix2Dao.selectMix2(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return mix2Dao.totlePage(factNo, yymm);
	}

	public Webmix2 findById(Webmix2Id id) {
		Webmix2 mix2 = mix2Dao.findById(id);
		return mix2;
	}

	public List<Webmix2> selByYymm(String yymm, String factNo) {
		return mix2Dao.selByYymm(yymm, factNo);
	}

	public void delete(Webmix2Id id) {
		mix2Dao.delete(id);
	}

	public Webmix2 selBycan(String factNo, Date date, String factCode) {
		return mix2Dao.selBycan(factNo, date, factCode);
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
		Webmix2 mix2s =this.selBycan(factNo, date, factCode);
		if (mix2s != null) {
			flag=true;
		}
		return flag;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return mix2Dao.findPageBean(pageSize, page, factNo, yymm);
	}

	public List<Webmix2> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		return mix2Dao.findByAny(factNo, beginDate, endDate);
	}

	public Webmix2 findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return mix2Dao.findById(factNo, factCode, yymm);
	}

}
