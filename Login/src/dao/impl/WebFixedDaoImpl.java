package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebFixedDao;
import entity.KyzExpectmatmLog;
import entity.WebFixed;
import entity.WebYieldData;

public class WebFixedDaoImpl extends Basedao implements IWebFixedDao {

	public void addWebFixed(WebFixed fix) {
		// TODO Auto-generated method stub
		super.merge(fix);

	}

	public PageBean findFixWithPage(int pageSize, int page, String factNo,
			String yymm,String yymm_s,String lostmk) {
		// TODO Auto-generated method stub
		int allRow = 0;
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from WebFixed where 1=1 ");
		hql2.append("select count(factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and factNo =:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and substr(addTime,1,6)=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm_s!=null&&!yymm_s.equals("")){
			hql.append(" and substr(checkdate,1,6)=:yymms ");
			map.put("yymms", yymm_s);
		}
		if(lostmk!=null&&!lostmk.equals("")&&!lostmk.equals("all")){
			hql.append(" and lostMk=:lostmk");
			map.put("lostmk", lostmk);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))&&(yymm_s==null||yymm_s.equals(""))&&(lostmk==null||lostmk.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(lostmk==null||lostmk.equals("")){
			hql.append(" and (lostMk like 'N%' or lostMk='' or lostMk=null) ");
		}
		hql2.append(hql);
		hql.append(" order by factNo,fixedassetsId");
		int currentPage = PageBean.countCurrentPage(page);

		Integer rows = (Integer) ActionContext.getContext().getSession()
				.get("allRow");
		if (rows != null && rows != 0 && page > 0) {
			allRow = rows;
		} else {
			allRow = super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allRow", allRow);
		}

		// allRow= super.getAllRowCount(hql.toString(),map);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<WebFixed> list = super.queryForPage(hql.toString(), offset,
				length, map);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public WebFixed findById(String id) {
		// TODO Auto-generated method stub
		String hql = "from WebFixed where fixedassetsId=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		return (WebFixed) query.uniqueResult();
	}

	public void deleteWebFixed(String id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebFixed fix = this.findById(id);
		super.delete(fix,delLog);

	}

	public List findById2(String majorid) {
		// TODO Auto-generated method stub
		String hql = "select assetscoding from WebFixed where majorId=?";
		String[] objs = { majorid };
		return super.findAll(hql, objs);
	}

	public List findAllFixedassetsId() {
		// TODO Auto-generated method stub
		String hql = "select fixedassetsId from WebFixed";
		return super.findAll(hql, null);
	}

	public List<WebFixed> findByFactNo(String factNo, String yymm,String yymm_s,String lostmk) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from WebFixed where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and factNo =:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and substr(addTime,1,6)=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm_s!=null&&!yymm_s.equals("")){
			hql.append(" and substr(checkdate,1,6)=:yymms");
			map.put("yymms", yymm_s);
		}
		if(lostmk!=null&&!lostmk.equals("")&&!lostmk.equals("all")){
			hql.append(" and lostMk=:lostmk");
			map.put("lostmk", lostmk);
		}else{
			hql.append(" and (lostMk like 'N%' or lostMk='' or lostMk=null) ");
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))&&(yymm_s==null||yymm_s.equals(""))&&(lostmk==null||lostmk.equals(""))){
			hql.append(" and factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by factNo,fixedassetsId");
		return super.getAllWithNoPage(hql.toString(), map);
	}

	public String findByFixId(String fixedId) {
		// TODO Auto-generated method stub
		String hql="select fixedId from WebFixed where fixedId=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, fixedId);
		String id=(String)query.uniqueResult();
		return id;
	}

}
