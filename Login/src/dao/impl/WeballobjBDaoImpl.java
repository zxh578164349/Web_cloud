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
import dao.IWeballobjBDao;
import entity.KyzExpectmatmLog;
import entity.VKpiWebprofitloss;
import entity.VWeballobjasumwebyield;
import entity.VWeballobjasumwebyield2019;
import entity.VWeballobjbStorage;
import entity.WeballobjB;

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
public class WeballobjBDaoImpl extends Basedao implements IWeballobjBDao{

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public void addMore(List<WeballobjB>list) {
		// TODO Auto-generated method stub
		try{
			for(WeballobjB obj:list){
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
		hql.append("from WeballobjB where 1=1 ");
		hql2.append("select count(id.yymm) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
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
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))&&(yymm2==null||yymm2.equals(""))){
			hql.append(" and id.fact.id.factNo=:factno");
			map.put("factno", factNo);
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
		List<WeballobjB>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WeballobjB obj:list){
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
	 * 日期:2016/4/21
	 * 描述:
	 */
	
	
	public List<WeballobjB> findAllobj(String factNo, String yymm, String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WeballobjB where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!"all".equals(factNo)){
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
		hql.append(" order by id.yymm,id.fact.id.factArea");
		List<WeballobjB>list=super.getAllWithNoPage(hql.toString(), map);
		//解決緩存問題
		for(WeballobjB obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}
	
	/*public List<VWeballobjasumwebyield>findAllobj2(String factNo,String yymm,String yymm2){
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from VWeballobjasumwebyield where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!"all".equals(factNo)){
			hql.append(" and id.factNo=:factNo");
			map.put("factNo", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2");
			map.put("yymm2", yymm2);
		}
		hql.append(" order by id.yymm,id.factCode");
		List<VWeballobjasumwebyield>list=super.getAllWithNoPage(hql.toString(), map);
		for(VWeballobjasumwebyield obj:list){
			obj.getFact().getFactSname();
		}
		return list;
		
	}*/

	/**
	 * 日期:2016/4/22
	 * 描述:
	 */
	
	
	public WeballobjB findById(String factNo, String factcode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from WeballobjB where id.fact.id.factNo=? and id.fact.id.factArea=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factcode);
		query.setString(2, yymm);
		return (WeballobjB)query.uniqueResult();
	}

	/**
	 * 日期:2016/4/22
	 * 描述:
	 */
	
	
	public void delete(String factNo,String factCode,String yymm,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WeballobjB obj=this.findById(factNo, factCode, yymm);
		super.delete(obj,log);
	}

	/**
	 * 日期:2016/10/13
	 * 描述:
	 */
	
	
	public List<WeballobjB> findObj(String yymm,String yymm2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WeballobjB where 1=1 ");
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm ");
			map.put("yymm",yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2");
			map.put("yymm2",yymm2);
		}
		return super.getAllWithNoPage(hql.toString(),map);
		
	}

	/**
	 * 日期:2017/1/17
	 * 描述:
	 */
	
	
	public List<String> findFactCodes(String factNo,String yymm,String yymm2){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct id.fact.id.factArea from WeballobjB where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.fact.id.factNo=:factNo");
			map.put("factNo",factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm>=:yymm");
			map.put("yymm",yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and id.yymm<=:yymm2");
			map.put("yymm2",yymm2);
		}
		List<String>list=super.getAllWithNoPage(hql.toString(),map);
		return list;
	}

	public List<WeballobjB> findWeballobjB(String yymm) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WeballobjB where 1=1 ");
		/*if(list_factcode!=null&&list_factcode.size()>0){
			hql.append(" and id.fact.id.factArea in (:list_factcode) ");
			map.put("list_factcode",list_factcode);
		}*/
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm=:yymm ");
			map.put("yymm",yymm);
		}
		hql.append(" order by id.fact.orderNo,id.fact.fcodeIndex ");
		List<WeballobjB>list=super.getAllWithNoPage(hql.toString(),map);
		for(WeballobjB obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

	public List<WeballobjB> findobjA41(String yymm) {
		// TODO Auto-generated method stub
		String hql="from WeballobjB where id.yymm like ? order by id.fact.orderNo,id.fact.fcodeIndex,id.yymm";
		String[]objs={yymm+"%"};
		List<WeballobjB>list=super.findAll(hql, objs);
		for(WeballobjB obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

	public List<WeballobjB> findProDiff(String factNo, String yymm, String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		//WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
		hql.append("from WeballobjB where 1=1 ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(factNo!=null&&!"".equals(factNo)&&!"tw".equals(factNo)){
			hql.append(" and id.fact.id.factNo=:factNo");
			map.put("factNo", factNo);
		}
		if(yymm!=null&&!"".equals(yymm)){
			hql.append(" and id.yymm>=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!"".equals(yymm2)){
			hql.append(" and id.yymm<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		hql.append(" order by id.yymm,id.fact.id.factNo,id.fact.id.factArea ");
				
		List<WeballobjB>list=super.getAllWithNoPage(hql.toString(), map);
		for(WeballobjB obj:list){
			obj.getId().getFact().getFactSname();
		}
		return list;
	}

	public List<VWeballobjbStorage> findStorage(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWeballobjbStorage where id.yymm like ? order by id.yymm";
		String[]objs={yymm+"%"};
		List<VWeballobjbStorage>list=super.findAll(hql, objs);
		for(VWeballobjbStorage obj:list){
			obj.getFactNo2().getFactSname();
		}		
		return list;
	}

	

}
