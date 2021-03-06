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
import dao.IWebremittancelistDao;
import entity.KyzExpectmatmLog;
import entity.WebUser;
import entity.Webremittancelist;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebremittancelistDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/25 下午3:28:55   
 * 修改人：Administrator   
 * 修改时间：2016/4/25 下午3:28:55   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebremittancelistDaoImpl extends Basedao implements IWebremittancelistDao{

	/**
	 * 日期:2016/4/25
	 * 描述:
	 */
	
	
	public void add(Webremittancelist obj) {
		// TODO Auto-generated method stub
		super.merge(obj);
		
	}

	/**
	 * 日期:2016/4/25
	 * 描述:
	 */
	
	
	public void delete(String billNo,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		Webremittancelist obj=this.findById(billNo);
		super.delete(obj,log);
	}

	/**
	 * 日期:2016/4/25
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String visaTypem,
			String factNo, String billNo,WebUser user) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Webremittancelist where 1=1 ");
		hql2.append("select count(*) ");
		if(visaTypem!=null&&!visaTypem.equals("")){
			hql.append(" and webtype.id.typeNo=:typeno");
			map.put("typeno",visaTypem);
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and factNo=:factno");
			map.put("factno", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and billNo=:billno");
			map.put("billno", billNo);
		}
		String adminMk=user.getAdminMk()==null?"no":user.getAdminMk();
		if(!adminMk.equals("Y")){
			hql.append(" and username2=:username2");
			map.put("username2", user.getUsername());
		}
		if(factNo.equals("nothing")&&(visaTypem==null||visaTypem.equals(""))&&(billNo==null||billNo.equals(""))){
			hql.append(" and factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by factNo,webtype.id.typeNo,billNo");
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
		List<Webremittancelist>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(Webremittancelist obj :list){
			obj.getWebtype().getTypeName();
			obj.getVbm().getLastMk();
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
	 * 日期:2016/4/25
	 * 描述:
	 */
	
	
	public Webremittancelist findById(String billNo) {
		// TODO Auto-generated method stub
		String hql="from Webremittancelist where billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		Webremittancelist obj=(Webremittancelist)query.uniqueResult();
		obj.getWebtype().getTypeName();
		obj.getWebremittancelistses().size();
		return obj;
	}
	
	/**
	 * 不查找webtype類型名稱
	 * 日期:2016/4/29
	 * 描述:
	 */
	public Webremittancelist findById_notype(String billNo) {
		// TODO Auto-generated method stub
		String hql="from Webremittancelist where billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		Webremittancelist obj=(Webremittancelist)query.uniqueResult();
		obj.getWebremittancelistses().size();
		return obj;
	}

	/**
	 * 日期:2016/4/26
	 * 描述:
	 */
	
	
	public String findMaxBillNo(String factNo, String yymm) {
		// TODO Auto-generated method stub
		String hql="select max(billNo) from Webremittancelist where factNo=? and yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, yymm);
		String result=(String)query.uniqueResult();
		return result;
	}

}
