package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;

import com.opensymphony.xwork2.ActionContext;

import dao.Basedao;
import dao.IWebObjsBDao;
import entity.KyzExpectmatmLog;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjA3;
import entity.VWebobjBYdate;
import entity.VWebydatabyfcode2;
import entity.WebObjsB;

public class WebObjsBDaoImpl extends Basedao implements IWebObjsBDao{

	public List<VWebobjBYdate> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebobjBYdate where id.yymmdd like ? order by id.webFact.id.factNo,id.webFact.id.factArea";
		String[]objs={yymm+"%"};
		List<VWebobjBYdate>list=super.findAll(hql, objs);
		return list;
	}

	public List<VWebydatabyfcode2> findByYymm2(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebydatabyfcode2 where id.yymmdd like ?";
		String[]objs={yymm+"%"};
		List<VWebydatabyfcode2>list=super.findAll(hql, objs);
		return list;
	}
	
	
	
	public void addMore(List<WebObjsB> list) {
		// TODO Auto-generated method stub
		try{
			for(WebObjsB obj:list){
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
		hql.append("from WebObjsB where 1=1 ");
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
		List<WebObjsB>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebObjsB obj:list){
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
	
	public WebObjsB findById(String factNo, String factcode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from WebObjsB where id.webFact.id.factNo=? and id.webFact.id.factArea=? and id.yymmdd=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factcode);
		query.setString(2, yymm);
		return (WebObjsB)query.uniqueResult();
	}

	public void delete(String factNo, String factCode, String yymm,
			KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebObjsB obj=findById(factNo,factCode,yymm);
		super.delete(obj, log);
		
	}

	public List<WebObjsB> findByYymm(String factno, String yymm) {
		// TODO Auto-generated method stub
		String hql="from WebObjsB where id.webFact.id.factNo=? and id.yymmdd like ? order by id.webFact.fcodeIndex,id.webFact.orderNo,id.yymmdd";
		String[]objs={factno,yymm+"%"};
		List<WebObjsB> list=super.findAll(hql, objs);
		return list;
	}
	
	

	public List<VWebobjA> findByVwebobja(String yymm) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<VWebobjA2> findByVwebobja2(String yymm) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<VWebobjA3> findByVwebobja3(String yymmdd) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebObjsB> findObjByDay(String yymmdd) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebObjsB> findObjByMonth(String yymm) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String[]> findNoInput(String yymmdd) {
		// TODO Auto-generated method stub
		return null;
	}

}
