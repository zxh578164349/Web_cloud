package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyzContactLetterDao;
import entity.KyzContactletter;
import entity.KyzExpectmatm;

public class KyzContactLetterDaoImpl extends Basedao implements IKyzContactLetterDao{

	public void add(KyzContactletter letter) {
		// TODO Auto-generated method stub
		super.merge(letter);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String visaSort,String billNo,String userNm,String timeCreate,String timeCreate2) {
		// TODO Auto-generated method stub
		int allRow=0;
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from KyzContactletter where 1=1 ");
		hql2.append("select count(id.factNo) ");
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
			hql.append(" and ymExpect>=:timecreate");
			map.put("timecreate", timeCreate);
		}
		if(timeCreate!=null&&!timeCreate.equals("")&&(timeCreate2!=null&&!timeCreate2.equals(""))){
			hql.append(" and ymExpect between :timecreate and :timecreate2");
			map.put("timecreate", timeCreate);
			map.put("timecreate2", timeCreate2);
		}
		if(timeCreate2!=null&&!timeCreate2.equals("")&&(timeCreate==null||timeCreate.equals(""))){
			hql.append(" and ymExpect<=:timecreate");
			map.put("timecreate2", timeCreate2);
		}
		if(factNo.equals("nothing")&&(visaSort==null||visaSort.equals(""))
				&&(billNo==null||billNo.equals(""))
				&&(timeCreate==null||timeCreate.equals(""))
				&&(timeCreate2==null||timeCreate2.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,ymExpect desc");
		int currentPage = PageBean.countCurrentPage(page);
		
	    Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");	    
	    if(rows!=null&&rows!=0&&page>0){
	     allRow=rows;	     
	    }else{
		 allRow= super.getAllRowCount2(hql2.toString(),map);
		 ActionContext.getContext().getSession().put("allRow", allRow);
		}
	    
	    //allRow= super.getAllRowCount(hql.toString(),map);
		int totalPage = PageBean.countTotalPage(pageSize,allRow);
		if (currentPage > totalPage){
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<KyzContactletter> list = super.queryForPage(hql.toString(), offset,length, map);
				
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public List<String> findBillNo(String factNo, String timeCreate) {
		// TODO Auto-generated method stub
		String hql="select id.billNo from KyzContactletter where id.factNo=? and ymExpect=?";
		String[]objs={factNo,timeCreate};
		return super.findAll(hql, objs);
	}

	public KyzContactletter findById(String factNo, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyzContactletter where id.factNo=? and id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, billNo);
		KyzContactletter letter=(KyzContactletter)query.uniqueResult();
		return letter;
	}

	public void delete(String factNo, String billNo) {
		// TODO Auto-generated method stub
		KyzContactletter letter=this.findById(factNo, billNo);
		super.delete(letter);
	}

	public String findTitleByBillno(String billNo) {
		// TODO Auto-generated method stub
		String hql="select title from KyzContactletter where id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		String title=(String)query.uniqueResult();
		return title;
	}

	public List<Object[]> findTitle(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select id.factNo,id.billNo,title from KyzContactletter where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		List<Object[]>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

}
