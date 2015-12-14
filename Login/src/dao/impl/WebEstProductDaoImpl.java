package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;
import dao.Basedao;
import dao.IWebEstProductDao;
import entity.Webcost;
import entity.Webestproduct;
import entity.WebestproductId;

public class WebEstProductDaoImpl extends Basedao implements IWebEstProductDao {

	public void add(Webestproduct product) {
		// TODO Auto-generated method stub
		super.merge(product);

	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from Webestproduct where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append("and id.factNo=:factno ");
			map.put("factno", factNo);
		}
		if (yymm != null && !yymm.equals("")) {
			hql.append("and to_char(id.yymm,'yyyymm')>=:yymm ");
			map.put("yymm", yymm);
		}
		if (yymm2 != null && !yymm2.equals("")) {
			hql.append("and to_char(id.yymm,'yyyymm')<=:yymm2 ");
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
		List<Webestproduct> list = super.queryForPage(hql.toString(), offset,
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

	public List<Webestproduct> findByFactNo(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("from Webestproduct where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")) {
			hql.append("and id.factNo='" + factNo + "'");
		}
		return super.findAll(hql.toString(), null);
	}

	public Webestproduct findById(WebestproductId id) {
		// TODO Auto-generated method stub
		String hql = "from Webestproduct where id.factNo=? and id.factCode=? and id.yymm=? and id.type=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getFactCode());
		query.setDate(2, id.getYymm());
		query.setString(3, id.getType());
		return (Webestproduct) query.uniqueResult();
	}

	public void delete(WebestproductId id) {
		// TODO Auto-generated method stub
		Webestproduct pro = this.findById(id);
		super.delete(pro);

	}

	public List<Webestproduct> selByFactNoAndYymm(String factNo, String yymm,
			String type) {
		// TODO Auto-generated method stub
		String hql = "from Webestproduct where id.factNo=? and to_char(id.yymm,'yyyyMM')=? and type=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, yymm);
		query.setString(2, type);
		return query.list();
	}

	public Webestproduct findById(String factNo, String factCode, String yymm,
			String type) {
		// TODO Auto-generated method stub
		String hql="from Webestproduct where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyyMM')=? and id.type=? ";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		query.setString(3, type);
		Webestproduct product=(Webestproduct)query.uniqueResult();
		return product;
	}

	public Object[] findSum(String factCode, String yymm,String type) {
		// TODO Auto-generated method stub
		String hql="select sum(hole),sum(machinepower),sum(estdays),sum(esteverymodel),sum(esteverypeople),sum(estmodel),sum(estnum),sum(estpay),sum(estmoney)" +
				" from Webestproduct where id.factCode=? and to_char(id.yymm,'yyyyMM')=? and id.type=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factCode);
		query.setString(1, yymm);
		query.setString(2, type);
		Object[] obj=(Object[])query.uniqueResult();
		return obj;
	}

	public List<Webestproduct> findByFactcode(String factNo, String factCode,
			String yymm) {
		// TODO Auto-generated method stub
		String hql="from Webestproduct where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyymm')=? and id.type='zd'";
		String[]objs={factNo,factCode,yymm};
		return super.findAll(hql, objs);
	}

	public List<Webestproduct> findNullYpre(String factNo, String factCode,
			String yymm) {		
		// TODO Auto-generated method stub
		String hql="from Webestproduct where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyymm')=? and id.type='zd' " +
				" and hole is null and machinepower is null and estdays is null and esteverymodel is null and esteverypeople is null and " +
				" estmodel is null and estnum is null and estpay is null and estmoney is null and totalhole is null and " +
				" sample is null and accessories is null and other is null";
		String[]objs={factNo,factCode,yymm};
		return super.findAll(hql, objs);
	}

	public List<Webestproduct> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from Webestproduct where 1=1");
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
		List<Webestproduct>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	public List<String[]> getFactPrint_show(String date) {
		// TODO Auto-generated method stub
		String hql="select factSname,id.factArea from WebFact where id.factNo||id.factArea not in "
				+ "(select id.factNo||id.factCode from Webestproduct  where id.type='zd' and to_char(id.yymm,'yyyymm')= ? )"
				+ " and factShow='0'";
		String[]objs={date};
		return super.findAll(hql, objs);
	}

	public Object[] reportWebCashout(String factNo,String factCode,String yymm,String type) {
		// TODO Auto-generated method stub
		String hql="select estnum,estpay,estmoney,id.type from Webestproduct where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyymm')=? and id.type=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		query.setString(3, type);
		Object[] obj=(Object[])query.uniqueResult();
		return obj;
	}

	public List<String> findtypeById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="select id.type from Webestproduct where id.factNo=? and id.factCode=? and to_char(id.yymm,'yyyymm')=?";
		String[]objs={factNo,factCode,yymm};
		return super.findAll(hql, objs);
	}


}
