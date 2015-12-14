package dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebScraptDao;
import entity.Webmix2;
import entity.Webmixperson;
import entity.Webscrapt;
import entity.WebscraptId;

public class WebScraptDaoImpl extends Basedao implements IWebScraptDao {

	public void addWebScraptDao(Webscrapt scrapt) {
		super.merge(scrapt);
	}

	int size = 0;

	public List<Webscrapt> selectScrapt(String factNo, String yymm, int page,
			int rows) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date s = null;
		try {
			if (yymm != null) {
				s = format.parse(yymm);
			}
		} catch (ParseException e) {
		}
		String hql = null;
		Map<String, Object> map = new HashMap<String, Object>();
		hql = "from Webscrapt where 1=1 ";
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql += " and id.factNo =:factno ";
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql += " and id.yymm =:yymm ";
			map.put("yymm", s);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql+=" and id.factNo=:factno";
			map.put("factno", factNo);
		}
		hql += " order by id.yymm";
		Query query = getSession().createQuery(hql);
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		if (size == 0) {
			List<Webscrapt> sList = query.list();
			size = sList.size();
		}
		if (page == 0 && rows == 0) {
			query.setFirstResult((1 - 1) * 10);
			query.setMaxResults(10);
		} else {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}
		List<Webscrapt> sList = query.list();
		return sList;
	}

	public int totlePage(String factNo, String yymm) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date s = null;
		try {
			if (yymm != null) {
				s = format.parse(yymm);
			}
		} catch (ParseException e) {
		}
		String hql = null;
		Map<String, Object> map = new HashMap<String, Object>();
		hql = "from Webscrapt where 1=1 ";
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql += " and id.factNo =:factno ";
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql += " and id.yymm =:yymm ";
			map.put("yymm", s);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql+=" and id.factNo=:factno";
			map.put("factno", factNo);
					
		}
		Query query = getSession().createQuery(hql);
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		if (size == 0) {
			List<Webscrapt> sList = query.list();
			size = sList.size();
		}

		List<Webscrapt> sList = query.list();
		return sList.size();

	}

	public Webscrapt findById(WebscraptId id) {
		String hql = "from Webscrapt w where w.id.factNo=? and w.id.factCode=? and w.id.yymm=? ";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getFactCode());
		query.setDate(2, id.getYymm());
		Webscrapt scrapt = (Webscrapt) query.uniqueResult();
		return scrapt;
	}

	public List<Webscrapt> selByYymm(String yymm, String factNo) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Date s = null;
		Query query = null;
		try {
			if (yymm != null) {
				s = format.parse(yymm);
			}
		} catch (ParseException e) {
		}
		if (factNo != null) {
			if (!factNo.equals("tw")) {
				String hql = "from Webscrapt where id.yymm =:yymm and id.factNo=:factNo";
				query = getSession().createQuery(hql);
				query.setParameter("yymm", s);
				query.setParameter("factNo", factNo);
			} else {
				String hql = "from Webscrapt where id.yymm =:yymm";
				query = getSession().createQuery(hql);
				query.setParameter("yymm", s);
			}
		}
		List<Webscrapt> list = query.list();
		return list;
	}

	public void delete(WebscraptId id) {
		Webscrapt scrapt = this.findById(id);
		super.delete(scrapt);

	}

	public Webscrapt selBycan(String factNo, Date date, String factCode) {
		String hql = "from Webscrapt where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setDate(2, date);
		Webscrapt mix2 = (Webscrapt) query.uniqueResult();
		return mix2;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from Webscrapt where 1=1");
		hql2.append("select count(id.factNo) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and to_char(id.yymm,'yyyymm')>=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and to_char(id.yymm,'yyyymm')<=:yymm2");
			map.put("yymm2", yymm2);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))&&(yymm2==null||yymm2.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yymm desc");
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
		List<Webscrapt>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public List<Webscrapt> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Webscrapt where 1=1");
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
		hql.append(" order by id.factNo,id.factCode,id.yymm desc");
		List<Webscrapt>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	public Webscrapt findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from Webscrapt where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyymm')=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		Webscrapt scr=(Webscrapt)query.uniqueResult();
		return scr;
	}

}
