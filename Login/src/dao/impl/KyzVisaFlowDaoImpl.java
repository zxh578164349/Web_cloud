package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyzVisaFlowDao;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;

public class KyzVisaFlowDaoImpl extends Basedao implements IKyzVisaFlowDao {

	public void add(KyzVisaflow flow) {
		// TODO Auto-generated method stub
		super.merge(flow);
	}

	public PageBean findFixWithPage(int pageSize, int page, String factNo,
			String visaSort,String trMk,String purmanNo,String visaSigner) {
		// TODO Auto-generated method stub
		int allRow=0;
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from KyzVisaflow where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if(factNo==null||"".equals(factNo)){
			factNo = (String) ActionContext.getContext().getSession().get("factNo");
		}		
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			//hql.append(" and id.visaSort like:visasort ");
			//map.put("visasort", visaSort+"%");
			hql.append(" and visaSortM=:visasort");
			map.put("visasort", visaSort);
			
		}
		if(factNo.equals("nothing")&&(visaSort==null||"".equals(visaSort))&&(trMk==null||"".equals(trMk))&&
				(purmanNo==null||"".equals(purmanNo))&&(visaSigner==null||"".equals(visaSigner))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		
		if(purmanNo!=null&&!"".equals(purmanNo)){
			hql.append(" and id.purmanNo like :purmanNo");
			map.put("purmanNo", purmanNo+"%");			
		}
		if(visaSigner!=null&&!"".equals(visaSigner)){
			hql.append(" and lower(visaSigner)=:visaSigner");
			map.put("visaSigner", visaSigner.toLowerCase());
		}
		if(trMk!=null&&!"".equals(trMk)){
			hql.append(" and trMk=:trMk");
			map.put("trMk",trMk);
		}
		//hql.append(" and flowMk='Y'");
		hql2.append(hql);
		hql.append(" order by id.factNo, id.visaSort,id.itemNo");
		int currentPage = PageBean.countCurrentPage(page);
		
	    Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");	    
	    if(rows!=null&&rows!=0&&page>0){
	     allRow=rows;	     
	    }else{
		 allRow= super.getAllRowCount2(hql2.toString(),map);
		 ActionContext.getContext().getSession().put("allRow", allRow);
		}
	    
	    //allRow= super.getAllRowCount(hql.toString(),map);
		int totalPage = PageBean.countTotalPage(pageSize,allRow);
		if (currentPage > totalPage){
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<KyzVisaflow> list = super.queryForPage(hql.toString(), offset,length, map);
		for(KyzVisaflow flow:list){
			if(flow.getDepId()!=null){
				flow.getDepId().getDepName();
			}
			if(flow.getWebformtype()!=null){
				flow.getWebformtype().getFname();
			}
			flow.getWebtype().getTypeName();			
		}
				
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public KyzVisaflow findById(KyzVisaflowId id) {
		// TODO Auto-generated method stub
		String hql="from KyzVisaflow where id.factNo=? and id.visaSort=? and id.purmanNo=? and id.itemNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getVisaSort());
		query.setString(2, id.getPurmanNo());
		query.setString(3, id.getItemNo());
		return (KyzVisaflow)query.uniqueResult();
	}

	public void delete(KyzVisaflowId id,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		super.delete(this.findById(id),log);	
	}
	public void delete(KyzVisaflowId id) {
		// TODO Auto-generated method stub
		super.delete(this.findById(id));	
	}

	public List<KyzVisaflow> findByFactNo(String factno) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		hql.append("from KyzVisaflow where 1=1 ");
		if(factno!=null&&!factno.equals("")&&!factno.equals("tw")){
			hql.append("and id.factNo='"+factno+"'");
		}
		String[]objs={factno};
		return super.findAll(hql.toString(), objs);
	}

	public List<KyzVisaflow> findByType(String factNo,String visaSort) {
		// TODO Auto-generated method stub
		String hql="from KyzVisaflow where id.factNo=? and id.visaSort like ?  order by id.factNo, id.visaSort,id.itemNo";
		String[]objs={factNo,visaSort+"%"};
		return super.findAll(hql, objs);
	}

	public String findVisaSort_dwr(String factNo, String visaSort, String email) {
		// TODO Auto-generated method stub
		String hql="select id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ? and lower(visaSigner)=? and id.itemNo='01'";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort+"%");
		query.setString(2, email);
		String visasort=(String)query.uniqueResult();
		return visasort;
	}
	
	public String findVisaSort_dwr(String factNo, String visaSort, String email,String trMk) {
		// TODO Auto-generated method stub
		String hql="select id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ? and lower(visaSigner)=? and id.itemNo='01' and trMk=? ";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort+"%");
		query.setString(2, email);
		query.setString(3,trMk);
		String visasort=(String)query.uniqueResult();
		return visasort;
	}
	
	public List<Object[]> findVisaSort_dwr2(String factNo, String visaSort, String email,String trMk) {
		String hql="select depId.depId,depId.depName from KyzVisaflow where id.factNo=? and id.visaSort like ? and lower(visaSigner)=? and id.itemNo='01' and trMk=?";
		String[]objs={factNo,visaSort+"%",email,trMk};
		List<Object[]>list=super.findAll(hql,objs);		
		return list;
	}
	
	
	
	public String findVisaSort_dwr_depidAndfid(String factNo,String visaSort,String email,String trMk,String depId,Integer fid){
		//String hql="select id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ? and lower(visaSigner)=? and id.itemNo='01' and trMk=? and to_char(depId.depId)=? and webformtype.fid=?";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select id.visaSort from KyzVisaflow where 1=1 ");
		if(factNo!=null&&!"".equals(factNo)){
			hql.append("and id.factNo=:factNo ");
			map.put("factNo", factNo);
		}
		if(visaSort!=null&&!"".equals(visaSort)){
			hql.append("and id.visaSort like:visaSort ");
			map.put("visaSort", visaSort+"%");
		}
		if(email!=null&&!"".equals(email)){
			hql.append("and lower(visaSigner)=:email ");
			map.put("email", email);
		}
		if(trMk!=null&&!"".equals(trMk)){
			hql.append(" and trMk=:trMk ");
			map.put("trMk", trMk);
		}
		if(depId!=null&&!"".equals(depId)){
			hql.append(" and to_char(depId.depId)=:depId ");
			map.put("depId", depId);
		}
		if(fid!=null&&fid!=0){
			hql.append(" and webformtype.fid=:fid ");
			map.put("fid", fid);
		}
		hql.append(" and id.itemNo='01' ");		
		String visasort=(String)super.getObj(hql.toString(), map);
		return visasort;
	}
	
	public List<String> findVisaSort_dwr3(String factNo, String visaSort, String email,String trMk) {
		String hql="select id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ? and lower(visaSigner)=? and id.itemNo='01' and trMk=?";
		String[]objs={factNo,visaSort+"%",email,trMk};
		List<String>list=super.findAll(hql,objs);		
		return list;
	}
	
	public List<String> findVisaSort_dwr4(String factNo, String visaSort, String depId,String trMk) {
		String hql="select id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ? and to_char(depId.depId)=? and id.itemNo='01' and trMk=?";
		String[]objs={factNo,visaSort+"%",depId,trMk};
		List<String>list=super.findAll(hql,objs);		
		return list;
	}
	
	public List<String> findVisaSort_dwr_depidAndfidB(String factNo, String visaSort,String trMk, String depId,Integer fid) {
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select id.visaSort from KyzVisaflow where 1=1 ");
		if(factNo!=null&&!"".equals(factNo)){
			hql.append("and id.factNo=:factNo ");
			map.put("factNo", factNo);
		}
		if(visaSort!=null&&!"".equals(visaSort)){
			hql.append("and id.visaSort like:visaSort ");
			map.put("visaSort", visaSort+"%");
		}		
		if(trMk!=null&&!"".equals(trMk)){
			hql.append(" and trMk=:trMk ");
			map.put("trMk", trMk);
		}
		if(depId!=null&&!"".equals(depId)){
			hql.append(" and to_char(depId.depId)=:depId ");
			map.put("depId", depId);
		}
		if(fid!=null&&fid!=0){
			hql.append(" and webformtype.fid=:fid ");
			map.put("fid", fid);
		}
		hql.append(" and id.itemNo='01' ");		
		List<String> list=(List<String>)super.getLists(hql.toString(), map);	
		return list;
	}
	
	
	public List<String> findVisaSort_C(String factNo,String mainSort) {
		// TODO Auto-generated method stub
		//String hql="select distinct id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ?  order by id.visaSort";
		String hql="select distinct id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ? and trMk='Y'  order by length(id.visaSort),id.visaSort";
		String[]objs={factNo,mainSort+"%"};
		return super.findAll(hql, objs);
	}
	public List<KyzVisaflow>findByFactNoVisaSort(String factNo,String visaSort){
		String hql="from KyzVisaflow where id.factNo=? and id.visaSort=? order by id.itemNo";
		String[]objs={factNo,visaSort};
		return super.findAll(hql, objs);
	}
	
	public KyzVisaflow findMaxFlow(String factNo, String visaSort) {
		// TODO Auto-generated method stub
		String hql="from KyzVisaflow where id.itemNo=(select max(id.itemNo) from KyzVisaflow where id.factNo=:factno and id.visaSort=:visasort) and " +
				"id.factNo=:factno and id.visaSort=:visasort";
		Query query=getSession().createQuery(hql);
		query.setString("factno", factNo);
		query.setString("visasort", visaSort);
		KyzVisaflow flow=(KyzVisaflow)query.uniqueResult();
		return flow;
	}

	public void delete2(KyzVisaflow flow) {
		// TODO Auto-generated method stub
		super.delete(flow);
	}

	/**
	 * 根據廠別，類別找出申請人
	 */
	public String findVisaSigner(String factNo, String visaSort) {
		// TODO Auto-generated method stub
		String hql="select visaSigner from KyzVisaflow where id.factNo=? and id.visaSort=? and id.itemNo='01'";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort);
		return (String)query.uniqueResult();
	}

	/**
	 * 找出是否存在出差函文流程（返回的結果>0,則存在）20160203
	 */
	public long findWebbuss(String factNo) {
		// TODO Auto-generated method stub
		long result=0;
		try{
			String hql="select count(id.factNo) from KyzVisaflow where id.factNo=? and id.visaSort='TR'";
			Query query=getSession().createQuery(hql);
			query.setString(0, factNo);
			 result=(Long)query.uniqueResult();
		}catch(Exception e){
			System.out.println(e);
		}		
		return result;
	}

	/**
	 * 日期:2016/11/17
	 * 描述:
	 */
	
	
	public Long findNums(String factNo,String visaSort){
		// TODO Auto-generated method stub
		String hql="select count(id.factNo) from KyzVisaflow where id.factNo=? and id.visaSort=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,factNo);
		query.setString(1,visaSort);
		return (Long)query.uniqueResult();
	}

	/**
	 * 日期:2016/11/18
	 * 描述:
	 */
	
	
	public List<KyzVisaflow> findTR(String factNo){
		// TODO Auto-generated method stub
		String hql="from KyzVisaflow where id.factNo=? and id.visaSort='TR' order by id.itemNo";
		String[]objs={factNo};
		return super.findAll(hql,objs);
	}

	/**
	 * 日期:2016/11/18
	 * 描述:
	 */
	
	
	public List<KyzVisaflow> findPF(String factNo){
		// TODO Auto-generated method stub
		String hql="from KyzVisaflow where id.factNo=? and id.visaSort='PF' order by id.itemNo";
		String[]objs={factNo};
		return super.findAll(hql,objs);
	}

	/**
	 * 日期:2017/5/9
	 * 描述:
	 */
	
	
	public void add_d(KyzVisaflow f1,KyzVisaflow f2){
		// TODO Auto-generated method stub		
		Transaction tx=null;
		try{
			tx=getSession().beginTransaction();
			if(!f1.getId().getPurmanNo().equals(f2.getId().getPurmanNo())){
				getSession().delete(f2);
				getSession().flush();//這一個為了立即執行delete,否則最後就會先是插入（違反唯一原則），後刪除
			}			
			getSession().merge(f1);
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
			
		}			
	}

	public List<KyzVisaflow> findByFnoAndVsortAndTrmk(String factNo,
			String visaSort, String trMk) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append(" from KyzVisaflow where 1=1 ");
		if(factNo==null||"".equals(factNo)){
			factNo = (String) ActionContext.getContext().getSession().get("factNo");
		}		
		if (factNo != null && !factNo.equals("") &&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			//hql.append(" and id.visaSort like:visasort ");
			//map.put("visasort", visaSort+"%");
			hql.append(" and visaSortM=:visasort");
			map.put("visasort", visaSort);
			
		}
		if(factNo.equals("nothing")&&(visaSort==null||"".equals(visaSort))&&(trMk==null||"".equals(trMk))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" order by id.factNo,id.visaSort,id.itemNo");
		List<KyzVisaflow>list=super.getAllWithNoPage(hql.toString(), map);		
		return list;
	}

	public void addMore(List<KyzVisaflow> list) {
		// TODO Auto-generated method stub
		try{
			for(KyzVisaflow obj:list){
				super.merge(obj);
			}
			getSession().flush();
			getSession().clear();
		}catch(Exception e){
			e.printStackTrace();	
			//throw new RuntimeException();  
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//數據回滾
		}
		
	}
}
