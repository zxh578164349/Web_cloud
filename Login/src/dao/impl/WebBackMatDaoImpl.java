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
import dao.IWebBackMatDao;
import entity.WebBackmat;
import entity.WebBackmatId;

public class WebBackMatDaoImpl extends Basedao implements IWebBackMatDao {

	/**
	 * 氝樓隙彶煨妏蚚緙�
	 */
	public void addBackMat(WebBackmat backmat) {
		super.merge(backmat);
	}

	int size = 0;

	public List<WebBackmat> selectBackMat(String factNo, String yymm, int page,
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
		hql = "from WebBackmat where 1=1 ";
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql += " and id.factNo =:factno ";
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql += " and id.yymm =:yymm ";
			map.put("yymm", s);
		}
		if((factNo.equals("nothing"))&&(yymm==null||yymm.equals(""))){
			hql+=" and id.factNo=:factno ";
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
			List<WebBackmat> sList = query.list();
			size = sList.size();
		}
		if (page == 0 && rows == 0) {
			query.setFirstResult((1 - 1) * 10);
			query.setMaxResults(10);
		} else {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}
		List<WebBackmat> sList = query.list();
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
		hql = "from WebBackmat where 1=1 ";
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
			List<WebBackmat> sList = query.list();
			size = sList.size();
		}
		List<WebBackmat> sList = query.list();
		return sList.size();
	}

	/**
	 * 根據Id查找
	 */
	public WebBackmat findBackById(WebBackmatId id) {
		// TODO Auto-generated method stub
		String hql = "from WebBackmat w where w.id.factNo=? and w.id.billNo=? and w.id.yymm=? ";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getBillNo());
		query.setDate(2, id.getYymm());
		WebBackmat backmat = (WebBackmat) query.uniqueResult();
		return backmat;
	}

	public List<WebBackmat> selByYymm(String yymm, String factNo) {
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
				String hql = "from WebBackmat where id.yymm =:yymm and id.factNo=:factNo";
				query = getSession().createQuery(hql);
				query.setParameter("yymm", s);
				query.setParameter("factNo", factNo);
			} else {
				String hql = "from WebBackmat where id.yymm =:yymm";
				query = getSession().createQuery(hql);
				query.setParameter("yymm", s);
			}
		}
		List<WebBackmat> list = query.list();
		return list;
	}

	public void delete(WebBackmatId id) {
		// TODO Auto-generated method stub
		WebBackmat backmat = this.findBackById(id);
		super.delete(backmat);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allRow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from WebBackmat where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and to_char(id.yymm,'yyyymm')=:yymm");
			map.put("yymm", yymm);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo,factCode,id.yymm desc");
		if(rows!=null&&page>0){
			allRow=rows;
		}else{
			allRow=super.getAllRowCount(hql.toString(), map);
			ActionContext.getContext().getSession().put("rows", allRow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebBackmat>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

}
