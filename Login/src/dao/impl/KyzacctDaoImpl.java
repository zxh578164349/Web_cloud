package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyzacctDao;
import entity.KyzAcct;
import entity.KyzExpectmatmLog;

public class KyzacctDaoImpl extends Basedao implements IKyzacctDao{

	public void add(KyzAcct kyzacct) {
		// TODO Auto-generated method stub
		super.merge(kyzacct);
	}

	public PageBean findPageBean(int pageSize, int page, String acctNo,String acctName) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		hql.append("from KyzAcct where 1=1 ");
		hql2.append("select count(acctNo)  ");
		if(acctNo!=null&&!acctNo.equals("")){
			hql.append(" and acctNo=:acctno");
			map.put("acctno", acctNo);
		}	
		if(acctName!=null&&!acctName.equals("")){
			hql.append(" and acctName like :acctname");
			map.put("acctname", "%"+acctName+"%");
		}
		hql2.append(hql);
		hql.append(" order by acctNo");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		//int length=pageSize;
		List<KyzAcct>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		bean.setList(list);
		return bean;
	}

	public KyzAcct findById(String acctNo) {
		// TODO Auto-generated method stub
		String hql="from KyzAcct where acctNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, acctNo);
		return (KyzAcct)query.uniqueResult();
	}

	public void delete(String acctNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		KyzAcct kyzacct=this.findById(acctNo);
		super.delete(kyzacct,delLog);
	}

	public List<KyzAcct> findAll() {
		// TODO Auto-generated method stub
		String hql="from KyzAcct";
		return super.findAll(hql, null);
	}

	/**
	 * 日期:2016/3/9
	 * 描述:
	 */
	
	
	public String findAcctNo(String acctNo) {
		// TODO Auto-generated method stub
		String hql="select acctNo from KyzAcct where acctNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, acctNo);		
		return (String)query.uniqueResult();
	}

}
