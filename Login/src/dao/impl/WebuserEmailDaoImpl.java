package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebuserEmailDao;
import entity.KyzExpectmatmLog;
import entity.WebuserEmail;

public class WebuserEmailDaoImpl extends Basedao implements IWebuserEmailDao{

	

	public void add(WebuserEmail email) {
		// TODO Auto-generated method stub
		super.merge(email);
	}

	public WebuserEmail findById(String factNo, String email, String emailpwd,String typeMk) {
		// TODO Auto-generated method stub		
		String hql="from WebuserEmail where id.factNo=? and lower(id.email)=? and lower(id.emailpassword)=? and id.typeMk=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, email.toLowerCase());
		query.setString(2, emailpwd.toLowerCase());
		query.setString(3,typeMk);
		WebuserEmail obj=(WebuserEmail)query.uniqueResult();
		return obj;
	}

	public void delete(String factNo,String email,String emailpwd,String typeMk,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebuserEmail obj=this.findById(factNo, email, emailpwd,typeMk);
		super.delete(obj,delLog);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String email) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object> map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from WebuserEmail where 1=1");
		hql2.append("select count(id.factNo) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(email!=null&&!email.equals("")){
			hql.append(" and lower(id.email)=:email");
			map.put("email", email.toLowerCase());
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.typeMk,email");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("rows",allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebuserEmail>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public List<WebuserEmail> findByFactNoAEmailPwd(String factNo, String email) {
		// TODO Auto-generated method stub
		String hql="from WebuserEmail where id.factNo=? and lower(id.email)=?";
		String[]objs={factNo,email.toLowerCase()};
		return super.findAll(hql, objs);
	}

	public List<String> findByFactNoAEmailPwd2(String factNo, String email) {
		// TODO Auto-generated method stub
		String hql="select id.emailpassword from WebuserEmail where id.factNo=? and lower(id.email)=? and id.typeMk='0'";
		String[]objs={factNo,email.toLowerCase()};
		return super.findAll(hql, objs);
	}

	/**
	 * 日期:2017/1/12
	 * 描述:
	 */
	
	
	public List<String> findByFactNoAEmailPwd3(String factNo,String email){
		// TODO Auto-generated method stub
		String hql="select id.emailpassword from WebuserEmail where id.factNo=? and lower(id.email)=? and typeMk='1'";
		String[]objs={factNo,email.toLowerCase()};
		return super.findAll(hql, objs);
	}

}
