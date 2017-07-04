package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.ISubKyzmatDao;
import entity.KyzMat;
import entity.SubKyzmat;

public class SubKyzmatDaoImpl extends Basedao implements ISubKyzmatDao{

	public void add(SubKyzmat sub) {
		// TODO Auto-generated method stub
		super.merge(sub);
	}

	public List<String> findByMatNo(String matNo) {
		// TODO Auto-generated method stub
		String hql="select id.factNo from SubKyzmat where id.kyzMat.matNo=?";
		String[]objs={matNo};
		return super.findAll(hql, objs);
	}

	public PageBean findPageBean(int pageSize, int page, String fromDate,
			String endDate, String matCname, String bNo, String mNo, String sNo,
			String factNo,String matNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from SubKyzmat where 1=1");
		hql2.append("select count(id.factNo) ");
		if(factNo==null||"".equals(factNo)){
			factNo=(String)ActionContext.getContext().getSession().get("factNo");
		}
		if((fromDate==null||fromDate.equals(""))&&(endDate==null||endDate.equals(""))
				&&(matCname==null||matCname.equals(""))&&(bNo==null||bNo.equals(""))
				&&(mNo==null||mNo.equals(""))&&(sNo==null||sNo.equals(""))&&(factNo==null||factNo.equals(""))
				&&(matNo==null||matNo.equals(""))){
			hql.append(" and 1=2");
		}else{
			if(fromDate!=null&&!fromDate.equals("")){
				hql.append(" and id.kyzMat.dateB>=:fromdate");
				map.put("fromdate", fromDate);
			}
			if(endDate!=null&&!endDate.equals("")){
				hql.append(" and id.kyzMat.dateB<=:enddate");
				map.put("enddate", endDate);
			}
			if(matCname!=null&&!matCname.equals("")){
				hql.append(" and id.kyzMat.matCname like:matcname");
				map.put("matcname", "%"+matCname+"%");
			}
			if(bNo!=null&&!bNo.equals("")){
				hql.append(" and id.kyzMat.typeBno=:bno");
				map.put("bno", bNo);
			}
			if(mNo!=null&&!mNo.equals("")){
				hql.append(" and id.kyzMat.typeMno=:mno");
				map.put("mno", mNo);
			}
			if(sNo!=null&&!sNo.equals("")){
				hql.append(" and id.kyzMat.typeSno=:sno");
				map.put("sno", sNo);
			}
			if(factNo!=null&&!factNo.equals("")&&!"tw".equals(factNo)){				
					hql.append(" and id.factNo=:factno");
					map.put("factno", factNo);								
			}
			if(matNo!=null&&!matNo.equals("")){
				hql.append(" and id.kyzMat.matNo like:matno");
				map.put("matno", "%"+matNo+"%");
			}
		}
		hql2.append(hql);
		hql.append(" order by id.kyzMat.typeBno,id.kyzMat.typeMno,id.kyzMat.typeSno");
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
		List<SubKyzmat>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;		
	}

	public List<SubKyzmat> findWithNoPage(String fromDate, String endDate,
			String matCname, String bNo, String mNo, String sNo, String factNo,String matNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from SubKyzmat where 1=1");
		if((fromDate==null||fromDate.equals(""))&&(endDate==null||endDate.equals("")&&(matCname==null||matCname.equals("")))
				&&(bNo==null||bNo.equals(""))&&(mNo==null||mNo.equals(""))&&(sNo==null||sNo.equals(""))&&(factNo==null||factNo.equals(""))
				&&(matNo==null||matNo.equals(""))){
			hql.append(" and 1=2");
		}else{
			if(fromDate!=null&&!fromDate.equals("")){
				hql.append(" and id.kyzMat.dateB>=:fromdate");
				map.put("fromdate", fromDate);
			}
			if(endDate!=null&&!endDate.equals("")){
				hql.append(" and id.kyzMat.dateB<=:enddate");
				map.put("enddate", endDate);
			}
			if(matCname!=null&&!matCname.equals("")){
				hql.append(" and id.kyzMat.matCname like :matcname");
				map.put("matcname", "%"+matCname+"%");
			}
			if(bNo!=null&&!bNo.equals("")){
				hql.append(" and id.kyzMat.typeBno=:bno");
				map.put("bno", bNo);
			}
			if(mNo!=null&&!mNo.equals("")){
				hql.append(" and id.kyzMat.typeMno=:mno");
				map.put("mno", mNo);
			}
			if(sNo!=null&&!sNo.equals("")){
				hql.append(" and id.kyzMat.typeSno=:sno");
				map.put("sno", sNo);
			}
			if(factNo!=null&&!factNo.equals("")){
				if(factNo.equals("tw")){
					hql.append( "and 1=1");
				}else{
					hql.append(" and id.kyzMat.factNo=:factno");
					map.put("factno", factNo);
				}				
			}
			if(matNo!=null&&!matNo.equals("")){
				hql.append(" and id.kyzMat.matNo like:matno");
				map.put("matno", "%"+matNo+"%");
			}
			hql.append(" order by id.kyzMat.typeBno,id.kyzMat.typeMno,id.kyzMat.typeSno");
		}
		List<SubKyzmat>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	public SubKyzmat findById(String userName,String factNo,String matNo) {
		// TODO Auto-generated method stub
		String hql="from SubKyzmat where id.userName and id.factNo=? and id.kyzMat.matNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, userName);
		query.setString(1, factNo);
		query.setString(2, matNo);
		SubKyzmat sub=(SubKyzmat)query.uniqueResult();
		return sub;
	}

	public void delete(String userName,String factNo,String matNo) {
		// TODO Auto-generated method stub
		this.findById(userName, factNo, matNo);
		
	}

}
