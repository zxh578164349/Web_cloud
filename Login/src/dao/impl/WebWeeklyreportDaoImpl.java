package dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebWeeklyreportDao;
import entity.KyzExpectmatmLog;
import entity.WebUser;
import entity.WebWeeklyreport;

public class WebWeeklyreportDaoImpl extends Basedao implements IWebWeeklyreportDao{

	public void add(WebWeeklyreport obj) {
		// TODO Auto-generated method stub
		super.merge(obj);
	}

	public PageBean findPageBean(int page, int pageSize,int uid, String sdate,int bid) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		hql.append("from WebWeeklyreport where 1=1 ");
		hql2.append("select count(RId) ");
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		uid=user.getId();
		if(uid!=0){
			hql.append(" and webUser.id=:uid");
			map.put("uid", uid);
		}
		if(sdate!=null&&!"".equals(sdate)){
			hql.append(" and SDate=:SDate");
			map.put("SDate", sdate);
		}
		if(bid!=0){
			hql.append(" and webErpBrankProcess.id=:bid");
			map.put("bid", bid);
		}
		hql2.append(hql);
		hql.append(" order by SDate desc");
		
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null){
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebWeeklyreport>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebWeeklyreport obj:list){
			if(obj.getWebErpBrankProcess()!=null){
				obj.getWebErpBrankProcess().getName();
			}
			obj.getWebUser().getName();
			
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public WebWeeklyreport findById(int rid) {
		// TODO Auto-generated method stub
		String hql="from WebWeeklyreport where RId=?";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, rid);
		WebWeeklyreport obj=(WebWeeklyreport)query.uniqueResult();
		obj.getWebErpBrankProcess().getName();
		return obj;
	}

	public WebWeeklyreport findByUidASdateABid(int uid, String sdate,int bid) {
		// TODO Auto-generated method stub
		String hql="from WebWeeklyreport where webUser.id=? and SDate=? and webErpBrankProcess.id=?";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, uid);
		query.setString(1, sdate);
		query.setInteger(2, bid);
		WebWeeklyreport obj=(WebWeeklyreport)query.uniqueResult();
		return obj;
	}

	public void delete(int rid, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebWeeklyreport obj=this.findById(rid);
		super.delete(obj, log);
	}

	public List<WebWeeklyreport> findOneATwo(int uid,int bid, String sdate,
			String sdate_last) {
		// TODO Auto-generated method stub
		String hql="from WebWeeklyreport where webUser.id=? and webErpBrankProcess.id=? and SDate in(?,?) and createDate>=? order by SDate";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Calendar cal=Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Query query=getSession().createQuery(hql);
		query.setInteger(0, uid);
		query.setInteger(1, bid);
		query.setString(2, sdate);
		query.setString(3, sdate_last);
		query.setString(4, sdf.format(cal.getTime()));
		List<WebWeeklyreport>list=query.list();
		return list;
	}

	public List<WebWeeklyreport> findByEdate(String sdate) {
		// TODO Auto-generated method stub
		String hql="from WebWeeklyreport where SDate=?";
		String[]objs={sdate};
		List<WebWeeklyreport>list=super.findAll(hql, objs);
		for(WebWeeklyreport obj:list){
			obj.getWebUser().getName();
			obj.getWebErpBrankProcess().getName();
		}
		return list;
	}

}
