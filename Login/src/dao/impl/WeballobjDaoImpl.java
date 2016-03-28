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
import dao.IWeballobjDao;
import entity.Weballobj;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WeballobjDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 上午11:14:59   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 上午11:14:59   
 * 修改备注：   
 * @version    
 *    
 **/
public class WeballobjDaoImpl extends Basedao implements IWeballobjDao{

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public void addMore(List<Weballobj>list) {
		// TODO Auto-generated method stub
		try{
			for(Weballobj obj:list){
				getSession().merge(obj);			
			}
			getSession().flush();
			getSession().clear();
		}catch(Exception e){
			System.out.println("**************************"+e+"**********************");
		}
		
	}

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		Map<String,Object>map=new HashMap<String,Object>();
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from Weballobj where 1=1 ");
		hql2.append("select count(id.yymm) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.fact.id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2");
			map.put("yymm2", yymm2);
		}
		hql2.append(hql);
		hql.append(" order by id.fact.id.factNo,id.fact.id.factArea,id.yymm ");
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
		List<Weballobj>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(Weballobj obj:list){
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

}
