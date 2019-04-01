package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebSamplescheduleDao;
import entity.WebSampleschedule;
import entity.WebTestmouldregistrationform;

public class WebSamplescheduleDaoImpl extends Basedao implements IWebSamplescheduleDao{

	public PageBean findPageBean(int page, int pageSize, String stype,
			String dateA,  String dateB,String samplelevel, String brand,
			String customer) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		//WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
		hql.append("from WebSampleschedule where 1=1 ");
		hql2.append("select count(ssid) ");
		
		if(stype!=null&&!"".equals(stype)){
			hql.append(" and SType=:stype ");
			map.put("stype", stype);
		}
		if(dateA!=null&&!"".equals(dateA)){
			hql.append(" and dateA>=:dateA ");
			map.put("dateA", dateA);
		}
		if(dateB!=null&&!"".equals(dateB)){
			hql.append(" and dateA<=:dateB ");
			map.put("dateB", dateB);
		}
		if(samplelevel!=null&&!"".equals(samplelevel)){
			hql.append(" and samplelevel=:samplelevel ");
			map.put("samplelevel", samplelevel);
		}
		if(customer!=null&&!"".equals(customer)){
			hql.append(" and customer=:customer ");
			map.put("customer", customer);
		}
		if(brand!=null&&!"".equals(brand)){
			hql.append(" and brand=:brand ");
			map.put("brand", brand);
		}
		
		hql2.append(hql);
		hql.append(" order by stype,dateA,customer,brand ");
		
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
		List<WebSampleschedule>list=super.queryForPage(hql.toString(), offset, pageSize, map);		
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public void addMore(List<WebSampleschedule> list) {
		// TODO Auto-generated method stub
		try{
			for(WebSampleschedule obj:list){
				super.merge(obj);
			}
			getSession().flush();
			getSession().clear();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
