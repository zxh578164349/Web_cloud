package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;
import dao.Basedao;
import dao.IWebCostDao;
import entity.KyzExpectmatmLog;
import entity.WebYieldData;
import entity.Webcost;
import entity.WebcostId;

public class WebCostDaoImpl extends Basedao implements IWebCostDao {

	public void add(Webcost cost) {
		// TODO Auto-generated method stub
		super.merge(cost);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {

		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from Webcost where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append("and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and to_char(id.yymm,'yyyymm')>=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and to_char(id.yymm,'yyyymm')<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))&&(yymm2==null||yymm2.equals(""))){
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
		List<Webcost> list = super.queryForPage(hql.toString(), offset, length,
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

	public List<Webcost> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from Webcost where 1=1");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append("and id.factNo='" + factNo + "'");
		}

		return super.findAll(hql.toString(), null);
	}

	public Webcost findById(WebcostId id) {
		// TODO Auto-generated method stub
		String hql = "from Webcost where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getFactCode());
		query.setDate(2, id.getYymm());
		return (Webcost) query.uniqueResult();
	}

	public void delete(WebcostId id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		Webcost cost = this.findById(id);
		super.delete(cost,delLog);

	}

	public List<Webcost> findByAny(String factNo, String beginDate,String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Webcost where 1=1");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
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
		return super.getAllWithNoPage(hql.toString(), map);
	}

	public Webcost findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from Webcost where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyymm')=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		Webcost cost=(Webcost)query.uniqueResult();
		return cost;
	}

	public List<Webcost> findByFactNoYm(String factNo, String yymm) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Webcost where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("")){
			hql.append(" and id.factNo=:factno");
		    map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and to_char(id.yymm,'yyyymm')=:yymm");
			map.put("yymm", yymm);
		}
		return super.getAllWithNoPage(hql.toString(), map);
	}

}
