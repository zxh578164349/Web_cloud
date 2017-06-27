package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebMix1Dao;
import entity.KyzExpectmatmLog;
import entity.WebYieldData;
import entity.WebYieldDataId;


public class WebMix1DaoImpl extends Basedao implements IWebMix1Dao {

	public void add(WebYieldData mix1) {
		// TODO Auto-generated method stub
		super.merge(mix1);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from WebYieldData where 1=1 ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String) ActionContext.getContext().getSession().get("factNo");
		}
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append("and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append("and to_char(id.yymmdd,'yyyymm')=:yymm ");
			map.put("yymm", yymm);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo,id.factCode,id.yymmdd");
		int currentPage = PageBean.countCurrentPage(page);
		int allRow = super.getAllRowCount(hql.toString(), map);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<WebYieldData> list = super.queryForPage(hql.toString(), offset, length,
				map);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public List<WebYieldData> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from WebYieldData where 1=1");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append("and id.factNo='" + factNo + "'");
		}

		return super.findAll(hql.toString(), null);
	}

	public WebYieldData findById(WebYieldDataId id) {
		// TODO Auto-generated method stub
		String hql = "from WebYieldData where id.factNo=? and id.factCode=? and id.yymmdd=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getFactCode());
		query.setDate(2, id.getYymmdd());
		return (WebYieldData) query.uniqueResult();
	}

	public void delete(WebYieldDataId id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebYieldData mix1 = this.findById(id);
		super.delete(mix1,delLog);

	}

}
