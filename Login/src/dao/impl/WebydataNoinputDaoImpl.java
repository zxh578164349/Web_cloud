/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebydataNoinputDao;
import entity.WebydataNoinput;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebydataNoinputDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/8 下午3:45:00   
 * 修改人：Administrator   
 * 修改时间：2016/4/8 下午3:45:00   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebydataNoinputDaoImpl extends Basedao implements IWebydataNoinputDao{

	/**
	 * 日期:2016/4/8
	 * 描述:
	 */
	
	
	public void addLarge(List<WebydataNoinput> list) {
		// TODO Auto-generated method stub
		try{
			for(int a=0;a<list.size();a++){
				getSession().merge(list.get(a));
				if(a%10==0){
					getSession().flush();
					getSession().clear();
				}
			}
		}catch(Exception e){
			System.out.println("*********************"+e+"*********************");
		}
		
		
	}

	/**
	 * 日期:2016/4/8
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymmdd, String yymmdd2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebydataNoinput where 1=1 ");
		hql2.append(" select count(*) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.fact.id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if(yymmdd!=null&&!yymmdd.equals("")){
			hql.append(" and id.yymmdd<=:yymmdd1 ");
			map.put("yymmdd1", yymmdd);
		}
		if(yymmdd2!=null&&!yymmdd2.equals("")){
			hql.append(" and id.yymmdd>=:yymmdd2 ");
			map.put("yymmdd2", yymmdd2);
		}
		hql2.append(hql);
		hql.append(" order by id.fact.id.factNo,id.fact.id.factArea,id.yymmdd desc");
		int currentPage=PageBean.countCurrentPage(page);
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null){
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebydataNoinput>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		//解決延遲問題
		for(WebydataNoinput obj:list){
			obj.getId().getFact().getFactSname();
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	/**
	 * 日期:2016/4/18
	 * 描述:
	 */
	
	
	public List<WebydataNoinput> print(String factNo, String yymmdd, String yymmdd2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebydataNoinput where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.fact.id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if(yymmdd!=null&&!yymmdd.equals("")){
			hql.append(" and id.yymmdd>=:yymmdd ");
			map.put("yymmdd", yymmdd);
		}
		if(yymmdd2!=null&&!yymmdd2.equals("")){
			hql.append(" and id.yymmdd<=:yymmdd2 ");
			map.put("yymmdd2", yymmdd2);
		}
		List<WebydataNoinput>list=super.getAllWithNoPage(hql.toString(), map);
		//解決延遲問題
		for(WebydataNoinput obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

}
