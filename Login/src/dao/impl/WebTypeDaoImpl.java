package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;

import com.opensymphony.xwork2.ActionContext;

import dao.Basedao;
import dao.IWebTypeDao;
import entity.KyzExpectmatmLog;
import entity.WebType;

public class WebTypeDaoImpl extends Basedao implements IWebTypeDao{

	public void add(WebType type) {
		// TODO Auto-generated method stub
		super.merge(type);
	}

	public PageBean findPageBean(int page, int pageSize, String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		hql.append("from WebType where 1=1");
		hql2.append("select count(id.factNo) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" and delMk='0'");//0爲未刪除的類別
		hql2.append(hql);
		hql.append(" order by id.factNo,id.typeNo");
		int currentPage=PageBean.countCurrentPage(page);		
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebType>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public WebType findById(String factNo, String typeNo) {
		// TODO Auto-generated method stub
		String hql="from WebType where id.factNo=? and id.typeNo=? and delMk='0'";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, typeNo);
		WebType type=(WebType)query.uniqueResult();		
		if(type!=null){
			type.getList_kyzexp().size();
		}		
		return type;
	}

	public void delete(String factNo, String typeNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebType type=this.findById(factNo, typeNo);
		super.delete(type,delLog);
	}

	public List<WebType> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		/*********************************無過濾函文出差類（TR）20160203**************************************/
		String hql="from WebType where id.factNo=? and delMk='0'";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}
	public List<WebType> findByFactNo3(String factNo) {
		// TODO Auto-generated method stub
		/*********************************有過濾函文出差類（TR）20160203**************************************/
		String hql="from WebType where id.factNo=? and id.typeNo not in('TR')  and delMk='0'";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}

	public String findTypeNameById(String factNo, String typeNo) {
		// TODO Auto-generated method stub
		String hql="select typeName from WebType where id.factNo=? and id.typeNo=?  and delMk='0'";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, typeNo);
		String typeName=(String)query.uniqueResult();
		return typeName;
	}

	public List<WebType> findByFactNo2(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebType where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append("and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" and delMk='0'");
		return super.getAllWithNoPage(hql.toString(), map);
	}

	/**
	 * 日期:2016/2/26
	 * 描述:
	 */
	
	
	public void update(WebType type) {
		// TODO Auto-generated method stub
		super.update(type);
	}

	
	
	
	
	


}
