package services.impl;

import java.util.List;

import dao.IWebWeeklyreportDao;
import entity.KyzExpectmatmLog;
import entity.WebWeeklyreport;
import services.IWebWeeklyreportServices;
import util.PageBean;

public class WebWeeklyreportServicesImpl implements IWebWeeklyreportServices{
	private IWebWeeklyreportDao webweeklyreportdao;

	public void setWebweeklyreportdao(IWebWeeklyreportDao webweeklyreportdao) {
		this.webweeklyreportdao = webweeklyreportdao;
	}

	public void add(WebWeeklyreport obj) {
		// TODO Auto-generated method stub
		webweeklyreportdao.add(obj);
	}

	public PageBean findPageBean(int page, int pageSize, int uid,
			String sdate,int bid) {
		// TODO Auto-generated method stub
		return webweeklyreportdao.findPageBean(page, pageSize, uid, sdate,bid);
	}

	public WebWeeklyreport findByUidASdateABid(int uid, String sdate,int bid) {
		// TODO Auto-generated method stub
		return webweeklyreportdao.findByUidASdateABid(uid, sdate,bid);
	}

	public WebWeeklyreport findById(int rid) {
		// TODO Auto-generated method stub
		return webweeklyreportdao.findById(rid);
	}

	public void delete(int rid, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webweeklyreportdao.delete(rid, log);
	}

	public List<WebWeeklyreport> findOneATwo(int uid,int bid, String sdate,
			String sdate_last) {
		// TODO Auto-generated method stub
		return webweeklyreportdao.findOneATwo(uid,bid, sdate, sdate_last);
	}
	
	public List<WebWeeklyreport> findByEdate(String sdate) {
		// TODO Auto-generated method stub
		return webweeklyreportdao.findByEdate(sdate);
	}


	public List<WebWeeklyreport> findByEdate(String sdate,String sdate2) {
		// TODO Auto-generated method stub
		return webweeklyreportdao.findByEdate(sdate,sdate2);
	}

	

	
	
	
	

}
