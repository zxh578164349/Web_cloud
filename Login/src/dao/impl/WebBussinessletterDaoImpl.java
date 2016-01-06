package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebBussinessletterDao;
import entity.WebBussinessletter;

public class WebBussinessletterDaoImpl extends Basedao implements IWebBussinessletterDao{

	public void add(WebBussinessletter buss) {
		// TODO Auto-generated method stub
		super.merge(buss);
		
	}

	public PageBean findPageBean(int pageSize, int page, String billNo,String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebBussinessletter where 1=1 ");
		hql2.append("select count(blNo) ");
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and blNo=:blno");
			map.put("blno", billNo);
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by factNo,blNo");
		//int rows=0;
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
		List list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public String findMaxBillNo(String factNo, String createDate) {
		// TODO Auto-generated method stub
		String hql="select max(blNo) from WebBussinessletter where factNo=? and createDate=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, createDate);
		return (String)query.uniqueResult();
	}

}
