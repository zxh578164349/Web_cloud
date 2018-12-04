/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebDepartmentDao;
import entity.WebDepartment;
import entity.WebType;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebDepartmentDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/12/15 下午2:47:44   
 * 修改人：Administrator   
 * 修改时间：2017/12/15 下午2:47:44   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebDepartmentDaoImpl extends Basedao implements IWebDepartmentDao{

	/**
	 * 日期:2017/12/15
	 * 描述:
	 */
	
	
	public List<WebDepartment> findWebDepartmentByFactNo(String factNo){
		// TODO Auto-generated method stub
		String hql="from WebDepartment where factNo=?";
		String[]objs={factNo};
		List<WebDepartment>list=super.findAll(hql,objs);
		return list;
	}

	public PageBean findPageBean(int page, int pageSize, String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		hql.append("from WebDepartment where 1=1");
		hql2.append("select count(depId) ");
		
		if(factNo==null||factNo.equals("")){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by factNo,depId");
		
		
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
		List<WebDepartment>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
		
	}

	public WebDepartment findById(String depId) {
		// TODO Auto-generated method stub
		String hql="from WebDepartment where to_char(depId)=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, depId);
		WebDepartment obj=(WebDepartment)query.uniqueResult();
		return obj;
	}

	public void add(WebDepartment dep) {
		// TODO Auto-generated method stub
		super.merge(dep);
	}

}
