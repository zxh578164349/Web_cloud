package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebObjsADao;
import entity.KyzExpectmatmLog;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjA3;
import entity.WebFact;
import entity.WebObjsA;
import entity.WeballobjB;

public class WebObjsADaoImpl extends Basedao implements IWebObjsADao{

	public void addMore(List<WebObjsA> list) {
		// TODO Auto-generated method stub
		try{
			for(WebObjsA obj:list){
				getSession().merge(obj);			
			}
			getSession().flush();
			getSession().clear();
		}catch(Exception e){
			System.out.println("**************************"+e+"**********************");
		}
		
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from WebObjsA where 1=1 ");
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
		List<WebObjsA>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebObjsA obj:list){
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
	
	public WebObjsA findById(String factNo, String factcode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from WebObjsA where id.webFact.id.factNo=? and id.webFact.id.factArea=? and id.yymmdd=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factcode);
		query.setString(2, yymm);
		return (WebObjsA)query.uniqueResult();
	}

	public void delete(String factNo, String factCode, String yymm,
			KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebObjsA obj=findById(factNo,factCode,yymm);
		super.delete(obj, log);
		
	}

	public List<WebObjsA> findByYymm(String factno, String yymm) {
		// TODO Auto-generated method stub
		String hql="from WebObjsA where id.webFact.id.factNo=? and id.yymmdd like ? order by id.webFact.fcodeIndex,id.webFact.orderNo,id.yymmdd";
		String[]objs={factno,yymm+"%"};
		List list=super.findAll(hql, objs);
		return list;
	}

	public List<VWebobjA> findByVwebobja(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebobjA where id.yymm=?";
		String[]objs={yymm};
		List<VWebobjA>list=super.findAll(hql, objs);
		for(VWebobjA obj:list){
			obj.getId().getWebFact().getFactSname();
		}
		return list;
	}

	public List<VWebobjA2> findByVwebobja2(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebobjA2 where id.yymm=?";
		String[]objs={yymm};
		List<VWebobjA2>list=super.findAll(hql, objs);
		for(VWebobjA2 obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

	public List<WebObjsA> findObjByDay(String yymmdd) {
		// TODO Auto-generated method stub
		String hql="from WebObjsA where id.yymmdd=?";
		String[]objs={yymmdd};
		List<WebObjsA>list=super.findAll(hql, objs);
		for(WebObjsA obj:list){
			obj.getId().getWebFact().getFactSname();
		}
		return list;
	}

	public List<VWebobjA3> findByVwebobja3(String yymmdd) {
		// TODO Auto-generated method stub
		String hql="from VWebobjA3 where id.yymmdd=?";
		String[]objs={yymmdd};
		List<VWebobjA3>list=super.findAll(hql, objs);
		for(VWebobjA3 obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

	public List<WebObjsA> findObjByMonth(String yymm) {
		// TODO Auto-generated method stub
		String hql="from WebObjsA where id.yymmdd like ? order by id.webFact.fcodeIndex,id.webFact.orderNo,id.yymmdd";
		String[]objs={yymm+"%"};
		List<WebObjsA> list=super.findAll(hql, objs);
		for(WebObjsA obj:list){
			obj.getId().getWebFact().getFactSname();
		}
		return list;
	}

	public List<String[]> findNoInput(String yymmdd) {
		// TODO Auto-generated method stub
		String hql= "select factSname,id.factArea,id.factNo from WebFact where id.factNo||id.factArea not in "
				+ "(select id.webFact.id.factNo||id.webFact.id.factArea from WebObjsA  where id.yymmdd= ? )"
				+ " and factShow='0' order by orderNo,fcodeIndex";
		String[]objs={yymmdd};
		List<String[]>list=super.findAll(hql, objs);
		return list;
	}
	

}
