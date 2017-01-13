package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebuserEmailADao;
import entity.KyzExpectmatmLog;
import entity.WebuserEmailA;

public class WebuserEmailADaoImpl extends Basedao implements IWebuserEmailADao{

	public void add(WebuserEmailA email) {
		// TODO Auto-generated method stub
		super.merge(email);
	}

	public WebuserEmailA findById(String factNo, String email, String emailPwd,
			String visaSort,String typeMk) {
		// TODO Auto-generated method stub
		String hql="from WebuserEmailA where id.factNo=? and id.email=? and id.emailpassword=? and id.visaSort=? and id.typeMk=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, email);
		query.setString(2, emailPwd);
		query.setString(3, visaSort);
		query.setString(4,typeMk);
		return (WebuserEmailA)query.uniqueResult();
	}

	public void delete(String factNo, String email, String emailPwd,
			String visaSort,String typeMk,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebuserEmailA emaila=findById(factNo,email,emailPwd,visaSort,typeMk);
		super.delete(emaila,delLog);
		
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String email, String visaSort) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map <String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from WebuserEmailA where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(email!=null&&!email.equals("")){
			hql.append(" and lower(id.email)=:email");
			map.put("email", email.toLowerCase());
		}
		if(visaSort!=null&&!visaSort.equals("")){
			hql.append(" and id.visaSort=:visasort");
			map.put("visasort", visaSort);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.typeMk,id.visaSort,id.email");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("rows", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebuserEmailA>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public List<String> findByEmail(String factNo, String email, String visaSort) {
		// TODO Auto-generated method stub
		String hql="select id.emailpassword from WebuserEmailA where id.factNo=? and lower(id.email)=? and id.visaSort=? and id.typeMk='0'";
		String[]objs={factNo,email.toLowerCase(),visaSort.substring(0,2)};
		return super.findAll(hql, objs);
	}
	
	public List<String> findByEmail2(String factNo, String email, String visaSort) {
		// TODO Auto-generated method stub
		String hql="select id.emailpassword from WebuserEmailA where id.factNo=? and lower(id.email)=? and id.visaSort=? and id.typeMk='1'";
		String[]objs={factNo,email.toLowerCase(),visaSort.substring(0,2)};
		return super.findAll(hql, objs);
	}

}
