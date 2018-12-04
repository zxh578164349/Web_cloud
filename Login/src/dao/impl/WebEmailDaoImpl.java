package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebEmailDao;
import entity.KyzExpectmatmLog;
import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;
import entity.WebEmailType;

public class WebEmailDaoImpl extends Basedao implements IWebEmailDao {

	/*public List<WebEmail> getEmail(String sendif) {
		String hql = "from WebEmail where sengif=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, sendif);
		return query.list();
	}

	public List<WebCc> getCC(String sendif) {
		String hql = "from WebCc where sendif=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, sendif);
		return query.list();
	}*/

	/**
	 * 日期:2016/6/24
	 * 描述:
	 */
	
	
	public List<WebEmailAll> findEmail(int eid,String emailOrCc){
		// TODO Auto-generated method stub
		String hql="from WebEmailAll where emailTypeFk.eid=? and emailOrCc=? and emailMk='Y'";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, eid);
		query.setString(1, emailOrCc);
		return query.list();
	}

	public List<WebEmailType> findAllEmailTypes() {
		// TODO Auto-generated method stub
		String hql="from WebEmailType";
		List<WebEmailType>list=super.findAll(hql, null);
		return list;
	}

	public void add(WebEmailAll obj) {
		// TODO Auto-generated method stub
		super.merge(obj);
		
	}

	public PageBean findPageBean(int page, int pageSize, int eid, String email,
			String uname,String emailOrcc,String emailMk) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebEmailAll where 1=1 ");
		hql2.append("select count(eid) ");
		if(eid!=0){
			hql.append(" and emailTypeFk.eid=:eid ");
			map.put("eid", eid);
		}
		if(email!=null&&!"".equals(email)){
			hql.append(" and email=:email ");
			map.put("email", email);
		}
		if(uname!=null&&!"".equals(uname)){
			hql.append(" and username=:uname ");
			map.put("uname", uname);
		}
		if(emailOrcc!=null&&!"".equals(emailOrcc)){
			hql.append(" and emailOrcc=:emailOrcc ");
			map.put("emailOrcc", emailOrcc);
		}
		if(emailMk!=null&&!"".equals(emailMk)){
			hql.append(" and emailMk=:emailMk ");
			map.put("emailMk", emailMk);
		}
		//hql.append(" and emailMk='Y'");
		hql2.append(hql);
		hql.append(" order by emailTypeFk.eid,eid");
		
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
		List<WebEmailAll>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(WebEmailAll obj:list){
			obj.getEmailTypeFk().getEname();			
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;		
	}

	public WebEmailAll findById(int eid) {
		// TODO Auto-generated method stub
		String hql="from WebEmailAll where eid=?";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, eid);
		WebEmailAll obj=(WebEmailAll)query.uniqueResult();
		obj.getEmailTypeFk().getEname();
		return obj;
	}

	public void delete(int eid, KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebEmailAll obj=this.findById(eid);
		super.delete(obj, log);
	}

	public WebEmailAll findByEmailAndEtypeAndEmailOrCc(String email, int type,
			String emailOrCc) {
		// TODO Auto-generated method stub
		String hql="from WebEmailAll where lower(email)=? and emailTypeFk.eid=? and emailOrCc=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, email.toLowerCase());
		query.setInteger(1, type);
		query.setString(2, emailOrCc);
		WebEmailAll obj=(WebEmailAll)query.uniqueResult();
		return obj;
	}

	/**
	 * 日期:2016/6/24
	 * 描述:
	 */
	
	
	/*public List<WebEmailAll> findCC(String emailType){
		// TODO Auto-generated method stub
		String hql="from WebEmailAll where toCc='Y' and emailType=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, emailType);
		return query.list();
	}*/

}
