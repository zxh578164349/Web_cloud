package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyzPettyDao;
import entity.KyzExpectmatmLog;
import entity.KyzPetty;

public class KyzPettyDaoImpl extends Basedao implements IKyzPettyDao{

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String billNo, String createTime) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		hql.append("from KyzPetty where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and id.billNo=:billno");
			map.put("billno", billNo);
		}
		if(createTime!=null&&!createTime.equals("")){
			hql.append(" and substr(datePaybill,1,6)=:datepaybill");
			map.put("datepaybill", createTime);
		}
		if(factNo.equals("nothing")&&(billNo==null||billNo.equals(""))&&(createTime==null||createTime.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo,datePaybill,itemNo");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount(hql.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<KyzPetty>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setTotalPage(totalPage);
		bean.setPageSize(pageSize);
		return bean;
	}

	public void add(KyzPetty kyzpetty) {
		// TODO Auto-generated method stub
		super.merge(kyzpetty);
	}

	public List<KyzPetty> findByFactNoAndPayDate(String factNo, String payDate) {
		// TODO Auto-generated method stub
		String hql="from KyzPetty where id.factNo=? and datePaybill=?";
		String[]objs={factNo,payDate};
		return super.findAll(hql, objs);
	}

	public KyzPetty findById(String factNo, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyzPetty where id.factNo=? and id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, billNo);
		return (KyzPetty)query.uniqueResult();
	}

	public void delete(String factNo, String billNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		KyzPetty kyzpetty=this.findById(factNo, billNo);
		super.delete(kyzpetty,delLog);
	}

	public List<KyzPetty> findByAnyThing(String factNo,String dateTime,String dateTime2, String expenseMk,
			String taxmMk) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from KyzPetty where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if(dateTime!=null&&!dateTime.equals("")){
			hql.append(" and datePaybill>=:datetime");
			map.put("datetime", dateTime);
		}
		if(dateTime2!=null&&!dateTime2.equals("")){
			hql.append(" and datePaybill<=:datetimeEnd");
			map.put("datetimeEnd", dateTime2);
		}
		if(expenseMk!=null&&!expenseMk.equals("")){
			hql.append(" and expenseMk=:expensemk");
			map.put("expensemk", expenseMk);
		}
		if(taxmMk!=null&&!taxmMk.equals("")){
			hql.append(" and taxmMk=:taxmmk");
			map.put("taxmmk", taxmMk);
		}
		hql.append(" order by id.factNo,datePaybill,itemNo");
		return super.getAllWithNoPage(hql.toString(), map);
	}

}
