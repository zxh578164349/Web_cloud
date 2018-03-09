package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebWeeklyreportDao;
import entity.WebUser;
import entity.WebWeeklyreport;

public class WebWeeklyreportDaoImpl extends Basedao implements IWebWeeklyreportDao{

	public void add(WebWeeklyreport obj) {
		// TODO Auto-generated method stub
		super.merge(obj);
	}

	public PageBean findPageBean(int page, int pageSize,int uid, String createDate) {
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
		if(createDate!=null&&!"".equals(createDate)){
			hql.append(" and createDate=:createDate");
			map.put("createDate", createDate);
		}
		hql2.append(hql);
		hql.append(" order by createDate");
		
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
		return obj;
	}

}
