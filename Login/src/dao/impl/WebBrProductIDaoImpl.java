/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebBrProductDao;
import entity.KyzExpectmatmLog;
import entity.WebBrProduct;
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
	
	
	public List<WebBrProduct> findByFactno(String factNo){
		// TODO Auto-generated method stub
		String hql="from WebBrProduct where factNo.factNo=?";
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
		hql2.append("select count(wid) ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and factNo.factNo=:factno ");
			map.put("factno",factNo);
		}
		hql2.append(hql);
		hql.append(" order by factNo,itemcategory,namec1,namec2");
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
			pro.getWeberppr2().getItemcategoryname();
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
		Transaction tx=null;
		try{
			tx=getSession().beginTransaction();
			for(int i=0;i<listbrpro.size();i++){
				getSession().merge(listbrpro.get(i));
				if(i%10==0){
					getSession().flush();
					getSession().clear();
				}
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
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
	
	
	public WebBrProduct findById(Integer wid){
		// TODO Auto-generated method stub
		WebBrProduct obj=(WebBrProduct)super.findById(wid,WebBrProduct.class);		
		return obj;
	}

}
