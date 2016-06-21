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
import entity.KyzExpectmatmLog;

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

	public void delete(String factNo, String visaSort, String billNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		KyVisabillm billm=this.findById(factNo, visaSort, billNo);
		super.delete(billm,delLog);
		
	}

	public List<KyVisabillm> findByVisaMk(String visaMk) {
		// TODO Auto-generated method stub
		//String hql="from KyVisabillm where visaMk<>? and dateCreate>'20150901' and substr(id.billNo,0,2) in ('CM','EM','BM') and emailMk is null and delMk is null ";
		String hql="from KyVisabillm where visaMk<>? and dateCreate>'20150901'  and emailMk is null and delMk is null ";
		String[]objs={visaMk};
		List<KyVisabillm>list=super.findAll(hql,objs);
		for(KyVisabillm vbm:list){
			if(vbm.getId().getBillNo().substring(0,2).equals("BM")){
				 vbm.getWebbussletter().getUserEmail();//獲取出差函文申請人的Email
			}		   
		}
		return list;
	}
		
	public List<KyVisabillm> findByVisaMk2(String visaMk) {
		// TODO Auto-generated method stub
		//String hql="from KyVisabillm where visaMk=? and dateCreate>'20150901'  and substr(id.billNo,0,2) in ('CM','EM','BM') and emailMk is null and delMk is null  order by dateCreate"; 
		String hql="from KyVisabillm where visaMk=? and dateCreate>'20150901'  and emailMk is null and delMk is null  order by dateCreate"; 
		String[]objs={visaMk};
		List<KyVisabillm>list=super.findAll(hql, objs);//解決hibernate延遲問題
		for(KyVisabillm vbm:list){
			vbm.getKyVisabillses().size();
			if(vbm.getId().getBillNo().substring(0,2).equals("BM")){
				 vbm.getWebbussletter().getUserEmail();//獲取出差函文申請人的Email
			}
		}
		return list;
	}
	public List<KyVisabillm> findAllVbm() {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm";
		return super.findAll(hql, null);
	}

	public KyVisabillm findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		return (KyVisabillm)query.uniqueResult();
	}

	
	/**
	 * 
	 * 日期:2016/2/17
	 * 描述:兩箇月之前沒有添加刪除標記的函文
	 */
	public List<KyVisabillm> findBefor2Month() {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where dateCreate<to_char(add_months(sysdate,-2),'yyyymmdd') and delMk is null order by dateCreate ";
		return super.findAll(hql, null);
	}

	/**
	 * 
	 * 日期:2016/2/17
	 * 描述:大批量添加
	 */
	public void addLarge(List<KyVisabillm> list) {
		// TODO Auto-generated method stub
		try{
			for(int i=0;i<list.size();i++){
				list.get(i).setDelMk("1");
				getSession().merge(list.get(i));
				if(i%10==0){
					getSession().flush();
					getSession().clear();
					
				}
			}
		}catch(Exception e){
			System.out.println("dao********************************"+e+"*************************************dao");
		}
	}

}
