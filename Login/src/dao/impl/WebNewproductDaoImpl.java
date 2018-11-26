package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebNewproductDao;
import entity.KyzExpectmatmLog;
import entity.WebNewproduct;

public class WebNewproductDaoImpl extends Basedao implements IWebNewproductDao{

	public void add(WebNewproduct obj) {
		// TODO Auto-generated method stub
		super.merge(obj);
		
	}

	public PageBean findPageBean(int page,int pageSize,String factNo,String billNo,String createDateA,String createDateB) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebNewproduct where 1=1 ");
		hql2.append("select count(billNo) ");
		if(factNo==null||"".equals(factNo)){
			factNo = (String) ActionContext.getContext().getSession().get("factNo");			
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and factNo=:factNo");
			map.put("factNo", factNo);
		}
		if(billNo!=null&&!"".equals(billNo)){
			hql.append(" and billNo=:billNo");
			map.put("billNo", billNo);
		}
		if(createDateA!=null&&!"".equals(createDateA)){
			hql.append(" and createDate>=:createDateA ");
			map.put("createDateA", createDateA);
		}
		if(createDateB!=null&&!"".equals(createDateB)){
			hql.append(" and createDate<=:createDateB ");
			map.put("createDateB", createDateB);
		}
		hql2.append(hql);
		
		int allRow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");
		if(rows!=null&&rows!=0&&page>0){
			allRow=rows;
		}else{
			allRow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allRow", allRow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebNewproduct>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}
	
	public WebNewproduct findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		String hql="from WebNewproduct where billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		WebNewproduct obj=(WebNewproduct)query.uniqueResult();
		return obj;
	}
	
	public void delete(String billNo,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebNewproduct obj=this.findByBillNo(billNo);
		super.delete(obj, log);
		
		
	}

	

	public String findByfactNoACreatedate(String factNo, String createDate) {
		// TODO Auto-generated method stub
		String hql="select max(billNo) from WebNewproduct where factNo=? and createDate like ?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, createDate.substring(0, 8)+"%");
		String str=(String)query.uniqueResult();
		return str;
	}

}
