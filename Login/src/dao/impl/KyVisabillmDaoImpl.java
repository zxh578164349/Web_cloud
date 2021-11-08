package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.GlobalMethod;
import util.PageBean;
import dao.Basedao;
import dao.IKyVisaBillmDao;
import entity.KyVisabillm;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmLog;

public class KyVisabillmDaoImpl extends Basedao implements IKyVisaBillmDao{

	private final static String SDATE="202109";
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
	
	public KyVisabillm findById2(String factNo, String visaSort, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where id.factNo=? and id.visaSort=? and id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort);
		query.setString(2, billNo);
		KyVisabillm vbm=(KyVisabillm)query.uniqueResult();
		if(vbm!=null){
			vbm.getKyVisabillses().size();
			vbm.getUserId().getId();
			GlobalMethod.vbmCotentsType(vbm);
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
		String hql="from KyVisabillm where visaMk<>? and dateCreate>'"+SDATE+"'  and emailMk is null and delMk is null order by dateCreate desc";
		String[]objs={visaMk};
		List<KyVisabillm>list=super.findAll(hql,objs);
		try{
			GlobalMethod.vbmCotentsTypes(list);//根據不同的函文類型來更改相應的函文標題，內容	(同時也解決hibernate延遲問題)	
		}catch(Exception e){
			e.printStackTrace();
		}		
		return list;
	}
		
	public List<KyVisabillm> findByVisaMk2(String visaMk) {
		// TODO Auto-generated method stub				
		//String hql="from KyVisabillm where visaMk=? and dateCreate>'20160531'  and emailMk='Y' and delMk is null and id.factNo='GJ' and id.billNo like'BM%'  order by dateCreate"; 
		String hql="from KyVisabillm where visaMk=? and dateCreate>'"+SDATE+"'  and emailMk is null and delMk is null   order by dateCreate";
		String[]objs={visaMk};
		List<KyVisabillm>list=super.findAll(hql, objs);
		try{
			GlobalMethod.vbmCotentsTypes2(list);
		}catch(Exception e){
			e.printStackTrace();
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
		KyVisabillm vbm=(KyVisabillm)query.uniqueResult();
		if(vbm.getId().getBillNo().substring(0,2).equals("BM")){
			vbm.getWebbussletter().getUserEmail();
		}		
		return vbm;
	}
	
	public List<KyVisabillm> findByBillNo2(String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where id.billNo=?";
		String[]objs={billNo};
		List<KyVisabillm>list=super.findAll(hql,objs);
		GlobalMethod.vbmCotentsTypes(list);//根據不同的函文類型來更改相應的函文標題，內容	(同時也解決hibernate延遲問題)	
		return list;		
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
	 * 日期:2016/9/29
	 * 描述:兩箇月之前沒有添加刪除標記,且沒有簽核的函文
	 */
	public List<KyVisabillm> findBefor2Month2() {
		// TODO Auto-generated method stub
		String hql="from KyVisabillm where dateCreate<to_char(add_months(sysdate,-2),'yyyymmdd') and delMk is null and visaMk='N' order by dateCreate ";
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
