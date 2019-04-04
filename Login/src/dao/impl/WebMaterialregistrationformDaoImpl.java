package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebMaterialregistrationformDao;
import entity.WebMaterialregistrationform;
import entity.WebMaterialregistrationitems;

public class WebMaterialregistrationformDaoImpl extends Basedao implements IWebMaterialregistrationformDao{

	public PageBean findPageBean(int page, int pageSize,String mtype, String sdate,
			String edate,String materielname) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		//WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
		hql.append("from WebMaterialregistrationitems where 1=1 ");
		hql2.append("select count(miid) ");
		
		if(mtype!=null&&!"".equals(mtype)){
			hql.append(" and webMaterialregistrationform.mtype=:mtype ");
			map.put("mtype", mtype);
		}
		if(sdate!=null&&!"".equals(sdate)){
			hql.append(" and webMaterialregistrationform.sdateA>=:sdate ");
			map.put("sdate", sdate);
		}
		if(edate!=null&&!"".equals(edate)){
			hql.append(" and webMaterialregistrationform.sdateA<=:edate ");
			map.put("edate", edate);
		}
		if(materielname!=null&&!"".equals(materielname)){
			hql.append(" and materielname like :materielname ");
			map.put("materielname", "%"+materielname+"%");
		}
		
		hql2.append(hql);
		hql.append(" order by webMaterialregistrationform.mtype,webMaterialregistrationform.sdateA,materielname ");
		
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
		List<WebMaterialregistrationitems>list=super.queryForPage(hql.toString(), offset, pageSize, map);	
		for(WebMaterialregistrationitems obj:list){
			obj.getWebMaterialregistrationform().getMtype();
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public void addMore(List<WebMaterialregistrationform> list) {
		// TODO Auto-generated method stub
		try{
			for(WebMaterialregistrationform obj:list){
				super.merge(obj);
			}
			getSession().flush();
			getSession().clear();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public List<String> findmtype() {
		// TODO Auto-generated method stub
		String hql="select distinct mtype from WebMaterialregistrationform";
		List<String>list=super.findAll(hql, null);
		return list;
	}

}
