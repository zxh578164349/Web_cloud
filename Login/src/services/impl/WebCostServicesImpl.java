package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebCostDao;
import entity.Webcost;
import entity.WebcostId;
import services.IWebCostServices;
import util.PageBean;

public class WebCostServicesImpl implements IWebCostServices {
	private IWebCostDao costDao;

	public void setCostDao(IWebCostDao costDao) {
		this.costDao = costDao;
	}

	public void add(Webcost cost) {
		// TODO Auto-generated method stub
		costDao.add(cost);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return costDao.findPageBean(pageSize, page, factNo, yymm);
	}

	public List<Webcost> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return costDao.findByFactNo(factNo);
	}

	public Webcost findById(WebcostId id) {
		// TODO Auto-generated method stub
		return costDao.findById(id);
	}

	public void delete(WebcostId id) {
		// TODO Auto-generated method stub
		costDao.delete(id);

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
		List<Webcost> list = this.findByFactNo(factNo);		
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

	public List<Webcost> findByAny(String factNo, String beginDate,String endDate) {
		// TODO Auto-generated method stub
		return costDao.findByAny(factNo, beginDate,endDate);
	}

	public Webcost findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return costDao.findById(factNo, factCode, yymm);
	}
}	
