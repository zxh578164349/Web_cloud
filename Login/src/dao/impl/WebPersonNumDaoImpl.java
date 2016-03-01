package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;
import dao.Basedao;
import dao.IWebPersonNumDao;
import entity.KyzExpectmatmLog;
import entity.Webbackfeed;
import entity.Webpersonnum;
import entity.WebpersonnumId;

public class WebPersonNumDaoImpl extends Basedao implements IWebPersonNumDao {

	public void add(Webpersonnum person) {
		// TODO Auto-generated method stub
		super.merge(person);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String beginDay,String endDay) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from Webpersonnum where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append("and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append(" and to_char(id.yymmdd,'yyyymm')=:yymm ");
			map.put("yymm", yymm);
		}
		if(beginDay!=null&&!beginDay.equals("")){
			hql.append(" and to_char(id.yymmdd,'yyyymmdd')>=:beginday");
			map.put("beginday", beginDay);
		}
		if(endDay!=null&&!endDay.equals("")){
			hql.append(" and to_char(id.yymmdd,'yyyymmdd')<=:endday");
			map.put("endday", endDay);
		}
		if(factNo.equals("nothing")&&(yymm==null||yymm.equals(""))&&(beginDay==null||beginDay.equals(""))&&(endDay==null||endDay.equals(""))){
			hql.append(" and 1=2 ");
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yymmdd desc");
		int currentPage = PageBean.countCurrentPage(page);
		int allRow = super.getAllRowCount2(hql2.toString(), map);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<Webpersonnum> list = super.queryForPage(hql.toString(), offset,
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

	public List<Webpersonnum> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from Webpersonnum where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append("and id.factNo='" + factNo + "'");
		}
		return super.findAll(hql.toString(), null);
	}

	public Webpersonnum findById(WebpersonnumId id) {
		// TODO Auto-generated method stub
		String hql = "from Webpersonnum where id.factNo=? and id.factCode=? and id.yymmdd=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getFactCode());
		query.setDate(2, id.getYymmdd());
		return (Webpersonnum) query.uniqueResult();
	}

	public void delete(WebpersonnumId id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		Webpersonnum person = this.findById(id);
		super.delete(person,delLog);

	}

	public List<Webpersonnum> findByYymmdd(String yymmdd) {
		// TODO Auto-generated method stub
		String hql="from Webpersonnum where to_char(id.yymmdd,'yyyymmdd')=? order by id.factNo";
		String[]objs={yymmdd};
		return super.findAll(hql, objs);
	}

	public List<Object[]> getTotalByYymmdd(String yymmdd) {
		// TODO Auto-generated method stub
		String hql="select id.factCode,sum(standardnumzg),sum(realnumzg),sum(standardnumjg),sum(realnumjg),sum(outstandardnum)," +
				"sum(outrealnum) from Webpersonnum where to_char(id.yymmdd,'yyyymmdd')=? group by id.factCode";
		Query query=getSession().createQuery(hql);
		query.setString(0, yymmdd);
		List<Object[]> list=query.list();
		return list;
	}

	public Webpersonnum findById2(String factNo, String factCode, String yymmdd) {
		// TODO Auto-generated method stub
		String hql="from Webpersonnum where id.factNo=? and id.factCode=? and to_char(id.yymmdd,'yyyymmdd')=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymmdd);		
		return (Webpersonnum)query.uniqueResult();
	}

	public List<Object[]> findByYnmmddAndFactcode( String yymmdd) {
		// TODO Auto-generated method stub
		String hql="select id.factCode,sum(standardnumzg),sum(realnumzg),sum(standardnumjg),sum(realnumjg),sum(outstandardnum)," +
				"sum(outrealnum) from Webpersonnum where  to_char(id.yymmdd,'yyyymmdd')=? group by id.factCode";
		String[]objs={yymmdd};
		return super.findAll(hql, objs);
	}

	/**
	 * 日期:2016/2/29
	 * 描述:
	 */
	
	
	public List<Webpersonnum> print_search(String factNo, String yymm1,
			String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Webpersonnum where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(yymm1!=null&&!yymm1.equals("")){
			hql.append(" and to_char(id.yymmdd,'yyyymmdd')>=:yymm1 ");
			map.put("yymm1", yymm1);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and to_char(id.yymmdd,'yyyymmdd')<=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		if(factNo.equals("nothing")&&(yymm1==null||yymm1.equals(""))&&(yymm2==null||yymm2.equals(""))){
			hql.append(" and 1=2 ");
		}
		hql.append(" order by id.factNo,id.yymmdd desc ");
		return super.getAllWithNoPage(hql.toString(), map);
	}

}
