package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IKyVisaBillmDao;
import entity.KyVisabillm;
import entity.KyzExpectmatm;

public class KyVisabillmDaoImpl extends Basedao implements IKyVisaBillmDao{

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String billNo,String visaMk) {
		// TODO Auto-generated method stub
		int allRow=0;
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from KyVisabillm where 1=1 ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){			
			hql.append(" and id.billNo=:billno ");
			map.put("billno", billNo);
		}
		if(factNo.equals("nothing")&&(billNo==null||billNo.equals(""))&&(visaMk==null||visaMk.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(visaMk!=null&&!visaMk.equals("")){
			hql.append(" and visaMk=:visaMk");
			map.put("visaMk", visaMk);
		}
		hql.append(" and (id.visaSort='F' or id.visaSort='W' or id.visaSort='G' or id.visaSort='I' or id.visaSort='L' or id.visaSort='P' or id.visaSort='Q'" +
				" or id.visaSort='S' or id.visaSort='T' or id.visaSort='Y' or id.visaSort='Z')");
		hql.append(" order by id.factNo,visaMk,dateCreate desc");
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
		List<KyVisabillm> list = super.queryForPage(hql.toString(), offset,length, map);
				
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public void add(KyVisabillm vbm) {
		// TODO Auto-generated method stub
		super.merge(vbm);
		
	}

	public KyVisabillm findById(String factNo, String visaSort, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where id.factNo=? and id.visaSort=? and id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort);
		query.setString(2, billNo);
		KyVisabillm vbm=(KyVisabillm)query.uniqueResult();
		if(vbm!=null){
			vbm.getKyVisabillses().size();
		}
		return vbm;
	}

	public void delete(String factNo, String visaSort, String billNo) {
		// TODO Auto-generated method stub
		KyVisabillm billm=this.findById(factNo, visaSort, billNo);
		super.delete(billm);
		
	}

	public List<KyVisabillm> findByVisaMk(String visaMk) {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where visaMk<>? and dateCreate>'20150901' and substr(id.billNo,0,2) in ('CM','EM','BM') and emailMk is null and id.factNo='631'";
		String[]objs={visaMk};
		return super.findAll(hql, objs);
	}
	

	public List<KyVisabillm> findAllVbm() {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm";
		return super.findAll(hql, null);
	}

	public List<KyVisabillm> findByVisaMk2(String visaMk) {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where visaMk=? and dateCreate>'20150901'  and substr(id.billNo,0,2) in ('CM','EM','BM') and emailMk is null and id.factNo='631' order by dateCreate"; 
		String[]objs={visaMk};
		return super.findAll(hql, objs);
	}

	public KyVisabillm findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		return (KyVisabillm)query.uniqueResult();
	}

}
