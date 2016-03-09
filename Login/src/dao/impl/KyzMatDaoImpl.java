package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyzMatDao;
import entity.KyzExpectmatmLog;
import entity.KyzMat;

public class KyzMatDaoImpl extends Basedao implements IKyzMatDao{

	public PageBean findPageBean(int pageSize, int page, String fromDate,
			String endDate,String matCname,String bNo,String mNo,String sNo,String factNo,String matNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from KyzMat where 1=1");
		hql2.append("select count(matNo) ");
		/*if((fromDate==null||fromDate.equals(""))&&(endDate==null||endDate.equals(""))
				&&(matCname==null||matCname.equals(""))&&(bNo==null||bNo.equals(""))
				&&(mNo==null||mNo.equals(""))&&(sNo==null||sNo.equals(""))&&(factNo==null||factNo.equals(""))
				&&(matNo==null||matNo.equals(""))){
			hql.append(" and 1=2");
		}else{
			if(fromDate!=null&&!fromDate.equals("")){
				hql.append(" and dateB>=:fromdate");
				map.put("fromdate", fromDate);
			}
			if(endDate!=null&&!endDate.equals("")){
				hql.append(" and date<=:enddate");
				map.put("enddate", endDate);
			}
			if(matCname!=null&&!matCname.equals("")){
				hql.append(" and matCname like:matcname");
				map.put("matcname", "%"+matCname+"%");
			}
			if(bNo!=null&&!bNo.equals("")){
				hql.append(" and typeBno=:bno");
				map.put("bno", bNo);
			}
			if(mNo!=null&&!mNo.equals("")){
				hql.append(" and typeMno=:mno");
				map.put("mno", mNo);
			}
			if(sNo!=null&&!sNo.equals("")){
				hql.append(" and typeSno=:sno");
				map.put("sno", sNo);
			}
			if(factNo!=null&&!factNo.equals("")){
				if(factNo.equals("tw")){
					hql.append( "and 1=1");
				}else{
					hql.append(" and factNo=:factno");
					map.put("factno", factNo);
				}				
			}
			if(matNo!=null&&!matNo.equals("")){
				hql.append(" and matNo like:matno");
				map.put("matno", "%"+matNo+"%");
			}
		}*/
		
		if(factNo!=null&&!factNo.equals("")){
			hql.append(" and factNo=:factno");
			map.put("factno", factNo);
		}
		if(fromDate!=null&&!fromDate.equals("")){
			hql.append(" and dateB>=:fromdate");
			map.put("fromdate", fromDate);
		}
		if(endDate!=null&&!endDate.equals("")){
			hql.append(" and date<=:enddate");
			map.put("enddate", endDate);
		}
		if(matCname!=null&&!matCname.equals("")){
			hql.append(" and matCname like:matcname");
			map.put("matcname", "%"+matCname+"%");
		}
		if(bNo!=null&&!bNo.equals("")){
			hql.append(" and typeBno=:bno");
			map.put("bno", bNo);
		}
		if(mNo!=null&&!mNo.equals("")){
			hql.append(" and typeMno=:mno");
			map.put("mno", mNo);
		}
		if(sNo!=null&&!sNo.equals("")){
			hql.append(" and typeSno=:sno");
			map.put("sno", sNo);
		}		
		if(matNo!=null&&!matNo.equals("")){
			hql.append(" and matNo like:matno");
			map.put("matno", "%"+matNo+"%");
		}
		hql2.append(hql);
		hql.append(" order by typeBno,typeMno,typeSno");
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<KyzMat>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(KyzMat mat:list){
			mat.getSubKyzmats().size();
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public void add(KyzMat mat) {
		// TODO Auto-generated method stub
		super.merge(mat);
		
	}

	public List<String> findAllMatNo() {
		// TODO Auto-generated method stub
		String hql="select matNo from KyzMat";
		return super.findAll(hql, null);
	}

	public KyzMat findById(String matNo) {
		// TODO Auto-generated method stub
		String hql="from KyzMat where matNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, matNo);
		KyzMat mat=(KyzMat)query.uniqueResult();
		return mat;
	}

	public void delete(String matNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		KyzMat mat=this.findById(matNo);
		super.delete(mat,delLog);
		
	}

	public List<KyzMat> findWithNoPage(String fromDate, String endDate,
			String matCname, String bNo, String mNo, String sNo,String factNo,String matNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from KyzMat where 1=1");
		if((fromDate==null||fromDate.equals(""))&&(endDate==null||endDate.equals("")&&(matCname==null||matCname.equals("")))
				&&(bNo==null||bNo.equals(""))&&(mNo==null||mNo.equals(""))&&(sNo==null||sNo.equals(""))&&(factNo==null||factNo.equals(""))
				&&(matNo==null||matNo.equals(""))){
			hql.append(" and 1=2");
		}else{
			if(fromDate!=null&&!fromDate.equals("")){
				hql.append(" and dateB>=:fromdate");
				map.put("fromdate", fromDate);
			}
			if(endDate!=null&&!endDate.equals("")){
				hql.append(" and dateB<=:enddate");
				map.put("enddate", endDate);
			}
			if(matCname!=null&&!matCname.equals("")){
				hql.append(" and matCname like :matcname");
				map.put("matcname","%"+ matCname+"%");
			}
			if(bNo!=null&&!bNo.equals("")){
				hql.append(" and typeBno=:bno");
				map.put("bno", bNo);
			}
			if(mNo!=null&&!mNo.equals("")){
				hql.append(" and typeMno=:mno");
				map.put("mno", mNo);
			}
			if(sNo!=null&&!sNo.equals("")){
				hql.append(" and typeSno=:sno");
				map.put("sno", sNo);
			}
			if(factNo!=null&&!factNo.equals("")){
				if(factNo.equals("tw")){
					hql.append( "and 1=1");
				}else{
					hql.append(" and factNo=:factno");
					map.put("factno", factNo);
				}				
			}
			if(matNo!=null&&!matNo.equals("")){
				hql.append(" and matNo like:matno");
				map.put("matno", "%"+matNo+"%");
			}
			hql.append(" order by typeBno,typeMno,typeSno");
		}
		List<KyzMat>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

}
