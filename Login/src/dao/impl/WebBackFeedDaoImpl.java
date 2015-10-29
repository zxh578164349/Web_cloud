package dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;
import dao.Basedao;
import dao.IWebBackFeedDao;
import entity.Webbackfeed;
import entity.WebbackfeedId;
import entity.Webcost;

public class WebBackFeedDaoImpl extends Basedao implements IWebBackFeedDao {

	public void add(Webbackfeed feed) {
		// TODO Auto-generated method stub
		super.merge(feed);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from Webbackfeed where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append("and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append("and to_char(id.yymm,'yyyymm')=:yymm ");
			map.put("yymm", yymm);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yymm desc");
		int currentPage = PageBean.countCurrentPage(page);
		int allRow = super.getAllRowCount2(hql2.toString(), map);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<Webbackfeed> list = super.queryForPage(hql.toString(), offset,
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

	public List<Webbackfeed> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from Webbackfeed where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append("and id.factNo='" + factNo + "'");
		}
		return super.findAll(hql.toString(), null);
	}

	public Webbackfeed findById(WebbackfeedId id) {
		// TODO Auto-generated method stub
		String hql = "from Webbackfeed where id. factNo=? and id.factCode=? and id.yymm=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getFactCode());
		query.setDate(2, id.getYymm());
		return (Webbackfeed) query.uniqueResult();
	}

	public void delete(WebbackfeedId id) {
		// TODO Auto-generated method stub
		Webbackfeed feed = this.findById(id);
		super.delete(feed);

	}

	public Webbackfeed findById(String factNo, String factCode, Date yymm) {
		// TODO Auto-generated method stub
		String hql="from Webbackfeed where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setDate(2, yymm);
		Webbackfeed feed=(Webbackfeed)query.uniqueResult();		
		return feed;
	}

	public List<Webbackfeed> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Webbackfeed where 1=1");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("nothing")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(beginDate!=null&&!beginDate.equals("")){
			hql.append(" and to_char(id.yymm,'yyyymm')>=:begindate");
			map.put("begindate", beginDate);
		}
		if(endDate!=null&&!endDate.equals("")){
			hql.append(" and to_char(id.yymm,'yyyymm')<=:enddate");
			map.put("enddate", endDate);
		}
		if(factNo.equals("nothing")&&(beginDate==null||beginDate.equals(""))&&(endDate==null||endDate.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo,id.factCode,id.yymm");
		List<Webbackfeed>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	public Webbackfeed findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from Webbackfeed where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyymm')=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		Webbackfeed feed=(Webbackfeed)query.uniqueResult();
		return feed;
	}

}
