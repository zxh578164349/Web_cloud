package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;
import dao.Basedao;
import dao.ISumWebYieldDataDao;
import entity.KyzExpectmatmLog;
import entity.SumWebYieldData;

public class SumWebYieldDataDaoImpl extends Basedao implements ISumWebYieldDataDao{

	public void add(SumWebYieldData sum_ydate) {
		// TODO Auto-generated method stub
		super.merge(sum_ydate);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String begin_yymm,String end_yymm) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from SumWebYieldData where 1=1");
		hql2.append("select count(id.factNo.factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo.factNo =:factno ");
			map.put("factno", factNo);
		}
		if (begin_yymm != null && !begin_yymm.equals("")) {
			hql.append(" and id.yymm>=:begin_yymm ");
			map.put("begin_yymm", begin_yymm);
		}
		if (end_yymm != null && !end_yymm.equals("")) {
			hql.append(" and id.yymm<=:end_yymm ");
			map.put("end_yymm", end_yymm);
		}		
		if(factNo.equals("nothing")&&(begin_yymm==null||begin_yymm.equals(""))&&(end_yymm==null||end_yymm.equals(""))){
			hql.append(" and id.factNo.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo.factNo,id.factCode,id.yymm desc");
		int currentPage = PageBean.countCurrentPage(page);
		int allRow = super.getAllRowCount2(hql2.toString(), map);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<SumWebYieldData> list = super.queryForPage(hql.toString(), offset, length, map);
		/*for(SumWebYieldData data:list){
			data.getId().getFactNo().getFactSname();
		}*/

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public SumWebYieldData findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from SumWebYieldData where id.factNo.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		SumWebYieldData ydata=(SumWebYieldData)query.uniqueResult();
		return ydata;
	}

	public List<SumWebYieldData> findByFactNo(String factNo,String yymm) {
		// TODO Auto-generated method stub
		String hql="from SumWebYieldData where id.factNo.factNo=? and id.yymm=?";
		String[]objs={factNo,yymm};
		return super.findAll(hql, objs);
	}

	public void delete(SumWebYieldData ydata) {
		// TODO Auto-generated method stub
		super.delete(ydata);
	}

	public List<SumWebYieldData> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from SumWebYieldData where 1=1");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("nothing")&&!factNo.equals("tw")){
			hql.append(" and id.factNo.factNo=:factno");
			map.put("factno", factNo);
		}
		if(beginDate!=null&&!beginDate.equals("")){
			hql.append(" and id.yymm>=:begindate");
			map.put("begindate", beginDate);
		}
		if(endDate!=null&&!endDate.equals("")){
			hql.append(" and id.yymm<=:enddate");
			map.put("enddate", endDate);
		}
		if(factNo.equals("nothing")&&(beginDate==null||beginDate.equals("")&&(endDate==null||endDate.equals("")))){
			hql.append(" and id.factNo.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo.factNo,id.factCode,id.yymm");
		List<SumWebYieldData>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	public List<SumWebYieldData> findAll() {
		// TODO Auto-generated method stub
		String hql="from SumWebYieldData";
		return super.findAll(hql, null);
	}

	public List<SumWebYieldData> findByFactNoAndYymm(String factNo, String begin_yymm,String end_yymm) {
		// TODO Auto-generated method stub		
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from SumWebYieldData where 1=1");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and id.factNo.factNo=:factno");
			map.put("factno", factNo);
		}
		if(begin_yymm!=null&&!begin_yymm.equals("")){
			hql.append(" and id.yymm>=:begin_yymm");
			map.put("begin_yymm", begin_yymm);
		}
		if(end_yymm!=null&&!end_yymm.equals("")){
			hql.append(" and id.yymm<=:end_yymm");
			map.put("end_yymm", end_yymm);
		}
		if(factNo.equals("nothing")&&(begin_yymm==null||begin_yymm.equals(""))&&(end_yymm==null||end_yymm.equals(""))){
			hql.append(" and id.factNo.factNo=:factno");
			map.put("factno", factNo);
		}
		/****************************解決Hibernate延遲加載的問題20160219****************************/
		List<SumWebYieldData>list=super.getAllWithNoPage(hql.toString(), map);
		for(int i=0;i<list.size();i++){
			list.get(i).getId().getFactNo().getFactSname();
		}
		return list;
	}

	public String findUsername(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="select username from SumWebYieldData where id.factNo.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		String username=(String)query.uniqueResult();
		return username;
	}

}
