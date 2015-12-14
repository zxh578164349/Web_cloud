package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebBackFeedDao;

import entity.Webbackfeed;
import entity.WebbackfeedId;
import services.IWebBackFeedServices;
import util.PageBean;

public class WebBackFeedServicesImpl implements IWebBackFeedServices {
	private IWebBackFeedDao feedDao;

	public void setFeedDao(IWebBackFeedDao feedDao) {
		this.feedDao = feedDao;
	}

	public void add(Webbackfeed feed) {
		// TODO Auto-generated method stub
		feedDao.add(feed);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return feedDao.findPageBean(pageSize, page, factNo, yymm,yymm2);
	}

	public List<Webbackfeed> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		return feedDao.findByFactNo(factNo);
	}

	public Webbackfeed findById(WebbackfeedId id) {
		// TODO Auto-generated method stub
		return feedDao.findById(id);
	}

	public void delete(WebbackfeedId id) {
		// TODO Auto-generated method stub
		feedDao.delete(id);
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
		List<Webbackfeed>list=this.findByFactNo(factNo);
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

	public Webbackfeed findById(String factNo, String factCode, Date yymm) {
		// TODO Auto-generated method stub
		return feedDao.findById(factNo, factCode, yymm);
	}

	public List<Webbackfeed> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		return feedDao.findByAny(factNo, beginDate, endDate);
	}

}
