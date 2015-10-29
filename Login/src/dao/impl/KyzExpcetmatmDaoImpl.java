package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;

import com.lowagie.text.pdf.codec.Base64;
import com.opensymphony.xwork2.ActionContext;

import dao.Basedao;
import dao.IKyzExpectmatmDao;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmId;
import entity.KyzExpectmats;
import entity.WebFixed;

public class KyzExpcetmatmDaoImpl extends Basedao implements IKyzExpectmatmDao {

	public void add(KyzExpectmatm kyz) {
		// TODO Auto-generated method stub
		super.merge(kyz);

	}

	public List<String> findBillNo(String factNo, String timeCreate) {
		// TODO Auto-generated method stub
		String hql = "select id.billNo from KyzExpectmatm where id. factNo=? and to_char(timeCreate,'yyyyMMdd')=?";
		String[] objs = { factNo, timeCreate };
		return super.findAll(hql, objs);
	}

	public List<KyzExpectmatm> getList(String memoSmk) {
		String hql = "from KyzExpectmatm where id.billNo=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, memoSmk);
		return query.list();
	}

	public KyzExpectmatm findById(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.factNo=? and id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getBillNo());
		return (KyzExpectmatm)query.uniqueResult();
	}

	public PageBean findFixWithPage(int pageSize, int page, String factNo,
			String visaSort,String billNo,String userNm,String timeCreate,String timeCreate2) {
		// TODO Auto-generated method stub
		int allRow=0;
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from KyzExpectmatm where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			hql.append(" and visaType like:visaType ");
			map.put("visaType", visaSort+"%");
		}
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and id.billNo=:billNo ");
			map.put("billNo", billNo);
		}
		if(userNm!=null&&!userNm.equals("")&&!userNm.contains("ºÞ²z­û")){
			hql.append(" and userNm=:usernm");
			map.put("usernm", userNm);
		}
		if(timeCreate!=null&&!timeCreate.equals("")&&(timeCreate2==null||timeCreate2.equals(""))){
			hql.append(" and to_char(timeCreate,'yyyymmdd')>=:timecreate");
			map.put("timecreate", timeCreate);
		}
		if(timeCreate!=null&&!timeCreate.equals("")&&(timeCreate2!=null&&!timeCreate2.equals(""))){
			hql.append(" and to_char(timeCreate,'yyyymmdd') between :timecreate and :timecreate2");
			map.put("timecreate", timeCreate);
			map.put("timecreate2", timeCreate2);
		}
		if(timeCreate2!=null&&!timeCreate2.equals("")&&(timeCreate==null||timeCreate.equals(""))){
			hql.append(" and to_char(timeCreate,'yyyymmdd')<=:timecreate");
			map.put("timecreate2", timeCreate2);
		}
		if(factNo.equals("nothing")&&(visaSort==null||visaSort.equals(""))
				&&(billNo==null||billNo.equals(""))
				&&(timeCreate==null||timeCreate.equals(""))
				&&(timeCreate2==null||timeCreate2.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo,timeCreate desc");
		int currentPage = PageBean.countCurrentPage(page);
		
	    Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");	    
	    if(rows!=null&&rows!=0&&page>0){
	     allRow=rows;	     
	    }else{
		 allRow= super.getAllRowCount(hql.toString(),map);
		 ActionContext.getContext().getSession().put("allRow", allRow);
		}
	    
	    //allRow= super.getAllRowCount(hql.toString(),map);
		int totalPage = PageBean.countTotalPage(pageSize,allRow);
		if (currentPage > totalPage){
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<KyzExpectmatm> list = super.queryForPage(hql.toString(), offset,length, map);
				
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public void delete(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		KyzExpectmatm kyz=this.findById(id);
		super.delete(kyz);
		
	}

	public List<KyzExpectmatm> findByFactNo(String factno) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		hql.append("from KyzExpectmatm where 1=1 ");
		if(factno!=null&&!factno.equals("")&&!factno.equals("tw")){
			hql.append("and id.factNo='"+factno+"'");
		}		
		return super.findAll(hql.toString(),null);
	}

	public List<KyzExpectmatm> findById_Print(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.factNo=? and id.billNo=?";
		String[]objs={id.getFactNo(),id.getBillNo()};	
		return super.findAll(hql, objs);
	}

	public KyzExpectmatm findById2(String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		return (KyzExpectmatm)query.uniqueResult();
	}

	public String findTitleByBillno(String billNo) {
		// TODO Auto-generated method stub
		String hql="select memoSmk from KyzExpectmatm where id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		String title=(String)query.uniqueResult();
		return title;
	}
}
