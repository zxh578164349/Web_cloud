package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyzVisaFlowDao;
import entity.KyzExpectmatm;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;

public class KyzVisaFlowDaoImpl extends Basedao implements IKyzVisaFlowDao {

	public void add(KyzVisaflow flow) {
		// TODO Auto-generated method stub
		super.merge(flow);
	}

	public PageBean findFixWithPage(int pageSize, int page, String factNo,
			String visaSort) {
		// TODO Auto-generated method stub
		int allRow=0;
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from KyzVisaflow where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			hql.append(" and id.visaSort like:visasort ");
			map.put("visasort", visaSort+"%");
		}
		if(factNo.equals("nothing")&&(visaSort==null||visaSort.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		//hql.append(" and flowMk='Y'");
		hql.append(" order by id.factNo, id.visaSort,id.itemNo");
		int currentPage = PageBean.countCurrentPage(page);
		
	    Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");	    
	    if(rows!=null&&rows!=0&&page>0){
	     allRow=rows;	     
	    }else{
		 allRow= super.getAllRowCount(hql.toString(),map);
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
		String hql="select id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ? and visaSigner=? and id.itemNo='01'";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort+"%");
		query.setString(2, email);
		String visasort=(String)query.uniqueResult();
		return visasort;
	}
	
    /**
     * 此方法用於添加審核流程時，當選擇的函文的各類為"費用簽核1"與"費用簽核2"時使用
     * 因為兩個"費用簽核"中分別都有多個具有排序的子類別
     * 例如"費用簽核1"：C10,C11,C12....... ;"費用簽核2":C20,C21,C22....
     * 所以，根據排序生成一個新的子類別
     */
	public List<String> findVisaSort_C(String factNo,String mainSort) {
		// TODO Auto-generated method stub
		String hql="select distinct id.visaSort from KyzVisaflow where id.factNo=? and id.visaSort like ?  order by id.visaSort";
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


}
