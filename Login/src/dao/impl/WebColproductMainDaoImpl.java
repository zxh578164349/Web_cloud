package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebColproductMainDao;
import entity.KyzExpectmatmLog;
import entity.WebColproductItems;
import entity.WebColproductMain;
import entity.WebUser;

public class WebColproductMainDaoImpl extends Basedao implements IWebColproductMainDao{

	public void add(WebColproductMain obj) {
		// TODO Auto-generated method stub
		super.merge(obj);
		
	}

	public WebColproductMain findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		String hql="from WebColproductMain where billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		WebColproductMain obj=(WebColproductMain)query.uniqueResult();
		if(obj!=null){
			obj.getWebColproductItemses().size();
			obj.getFactNo().getFactSname();
		}		
		return obj;
	}

	public void delete(String billNo, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebColproductMain obj=this.findByBillNo(billNo);
		super.delete(obj, log);
		
	}

	public PageBean findPageBean(int page, int pageSize, String factNo,
			String billNo, String dateA, String dateB) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		if(factNo==null||"".equals(factNo)){
			factNo=user.getFactno();
		}
		hql.append("from WebColproductItems where 1=1 ");
		hql2.append("select count(iid) ");
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and webColproductMain.factNo.factNo=:factNo ");
			map.put("factNo", factNo);
		}
		if(billNo!=null&&!"".equals(billNo)){
			hql.append(" and billNo=:billNo ");
			map.put("billNo", billNo);
		}
		if(dateA!=null&&!"".equals(dateA)){
			hql.append(" and webColproductMain.colDateMain>=:dateA ");
			map.put("dateA", dateA);
		}
		if(dateB!=null&&!"".equals(dateB)){
			hql.append(" and webColproductMain.colDateMain<=:dateB ");
			map.put("dateB", dateB);
		}
		if("Y".equals(user.getAdminMk())){
			hql.append(" and webColproductMain.webUserByCreateUserFid.id=:uid ");
			map.put("uid", user.getId());
		}
		hql2.append(hql);
		hql.append(" order by webColproductMain.factNo.factNo,webColproductMain.billNo ");
		
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
		List<WebColproductItems>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebColproductItems obj:list){
			obj.getWebColproductMain().getWebUserByCreateUserFid().getName();
			obj.getWebColproductMain().getBillNo();
			obj.getWebColproductMain().getFactNo();
			obj.getWebColproductMain().getColDateMain();
			obj.getWebColproductMain().getVbm().getLastMk();

		}
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}
	
	public String findByfactNoACreatedate(String factNo, String createDate) {
		// TODO Auto-generated method stub
		String hql="select max(billNo) from WebColproductMain where factNo.factNo=? and createDate like ?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, createDate.substring(0, 8)+"%");
		String str=(String)query.uniqueResult();		
		return str;
	}

	public PageBean findPageBeanMain(int page, int pageSize, String factNo,
			String billNo, String dateA, String dateB, String title) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		if(factNo==null||"".equals(factNo)){
			factNo=user.getFactno();
		}
		hql.append("from WebColproductMain where 1=1 ");
		hql2.append("select count(billNo) ");
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and factNo.factNo=:factNo ");
			map.put("factNo", factNo);
		}
		if(billNo!=null&&!"".equals(billNo)){
			hql.append(" and billNo=:billNo ");
			map.put("billNo", billNo);
		}
		if(dateA!=null&&!"".equals(dateA)){
			hql.append(" and colDateMain>=:dateA ");
			map.put("dateA", dateA);
		}
		if(dateB!=null&&!"".equals(dateB)){
			hql.append(" and colDateMain<=:dateB ");
			map.put("dateB", dateB);
		}
		if(title!=null&&!"".equals(title)){
			hql.append(" and title=:title ");
			map.put("title", title);
		}
		if(!"Y".equals(user.getAdminMk())){
			hql.append(" and webUserByCreateUserFid.id=:uid ");
			map.put("uid", user.getId());
		}
		hql2.append(hql);
		hql.append(" order by factNo.factNo,billNo ");
		
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
		List<WebColproductMain>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebColproductMain obj:list){
			obj.getWebUserByCreateUserFid().getName();			
			obj.getVbm().getLastMk();
			obj.getFactNo().getFactSname();

		}
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}
	
	

}
