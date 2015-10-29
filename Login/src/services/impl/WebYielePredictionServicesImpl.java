package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import dao.IWebYielePredictionDao;
import services.IWebYielePredictionServices;
import util.PageBean;
import entity.WebYielePrediction;
import entity.WebYielePredictionId;

public class WebYielePredictionServicesImpl implements
		IWebYielePredictionServices {
	private IWebYielePredictionDao preDao;

	public List<WebYielePrediction> findByFactcode(String factno,
			String factcode, String yymm) {
		// TODO Auto-generated method stub
		return preDao.findByFactcode(factno, factcode, yymm);
	}

	public void setPreDao(IWebYielePredictionDao preDao) {
		this.preDao = preDao;
	}

	public void addYPre(WebYielePrediction pre) {
		// TODO Auto-generated method stub
		preDao.addYPre(pre);
	}

	public WebYielePrediction findById(WebYielePredictionId id) {
		// TODO Auto-generated method stub
		return preDao.findById(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return preDao.findYpreWithPage(pageSize, page, factNo, yymm);
	}

	public void delete(WebYielePredictionId id) {
		// TODO Auto-generated method stub
		preDao.delete(id);
	}

	public List<WebYielePrediction> findAByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return preDao.findAByFactNo(factNo);
	}

	public List<WebYielePrediction> selectYPre(String factNo, String yymm,
			int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	public int totlePage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<WebYielePrediction> getAllWithNoPage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return preDao.getAllWithNoPage(factNo, yymm);
	}

	public List<WebYielePrediction> findNullYpre(String factno,
			String factcode, String yymm) {
		// TODO Auto-generated method stub
		return preDao.findNullYpre(factno, factcode, yymm);
	}

	public List<WebYielePrediction> findNullYpre(String yymm) {
		// TODO Auto-generated method stub
		return preDao.findNullYpre(yymm);
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
		List<WebYielePrediction>list=this.findAByFactNo(factNo);
		if (list.size() > 0) {//start if
			for (int i = 0; i < list.size(); i++){
				if (factCode.equals(list.get(i).getId().getFactCode())
						&& factNo.equals(list.get(i).getId().getFactNo())
						&& date.equals(list.get(i).getId().getYymm())) {																					
                    flag=true;
					break;
				}		    
	       }
      }//end if					
		return flag;
	}

}
