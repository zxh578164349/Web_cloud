package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebMix1Dao;

import entity.KyzExpectmatmLog;
import entity.WebYieldData;
import entity.WebYieldDataId;
import entity.Webmix1;
import entity.Webmix1Id;
import services.IWebMix1Services;
import util.PageBean;

public class WebMix1ServicesImpl implements IWebMix1Services {
	private IWebMix1Dao mix1Dao;

	public void setMix1Dao(IWebMix1Dao mix1Dao) {
		this.mix1Dao = mix1Dao;
	}

	public void add(WebYieldData mix1) {
		// TODO Auto-generated method stub
		mix1Dao.add(mix1);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return mix1Dao.findPageBean(pageSize, page, factNo, yymm);
	}

	public List<WebYieldData> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return mix1Dao.findByFactNo(factNo);
	}

	public WebYieldData findById(WebYieldDataId id) {
		// TODO Auto-generated method stub
		return mix1Dao.findById(id);
	}

	public void delete(WebYieldDataId id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		mix1Dao.delete(id,delLog);
	}

	public boolean check(String factNo, String factCode, String yymm)  {
		// TODO Auto-generated method stub
		Boolean flag=false;
		List<WebYieldData> list = this.findByFactNo(factNo);
		DateFormat format=new SimpleDateFormat("yyyyMMdd");
		Date date=null;
		try {
			date=format.parse(yymm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() > 0) {//start if
			for (int i = 0; i < list.size(); i++) {
				if (factCode.equals(list.get(i).getId().getFactCode())
						&& factNo.equals(list.get(i).getId()
								.getFactNo())
						&&
								date.equals(list.get(i).getId().getYymmdd())) {
					flag=true;
					break;
				} 
			}
		}//end if	
		return flag;
	}
   
}
