package dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebYieldDataDao;
import entity.WebYieldData;
import entity.WebYieldDataId;
import entity.WebYieldDataLog;

public class WebYieldDataDaoImpl extends Basedao implements IWebYieldDataDao {

	public List<WebYieldData> findDataByFactcode(String factno,
			String factcode, String yymm) {
		// TODO Auto-generated method stub
		String hql = "from WebYieldData w where w.id.factNo=? and w.id.factCode=? and to_char(w.id.yymmdd,'yyyymm')=?";
		String[] objs = { factno, factcode, yymm };
		return super.findAll(hql, objs);
	}

	public void addYdata(WebYieldData data) {
		// TODO Auto-generated method stub
		super.merge(data);
	}

	int size = 0;

	public List<WebYieldData> selectYDate(String factNo, String yymm, int page,
			int rows) {
		// TODO Auto-generated method stub

		/*
		 * SimpleDateFormat format = new SimpleDateFormat("yyyyMM"); Date s =
		 * null; try { if (yymm != null) { s = format.parse(yymm); } } catch
		 * (ParseException e) { }
		 */
		String hql = null;
		Map<String, Object> map = new HashMap<String, Object>();
		hql = "from WebYieldData where 1=1 ";
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql += " and id.factNo =:factno ";
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql += " and to_char(id.yymmdd,'yyyymm')=:yymm ";
			map.put("yymm", yymm);
		}

		Query query = getSession().createQuery(hql);
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		if (size == 0) {
			List<WebYieldData> sList = query.list();
			size = sList.size();
		}
		if (page == 0 && rows == 0) {
			query.setFirstResult((1 - 1) * 10);
			query.setMaxResults(10);
		} else {
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
		}
		List<WebYieldData> sList = query.list();
		size = sList.size();
		return sList;
	}

	public int totlePage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return size;
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

	public PageBean findYdataWithPage(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		int allRow = 0;
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from WebYieldData where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and to_char(id.yymmdd,'yyyymm')=:yymm ");
			map.put("yymm", yymm);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yymmdd desc");
		int currentPage = PageBean.countCurrentPage(page);
		Integer rows = (Integer) ActionContext.getContext().getSession()
				.get("allRow");
		if (rows != null && rows != 0 && page > 0) {
			allRow = rows;
		} else {
			allRow = super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allRow", allRow);
		}
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<WebYieldData> list = super.queryForPage(hql.toString(), offset,
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

	public void delete(WebYieldDataId id) {
		// TODO Auto-generated method stub
		WebYieldData data = this.findById(id);
		super.delete(data);
	}

	public List<WebYieldData> findAByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from WebYieldData where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append("and id.factNo='" + factNo + "'");
		}
		return super.findAll(hql.toString(), null);
	}

	public List<WebYieldData> getAllWithNoPage(String factNo, String yymm) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();

		StringBuffer hql = new StringBuffer();
		hql.append("from WebYieldData where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and to_char(id.yymmdd,'yyyymm')=:yymm ");
			map.put("yymm", yymm);
		}
		return super.getAllWithNoPage(hql.toString(), map);
	}

	public PageBean findAllYDataForMonth(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select id.factNo,id.factCode,to_char(id.yymmdd,'yyyyMM'),sum(onModulus),sum(personnum),sum(standardOutput),sum(actualYield),sum(daycount)," +
				"sum(actualpairs),sum(hostpairs),sum(factpairs),sum(samplepairs),sum(outnum),sum(backnum),sum(workhours) from WebYieldData where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and to_char(id.yymmdd,'yyyyMM')=:yymm ");
			map.put("yymm", yymm);
		}
		hql.append(" group by id.factNo,id.factCode,to_char(id.yymmdd,'yyyyMM') ");
		hql.append(" order by id.factNo,id.factCode,to_char(id.yymmdd,'yyyyMM') desc");

		int currentPage = PageBean.countCurrentPage(page);
		int allRow = super.getAllRowCount(hql.toString(), map);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List list = super.queryForPage(hql.toString(), offset, length, map);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	/**
	 * 存在廠別,但數據為空
	 */
	public List<WebYieldData> findNullYdata(String factno, String factcode,
			String yymm) {
		// TODO Auto-generated method stub
		String hql = "from WebYieldData w where w.id.factNo=? and w.id.factCode=? and to_char(w.id.yymmdd,'yyyymm')=? "
				+ "and onModulus is null and personnum is null and standardOutput is null and actualYield is null";
		String[] objs = { factno, factcode, yymm };
		return super.findAll(hql, objs);
	}

	public List findNullYdata(String yymm) {
		// TODO Auto-generated method stub
		String hql = "select id.factNo,factSname,id.factArea from WebFact where id.factNo||id.factArea not in "
				+ "(select id.factNo||id.factCode from WebYieldData where to_char(id.yymmdd,'yyyymmdd')=?) ";
		String[] objs = { yymm };
		return super.findAll(hql, objs);
	}

	public Object[] totalWithFactCode(String factCode, String yymmdd) {
		// TODO Auto-generated method stub
		String hql = "select sum(standardOutput),sum(actualYield)"
				+ " from WebYieldData where id. factCode=? and to_char(id.yymmdd,'yyyymmdd')=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, factCode);
		query.setString(1, yymmdd);
		return (Object[]) query.uniqueResult();
	}

	public Object[] testireport(String factno, String factcode, String yymm) {
		// TODO Auto-generated method stub
		String hql = "select yp.id.factNo,yp.id.factCode,to_char(yd.id.yymmdd,'yyyymmdd'),yp.forceAnalysis,yp.expectedProduction,yp.expectedPayment,yp.hole "
				+ "yp.positiveNumber,yp.sample,yp.accessories,yp.other "
				+ "yd.onModulus,yd.personnum,yd.standardOutput,yd.actualYield,yd.achievingRate "
				+ "from WebYielePrediction yp,WebYieldData yd "
				+ "where yp.id.factNo=yd.id.factNo and yp.id.factCode=yd.id.factCode and to_char(yp.id.yymmdd,'yymmdd')=to_char(yd.id.yymmdd,'yyyymmdd') "
				+ "and yp.id.factNo=? and yp.id.factCode=? and to_char(yp.id.yymm,'yyyymm')=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, factno);
		query.setString(1, factcode);
		query.setString(2, yymm);
		return (Object[]) query.uniqueResult();
	}

	public void addYdate_log(WebYieldDataLog log) {
		// TODO Auto-generated method stub
		super.merge(log);
	}

	public List<String[]> getFactPrint(String date) {
		String hql = "select factSname,id.factArea from WebFact where id.factNo||id.factArea not in "
				+ "(select id.factNo||id.factCode from WebYieldData  where to_char(id.yymmdd,'yyyy/MM/dd')= ? )";
		String[] objs = { date };
		return super.findAll(hql, objs);
	}
	
	/**
	 * 只顯示要顯示的廠別狀態
	 */
	public List<String[]> getFactPrint_show(String date) {
		String hql = "select factSname,id.factArea from WebFact where id.factNo||id.factArea not in "
				+ "(select id.factNo||id.factCode from WebYieldData  where to_char(id.yymmdd,'yyyy/MM/dd')= ? )"
				+ " and factShow='0'";
		String[] objs = { date };
		return super.findAll(hql, objs);
	}

	public List<WebYieldData> findDataByFactcode2(String factno,
			String factcode, String firstyymmdd, String lastyymmdd) {
		String hql = "from WebYieldData w where w.id.factNo=? and w.id.factCode=? and to_char(w.id.yymmdd,'yyyymmdd')>? and to_char(w.id.yymmdd,'yyyymmdd')<?";
		String[] objs = { factno, factcode, firstyymmdd, lastyymmdd };
		return super.findAll(hql, objs);
	}

	public Object[] getSumWebYieldDate(String factNo, String factCode,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		String hql="select sum(onModulus),sum(personnum),sum(standardOutput),sum(actualYield),sum(daycount)," +
				"sum(actualpairs),sum(hostpairs),sum(factpairs),sum(samplepairs),sum(outnum),sum(backnum),sum(workhours) from WebYieldData"+
				" where id.factNo=? and id.factCode=? and to_char(id.yymmdd,'yyyymmdd') between ? and ?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, startDate);
		query.setString(3, endDate);
		Object[]objs=(Object[])query.uniqueResult();
		return objs;
	}

	public List<WebYieldData> findYdateSdateToEnddate(String factNo,
			String factCode, String startDate, String endDate) {
		// TODO Auto-generated method stub
		String hql="from WebYieldData where id.factNo=? and id.factCode=? and to_char(id.yymmdd,'yyyymmdd') between ? and ?";
		String[]objs={factNo,factCode,startDate,endDate};
		return super.findAll(hql, objs);
	}

	public List<WebYieldData> findYdate(String factNo, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebYieldData where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(startDate!=null&&!startDate.equals("")){
			hql.append(" and to_char(id.yymmdd,'yyyymmdd')>=:startdate");
			map.put("startdate", startDate);
		}
		if(endDate!=null&&!endDate.equals("")){
			hql.append(" and to_char(id.yymmdd,'yyyymmdd')<=:enddate");
			map.put("enddate", endDate);
		}
		hql.append(" order by id.factNo,id.factCode,id.yymmdd desc");		
		return super.getAllWithNoPage(hql.toString(), map);
	}

	public double findNums(String factNo, String factCode) {
		// TODO Auto-generated method stub
		String hql="select count(id.factNo) from WebYieldData where id.factNo=? and id.factCode=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		double result=0;
		try{
			result=(Long)query.uniqueResult();
		}catch(RuntimeException e){
			System.out.println(e);
		}
		
		return result;
	}
	
	

}
