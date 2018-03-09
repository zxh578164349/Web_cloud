package services.impl;

import dao.IWebWeeklyreportDao;
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
			String createDate) {
		// TODO Auto-generated method stub
		return webweeklyreportdao.findPageBean(page, pageSize, uid, createDate);
	}

	

	
	
	
	

}
