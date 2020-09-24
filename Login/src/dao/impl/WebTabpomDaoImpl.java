package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebTabpomDao;
import entity.KyzExpectmatmLog;
import entity.VWebFact;
import entity.WebTabpom;
import entity.WebTabpomfile;

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
		WebTabpom obj=(WebTabpom)query.uniqueResult();
		/**hibernate延遲問題解決**/		
		obj.getWebTabpomfiles().size();
		obj.getWebBrank().getName();
		
		/**hibernate延遲問題解決**/
		
		return obj;
	}

	public void delete(WebTabpom tabpom,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		super.delete(tabpom,delLog);
	}

	public void delete(String pomNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		super.delete(findById(pomNo),delLog);
	}

	public PageBean findPageBean(int pageSize, int page, String pomNo,
			String brank,String yymm,String yymm2,String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebTabpom where 1=1 ");
		hql2.append("select count(pomNo) ");
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(pomNo!=null&&!pomNo.equals("")){
			hql.append(" and pomNo=:pomNo");
			map.put("pomNo", pomNo);
		}
		if(brank!=null&&!brank.equals("")){
			hql.append(" and webBrank.sysno=:brank");
			map.put("brank", brank);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and tabpomDate>=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and tabpomDate<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and formulaId.factNo.factNo=:factNo");
			map.put("factNo",factNo);
		}
		hql2.append(hql);
		hql.append(" order by tabpomDate");
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
		/***解決延遲加載問題****/
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).getWebBrank().getName();
				list.get(i).getFormulaId().getVbm();
			}
		}
		/***解決延遲加載問題****/
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

	public List<String> findPomNos(String brank,String tabpomDate ) {
		// TODO Auto-generated method stub
		String hql="select pomNo from WebTabpom where webBrank.id=? and tabpomDate like ? order by pomNo desc";
		Object[]objs={Integer.parseInt(brank),tabpomDate+"%"};
		return super.findAll(hql, objs);
	}

	public List<WebTabpom> findByAny(String pomName, String brank,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebTabpom where 1=1 ");
		if(pomName!=null&&!pomName.equals("")){
			hql.append(" and pomName=:pomname");
			map.put("pomname", pomName);
		}
		if(brank!=null&&!brank.equals("")){
			hql.append(" and webBrank.BNo=:brank ");
			map.put("brank", brank);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and tabpomDate>=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and tabpomDate<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		hql.append(" order by tabpomDate desc");
		List<WebTabpom>list=super.getAllWithNoPage(hql.toString(), map);
		/***解決延遲加載問題****/
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).getWebBrank().getName();								
			}
		}
		/***解決延遲加載問題****/
		return list;
	}
	
}
