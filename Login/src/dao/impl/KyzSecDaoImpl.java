package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyzSecDao;
import entity.KyzExpectmatmLog;
import entity.KyzSec;

public class KyzSecDaoImpl extends Basedao implements IKyzSecDao{

	public void add(KyzSec kyzsec) {
		// TODO Auto-generated method stub
		super.merge(kyzsec);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String secNo) {
		// TODO Auto-generated method stub
		final Map<String ,Object>map=new HashMap<String,Object>();
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		int allRow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");
		hql.append("from KyzSec where 1=1 ");
		hql2.append("select count(id.secNo) ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if(!"tw".equals(factNo)&&!"nothing".equals(factNo)){
			hql.append(" and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if(secNo!=null&&!secNo.equals("")){
			hql.append(" and id.secNo=:secno ");
			map.put("secno",secNo);
		}
		if(factNo.equals("nothing")&&(secNo==null||secNo.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.secNo");
		if(rows!=null&&page>0){
			allRow=rows;
		}else{
			allRow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allRow", allRow);
		}
		int currenPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		if(currenPage>totalPage){
			currenPage=totalPage;
		}
		//int length=pageSize;
		int offset=PageBean.countOffset(pageSize, currenPage);
		List<KyzSec>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currenPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		//bean.init();
		return bean;
	}

	public List<KyzSec> findByFactno(String factNo) {
		// TODO Auto-generated method stub
		/*String hql="from KyzSec where id.factNo=?";
		String[]objs={factNo};
		List<KyzSec>list=super.findAll(hql, objs);*/
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from KyzSec where 1=1 ");
		if(factNo!=null&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		return super.getAllWithNoPage(hql.toString(), map);
	}

	public KyzSec findById(String factNo, String secNo) {
		// TODO Auto-generated method stub
		String hql="from KyzSec where id.factNo=? and id.secNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, secNo);
		return (KyzSec)query.uniqueResult();
	}

	public void delete(String factNo, String secNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		KyzSec kyzsec=this.findById(factNo, secNo);
		super.delete(kyzsec,delLog);
	}
}
