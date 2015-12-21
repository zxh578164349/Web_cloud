package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebTabpomDao;
import entity.WebTabpom;

public class WebTabpomDaoImpl extends Basedao implements IWebTabpomDao{

	public void add(WebTabpom tabpom) {
		// TODO Auto-generated method stub
		super.merge(tabpom);
	}

	public WebTabpom findById(String pomNo) {
		// TODO Auto-generated method stub
		String hql="from WebTabpom where pomNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, pomNo);
		return (WebTabpom)query.uniqueResult();
	}

	public void delete(WebTabpom tabpom) {
		// TODO Auto-generated method stub
		super.delete(tabpom);
	}

	public void delete(String pomNo) {
		// TODO Auto-generated method stub
		super.delete(findById(pomNo));
	}

	public PageBean findPageBean(int pageSize, int page, String pomName,
			String brank) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebTabpom where 1=1 ");
		hql2.append("select count(pomNo) ");
		if(pomName!=null&&!pomName.equals("")){
			hql.append(" and pomName=:pomname");
			map.put("pomname", pomName);
		}
		if(brank!=null&&!brank.equals("")){
			hql.append(" and brank=:brank");
			map.put("brank", brank);
		}
		hql2.append(hql);
		hql.append(" order by pomNo");
		int allrows=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		if(rows!=null&&page>0){
			allrows=rows;
		}else{
			allrows=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("rows", allrows);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrows);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebTabpom>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).getList_fact().size();
			}
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrows);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);	
		return bean;
	}

	
	/**
	 * 隻查詢主鍵，快速判斷是否爲空
	 * @param pomNO
	 * @return
	 */
	public String findPomNoById(String pomNo) {
		// TODO Auto-generated method stub
		String hql="select pomNo from WebTabpom where pomNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, pomNo);
		return (String)query.uniqueResult();
	}
	
}
