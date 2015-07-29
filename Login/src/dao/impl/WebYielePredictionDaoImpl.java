package dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import util.PageBean;
import dao.Basedao;
import dao.IWebYielePredictionDao;
import entity.*;

public class WebYielePredictionDaoImpl extends Basedao implements
		IWebYielePredictionDao {

	public List<WebYielePrediction> findByFactcode(String factno,
			String factcode, String yymm) {
		// TODO Auto-generated method stub
		String hql = "from WebYielePrediction w where w.id.factNo=? and w.id.factCode=? and to_char(w.id.yymm,'yyyymm')=?";
		String[] objs = { factno, factcode, yymm };
		return super.findAll(hql, objs);
	}

	public void addYPre(WebYielePrediction pre) {
		// TODO Auto-generated method stub
		super.merge(pre);

	}

	int size = 0;

	public List<WebYielePrediction> selectYPre(String factNo, String yymm,
			int page, int rows) {

		// TODO Auto-generated method stub

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
		hql = "from WebYielePrediction where 1=1 ";
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql += " and id.factNo =:factno ";
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql += " and id.yymm =:yymm ";
			map.put("yymm", s);
		}

		Query query = getSession().createQuery(hql);
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		if (size == 0) {
			List<WebYielePrediction> sList = query.list();
			size = sList.size();
		}
		if (page == 0 && rows == 0) {
			query.setFirstResult((1 - 1) * 10);
			query.setMaxResults(10);
		} else {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}
		List<WebYielePrediction> sList = query.list();
		return sList;
	}

	public int totlePage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return size;
	}

	public WebYielePrediction findById(WebYielePredictionId id) {
		// TODO Auto-generated method stub
		String hql = "from WebYielePrediction where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getFactCode());
		query.setDate(2, id.getYymm());
		WebYielePrediction pre = (WebYielePrediction) query.uniqueResult();
		return pre;
	}

	public PageBean findYpreWithPage(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();

		StringBuffer hql = new StringBuffer();
		hql.append("from WebYielePrediction where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and to_char(id.yymm,'yyyymm')=:yymm ");
			map.put("yymm", yymm);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo,id.factCode,id.yymm");
		int currentPage = PageBean.countCurrentPage(page);// �嗅���
		int allRow = super.getAllRowCount(hql.toString(), map);// �餉扇敶
		int totalPage = PageBean.countTotalPage(pageSize, allRow);// �駁△��
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);// 敶�憿萄�憪扇敶�
		final int length = pageSize;// 瘥△霈啣���
		List<WebYielePrediction> list = super.queryForPage(hql.toString(),
				offset, length, map);
		// ��憿萎縑�臭�摮Bean銝�
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public void delete(WebYielePredictionId id) {
		// TODO Auto-generated method stub
		WebYielePrediction pre = this.findById(id);
		super.delete(pre);
	}

	public List<WebYielePrediction> findAByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from WebYielePrediction where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append("and id.factNo='" + factNo + "'");
		}
		return super.findAll(hql.toString(), null);
	}

	public List<WebYielePrediction> getAllWithNoPage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from WebYielePrediction where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and to_char(id.yymm,'yyyymm')=:yymm ");
			map.put("yymm", yymm);
		}
		return super.getAllWithNoPage(hql.toString(), map);
	}

	public List findNullYpre(String yymm) {
		// TODO Auto-generated method stub
		String hql = "select id.factNo,factSname,id.factArea from WebFact where id.factNo||id.factArea not in(select id.factNo||id.factCode from WebYielePrediction  where to_char(id.yymm,'yyyymm')=?)";
		Query query = getSession().createQuery(hql);
		query.setString(0, yymm);
		return query.list();

	}

	public List<WebYielePrediction> findNullYpre(String factno,
			String factcode, String yymm) {
		// TODO Auto-generated method stub
		String hql = "from WebYielePrediction w where w.id.factNo=? and w.id.factCode=? and to_char(w.id.yymm,'yyyymm')=? "
				+ "and forceAnalysis is null and expectedProduction is null and expectedPayment is null and hole is null "
				+ "and positiveNumber is null and sample is null and accessories is null and other is null";
		String[] objs = { factno, factcode, yymm };
		return super.findAll(hql, objs);
	}

}
