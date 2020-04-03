package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebObjsCDao;
import entity.KyzExpectmatmLog;
import entity.WebObjsA;
import entity.WebObjsC;

public class WebObjsCDaoImpl extends Basedao implements IWebObjsCDao{

	public void add(WebObjsC obj) {
		// TODO Auto-generated method stub
		try{
			super.merge(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addMore(List<WebObjsC> list) {
		// TODO Auto-generated method stub
		try{
			for(WebObjsC obj:list){
				getSession().merge(obj);			
			}
			getSession().flush();
			getSession().clear();
		}catch(Exception e){
			System.out.println("**************************"+e+"**********************");
		}
		
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymmdd) {
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from WebObjsC where 1=1 ");
		hql2.append("select count(id.yymmdd) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and id.webFact.id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymmdd like:yymm");
			map.put("yymm", yymm+"%");
		}
		
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql.append(" and id.webFact.id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd =:yymmdd");
			map.put("yymmdd", yymmdd);
		}
		hql2.append(hql);
		hql.append(" order by id.webFact.id.factNo,id.webFact.id.factArea,id.yymmdd ");
		int currentPage=PageBean.countCurrentPage(page);		
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null){
			allrow=super.getAllRowCount2(hql2.toString(), map);
		}
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebObjsC>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebObjsC obj:list){
			obj.getId().getWebFact().getFactSname();
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public WebObjsC findById(String factNo, String factcode, String yymmdd) {
		// TODO Auto-generated method stub
		String hql="from WebObjsC where id.webFact.id.factNo=? and id.webFact.id.factArea=? and id.yymmdd=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factcode);
		query.setString(2, yymmdd);
		return (WebObjsC)query.uniqueResult();
	}

	public void delete(String factNo, String factCode, String yymm,
			KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebObjsC obj=findById(factNo,factCode,yymm);
		super.delete(obj, log);
		
	}

	public List<String[]> findNoInput(String yymmdd) {
		// TODO Auto-generated method stub
		String hql= "select factSname,id.factArea,id.factNo from WebFact where id.factNo||id.factArea not in "
				+ "(select id.webFact.id.factNo||id.webFact.id.factArea from WebObjsC  where id.yymmdd= ? )"
				+ " and factShow='0' order by orderNo,fcodeIndex";
		String[]objs={yymmdd};
		List<String[]>list=super.findAll(hql, objs);
		return list;
	}

	public List<WebObjsC> findObjByDay(String yymmdd) {
		// TODO Auto-generated method stub
		String hql="from WebObjsC where id.yymmdd=?";
		String[]objs={yymmdd};
		List<WebObjsC>list=super.findAll(hql, objs);
		for(WebObjsC obj:list){
			obj.getId().getWebFact().getFactSname();
		}
		return list;
	}

	

}
