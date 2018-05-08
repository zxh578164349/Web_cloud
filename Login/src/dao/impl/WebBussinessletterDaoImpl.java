package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebBussinessletterDao;
import entity.KyzExpectmatmLog;
import entity.WebBussinessletter;
import entity.WebUser;

public class WebBussinessletterDaoImpl extends Basedao implements IWebBussinessletterDao{

	public void add(WebBussinessletter buss) {
		// TODO Auto-generated method stub
		super.merge(buss);
		
	}

	public PageBean findPageBean(int pageSize, int page, String billNo,String factNo,WebUser user) {
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
		String adminmk=user.getAdminMk()==null?"no":user.getAdminMk();
		if(!adminmk.equals("Y")){
			hql.append(" and userAccount=:useraccount");
			map.put("useraccount", user.getUsername());
		}
		hql.append(" and delMk is null ");
		hql2.append(hql);
		hql.append(" order by factNo,createDate desc");
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
		List<WebBussinessletter>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebBussinessletter letter:list){
			letter.getVbm().getLastMk();
			if(letter.getUnit()==null&&letter.getDepId().getDepName()!=null){
				letter.setUnit(letter.getDepId().getDepName());
			}			
		}
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

	public WebBussinessletter findById(String billNo) {
		// TODO Auto-generated method stub
		String hql="from WebBussinessletter where blNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		WebBussinessletter obj=(WebBussinessletter)query.uniqueResult();
		obj.getDepId().getDepName();
		return obj;
	}

	public void delete(WebBussinessletter letter,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		super.delete(letter,delLog);
		
	}

	public void delete(String billNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebBussinessletter letter=this.findById(billNo);
		super.delete(letter,delLog);
	}

	/**
	 * 
	 * 日期:2016/2/17
	 * 描述:兩箇月之前沒有添加刪除標記的函文
	 */
	public List<WebBussinessletter> findBefor2Month() {
		// TODO Auto-generated method stub
		String hql="from WebBussinessletter where createDate<to_char(add_months(sysdate,-2),'yyyymmdd') and delMk is null order by createDate";
		return super.findAll(hql, null);
	}

	/**
	 * 
	 * 日期:2016/2/17
	 * 描述:大批量添加
	 */
	public void addLarge(List<WebBussinessletter> list) {
		// TODO Auto-generated method stub
		try{
			for(int i=0;i<list.size();i++){
				list.get(i).setDelMk("1");
				getSession().merge(list.get(i));
				if(i%10==0){
					getSession().flush();
					getSession().clear();
				}
			}
		
		}catch(Exception e){
			System.out.println("dao*******************************"+e+"******************************dao");
		}
	}

}
