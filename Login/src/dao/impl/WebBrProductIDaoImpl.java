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
import entity.VWebBrProandest;
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
	
	public void delete_pro(WebBrProductitem pro,KyzExpectmatmLog log){
		super.delete(pro,log);
	}
	public void delete_est(WebBrEstimatingitem est,KyzExpectmatmLog log){
		super.delete(est,log);
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

	/**
	 * 日期:2017/7/24
	 * 描述:
	 */
	
	
	public List<WebBrProductitem> findByfactNoAndYymmdd_print(String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();		
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebBrProductitem where 1=1 ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get(factNo);
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append( "and id.webBrProduct.id.factNo=:factno ");
			map.put("factno",factNo);
		}
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd>=:yymmdd ");
			map.put("yymmdd",yymmdd);
		}
		if(yymmdd!=null&&!"".equals(yymmdd2)){
			hql.append(" and id.yymmdd<=:yymmdd2");
			map.put("yymmdd2",yymmdd2);
		}
		hql.append(" order by id.webBrProduct.id.factNo,id.webBrProduct.namec1,id.webBrProduct.namec2");
		List<WebBrProductitem>list=super.getAllWithNoPage(hql.toString(),map);
		for(WebBrProductitem obj:list){
			obj.getId().getWebBrProduct().getNamec1();
			obj.getId().getWebBrProduct().getNamec2();
			obj.getId().getWebBrProduct().getFactNo2().getFactSname();
		}
		return list;
	}

	/**
	 * 日期:2017/7/24
	 * 描述:
	 */
	
	
	public PageBean findPageBean_pro(int pageSize,int page,String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebBrProductitem where 1=1 ");
		hql2.append("select count(id.yymmdd) ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and id.webBrProduct.id.factNo=:factno");
			map.put("factno",factNo);
		}
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd>=:yymmdd");
			map.put("yymmdd",yymmdd);
		}
		if(yymmdd2!=null&&!"".equals(yymmdd2)){
			hql.append(" and id.yymmdd<=:yymmdd2");
			map.put("yymmdd2",yymmdd2);
		}
		hql2.append(hql);
		hql.append(" order by id.webBrProduct.id.factNo,id.yymmdd");
		
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
		List<WebBrProductitem>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebBrProductitem pro:list){
			pro.getId().getWebBrProduct().getId().getWebErpProductinFormation().getNamec1();
			pro.getId().getWebBrProduct().getId().getWebErpProductinFormation().getNamec2();
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		
		return bean;
	}
	
	public PageBean findPageBean_est(int pageSize,int page,String factNo,String yymmdd,String yymmdd2){
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebBrEstimatingitem where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and id.factNo=:factno ");
			map.put("factno",factNo);
		}
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd>=:yymmdd");
			map.put("yymmdd",yymmdd);
		}
		if(yymmdd2!=null&&!"".equals(yymmdd2)){
			hql.append(" and id.yymmdd<=:yymmdd2");
			map.put("yymmdd2",yymmdd2);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yymmdd ");
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null||allrow==0||page==0){
			allrow=super.getAllRowCount2(hql2.toString(),map);
			ActionContext.getContext().getSession().put("allrow",allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize,allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize,currentPage);
		List<WebBrEstimatingitem>list=super.getAllWithNoPage(hql.toString(),map);
		for(WebBrEstimatingitem item:list){
			item.getFactNo2().getFactSname();
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
	 * 日期:2017/7/24
	 * 描述:
	 */
	
	
	public List<VWebBrProandest> findByfactNoAndYymmdd_print2(String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWebBrProandest where 1=1 ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and id.factNo=:factno");
			map.put("factno",factNo);
		}
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd>=:yymmdd");
			map.put("yymmdd",yymmdd);
		}
		if(yymmdd!=null&&!"".equals(yymmdd2)){
			hql.append(" and id.yymmdd<=:yymmdd2");
			map.put("yymmdd2",yymmdd2);
		}
		hql.append(" order by id.factNo,id.factCode,id.yymmdd");
		List<VWebBrProandest>list=super.getAllWithNoPage(hql.toString(),map);
		for(VWebBrProandest obj:list){
			obj.getFactNo2().getFactSname();
		}
		return list;
	}

	/**
	 * 日期:2017/7/25
	 * 描述:
	 */
	
	
	public List<VWebBrProandest> findByfactCodeAndfactNoAndYymmdd_print2(String factNo,String factCode,String yymmdd){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWebBrProandest where 1=1 ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and id.factNo=:factno");
			map.put("factno",factNo);
		}
		if(factCode!=null&&!"".equals(factCode)&&!"all".equals(factCode)){
			hql.append(" and id.factCode=:factcode");
			map.put("factcode",factCode);
		}
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd=:yymmdd ");
			map.put("yymmdd",yymmdd);
		}
		hql.append(" order by id.factCode,id.factNo,id.yymmdd");
		List<VWebBrProandest>list=super.getAllWithNoPage(hql.toString(),map);
		for(VWebBrProandest obj:list){
			obj.getFactNo2().getFactSname();
		}
		return list;
	}

	/**
	 * 日期:2017/7/27
	 * 描述:
	 */
	
	
	public List<WebBrEstimatingitem> findEstByYymmdd(String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebBrEstimatingitem where 1=1 ");
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd>=:yymmdd");
			map.put("yymmdd",yymmdd);
		}
		if(yymmdd2!=null&&!"".equals(yymmdd2)){
			hql.append(" and id.yymmdd<=:yymmdd2");
			map.put("yymmdd2",yymmdd2);
		}
		hql.append(" order by id.factNo,id.factCode,id.yymmdd");
		List<WebBrEstimatingitem>list=super.getAllWithNoPage(hql.toString(),map);
		for(WebBrEstimatingitem item:list){
			item.getFactNo2().getFactSname();
		}
		return list;
	}

	/**
	 * 日期:2017/7/28
	 * 描述:
	 */
	
	
	public List<Object[]> findSumGroupByfCodeAndYymmdd(String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select id.factCode,id.yymmdd,sum(actualPairs),sum(estimatingPairs1),sum(estimatingPairs2),sum(estimatingPairs3) from WebBrEstimatingitem where 1=1 ");
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd>=:yymmdd");
			map.put("yymmdd",yymmdd);
		}
		if(yymmdd2!=null&&!"".equals(yymmdd2)){
			hql.append(" and id. yymmdd<=:yymmdd2");
			map.put("yymmdd2",yymmdd2);
		}
		hql.append(" group by id.factCode,id.yymmdd");
		List<Object[]>list=super.getAllWithNoPage(hql.toString(),map);
		return list;
	}

	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public PageBean findPageBean_proAndest(int pageSize,int page,String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWebBrProandest where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if(factNo==null&&"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and id. factNo=:factno ");
			map.put("factno",factNo);
		}
		if(yymmdd!=null&&!"".equals(yymmdd)){
			hql.append(" and id.yymmdd=:yymmdd ");
			map.put("yymmdd",yymmdd);
		}
		if(yymmdd2!=null&&!"".equals(yymmdd2)){
			hql.append(" and id.yymmdd=:yymmdd2 ");
			map.put("yymmdd2",yymmdd2);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yymmdd ");
		List<VWebBrProandest>list=super.getAllWithNoPage(hql.toString(),map);
		for(VWebBrProandest obj:list){
			obj.getFactNo2().getFactSname();
		}
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null||allrow==0||page==1){
			allrow=super.getAllRowCount2(hql2.toString(),map);
			ActionContext.getContext().getSession().put("allrow",allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize,allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize,currentPage);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		
		return bean;
	}

	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public List<Object[]> findPro(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		String hql="select id.webBrProduct.id.factNo,id.webBrProduct.id.webErpProductinFormation.itemid,id.yymmdd," +
				"inventory,orderNotin,actualUsed,createUser.id,createDate " +
				" from WebBrProductitem where id.webBrProduct.id.factNo=? and id.yymmdd=?";
		String[]objs={factNo,yymmdd};
		return super.findAll(hql,objs);
	}

	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public List<Object[]> findEst(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		String hql="select id.factNo,id.factCode,id.yymmdd," +
				"actualPairs,estimatingPairs1,estimatingPairs2,estimatingPairs3,createUser.id,createDate " +
				" from WebBrEstimatingitem where id.factNo=? and id.yymmdd=?";
		String[]objs={factNo,yymmdd};
		return super.findAll(hql,objs);
	}
	
	public List<WebBrProductitem> findPro2(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		String hql="from WebBrProductitem where id.webBrProduct.id.factNo=? and id.yymmdd=?";
		String[]objs={factNo,yymmdd};
		List<WebBrProductitem>list=super.findAll(hql,objs);
		for(WebBrProductitem obj:list){
			obj.getId().getWebBrProduct().getNamec1();
			obj.getId().getWebBrProduct().getNamec2();
		}
		return list;
	}

	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public List<WebBrEstimatingitem> findEst2(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		String hql="from WebBrEstimatingitem where id.factNo=? and id.yymmdd=?";
		String[]objs={factNo,yymmdd};
		List<WebBrEstimatingitem>list=super.findAll(hql,objs);
		return list;
	}

	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public WebBrProductitem findById_Pro(String factNo,Integer wid,String yymmdd){
		// TODO Auto-generated method stub
		String hql="from WebBrProductitem where id.webBrProduct.id.factNo=? and id.webBrProduct.id.webErpProductinFormation.itemid=? and id.yymmdd=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,factNo);
		query.setParameter(1,wid);
		query.setParameter(2,yymmdd);
		WebBrProductitem obj=(WebBrProductitem)query.uniqueResult();
		obj.getId().getWebBrProduct().getNamec1();
		obj.getId().getWebBrProduct().getNamec2();
		return obj;
		
	}

	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public WebBrEstimatingitem findById_Est(String factNo,String factCode,String yymmdd){
		// TODO Auto-generated method stub
		String hql="from WebBrEstimatingitem where id.factNo=? and id.factCode=? and id.yymmdd=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,factNo);
		query.setString(1,factCode);
		query.setString(2,yymmdd);
		WebBrEstimatingitem obj=(WebBrEstimatingitem)query.uniqueResult();
		return obj;		
	}

	/**
	 * 日期:2017/8/1
	 * 描述:
	 */
	
	
	public void update_pro(WebBrProductitem pro){
		// TODO Auto-generated method stub
		super.merge(pro);
	}

	/**
	 * 日期:2017/8/1
	 * 描述:
	 */
	
	
	public void update_est(WebBrEstimatingitem est){
		// TODO Auto-generated method stub
		super.merge(est);
	}

	

}
