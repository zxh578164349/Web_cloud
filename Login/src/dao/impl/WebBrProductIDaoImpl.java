/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebBrProductDao;
import entity.KyzExpectmatmLog;
import entity.WebBrEstimatingitem;
import entity.WebBrProduct;
import entity.WebBrProductitem;
import entity.WebBussinessletter;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebBrProductIDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/7/13 下午1:50:30   
 * 修改人：Administrator   
 * 修改时间：2017/7/13 下午1:50:30   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebBrProductIDaoImpl extends Basedao implements IWebBrProductDao{

	/**
	 * 日期:2017/7/13
	 * 描述:
	 */
	
	
	public List<Object[]> findByFactno(String factNo){
		// TODO Auto-generated method stub
		String hql="select id.webErpProductinFormation.itemid,namec1,namec2 from WebBrProduct where id.factNo=?";
		String[]objs={factNo};
		return super.findAll(hql,objs);
	}

	/**
	 * 日期:2017/7/13
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize,int page,String factNo){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebBrProduct where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and id.factNo=:factno ");
			map.put("factno",factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,itemcategory,namec1,namec2");
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null||allrow==0||page==0){
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebBrProduct>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebBrProduct pro:list){
			pro.getFactNo2().getFactSname();
			pro.getId().getWebErpProductinFormation().getItemcategoryname();
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
	 * 日期:2017/7/17
	 * 描述:
	 */
	
	
	public void add(List<WebBrProduct> listbrpro){
		// TODO Auto-generated method stub		
		super.addList(listbrpro);
	}

	/**
	 * 日期:2017/7/18
	 * 描述:
	 */
	
	
	public void delete(WebBrProduct obj,KyzExpectmatmLog log){
		// TODO Auto-generated method stub
		super.delete(obj,log);
	}

	/**
	 * 日期:2017/7/18
	 * 描述:
	 */
	
	
	public WebBrProduct findById(String factNo,Integer wid){
		// TODO Auto-generated method stub
		String hql="from WebBrProduct where id.factNo=? and id.webErpProductinFormation.itemid=?";
		Query query=getSession().createQuery(hql);
	    query.setString(0,factNo);
	    query.setInteger(1,wid);
		WebBrProduct obj=(WebBrProduct)query.uniqueResult();		
		return obj;
	}

	/**
	 * 日期:2017/7/19
	 * 描述:
	 */
	
	
	public void add2(List<WebBrProductitem> listitem){
		// TODO Auto-generated method stub
		super.addList(listitem);
	}

	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public Integer findByfactNoAndyymmdd(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		String hql="select id.yymmdd from WebBrProductitem where id.webBrProduct.id.factNo=? and id.yymmdd=?";
		String[]objs={factNo,yymmdd};
		List<Object>list=super.findAll(hql,objs);
		return list.size();
	}

	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public Integer findByfactNoAndyymmdd2(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		String hql="select id.yymmdd from WebBrEstimatingitem where id.factNo=? and id.yymmdd=?";
		String[]objs={factNo,yymmdd};
		List<Object>list=super.findAll(hql,objs);
		return list.size();
	}

	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public void add3(List<WebBrEstimatingitem> listest){
		// TODO Auto-generated method stub
		super.addList(listest);
	}
	
	public Integer findByFactNo2(String factNo){
		String hql="select id.webErpProductinFormation.itemid from WebBrProduct where id.factNo=?";
		String[]objs={factNo};
		List<Object>list=super.findAll(hql,objs);
		return list.size();
	}

	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public void add2_3(List list){
		// TODO Auto-generated method stub
		super.addList(list);
	}

}
