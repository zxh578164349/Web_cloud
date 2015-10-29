package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IKpifactDao;
import entity.Kpifact;

public class KpifactDaoImpl extends Basedao implements IKpifactDao{

	public void add(Kpifact kpi) {
		// TODO Auto-generated method stub
		super.merge(kpi);
		
	}

	public Kpifact findById(String factNo, String factCode, String yyyy) {
		// TODO Auto-generated method stub
		String hql="from Kpifact where id.factNo=? and id.factCode=? and id.yyyy=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yyyy);
		Kpifact kpi=(Kpifact)query.uniqueResult();
		return kpi;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yyyy) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Kpifact where 1=1");
		hql2.append("select count(id.factNo) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yyyy!=null&&!yyyy.equals("")){
			hql.append(" and id.yyyy like:yyyy");
			map.put("yyyy", yyyy+"%");
		}
		if(factNo.equals("nothing")&&(yyyy==null||yyyy.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yyyy desc");
		Integer rows=(Integer)ActionContext.getContext().get("allrows");
		int allrows=0;
		if(rows!=null&&page>1){
			allrows=rows;
		}else{
			allrows=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrows", allrows);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrows);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<Kpifact>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrows);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public void delete(String factNo, String factCode, String yyyy) {
		// TODO Auto-generated method stub
		Kpifact kpi=this.findById(factNo, factCode, yyyy);
		super.delete(kpi);
	}

	public List<String> findDateByFactNo(String factNo,String yyyy) {
		// TODO Auto-generated method stub
		String hql="select distinct id.yyyy from Kpifact where id.factNo=? and id.yyyy like ? order by id.yyyy";
		String[]objs={factNo,yyyy+"%"};
		return super.findAll(hql, objs);
	}

}
